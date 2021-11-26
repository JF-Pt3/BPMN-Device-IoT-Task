package org.imixs.bpmn.ui.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class cmtest {
	///////arduino-cli compile -b arduino:megaavr:uno2018
	
	
	////arduino-cli upload -b arduino:megaavr:uno2018 -p COM3 serial
	
	
	//putty.exe -load "novo"
	public static void main(String[] args) throws IOException {
		
		 
			  
			
		
		// TODO Auto-generated method stub
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"C:\\Users\\Z1\\Desktop\\OutputCode\" && dir && arduino-cli compile -b arduino:megaavr:uno2018");
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	        
	        ProcessBuilder builder2 = new ProcessBuilder(
		            "cmd.exe", "/c", "cd \"C:\\Users\\Z1\\Desktop\\OutputCode\" && dir && arduino-cli upload -b arduino:megaavr:uno2018 -p COM3 serial");
		        builder2.redirectErrorStream(true);
		        Process p2 = builder2.start();
		        BufferedReader r2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
		        String line2;
		        while (true) {
		            line2 = r2.readLine();
		            if (line2 == null) { break; }
		            System.out.println(line2);
		        }
		        //"putty.exe -load \"novo\"
		        //"cd \"C:\\Users\\JF\\Desktop\\CLI\\blink\" && dir && arduino-cli upload -b arduino:megaavr:uno2018 -p COM3 serial"
		        ProcessBuilder builder3 = new ProcessBuilder(
			            "cmd.exe", "/c", "cd \"C:\\Program Files\\PuTTY\" && dir && putty.exe -load \"novo\"");
			        builder3.redirectErrorStream(true);
			        Process p3 = builder3.start();
			        BufferedReader r3 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
			        String line3;
			        while (true) {
			            line3 = r3.readLine();
			            if (line3 == null) { break; }
			            System.out.println(line3);
			        }
	        
	        
	}

}
