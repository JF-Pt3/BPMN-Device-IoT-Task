package org.imixs.bpmn.ui.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;


public class ComposeXSLTRules {
	
	public static String[] function_vector= new String[10];
	
	public static void main(String[] args) {
		

	      try {
	         File inputFile = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/output_2nd.xmi");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         
	         System.out.println("----------------------------");
	         get_XMI_Elements("xmi2xmi",doc);
	         
	        
	            
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	
	@SuppressWarnings("resource")
	public static void get_XMI_Elements(String Attribute,Document doc) throws IOException {
		
		int j=0;
		NodeList nList = doc.getElementsByTagName(Attribute);
		//Vector<String> resultVectorArray[] = (Vector<String>[]) new Vector[nList.getLength()];
		
		String[] array = new String[nList.getLength()];
	    System.out.println("\n->>>>Tamanho_Vector ->"+array.length);
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
	    		//System.out.println("Temp: "+temp+ "Limit "+nList.getLength());        
	        Node nNode = nList.item(temp);	
	        
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element element = (Element)nList.item(temp);
	            NamedNodeMap attributes = element.getAttributes();
	            int numAttrs = attributes.getLength();
	            
	            for(int i=0; i < numAttrs; i++) {
		            Attr attr = (Attr) attributes.item(i);			            
		            String attrName = attr.getNodeName();
		            String attrValue = attr.getNodeValue();			             
		            ///System.out.println("Found attribute: " + attrName + " with value: " + attrValue);		
		            
		            if(attrName.equals("task")) {
		            	
		            	//System.out.println("\n->>>>ola ->"+nList.getLength()+"O temp:"+temp);
		            	array[temp]= attrValue;
		            	
		            	System.out.println("\n->>>>ARRAY: ->"+array[temp]+"O temp:"+temp);
		            	
		            }
		            
	            }
	         }
               
         }
		 int res = 1;
		 
		    // Pick all elements one by one
		    for (int i = 1; i < array.length; i++)
		    {
		        int k = 0;
		        for (k = 0; k < i; k++) {
		        	String first;
		        	String second;		        	
		        	first = array[i];
		        	second = array[k];
		        	if (first.equals(second))
		                break;
		        	
		        }
		            
		 
		        // If not printed earlier,
		        // then print it
		        if (i == k)
		            res++;
		    }
		    System.out.println("foram encontrados "+res+" elementos");
		    String[] array2 = new String[res];
		    
		    /*
	         * convert array to list and then add all
	         * elements to LinkedHashSet. LinkedHashSet
	         * will automatically remove all duplicate elements. 
	         */
	        LinkedHashSet<String> lhSetColors =  
	                new LinkedHashSet<String>(Arrays.asList(array));
	        
	        //create array from the LinkedHashSet
	        String[] newArray = lhSetColors.toArray(new String[ lhSetColors.size() ]);
	        
	        System.out.println("Array after removing duplicates: " 
	                + Arrays.toString(newArray));
	        
	        
	        File source3 = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/result/xslt_original.xslt");
	        File dest3 = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_files/xslt_original.xslt");
	        
	        Path filePath4
            = Paths.get("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_files/xslt_original.xslt");
	        Files.deleteIfExists(filePath4);
	        
	        Files.copy(source3.toPath(), dest3.toPath());	
		    
		    
		    for(int y=0; y < res;y++) {
		    
		    
		    
			    PrintWriter out = null;
			    try {
			        out = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_files/xslt_original.xslt", true)));
			        out.println("<task name=\""+newArray[y]+"\""+">\r\n"
			        		+ "  <xsl:for-each select=\"xmi2xmi\">\r\n"
			        		+ "  \r\n"
			        		+ "	\r\n"
			        		+ "\r\n"
			        		+ "	<xsl:if test=\"@task='"+newArray[y]+"'\">	\r\n"
			        		+ "	\r\n"
			        		+ "	\r\n"
			        		+ "   \r\n"
			        		+ "    <xsl:variable name=\"valor\" select=\"*\"/>\r\n"
			        		+ "      <xsl:variable name=\"attributeName\" select=\"@*\"/> \r\n"
			        		+ "	  \r\n"
			        		+ "	  \r\n"
			        		+ "					\r\n"
			        		+ "								<xsl:element name=\"fields\">\r\n"
			        		+ "									\r\n"
			        		+ "								 <xsl:attribute name=\"name\"> \r\n"
			        		+ "								   <xsl:value-of select=\"name(@*)\" /> \r\n"
			        		+ "								  </xsl:attribute>	\r\n"
			        		+ "								\r\n"
			        		+ "								\r\n"
			        		+ "								  <xsl:attribute name=\"value\"> \r\n"
			        		+ "								   <xsl:value-of select=\"$attributeName\" /> \r\n"
			        		+ "								  </xsl:attribute>\r\n"
			        		+ "								</xsl:element>\r\n"
			        		+ "	\r\n"
			        		+ "		</xsl:if>\r\n"
			        		+ "  </xsl:for-each>\r\n"
			        		+ " </task>");
			    } catch (IOException e) {
			        System.err.println(e);
			    } finally {
			        if (out != null) {
			            out.close();
			        }
			    } 
		    
		   
		    } 
		    
		    
		    PrintWriter out2 = null;
		    try {
		        out2 = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_files/xslt_original.xslt", true)));
		        out2.println(" \r\n"
		        		+ " \r\n"
		        		+ " </org.mda:Package>\r\n"
		        		+ "</xmi:XMI>\r\n"
		        		+ "</xsl:template>\r\n"
		        		+ "</xsl:stylesheet>");
		    } catch (IOException e) {
		        System.err.println(e);
		    } finally {
		        if (out2 != null) {
		            out2.close();
		        }
		    } 
		    
		   
	        
	        
		    
		    
		    
		    
		    /***************MANIPULAÇÃO DE FICHEIROS AQUI*********************************/
		    
		    
		    
		    
		    
		
		
	}

}
