package org.imixs.bpmn.ui.task;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.m2m.atl.drivers.emf4atl.AtlEMFModelHandler; 
import org.eclipse.m2m.atl.engine.vm.AtlLauncher;
import org.eclipse.m2m.atl.engine.vm.AtlModelHandler;
import org.eclipse.m2m.atl.engine.vm.ModelLoader;
import org.eclipse.m2m.atl.engine.vm.nativelib.ASMModel;

import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;


import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/*2ª Transformação
 * 
 * 
 * 
 * Este código permite de forma programática fazer a transformação entre o xmi resultante da 1ª transformação
 * e um xmi que está de acordo com o Ecore do Arduino.
 * Existe um ficheiro chamado Transform.asm, este ficheiro contém as regras ATL que permitem concretizar o pretendido,neste caso é um código máquina do Transform.atl
 * 
 * */


public class Second_Transformation {
    
    public static void main(String[] args) {
    	try {
    		//AtlModelHandler and ModelLoader init
    		AtlModelHandler.registerDefaultHandler("EMF", new AtlEMFModelHandler());   		
	    	AtlModelHandler amh = AtlModelHandler.getDefault("EMF");
	    	ModelLoader ml = amh.createModelLoader();
	    	
	    	//stores metamodels and models
	    	Map models = new HashMap();
	    	//used to locate metamodels,models and trasformation file
	    	URL url;
	    	
	    	//URL where BPMN20.ecore (input metamodel) is located
	    	url = new URL("file:C:/Users/Z1/Desktop/Tese/test_atl/BPMN20.ecore");	    	
	    	//load BPMN20 metamodel (the name is the same in the ATL file  [ex.: ATL CODE-> create OUT: Arduino from IN: *BPMN20*;])
	    	ASMModel metamodel_BPMN20 = ml.loadModel("BPMN20", ml.getMOF(), url.openStream());
	    	//store   
	    	models.put("BPMN20", metamodel_BPMN20);
	    	
	    	//URL where arduino.ecore (output metamodel) is located
	    	url = new URL("file:C:/Users/Z1/Desktop/Tese/test_atl/arduino.ecore");
	    	//load arduino metamodel (the name is the same in the ATL file  [ex.: ATL CODE-> create OUT : *Arduino* from IN: BPMN20;])
	    	ASMModel metamodel_arduino = ml.loadModel("Arduino", ml.getMOF(), url.openStream());
	    	//store 
	    	models.put("Arduino", metamodel_arduino);
	    	
	    	//URL where ticket.bpmn.bpmn2xmi (input model) is located,(attention that .bpmn2xmi is the same as .xmi)
	    	url = new URL("file:/C:/Users/Z1/Desktop/Tese/test_atl/ticket.bpmn.bpmn2xmi");
	    	//load BPMN20 model (the name is the same in the ATL file  [ex.: ATL CODE-> create OUT : Arduino from *IN*: BPMN20;])
	    	ASMModel model_BPMN20 = ml.loadModel("IN", metamodel_BPMN20, url.openStream());
	    	//store 
	    	models.put("IN", model_BPMN20);
	    	
	    	//URL where output_2nd.xmi (output model) is located
	    	url = new URL("file:/C:/Users/Z1/Desktop/Tese/test_atl/output_2nd.xmi");
	    	//create a new output model (the name is the same in the ATL file  [ex.: ATL CODE-> create *OUT* : Arduino from IN: BPMN20;])
	    	ASMModel outputModel = ml.newModel("OUT", "C:/Users/Z1/Desktop/asd/test_atl/output_2nd.xmi", metamodel_arduino);
	    	//store 
	    	models.put("OUT", outputModel);
	    	
	    	//URL where Transform.asm (transformation .asm) is located, um ficheiro asm é o código binário do ficheiro das regras atl
	    	url = new URL("file:/C:/Users/Z1/Desktop/Tese/test_atl/Transform.asm");
	    	//get an Atllauncher
	    	AtlLauncher mylauncher = AtlLauncher.getDefault();
	    	//execute the transformation (in this case we pass just the map "models")
	    	mylauncher.launch(url, Collections.EMPTY_MAP, models, Collections.EMPTY_MAP, Collections.EMPTY_LIST, Collections.EMPTY_MAP);
	    	
	    	//save the outputModel in "file://C:/Users/JF/Desktop/Tese/test_atl/output_2nd.xmi"
	    	outputModel.getModelLoader().save(outputModel, "file://C:/Users/Z1/Desktop/Tese/test_atl/output_2nd.xmi");
	    	
	    	//once executed this file make a refresh on the destination folder
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
	
}