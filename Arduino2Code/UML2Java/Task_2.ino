#include <WiFiNINA.h>
#include <ArduinoHttpClient.h>


char SSID[]="Y";
char SSID_Pass[]="Y";



int Port1=Y;
int  Port2=Y;
const char Server_Address[]="Y";

const int  Button_Green=Y;
const int  Button_Red=Y;
int  LDR_Pin=Y;
const int  LED_Green=Y;
int buttonStateGreen = 0;
int buttonStateRed = 0;
int counter=0;
int first_time=0;
bool ciclo=true;
String recebe="";
WiFiClient wifi;
WiFiClient client2;
HttpClient client = HttpClient(wifi, Server_Address, Port1);
int Value_int=550;
String postData="Hello";
int status = WL_IDLE_STATUS;
int valor=0;
int control=0;

void setup() {
	Serial.begin(9600);
	while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to WPA SSID: ");
    Serial.println(SSID);
    status = WiFi.begin(SSID, SSID_Pass); 
    delay(5000);
  }
  
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());
  IPAddress ip = WiFi.localIP();
  IPAddress gateway = WiFi.gatewayIP();
  Serial.print("IP Address: ");
  Serial.println(ip);  
  
  // initialize the pushbutton pin as an input:
  pinMode(Button_Green, INPUT);
  pinMode(Button_Red, INPUT);  
  pinMode(LDR_Pin,INPUT);
  pinMode(LED_Green, OUTPUT);
  
  Serial.println("Connecting to server...");
  //If we get a connection, tell us
  if(client2.connect(Server_Address, Port2)) {
    Serial.println("Connection Success!");
    client2.println();
  }  
}

void contacta_imixs(int TASK_ID_int,int EVENT_ID_int,int Value_int,String variavel){
		first_time=1;
        Serial.println("making POST request");      

		String value = String(Value_int);
		String TASK_ID = String(TASK_ID_int);
		String EVENT_ID = String(EVENT_ID_int);
      
        postData = "<document xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"><item name=\"$modelversion\"><value xsi:type=\"xs:string\">1.0.0</value></item><item name=\"$taskid\"><value xsi:type=\"xs:int\">"+TASK_ID+"</value></item><item name=\"$eventid\"><value xsi:type=\"xs:int\">"+EVENT_ID+"</value></item><item name=\""+variavel+"\"><value xsi:type=\"xs:int\">"+value+"</value></item></document>";
      

		client.beginRequest();
		client.post("/api/workflow/workitem");    
		client.sendBasicAuth("admin","adminadmin");       
		client.sendHeader("Connection","keep-alive");
		client.sendHeader("Content-Type","application/xml");
		client.sendHeader("Content-Length", postData.length()); 
		client.sendHeader("Accept","application/xml");      
		client.beginBody(); 
		client.print(postData);
		client.endRequest();
	   
		  // read the status code and body of the response
		int statusCode = client.responseStatusCode();
		String response = client.responseBody();

		/*Serial.print("Status code: ");
		Serial.println(statusCode);
		Serial.print("Response: ");
		Serial.println(response);*/
		client.flush();
		client.stop();
	
}
void loop(){

		buttonStateGreen = digitalRead(Button_Green);
        buttonStateRed = digitalRead(Button_Red);
  

	   if ((buttonStateGreen == HIGH)&&(control==0)) {//SE BOTÃO VERDE PRESSIONADO ARRANCA O PROCESSO 
		
			contacta_imixs(1500,10,1,"_trigger");//ENVIA A MENSAGEM "1" PARA O ENGINE(1500 É O ID DA TASK,10 É O ID DO EVENTO, _TRIGGER É A IDENTIFICAÇÃO DA MENSAGEM
			control=1;
			first_time++;
	   }

        if ((buttonStateRed == HIGH)) { // BOTÃO VERMELHO->FAZ STOP AO PROCESSO
   
			contacta_imixs(1800,16,0,"_avalia");
			control=0;
			Serial.print("\n->>>adeus-ate a proxima!");
        }
	   
	   if(control){
    
			delay(500);  
			int contador=0; 

			while(client2.available()){ //VERIFICA SE O ENGINE ESTÁ A ENVIAR INFORMAÇÃO,Port1O 4999     
			  char c = client2.read();
			  recebe+= c;
			}

            Serial.println("->VALOR: \n"+recebe);
	        Serial.print(first_time);			
			valor = recebe.toInt();
			recebe=""; 

			if((valor == 1)||(first_time != 0)){//SE O ENGINE ENVIAR O CARACTER "1"(CICLO DO EVENTO RETROACÇÃO OU SE FOI PRESSIONADO O BOTÃO VERDE,VERIFICA-SE O ESTADO DO LDR
				int ldrStatus = analogRead(LDR_Pin);

                Serial.print("\n->LDR: ");
				Serial.println(ldrStatus);
				
				if(ldrStatus < 800){					
					if(!client2.connected()){
						client2.stop();
						digitalWrite(LED_Green, LOW);
						contacta_imixs(1800,12,1,"_avalia");// SE LUZ ABAIXO DESTE THRESHOLD,VOLTA À TASK ARDUINO_TASK
					}
				}
			    else
				  if(ldrStatus > 800){
					if(!client2.connected()){
						client2.stop();						
						contacta_imixs(1800,13,2,"_avalia");// SE LUZ ACIMA DESTE THRESHOLD,SEGUE PARA A TASK TURN_LED_ON
						//TURN_LED_ON chama o evento led_Green_on.Este evento obriga o engine a enviar o caracter "2" para o Arduino
					}
				  }
			
				valor=0;
                first_time=0;
				client2.connect(Server_Address,4999);     
			
			}
			
			if(valor == 2){
				if(!client2.connected()){
					client2.stop();
					digitalWrite(LED_Green, HIGH);	//Arduino recebe o caracter "2" do engine,obrigando-o a ligar o led verde					
					contacta_imixs(2100,15,2,"_check");// Arduino envia a instrução para voltar ao ciclo de monitorização do estado do ldr
					Serial.print("\n->>>LIGA LED!!!");
						
				}
				valor=0;
				client2.connect(Server_Address,4999);     
			}		
	   }
}