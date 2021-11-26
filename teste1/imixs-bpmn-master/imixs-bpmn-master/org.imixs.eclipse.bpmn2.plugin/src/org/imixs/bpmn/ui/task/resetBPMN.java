package org.imixs.bpmn.ui.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class resetBPMN {
	   public static void main(String args[]) throws IOException {
	      
		   
		  
		  Path filePath = Paths.get("C:/Users/Z1/Desktop/Tese/teste1/Runtime_teste/ticket.bpmn");
							        
		  Files.deleteIfExists(filePath);
									
		  File source = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/bpmnOriginal/ticket.bpmn");
		  File dest = new File("C:/Users/Z1/Desktop/Tese/teste1/Runtime_teste/ticket.bpmn");
		
		  Files.copy(source.toPath(), dest.toPath());	
		  
		  
	   }
	}

