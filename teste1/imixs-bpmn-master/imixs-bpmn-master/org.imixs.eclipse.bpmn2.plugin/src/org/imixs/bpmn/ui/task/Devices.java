package org.imixs.bpmn.ui.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;
import org.imixs.bpmn.ImixsBPMNPlugin;
import org.imixs.bpmn.model.Item;
import org.imixs.bpmn.model.Value;
import org.imixs.bpmn.ui.BooleanEditor;
import org.imixs.bpmn.ui.CheckBoxEditor;
import org.imixs.bpmn.ui.ImixsDetailComposite;
import org.imixs.bpmn.ui.ListEditor;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.RunListener;

import Teste.programa;


import org.eclipse.m2m.atl.drivers.emf4atl.AtlEMFModelHandler;
import org.eclipse.m2m.atl.engine.compiler.Atl2004Compiler;
import org.eclipse.m2m.atl.engine.vm.AtlLauncher;
import org.eclipse.m2m.atl.engine.vm.AtlModelHandler;
import org.eclipse.m2m.atl.engine.vm.ModelLoader;
import org.eclipse.m2m.atl.engine.vm.nativelib.ASMModel;

//import java.awt.GridLayout;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.KeyEvent;

import java.nio.channels.FileChannel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;


/******************************************************

Com este programa,são apresentados dois menus

1-Engine_Arduino_Parametrization
 Permite parametrizar um arduino e posteriormente gerar um código de forma a testar a interação entre o Engine e o Arduino de forma a ligar/desligar um led através dos valores lidos pelo LDR.
 
2-General_Arduino_Parametrization
	Permite parametrizar genericamente um Arduino, gerando os portos de input/output e se são digitais/analógicos
	É o mecanismo mais genérico,permitindo o programador adaptar às conveniências do seu projecto
	
Em seguida concretiza-se a geração automática do código de cada um dos menus.
	
******************************************************/

/*Por aqui abaixo, estende-se as novas propriedades da aba "Devices"*/

public class Devices extends AbstractProcessPropertySection {
	
	/*Variáveis globais*/
	String Value;
	int controller = 0;
	String Temp_parameter;
	String BaudRate_parameter;	
	String SSID_parameter ;
	String SSID_Pass_parameter ;
	String Wifi_lib_parameter ;
	String Http_Lib_parameter ;
	String Port1_parameter ;
	String Port2_parameter ;		
	String Server_Address_parameter ;			
	String ButtonGreen_parameter ;
	String ButtonRed_parameter ;
	String LDR_PIN_parameter  ;	
	String LED_GREEN_parameter;	
	boolean wifi_var=false,http_var=false;	
	String Value_selected =null;
	String One = "Device Parametrization";
	String Two = "Engine Interaction";
	int option_selected = 0;	
	int inputAnalog=0;
	int inputDigital=0;
	int outputDigital=0;
	int outputAnalog=0;	
	int inputAnalogTotal=99;
	int outputAnalogTotal=99;	
	int inputDigitalTotal=99;
	int outputDigitalTotal=99;
	int control_cb_3=0;	
	int transformation_selected=0;	
	int delete_control=0;
	int res=1;	
	Vector<Value> vector_total= new Vector<>();	
	Vector<Value> vector_total_code_gen= new Vector<>();	
	int old_option = 0;	
	int last_option_selected=0;	
	int unlock=0;	
	int contador=0;
	String IMU_lib;
	private JPasswordField textField_ssidpass;
	boolean imu_var;
	String Wifi_lib_string;
	String Http_Lib_string;
	String Imu_Lib_string;
	String Imu_Lib_parameter;
	/*Variáveis globais-end*/
	

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new DevicesDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new DevicesDetailComposite(parent, style);
	}

	public class DevicesDetailComposite extends ImixsDetailComposite {

		Composite aclComposite = null;		
		Composite aclComposite2 = null;
		Composite aclComposite3 = null;
		Composite aclComposite4 = null;
		Composite aclComposite5 = null;		
		Composite aclComposite6 = null;
		Composite aclComposite7 = null;
		Composite aclComposite99 = null;
		Composite aclComposite98 = null;
		Composite aclCompositeParametrization = null;
		
public void Upload_Code_to_Arduino_GUI_1() {
	String[] args_34 = null;
	JFrame frame = new JFrame();
	frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
	frame.setTitle("Device_Task_Menu");
	frame.setBounds(100, 100, 641, 220);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	String[] args_3 = null;
	
	JTextArea txtrUploadCodeTo = new JTextArea();
	txtrUploadCodeTo.setForeground(new Color(0, 0, 128));
	txtrUploadCodeTo.setFont(new Font("Dubai", Font.PLAIN, 28));
	txtrUploadCodeTo.setBackground(SystemColor.inactiveCaptionBorder);
	txtrUploadCodeTo.setText("Do you want to upload code to device and run it?");
	txtrUploadCodeTo.setBounds(28, 10, 589, 61);
	frame.getContentPane().add(txtrUploadCodeTo);
	
	JButton btnNewButton = new JButton("Yes");
	btnNewButton.setForeground(new Color(0, 128, 0));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setState(frame.ICONIFIED);
			Arduino2Code_A.main(args_3);			
			try {
				cmtest.main(args_34);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	btnNewButton.setBounds(188, 100, 89, 36);
	frame.getContentPane().add(btnNewButton);
	
	JButton btnNo = new JButton("No");
	btnNo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setState(frame.ICONIFIED);
			Arduino2Code.main(args_3);
			//frame.setVisible(false);
		}
	});
	btnNo.setForeground(new Color(255, 0, 0));
	btnNo.setBounds(384, 100, 89, 36);
	frame.getContentPane().add(btnNo);
	frame.setVisible(true);
	
}

