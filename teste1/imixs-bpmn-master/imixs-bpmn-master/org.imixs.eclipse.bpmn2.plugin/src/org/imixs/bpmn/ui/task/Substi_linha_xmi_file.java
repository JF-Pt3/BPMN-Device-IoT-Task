package org.imixs.bpmn.ui.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Substi_linha_xmi_file {
	   public static void main(String args[]) throws IOException {
	      //Instantiating the File class
	      String filePath = "C:/Users/Z1/Desktop/Tese/test_atl/output_2nd.xmi";
	      //Instantiating the Scanner class to read the file
	      Scanner sc = new Scanner(new File(filePath));
	      //instantiating the StringBuffer class
	      StringBuffer buffer = new StringBuffer();
	      //Reading lines of the file and appending them to StringBuffer
	      while (sc.hasNextLine()) {
	         buffer.append(sc.nextLine()+System.lineSeparator());
	      }
	      String fileContents = buffer.toString();
	      System.out.println("Contents of the file: "+fileContents);
	      //closing the Scanner object
	      sc.close();
	      String oldLine = "<xmi:XMI xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns=\"arduino\">";
	      String newLine = "<result>";
	      String oldLine2 = "</xmi:XMI>";
	      String newLine2 = "</result>";
	      //Replacing the old line with new line
	      fileContents = fileContents.replaceAll(oldLine, newLine);	      
	      fileContents = fileContents.replaceAll(oldLine2, newLine2);
	      //instantiating the FileWriter class
	      FileWriter writer = new FileWriter(filePath);
	      System.out.println("");
	      System.out.println("new data: "+fileContents);
	      writer.append(fileContents);
	      writer.flush();
	   }
	}