public void Upload_Code_to_Arduino_GUI_2() {
	String[] args_34 = null;
	JFrame frame = new JFrame();
	frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
	frame.setTitle("Device_Task_Menu");
	frame.setBounds(100, 100, 641, 220);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	String[] args_3 = null;
	
	JTextArea txtrUploadCodeTo = new JTextArea();
	txtrUploadCodeTo.setForeground(new Color(0, 0, 128));
	txtrUploadCodeTo.setFont(new Font("Dubai", Font.PLAIN, 28));
	txtrUploadCodeTo.setBackground(SystemColor.inactiveCaptionBorder);
	txtrUploadCodeTo.setText("Do you want to upload code to device and run it?");
	txtrUploadCodeTo.setBounds(28, 10, 589, 61);
	frame.getContentPane().add(txtrUploadCodeTo);
	
	JButton btnNewButton = new JButton("Yes");
	btnNewButton.setForeground(new Color(0, 128, 0));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setState(frame.ICONIFIED);
			Arduino2CodeSecond_A.main(args_3);
			try {
				cmtest.main(args_34);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	btnNewButton.setBounds(188, 100, 89, 36);
	frame.getContentPane().add(btnNewButton);
	
	JButton btnNo = new JButton("No");
	btnNo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setState(frame.ICONIFIED);
			Arduino2CodeSecond.main(args_3);
			//frame.setVisible(false);
		}
	});
	btnNo.setForeground(new Color(255, 0, 0));
	btnNo.setBounds(384, 100, 89, 36);
	frame.getContentPane().add(btnNo);
	frame.setVisible(true);
	
}
		
		
public  void Engine_Arduino_Parametrization()   {
	/*Código responsável por abrir a Janela que contém um formulário para parametrizar o Arduino, com os dados necessários para gerar um código para ligar e desligar o led com o auxílio do Imixs Engine*/
	
	controller=0;
	JFrame frame = new JFrame();
	frame.getContentPane().setBackground(SystemColor.inactiveCaption);
	frame.setBounds(100, 100, 628, 454);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JButton btnNewButton = new JButton("OK");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 27));
	btnNewButton.setBackground(SystemColor.activeCaption);
	btnNewButton.setForeground(new Color(255, 255, 255));
	btnNewButton.setBounds(244, 362, 74, 41);
	frame.getContentPane().add(btnNewButton);
	
	JLabel lblNewLabel_1_1 = new JLabel("Baud_Rate");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
	lblNewLabel_1_1.setBounds(147, 188, 90, 13);
	frame.getContentPane().add(lblNewLabel_1_1);
	
	JComboBox comboBox_4 = new JComboBox();
	comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"115200", "14400", "9600", "1200"}));
	comboBox_4.setBounds(310, 185, 96, 21);
	frame.getContentPane().add(comboBox_4);
	comboBox_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String Baud_Rate_selected = (String)comboBox_4.getSelectedItem();
			System.out.println("Baudarate->>>"+Baud_Rate_selected);
		}
	});
	
	JLabel lblNewLabel_2_2 = new JLabel("SSID");
	lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_2_2.setForeground(new Color(0, 0, 128));
	lblNewLabel_2_2.setBounds(147, 233, 66, 13);
	frame.getContentPane().add(lblNewLabel_2_2);
	
	JTextField textField_ssid = new JTextField();
	textField_ssid.setColumns(10);
	textField_ssid.setBounds(310, 227, 96, 19);
	frame.getContentPane().add(textField_ssid);
	
	JLabel lblNewLabel_2_1_1 = new JLabel("SSID_Password");
	lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_2_1_1.setForeground(new Color(0, 0, 128));
	lblNewLabel_2_1_1.setBounds(147, 256, 109, 13);
	frame.getContentPane().add(lblNewLabel_2_1_1);
	
	JLabel lblNewLabel_5 = new JLabel("Port_1");
	lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_5.setForeground(new Color(0, 0, 128));
	lblNewLabel_5.setBounds(147, 279, 90, 13);
	frame.getContentPane().add(lblNewLabel_5);
	
	JTextField textField_port_1 = new JTextField();
	textField_port_1.setColumns(10);
	textField_port_1.setBounds(310, 283, 96, 19);
	frame.getContentPane().add(textField_port_1);
	
	JLabel lblPort = new JLabel("Port_2");
	lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblPort.setForeground(new Color(0, 0, 128));
	lblPort.setBounds(147, 312, 90, 13);
	frame.getContentPane().add(lblPort);
	
	JTextField textField_port_2 = new JTextField();
	textField_port_2.setColumns(10);
	textField_port_2.setBounds(310, 310, 96, 19);
	frame.getContentPane().add(textField_port_2);
	
	JLabel lblEngineaddress = new JLabel("Engine_Address");
	lblEngineaddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblEngineaddress.setForeground(new Color(0, 0, 128));
	lblEngineaddress.setBounds(147, 339, 109, 13);
	frame.getContentPane().add(lblEngineaddress);
	
	JTextField textField_engineaddr = new JTextField();
	textField_engineaddr.setColumns(10);
	textField_engineaddr.setBounds(310, 337, 136, 19);
	frame.getContentPane().add(textField_engineaddr);
	
	JLabel lblGeneralConfiguration = new JLabel("Configuration");
	lblGeneralConfiguration.setForeground(Color.BLUE);
	lblGeneralConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblGeneralConfiguration.setBounds(147, 78, 136, 22);
	frame.getContentPane().add(lblGeneralConfiguration);
	
	JLabel lblDeviceTask = new JLabel("Device Acquisition Task");
	lblDeviceTask.setForeground(new Color(0, 0, 128));
	lblDeviceTask.setFont(new Font("Tahoma", Font.BOLD, 18));
	lblDeviceTask.setBounds(184, 30, 243, 22);
	frame.getContentPane().add(lblDeviceTask);
	
	JLabel lblNewLabel_3_1 = new JLabel("Desired Libraries:");
	lblNewLabel_3_1.setForeground(new Color(0, 0, 128));
	lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_3_1.setBounds(148, 118, 126, 13);
	frame.getContentPane().add(lblNewLabel_3_1);
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("WifiNina");
	chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
	chckbxNewCheckBox.setBackground(SystemColor.inactiveCaption);
	chckbxNewCheckBox.setBounds(311, 115, 93, 21);
	frame.getContentPane().add(chckbxNewCheckBox);
	
	chckbxNewCheckBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange()==ItemEvent.SELECTED) {
				wifi_var=true;
			}
			else {
				wifi_var=false;
			}
			
		}
	});
	
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("HttpClient");
	chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	chckbxNewCheckBox_1.setBackground(SystemColor.inactiveCaption);
	chckbxNewCheckBox_1.setBounds(310, 151, 93, 21);
	frame.getContentPane().add(chckbxNewCheckBox_1);
	
	JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Hide/Show");
	chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	chckbxNewCheckBox_2.setBackground(SystemColor.inactiveCaption);
	chckbxNewCheckBox_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(chckbxNewCheckBox_2.isSelected()) {
				textField_ssidpass.setEchoChar((char)0);
			}
			else {
				
				textField_ssidpass.setEchoChar('*');
			}
			
		}
	});
	chckbxNewCheckBox_2.setBounds(429, 253, 93, 21);
	frame.getContentPane().add(chckbxNewCheckBox_2);
	
	textField_ssidpass = new JPasswordField();
	textField_ssidpass.setBounds(311, 254, 96, 19);
	frame.getContentPane().add(textField_ssidpass);
	
	JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("IMU_LSM6DS3");
	chckbxNewCheckBox_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	chckbxNewCheckBox_1_1.setBackground(SystemColor.inactiveCaption);
	chckbxNewCheckBox_1_1.setBounds(406, 116, 116, 21);
	frame.getContentPane().add(chckbxNewCheckBox_1_1);
	
	chckbxNewCheckBox_1_1.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange()==ItemEvent.SELECTED) {
				imu_var=true;
			}
			else {
				imu_var=false;
			}
			
		}
	});
	
	chckbxNewCheckBox_1.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange()==ItemEvent.SELECTED) {
				http_var=true;
			}
			else {
				http_var=false;
			}
			
		}
	});
	
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			
		}
	});
	
	//frame.setVisible(true);
	
	
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			
			
			String message = "Hello,... ";
			if(wifi_var==true)
				message+="Selected Wifi ";
			
			if(http_var==true)
				message+="Selected Http ";
			
			
			
			int res = JOptionPane.showOptionDialog(null, "Hello", "Test", JOptionPane.DEFAULT_OPTION,
			        JOptionPane.INFORMATION_MESSAGE, null, null, null);
			
			if(wifi_var) {
				
				 Wifi_lib_string ="WiFiNINA.h";
			}
			else
				 Wifi_lib_string ="";
			
			if(http_var) {
				
				 Http_Lib_string = "ArduinoHttpClient.h";
			}
			else
				Http_Lib_string="";
			
			if(imu_var) {
				
				 Imu_Lib_string = "Arduino_LSM6DS3.h";
			}
			else
				Imu_Lib_string="";
			
			BaudRate_parameter = (String)comboBox_4.getSelectedItem();
			SSID_parameter = textField_ssid.getText().toString();
			String auxi_pass=null;				
			char[] pswd = textField_ssidpass.getPassword();
			SSID_Pass_parameter = new String(pswd);				
			Port1_parameter = textField_port_1.getText().toString();
			Port2_parameter = textField_port_2.getText().toString();				
			Server_Address_parameter = textField_engineaddr.getText().toString();
			Wifi_lib_parameter = Wifi_lib_string;
			Http_Lib_parameter	= Http_Lib_string;
			Imu_Lib_parameter	= Imu_Lib_string;
			/*System.out.println(message);
			System.out.println("\n\n");
			System.out.println("\nBaudRate: "+BaudRate_parameter);
			System.out.println("\nSSID: "+SSID_parameter);
			System.out.println("\nSSID_pass: "+SSID_Pass_parameter);
			System.out.println("\nPort1: "+Port1_parameter);
			System.out.println("\nPort2: "+Port2_parameter);
			System.out.println("\nServerAddress_parameter: "+Server_Address_parameter);				
			System.out.println("\nwifi: "+Wifi_lib_string);
			System.out.println("\nhttp: "+Http_Lib_string);
			System.out.println("\nimu: "+Imu_Lib_string);*/
			
			controller = 1;
			
			
		}
	});
	
	frame.setVisible(true);
						
		}
	

		/*Parametrizar um Arduino Uno rev2 wifi-Inicio*/
		public void General_Arduino_Parametrization() {
			/*GUI para parametrizar um Arduino Uno rev2 wifi.Permite gerar portos de entrada/saída e também definir se são analógicos ou digitais*/
			
			JFrame frame = new JFrame();
			JTextField textField_ssid;
			JTextField textField_ssidpass;
			JTextField textField_port_1;
			JTextField textField_port_2;
			JTextField textField_engineaddr;
			frame.getContentPane().setBackground(SystemColor.inactiveCaption);
			frame.setBounds(100, 100, 770, 524);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Choose Total Number of I/O Ports");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(50, 83, 301, 22);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Analog Input Ports");
			lblNewLabel_1.setForeground(new Color(0, 0, 128));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(50, 116, 179, 22);
			frame.getContentPane().add(lblNewLabel_1);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6"}));
			comboBox.setBounds(223, 118, 79, 21);
			frame.getContentPane().add(comboBox);
			
			JLabel lblNewLabel_2 = new JLabel("Digital Input Ports");
			lblNewLabel_2.setForeground(new Color(0, 0, 128));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(50, 198, 166, 22);
			frame.getContentPane().add(lblNewLabel_2);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}));
			comboBox_1.setBounds(223, 200, 79, 21);
			frame.getContentPane().add(comboBox_1);
			
			JLabel lblNewLabel_3 = new JLabel("Digital Output Ports");
			lblNewLabel_3.setForeground(new Color(0, 0, 128));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(50, 231, 150, 22);
			frame.getContentPane().add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Analog Output Ports");
			lblNewLabel_4.setForeground(new Color(0, 0, 128));
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_4.setBounds(50, 161, 166, 22);
			frame.getContentPane().add(lblNewLabel_4);
			
			JComboBox comboBox_2 = new JComboBox();
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}));
			comboBox_2.setBounds(223, 233, 79, 21);
			frame.getContentPane().add(comboBox_2);
			
			JComboBox comboBox_3 = new JComboBox();
			comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6"}));
			comboBox_3.setBounds(223, 163, 79, 21);
			frame.getContentPane().add(comboBox_3);
			
			JButton btnNewButton = new JButton("OK");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 27));
			btnNewButton.setBackground(new Color(255, 0, 0));
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBounds(310, 406, 136, 58);
			frame.getContentPane().add(btnNewButton);
			
			JLabel lblNewLabel_2_1 = new JLabel("SSID");
			lblNewLabel_2_1.setBounds(265, 167, 66, 13);
			frame.getContentPane().add(lblNewLabel_2_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Baud_Rate");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
			lblNewLabel_1_1.setBounds(383, 204, 66, 13);
			frame.getContentPane().add(lblNewLabel_1_1);
			
			JComboBox comboBox_4 = new JComboBox();
			comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"115200", "14400", "9600", "1200"}));
			comboBox_4.setBounds(522, 200, 96, 21);
			frame.getContentPane().add(comboBox_4);
			comboBox_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String Baud_Rate_selected = (String)comboBox_4.getSelectedItem();
					System.out.println("Baudarate->>>"+Baud_Rate_selected);
				}
			});
			
			JLabel lblNewLabel_2_2 = new JLabel("SSID");
			lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2_2.setForeground(new Color(0, 0, 128));
			lblNewLabel_2_2.setBounds(383, 235, 66, 13);
			frame.getContentPane().add(lblNewLabel_2_2);
			
			textField_ssid = new JTextField();
			textField_ssid.setColumns(10);
			textField_ssid.setBounds(522, 231, 96, 19);
			frame.getContentPane().add(textField_ssid);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("SSID_Password");
			lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2_1_1.setForeground(new Color(0, 0, 128));
			lblNewLabel_2_1_1.setBounds(383, 258, 90, 13);
			frame.getContentPane().add(lblNewLabel_2_1_1);
			
			textField_ssidpass = new JTextField();
			textField_ssidpass.setColumns(10);
			textField_ssidpass.setBounds(522, 254, 96, 19);
			frame.getContentPane().add(textField_ssidpass);
			
			JLabel lblNewLabel_5 = new JLabel("Port_1");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_5.setForeground(new Color(0, 0, 128));
			lblNewLabel_5.setBounds(383, 282, 90, 13);
			frame.getContentPane().add(lblNewLabel_5);
			
			textField_port_1 = new JTextField();
			textField_port_1.setColumns(10);
			textField_port_1.setBounds(522, 278, 96, 19);
			frame.getContentPane().add(textField_port_1);
			
			JLabel lblPort = new JLabel("Port_2");
			lblPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPort.setForeground(new Color(0, 0, 128));
			lblPort.setBounds(383, 306, 90, 13);
			frame.getContentPane().add(lblPort);
			
			textField_port_2 = new JTextField();
			textField_port_2.setColumns(10);
			textField_port_2.setBounds(522, 302, 96, 19);
			frame.getContentPane().add(textField_port_2);
			
			JLabel lblEngineaddress = new JLabel("Engine_Address");
			lblEngineaddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEngineaddress.setForeground(new Color(0, 0, 128));
			lblEngineaddress.setBounds(383, 330, 109, 13);
			frame.getContentPane().add(lblEngineaddress);
			
			textField_engineaddr = new JTextField();
			textField_engineaddr.setColumns(10);
			textField_engineaddr.setBounds(522, 326, 136, 19);
			frame.getContentPane().add(textField_engineaddr);
			
			JLabel lblGeneralConfiguration = new JLabel("General Configuration");
			lblGeneralConfiguration.setForeground(Color.BLUE);
			lblGeneralConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblGeneralConfiguration.setBounds(383, 83, 301, 22);
			frame.getContentPane().add(lblGeneralConfiguration);
			
			JLabel lblDeviceTask = new JLabel("Device Task");
			lblDeviceTask.setForeground(new Color(0, 0, 128));
			lblDeviceTask.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblDeviceTask.setBounds(310, 9, 116, 22);
			frame.getContentPane().add(lblDeviceTask);
			
			JLabel lblNewLabel_3_1 = new JLabel("Desired Libraries:");
			lblNewLabel_3_1.setForeground(new Color(0, 0, 128));
			lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_3_1.setBounds(383, 121, 126, 13);
			frame.getContentPane().add(lblNewLabel_3_1);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("WifiNina");
			chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
			chckbxNewCheckBox.setBackground(SystemColor.inactiveCaption);
			chckbxNewCheckBox.setBounds(525, 118, 93, 21);
			frame.getContentPane().add(chckbxNewCheckBox);
			
			chckbxNewCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange()==ItemEvent.SELECTED) {
						wifi_var=true;
					}
					else {
						wifi_var=false;
					}
					
				}
			});
			
			JCheckBox chckbxNewCheckBox_1 = new JCheckBox("HttpClient");
			chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			chckbxNewCheckBox_1.setBackground(SystemColor.inactiveCaption);
			chckbxNewCheckBox_1.setBounds(525, 163, 93, 21);
			frame.getContentPane().add(chckbxNewCheckBox_1);
			
			chckbxNewCheckBox_1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange()==ItemEvent.SELECTED) {
						http_var=true;
					}
					else {
						http_var=false;
					}
					
				}
			});
			
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i=0;
					int j=0;
					int count_elements=comboBox_3.getItemCount();
					String Value_selected = (String)comboBox.getSelectedItem();
					inputAnalog = Integer.parseInt(Value_selected);
					inputAnalogTotal = inputAnalog;
					System.out.println("Input_Analog->>>"+inputAnalogTotal);
					
					
					
					if(inputAnalogTotal == 0) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6"}));
						
						
					}
					if(inputAnalogTotal == 1) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
						
						
					}
					if(inputAnalogTotal == 2) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
						
						
					}
					
					if(inputAnalogTotal == 3) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
						
						
					}
					
					if(inputAnalogTotal == 4) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
						
						
					}
					
					if(inputAnalogTotal == 5) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
						
						
					}
					
					if(inputAnalogTotal == 6) {
						comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0"}));
						
						
					}
					
				}
			});
			
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int i=0;
					int j=0;
					int count_elements=comboBox_2.getItemCount();
					String Value_selected = (String)comboBox_1.getSelectedItem();
				    inputDigital = Integer.parseInt(Value_selected);
					inputDigitalTotal = inputDigital;
					System.out.println("Input_Digital->>>"+inputDigitalTotal);
					
					
					if(inputDigitalTotal == 0) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}));
						
						
					}
					if(inputDigitalTotal == 1) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}));
						
						
					}
					if(inputDigitalTotal == 2) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
						
						
					}
					
					if(inputDigitalTotal == 3) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
						
						
					}
					
					if(inputDigitalTotal == 4) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
						
						
					}
					
					if(inputDigitalTotal == 5) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
						
						
					}
					
					if(inputDigitalTotal == 6) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8"}));
						
						
					}
					
					if(inputDigitalTotal == 7) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7"}));
						
						
					}
					
					if(inputDigitalTotal == 8) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6"}));
						
						
					}
					
					if(inputDigitalTotal == 9) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
						
						
					}
					
					if(inputDigitalTotal == 10) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
						
						
					}
					
					if(inputDigitalTotal == 11) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
						
						
					}
					
					if(inputDigitalTotal == 12) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
						
						
					}
					
					if(inputDigitalTotal == 12) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
						
						
					}
					
					if(inputDigitalTotal == 13) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
						
					}
					
					if(inputDigitalTotal == 14) {
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0"}));
						
						
					}
				}
			});
			
			comboBox_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					if(inputDigitalTotal == 99){
						JOptionPane.showMessageDialog(null, "Please select Total input digital number of ports first!");
						
						
				
				
			        }
					else {
						
						String Value_selected2 = (String)comboBox_2.getSelectedItem();						
						outputDigitalTotal = Integer.parseInt(Value_selected2);
						outputDigital= outputDigitalTotal;
						System.out.println("OutputDigitalTotal->>>"+outputDigitalTotal);
						
						
					}
				}
			});
			
			comboBox_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(inputAnalogTotal == 99) {
						control_cb_3=0;
						JOptionPane.showMessageDialog(null, "Please select Total input analog number of ports first!");
						
					}
						
					else {			
						String Value_selected = (String)comboBox_3.getSelectedItem();
						outputAnalogTotal = Integer.parseInt(Value_selected);
						outputAnalog = outputAnalogTotal;
						System.out.println("Output_Analog->>>"+outputAnalogTotal);				
					}
				}
			});
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					
				}
			});
			
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					String message = "Hello,... ";
					if(wifi_var==true)
						message+="Selected Wifi ";
					
					if(http_var==true)
						message+="Selected Http ";
					
					
					
					int res = JOptionPane.showOptionDialog(null, "Hello", "Test", JOptionPane.DEFAULT_OPTION,
					        JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					if(wifi_var) {
						
						Wifi_lib_parameter ="WiFiNINA.h";
					}
					else
						Wifi_lib_parameter ="";
					
					if(http_var) {
						
						Http_Lib_parameter = "ArduinoHttpClient.h";
					}
					else
						Http_Lib_parameter="";
					
					BaudRate_parameter = (String)comboBox_4.getSelectedItem();
					SSID_parameter = textField_ssid.getText().toString();
					SSID_Pass_parameter = textField_ssidpass.getText().toString();
					Port1_parameter = textField_port_1.getText().toString();
					Port2_parameter = textField_port_2.getText().toString();				
					Server_Address_parameter = textField_engineaddr.getText().toString();					
					frame.setVisible(false);	
					
					/*Essencialmente esta mecânica toda foi para atribuir valores a 10 variáveis necessárias a parametrizar o dispositivo
					BaudRate_parameter;
					SSID_parameter
					SSID_Pass_parameter
					Port1_parameter
					Port2_parameter
					Server_Address_parameter
					inputAnalogTotal
					inputDigitalTotal
					outputAnalogTotal
					outputDigitalTotal*/
				}
			});
			
			frame.setVisible(true);
			
}/*Parametrizar um Arduino Uno rev2 wifi-Fim*/

		/*Permite selecionar o programa 1 ou 2(ler linha de código número 109)*/
		public void select_program() {
			
			JFrame frame;
			String[] args = null;
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Plese select desired program:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(111, 23, 225, 31);
			frame.getContentPane().add(lblNewLabel);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Engine Interaction", "Device Parametrization"}));
			comboBox.setBounds(111, 91, 217, 21);
			frame.getContentPane().add(comboBox);
			
			JButton btnNewButton = new JButton("OK");
			btnNewButton.setBackground(new Color(51, 153, 51));
			btnNewButton.setForeground(Color.BLACK);
			btnNewButton.setBounds(103, 182, 85, 21);
			frame.getContentPane().add(btnNewButton);
			
			comboBox.setSelectedIndex(0);
			Value_selected = (String)comboBox.getSelectedItem();
			
			JButton btnNewButton_1 = new JButton("Reset BPMN Values");
			btnNewButton_1.setBackground(Color.RED);
			btnNewButton_1.setBounds(255, 181, 149, 23);
			frame.getContentPane().add(btnNewButton_1);
			Value_selected = (String)comboBox.getSelectedItem();
			
			frame.setVisible(true);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					 Value_selected = (String)comboBox.getSelectedItem();
					System.out.println("Selected Option:"+Value_selected);
				}
			});
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(Value_selected.equals(One)) {
						
						if(old_option == 2)
							JOptionPane.showMessageDialog(null, "\nYOU NEED TO REBOOT PLUGIN PLEASE,thank you.");
						else {
							System.out.println("\nYou selected Arduino Ports!");
							General_Arduino_Parametrization();// chama-se  GUI para parametrizar o Programa 2,
							option_selected=1;
							transformation_selected=1;
						}
					}
						
					if(Value_selected.equals(Two)) {
						
						if(old_option == 1)
							JOptionPane.showMessageDialog(null, "\nYOU NEED TO REBOOT PLUGIN PLEASE,thank you.");
						else {							
							Engine_Arduino_Parametrization();//// chama-se  GUI para parametrizar o Programa 1,
							System.out.println("\nYou selected Imixs Engine!");
							option_selected=2;
							transformation_selected=2;
						}						
					}						
					frame.setVisible(false);					
				}
			});
			
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 res = JOptionPane.showOptionDialog(null, "Save this file first,thank you!", "Test", JOptionPane.DEFAULT_OPTION,
					        JOptionPane.INFORMATION_MESSAGE, null, null, null);
					 
					 if(last_option_selected == 1)					
						 delete_control=3;
					 
					 if(last_option_selected == 2)					
						 delete_control=2;
						
					frame.setVisible(false);
					
				}
			});
			
			if(res == 0) {
				
				System.out.println("\nPressionou ok!");
				
				try {
					resetBPMN.main(args);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				res=1;				
				option_selected=99;
				last_option_selected=99;
			}
						
		}

		/*PERMITE ESCOLHER QUAL O DISPOSITIVO A PARAMETRIZAR- NESTE PROGRAMA SÓ SE PARAMETRIZA O ARDUINO*/
		public void initial_menu_devices() {
			
			JFrame frame;			
			frame = new JFrame();
			frame.getContentPane().setBackground(new Color(192, 192, 192));
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Please Choose one Device:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(39, 20, 240, 17);
			frame.getContentPane().add(lblNewLabel);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Arduino Uno Rev2 Wifi");
			rdbtnNewRadioButton.setBackground(new Color(192, 192, 192));
			rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rdbtnNewRadioButton.setBounds(198, 57, 171, 21);
			frame.getContentPane().add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("PIC24F");
			rdbtnNewRadioButton_1.setBackground(new Color(192, 192, 192));
			rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rdbtnNewRadioButton_1.setBounds(198, 95, 103, 21);
			frame.getContentPane().add(rdbtnNewRadioButton_1);
			
			JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("RaspBerry Pi");
			rdbtnNewRadioButton_2.setBackground(new Color(192, 192, 192));
			rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rdbtnNewRadioButton_2.setBounds(198, 143, 158, 21);
			frame.getContentPane().add(rdbtnNewRadioButton_2);
			
			JButton btnNewButton = new JButton("OK");
			btnNewButton.setBounds(169, 200, 85, 21);
			frame.getContentPane().add(btnNewButton);
			frame.setVisible(true);
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					select_program();//chama-se a função para escolher se pretende-se o programa 1 ou 2
					frame.setVisible(false);
					
				}
			});
			
		}
		


		public DevicesDetailComposite(AbstractBpmn2PropertySection section) {
			super(section);
		}

		public DevicesDetailComposite(Composite parent, int style) {
			super(parent, style);
		}

		@Override
		public void resourceSetChanged(ResourceSetChangeEvent event) {
			super.resourceSetChanged(event);
		}

		/*ESTA FUNÇÃO PERMITE GUARDAR VALORES NO FICHEIRO BPMN E PERMITE CONCRETIZAR AS TRANSFORMAÇÕES*/
		@Override
		public void createBindings(final EObject be) {
			if (!ImixsBPMNPlugin.isImixsTask(businessObject)) {
				return;
			}
			
			setTitle("Application Properties");
			aclCompositeParametrization = toolkit.createComposite(attributesComposite);
			aclCompositeParametrization.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));	
			aclCompositeParametrization.setLayout(new GridLayout(6, false));			
			Object Parametrization = toolkit.createButton(aclCompositeParametrization, "PARAMETRIZATION", SWT.BUTTON_MASK);//coloca o botão Parametrization na aba Devices
			
			
			//Coloca-se um listener ao botão
			((Button) Parametrization).addSelectionListener(new SelectionListener() {
				@Override
				
				
				public void widgetSelected(SelectionEvent e) {
					
					boolean check_param_button = ((Button) e.getSource()).getSelection();
					aclCompositeParametrization.setVisible(check_param_button);					
					
					initial_menu_devices();//é chamada a função para escolher o dispositivo
					
				}
				

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// no op
				}
			});
			
			if((option_selected == 1)||(unlock == 1)) {
				/*A opção selecionada foi o programa 2.Após se ter apurado os valores das 10 variáveis:
				BaudRate_parameter;
				SSID_parameter
				SSID_Pass_parameter
				Port1_parameter
				Port2_parameter
				Server_Address_parameter
				inputAnalogTotal
				inputDigitalTotal
				outputAnalogTotal
				outputDigitalTotal
				
				O objectivo passa por guardar estes valores dentro do ficheiro bpmn.
				
				Inicializam-se tantos portos digitais ou analógicos(de saída ou entrada), quantos os que foram indicados por:
				inputAnalogTotal,
				inputDigitalTotal,
				outputAnalogTotal,
				outputDigitalTotal
				NOTA 1
				*/
			
				Value A0 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value A1 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value A2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value A3 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value A4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value A5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");			
				Value A6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D0 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D1 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D3 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D7 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D8 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D9 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D10 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D11 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D12 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value D13 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value baud_rate = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value wifi_lib = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value http_lib = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value ssid = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value ssid_pass = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value port1 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value port2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				Value eng_addr = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo11", null, "");
				TextObjectEditor object_portos = new TextObjectEditor(this, A0, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
		
				
				
				
				A0 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A0_param", null, "");
				object_portos = new TextObjectEditor(this, A0, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				A1 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A1_param", null, "");
				object_portos = new TextObjectEditor(this, A1, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				A2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A2_param", null, "");
				object_portos = new TextObjectEditor(this, A2, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				A3 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A3_param", null, "");
				object_portos = new TextObjectEditor(this, A3, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				A4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A4_param", null, "");
				object_portos = new TextObjectEditor(this, A4, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				A5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A5_param", null, "");
				object_portos = new TextObjectEditor(this, A5, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				A6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "A6_param", null, "");
				object_portos = new TextObjectEditor(this, A6, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				
				/*********************************************/
				
				D0 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D0_param", null, "");
				object_portos = new TextObjectEditor(this, D0, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D1 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D1_param", null, "");
				object_portos = new TextObjectEditor(this, D1, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D2_param", null, "");
				object_portos = new TextObjectEditor(this, D2, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D3 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D3_param", null, "");
				object_portos = new TextObjectEditor(this, D3, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D4_param", null, "");
				object_portos = new TextObjectEditor(this, D4, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D5_param", null, "");
				object_portos = new TextObjectEditor(this, D5, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D6_param", null, "");
				object_portos = new TextObjectEditor(this, D6, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D7 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D7_param", null, "");
				object_portos = new TextObjectEditor(this, D7, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D8 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D8_param", null, "");
				object_portos = new TextObjectEditor(this, D8, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D9 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D9_param", null, "");
				object_portos = new TextObjectEditor(this, D9, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D10 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D10_param", null, "");
				object_portos = new TextObjectEditor(this, D10, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D11 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D11_param", null, "");
				object_portos = new TextObjectEditor(this, D11, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D12 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D12_param", null, "");
				object_portos = new TextObjectEditor(this, D12, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				D13 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "D13_param", null, "");
				object_portos = new TextObjectEditor(this, D13, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
				
				
			if(unlock !=1) {
				System.out.println("Aqui vai->>\n "+inputAnalog);
				System.out.println("\n "+outputAnalog);
				System.out.println("\n "+inputDigital);
				System.out.println("\n "+outputDigital);				
				Vector<Value> vector_analog= new Vector<>();
				Vector<Value> vector_digital= new Vector<>();
				
				
				if(unlock ==0) {
				
				System.out.println("\nINPUTANALOG: "+inputAnalog+"inputDigital->>> "+inputDigital);
				
				String theA0 = A0.getValue().toString();
				String theA1 = A1.getValue().toString();
				String theA2 = A2.getValue().toString();
				String theA3 = A3.getValue().toString();
				String theA4 = A4.getValue().toString();
				String theA5 = A5.getValue().toString();
				
			
				
				vector_analog.add(A0);
				vector_analog.add(A1);
				vector_analog.add(A2);
				vector_analog.add(A3);
				vector_analog.add(A4);
				vector_analog.add(A5);
				vector_digital.add(D0);
				vector_digital.add(D1);
				vector_digital.add(D2);
				vector_digital.add(D3);
				vector_digital.add(D4);
				vector_digital.add(D5);
				vector_digital.add(D6);
				vector_digital.add(D7);
				vector_digital.add(D8);
				vector_digital.add(D9);
				vector_digital.add(D10);
				vector_digital.add(D11);
				vector_digital.add(D12);
				vector_digital.add(D13);
				
				
				/********************************/
				
				vector_total.add(A0);
				vector_total.add(A1);
				vector_total.add(A2);
				vector_total.add(A3);
				vector_total.add(A4);
				vector_total.add(A5);
				vector_total.add(D0);
				vector_total.add(D1);
				vector_total.add(D2);
				vector_total.add(D3);
				vector_total.add(D4);
				vector_total.add(D5);
				vector_total.add(D6);
				vector_total.add(D7);
				vector_total.add(D8);
				vector_total.add(D9);
				vector_total.add(D10);
				vector_total.add(D11);
				vector_total.add(D12);
				vector_total.add(D13);
				
				System.out.println("\n THE INITIAL SIZE: "+vector_total.size());				
				
				/********************************/
				
				int the_limite= vector_analog.size();
				int the_limite2= vector_digital.size();
				
				
				int t=0;
				int z1=0;
				
				/*Instrução setValue permite concretizar a escrita desse valor no ficheiro bpmn:NOTA 1*/
				if(inputAnalog == 1) {					
					A0.setValue("INPUT");
					
				}
				
				if(inputAnalog == 2) {
					
					A0.setValue("INPUT");
					A1.setValue("INPUT");
					
					
					
				}
				
				if(inputAnalog == 3) {
					A0.setValue("INPUT");
					A1.setValue("INPUT");
					A2.setValue("INPUT");
					
					
					
					
				}
				
				if(inputAnalog == 4) {
					A0.setValue("INPUT");
					A1.setValue("INPUT");
					A2.setValue("INPUT");
					A3.setValue("INPUT");
					
					
					
				}
				
				if(inputAnalog == 5) {
					A0.setValue("INPUT");
					A1.setValue("INPUT");
					A2.setValue("INPUT");
					A3.setValue("INPUT");
					A4.setValue("INPUT");
					
					
					
					
				}
				
				if(inputAnalog == 6) {
					A0.setValue("INPUT");
					A1.setValue("INPUT");
					A2.setValue("INPUT");
					A3.setValue("INPUT");
					A4.setValue("INPUT");
					A5.setValue("INPUT");
					
					
					
				}
				
				
				if(inputDigital == 1) {					
					D0.setValue("INPUT");
					
				}
				
				if(inputDigital == 2) {
					
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 3) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					
					
					
					
				}
				
				if(inputDigital == 4) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 5) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					
					
					
					
				}
				
				if(inputDigital == 6) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 7) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 8) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 9) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					D8.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 10) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					D8.setValue("INPUT");
					D9.setValue("INPUT");
					
					
					
				}
				
				if(inputDigital == 11) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					D8.setValue("INPUT");
					D9.setValue("INPUT");
					D10.setValue("INPUT");
					
				}
				
				if(inputDigital == 12) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					D8.setValue("INPUT");
					D9.setValue("INPUT");
					D10.setValue("INPUT");
					D11.setValue("INPUT");
					
				}
				
				if(inputDigital == 13) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					D8.setValue("INPUT");
					D9.setValue("INPUT");
					D10.setValue("INPUT");
					D11.setValue("INPUT");
					D12.setValue("INPUT");
					
				}
				
				if(inputDigital == 14) {
					D0.setValue("INPUT");
					D1.setValue("INPUT");
					D2.setValue("INPUT");
					D3.setValue("INPUT");
					D4.setValue("INPUT");
					D5.setValue("INPUT");
					D6.setValue("INPUT");
					D7.setValue("INPUT");
					D8.setValue("INPUT");
					D9.setValue("INPUT");
					D10.setValue("INPUT");
					D11.setValue("INPUT");
					D12.setValue("INPUT");					
					D13.setValue("INPUT");
					
				}
				
				/**************************************/
				/*Os restantes 8 variáveis são guardadas no ficheiro bpmn aqui:setValue é o que concretiza esta operação*/
				baud_rate = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Baud_rate_param", null, "");
				object_portos = new TextObjectEditor(this, baud_rate, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				baud_rate.setValue(BaudRate_parameter);
				
				
				ssid = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "SSID_param", null, "");
				object_portos = new TextObjectEditor(this, ssid, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				ssid.setValue(SSID_parameter);
				
				ssid_pass = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "SSID_Pass_param", null, "");
				object_portos = new TextObjectEditor(this, ssid_pass, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				ssid_pass.setValue(SSID_Pass_parameter);
				
				
				wifi_lib = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Wifi_lib_param", null, "");
				object_portos = new TextObjectEditor(this, wifi_lib, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				wifi_lib.setValue(Wifi_lib_parameter);
								
				http_lib = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Http_Lib_param", null, "");
				object_portos = new TextObjectEditor(this, http_lib, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				http_lib.setValue(Http_Lib_parameter);				
				
				wifi_var=false;
				http_var=false;
				
				port1 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Port1_param", null, "");
				object_portos = new TextObjectEditor(this, port1, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				port1.setValue(Port1_parameter);
				
				port2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Port2_param", null, "");
				object_portos = new TextObjectEditor(this, port2, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				port2.setValue(Port2_parameter);
				
				eng_addr = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Server_Address_param", null, "");
				object_portos = new TextObjectEditor(this, eng_addr, ImixsBPMNPlugin.IMIXS_ITEMVALUE);				
				eng_addr.setValue(Server_Address_parameter);
				
				int j=0;				
				int limite = vector_analog.size();				
				int contador = inputAnalog;
				int k=0;
				int aux=0;				
				int y=0;				
				
				for(k=1;k <= outputAnalog;k++) {
					
					String s1 = vector_analog.elementAt(contador + aux).toString();
					if(s1.equals(""))
						continue;
					vector_analog.elementAt(contador + aux).setValue("OUTPUT");
					aux++;
					
				}
				for(y=0;y < limite; y++) {
					System.out.println("\n->"+vector_analog.elementAt(y).getValue().toString());
					
				}
				
				int limite2 = vector_digital.size();				
				int contador2 = inputDigital;
				int k1=0;
				int aux1=0;				
				int y1=0;				
				
				for(k1=1;k1 <= outputDigital;k1++) {
					String s1 = vector_digital.elementAt(contador2 + aux1).toString();
					if(s1.equals(""))
						continue;
					else
						vector_digital.elementAt(contador2 + aux1).setValue("OUTPUT");
					aux1++;
					
				}
				
				for(y1=0;y1 < limite2; y1++) {
					System.out.println("\nPinta-> "+y1);
					System.out.println("\n->"+vector_digital.elementAt(y1).getValue().toString()+"O y1: "+y1);
					
				}
		        last_option_selected=option_selected;				
			    }
		}
				
				if(unlock == 1) {
					inputAnalog=0;
					outputAnalog=0;
					inputDigital=0;
					outputDigital=0;
					unlock =0;
					A0=null;
					A1=null;
					A2=null;
					A3=null;
					A4=null;
					A5=null;
				}
				
				inputAnalog=0;
				outputAnalog=0;
				inputDigital=0;
				outputDigital=0;
				
			}
			
			if(delete_control == 3) {
				
				System.out.println("\nDELETE ENTROU CARA!!!!");
				String[] args = null;
				unlock=1;
				old_option = option_selected;
				System.out.println("\n THE OLD OPTION WAS: "+old_option);
				option_selected=99;
				
				try {
					resetBPMN.main(args);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				delete_control=99;
			}
			
			
		if(option_selected == 2) {//Se o programa 1 foi selecionado, então repete-se o processo de gravação das variáveis no ficheiro bpmn analogamente ao que foi feito atrás para o programa 2
			
			/*Neste caso são guardadas 12 variáveis*/
			/*  
			 *  BaudRate_parameter;
				SSID_parameter
				SSID_Pass_parameter
				Port1_parameter
				Port2_parameter
				Server_Address_parameter				
				Wifi_lib_param
				Http_Lib_param
				Imu_Lib_parameter
			*/

			setTitle("DEVICES");

			// Create checkbox to hide acl elements...
			Value valueUpdateACL = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			Value valueUpdateACL2 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			
			Value valueUpdateACL3 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			
			Value valueUpdateACL4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			
			Value valueUpdateACL5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			
			Value valueUpdateACL6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			
			Value valueUpdateACL7 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");
			
			Value valueUpdateACL8 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "keyUpdateACL", "xs:boolean",
					"false");

		if(controller == 1) {
	
			aclComposite99 = toolkit.createComposite(attributesComposite);
			
			aclComposite7 = toolkit.createComposite(attributesComposite);
			
			aclComposite7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

			aclComposite7.setLayout(new GridLayout(6, false));
			
			Section section5 = createSection(aclComposite7, "Parameters", false);
			aclComposite7.setLayout(new FillLayout(SWT.VERTICAL));
			
			Value itemValue4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue7 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue8 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue9 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");			
			Value itemValue10 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue11 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
			Value itemValue12 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "txteditorid_novo1", null, "");
					
			
			
			TextObjectEditor valueEditor4 = new TextObjectEditor(this, itemValue4, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
		
			itemValue4 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Baud_rate_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue4, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Data Type:");
			itemValue4.setValue(BaudRate_parameter);
			
			
			itemValue5 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "SSID_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue5, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Baud Rate:");
			itemValue5.setValue(SSID_parameter);
			
			itemValue6 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "SSID_Pass_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue6, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Timestamp:");
			itemValue6.setValue(SSID_Pass_parameter);
			
			
			itemValue7 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Wifi_lib_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue7, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Temperature:");
			itemValue7.setValue(Wifi_lib_parameter);
			
			
			
			itemValue8 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Http_Lib_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue8, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Temperature:");
			itemValue8.setValue(Http_Lib_parameter);
			
			
			wifi_var=false;
			http_var=false;
			imu_var=false;
			
			itemValue9 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Port1_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue9, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Temperature:");
			itemValue9.setValue(Port1_parameter);
			
			
			itemValue10 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Port2_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue9, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Temperature:");
			itemValue10.setValue(Port2_parameter);
			
			
			itemValue11 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Server_Address_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue9, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Temperature:");
			itemValue11.setValue(Server_Address_parameter);
			
			
			itemValue12 = ImixsBPMNPlugin.getItemValueByName((BaseElement) be, "Imu_Lib_param", null, "");
			valueEditor4 = new TextObjectEditor(this, itemValue9, ImixsBPMNPlugin.IMIXS_ITEMVALUE);
			//valueEditor4.createControl(aclComposite7, "Temperature:");
			itemValue12.setValue(Imu_Lib_parameter);
			
			
			
			
			vector_total_code_gen.add(itemValue4);
			vector_total_code_gen.add(itemValue5);
			vector_total_code_gen.add(itemValue6);
			vector_total_code_gen.add(itemValue7);
			vector_total_code_gen.add(itemValue8);
			vector_total_code_gen.add(itemValue9);
			vector_total_code_gen.add(itemValue10);
			vector_total_code_gen.add(itemValue11);
			vector_total_code_gen.add(itemValue12);
			
	
			
			
			System.out.println("\nAqui ABAIXO:!!!!!");
			System.out.println("\n->BaudRate_parameter: "+BaudRate_parameter);
			System.out.println("\n->SSID_parameter: "+SSID_parameter);
			System.out.println("\n->SSID_Pass_parameter: "+SSID_Pass_parameter);
			System.out.println("\n->Wifi_lib_parameter: "+Wifi_lib_parameter);			
			System.out.println("\n->Http_Lib_parameter: "+Http_Lib_parameter);
			System.out.println("\n->Port1_parameter: "+Port1_parameter);
			System.out.println("\n->Port2_parameter: "+Port2_parameter);
			System.out.println("\n->Server_Address_parameter: "+Server_Address_parameter);
			System.out.println("\n->ButtonGreen_parameter: "+ButtonGreen_parameter);
			System.out.println("\n->ButtonRed_parameter: "+ButtonRed_parameter);
			System.out.println("\n->LED_GREEN_parameter: "+LED_GREEN_parameter);
			System.out.println("\n->LDR_PIN_parameter: "+LDR_PIN_parameter);
			
			controller = 0;
			
		}
		
		    last_option_selected=option_selected;
			
			//option_selected=99;
			
	}
		
		if(delete_control == 2) {
			
			
			String[] args = null;			
			old_option = option_selected;
			option_selected=99;
			
			
			try {
				resetBPMN.main(args);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			delete_control=99;
		}
		
		/*Após se ter gravado os valores no ficheiro bpmn, de aqui a baixo, está a implementação da Transformação desses valores em código de Dispositivo*/
		
			setTitle("Application Properties");
			aclComposite98 = toolkit.createComposite(attributesComposite);
			aclComposite98.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));	
			aclComposite98.setLayout(new GridLayout(6, false));			
			Object addMultiLoopButton46 = toolkit.createButton(aclComposite98, "TRANSFORMATION", SWT.BUTTON_MASK);//botão de transformação
			
			
			((Button) addMultiLoopButton46).addSelectionListener(new SelectionListener() {//Se o botão de transformação foi pressionado, então desencadeia-se todos os procedimentos para fazer a transformação.
				@Override
				
				
				public void widgetSelected(SelectionEvent e) {
					
					boolean checked9 = ((Button) e.getSource()).getSelection();
					aclComposite98.setVisible(checked9);					
					String message = "thrtrhr";	
					FileWriter myWriter = null;
					String[] args = null;
					
							 
							try {
								/************************1st Transformation Preparation***********************************/
									/* O ficheiro BPMN, neste momento possui todas as características que já possuía e adicionalmente possuí todos os parâmetros da Device_Task*/
									/*O FICHEIRO BPMN está em formato xml, temos de transformar o formato xml em xmi.De preferência vamos transformar para um ficheiro xmi que contém unicamente os elementos da Device Task*/
									
									/*Eliminam-se todos os ficheiros que são usados nesta transformação xml para xmi*/
									Path filePath1
						            = Paths.get("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/res/ticket.bpmn");
							        
							        Files.deleteIfExists(filePath1);
							        
							        Path filePath2
						            = Paths.get("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/res/ticket.bpmn.bpmn2xmi");
							        
							        Files.deleteIfExists(filePath2);
							        
									/*Copia-se o ficheiro bpmn(em formato xml) para a pasta de destino: "C:/Users/JF/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/res/"*/
									File source = new File("C:/Users/Z1/Desktop/Tese/teste1/Runtime_teste/ticket.bpmn");
							        File dest = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/res/ticket.bpmn");							        
							        Files.copy(source.toPath(), dest.toPath());	
							    /*******************************1st Transformation Preparation-end********************/
							        
							    /************************1st Transformation***********************************/
							        /*1ª transformação de bpmn(em xml) para um ficheiro xmi que contêm unicamente os valores da Device Task*/
							        /*Este código chama o programa Transfor_BPMN2_TO_XMI que irá proceder à transformação:bpmn(em xml) para um ficheiro xmi que só tem os valores da Device Task,isto é, os parâmetros do programa 1 ou programa 2. */
							        /*Entrar no programa Transfor_BPMN2_TO_XMI*/
							        JUnitCore junit = new JUnitCore();
							        junit.addListener(new RunListener());
							        junit.run(Transfor_BPMN2_TO_XMI.class);
							    /************************1st Transformation-end***********************************/
									
									
							    /************************2nd Transformation Preparation**************************************/
									
							        /*Copia-se o ficheiro resultante da 1ª transformação(ficheiro resultante é o ficheiro xmi), para a pasta que está em :
							         * "C:/Users/JF/Desktop/Tese/test_atl/ticket.bpmn.bpmn2xmi"
							         */
									
										Path filePath3
								            = Paths.get("C:/Users/Z1/Desktop/Tese/test_atl/ticket.bpmn.bpmn2xmi");
								        
								        Files.deleteIfExists(filePath3);
										
										File source2 = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/res/ticket.bpmn.bpmn2xmi");
								        File dest2 = new File("C:/Users/Z1/Desktop/Tese/test_atl/ticket.bpmn.bpmn2xmi");
								        
								        Files.copy(source2.toPath(), dest2.toPath());	
								        
								 /************************2nd Transformation Preparation-end**************************************/
								 
								 /************************2nd Transformation***********************************/
									/*2nd transformação.O ficheiro xmi resultante da primeira transformação, tem os seus elementos descritos de acordo com o meta modelo Ecore BPMN20(C:\Users\JF\Desktop\Tese\test_atl\BPMN20.ecore)
									 * Pretende-se pegar nesses elementos do ficheiro xmi resultante da 1ª transformação e através de uma transformação ATL,
									 * Vamos transformar noutros elementos num ficheiro denominado "output_2nd.xmi", o qual terá os seus elementos de acordo com o meta modelo do Arduino
									 * Esta 2ª transformação concretiza o descrito anteriormente.									 * 
									 * */	
									        Second_Transformation.main(args);
									        
							     /************************2nd Transformation-end***********************************/
									        
							     /************************3rd Transformation-Preparation***********************************/
									        
									     
									       /*Antes de proceder à última transformação de um modelo xmi para texto (M2T),necessita-se de fazer 1 alteração ao ficheiro xmi que resulta da 2ª transformação.E 2 alterações a um ficheiro das regras xslt que permite fazer a 3ª transformação.
									        * Porque o código que permite fazer a transformação M2T, necessita que o ficheiro xmi da 2ª transformação esteja num formato muito próprio.
									        * Temos assim:
									        * 	-Alteração ao xmi->altera o cabeçalho do ficheiro xmi que resulta da 2ª transformação;
									        * 	-Altera ficheiro XSLT-> permite compor/adicionar  regras a um ficheiro xslt,permitindo assim que este ficheiro de transformação xslt, consiga verificar quantas Tarefas dentro do diagrama BPMN foram parametrizadas.Permitindo gerar depois na última transformação,tantos códigos quantas as Tasks que foram parametrizadas. 
									        * 	-Transformação xslt ultima-> permite de forma programática fazer uma transformação xslt(ATRAVÉS DAS REGRAS DO FICHEIRO XSLT ALTERADO,IMEDIATAMENTE ATRAÉS), que pega nos elementos que estão definidos no interior do ficheiro output_2nd.xmi e redimensiona todo o seu formato para o formato necessário para fazer a 3ª transformação M2T que gera o código executável. 		        
									        */ 
									        /****Alteração ao xmi 1*******/
									        Substi_linha_xmi_file.main(args);
									        /****Alteração ao xmi 1-end*******/
									        
									        
									        File source3 = new File("C:/Users/Z1/Desktop/Tese/test_atl/output_2nd.xmi");
									        File dest3 = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/output_2nd.xmi");
									        
									        
									        /**Apaga-se este ficheiro*/
									        Path filePath4
								            = Paths.get("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/output_2nd.xmi");
									        Files.deleteIfExists(filePath4);
									        /***************************/
									        
									        /* Copia o ficheiro xmi que saíu da função "Substi_linha_xmi_file.main(args);" para uma nova localização*/									        
									        Files.copy(source3.toPath(), dest3.toPath());										        
									        /***************************************/
									        
									        /****Altera ficheiro XSLT*******/	
									        	ComposeXSLTRules.main(args);
									        /********************************/
									        	
									        /****Transformação xslt ultima*******/	
									        XSLT_last_transform.main(args);
									        /********************************/
									        
									        /*Copia o ficheiro resultante da XSLT_last_transform, trocando de sítio */
									        File source4 = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/saida.xmi");
									        File dest4 = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/saida.xmi");
									        
									        Path filePath5
								            = Paths.get("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/saida.xmi");
									        Files.deleteIfExists(filePath5);
									        
									        Files.copy(source4.toPath(), dest4.toPath());
								  /************************3rd Transformation-Preparation-END***********************************/
									        
								  /************************3rd Transformation***********************************/
									/*Transformações M2T,
									 * Permitem transformar o ficheiro saida.xmi que é um modelo que contém todos os elementos da Device Task para código executável de dispositivo
									 * São executados programáticamente Queries ATL através de Arduino2Code e Arduino2CodeSecond.
									 * Assim através de Queries ATL definidas em Arduino2Code.atl e Arduino2CodeSecond.atl,respectivamente, é possível através do meta modelo Ecore do Arduino, gerar o código de dispositivo,isto é, o código .ino
									 
									 * */        
									        
									        
									        
									        String[] args_3 = null;
											if(transformation_selected == 2) {//GERA O CÓDIGO do programa 1
									        	
												Upload_Code_to_Arduino_GUI_1();
									        	//Arduino2Code.main(args_3);
									        	//JOptionPane.showMessageDialog(null,"Output Code was generated in Desktop folder,thank you!");
									        	
									        }
									        
									        if(transformation_selected == 1) {//GERA O CÓDIGO do programa 2
									        	
									        	Upload_Code_to_Arduino_GUI_2();
									        	//Arduino2CodeSecond.main(args_3);
									        	//JOptionPane.showMessageDialog(null,"Output Code was generated in Desktop folder,thank you!");
									        	
									        	
									        }
							      /************************3rd Transformation-end***********************************/        
									
								
							} catch (IOException   e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						      try {
						    	 
								myWriter.write("Message:"+message);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						      try {
								myWriter.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					
				}
				
				

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// no op
				}
			});
			
			
		
		
		}

	}

}