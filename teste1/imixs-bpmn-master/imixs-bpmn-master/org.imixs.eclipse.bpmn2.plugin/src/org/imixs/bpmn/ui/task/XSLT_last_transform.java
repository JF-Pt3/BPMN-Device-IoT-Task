package org.imixs.bpmn.ui.task;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/*
 * Pega-se no ficheiro output_2nd.xmi aplica-se a transforma��o xslt atrav�s do ficheiro "xslt_original.xslt"( que j� cont�m todas as regras necess�rias a formatar o ficheiro output_2nd.xmi para o formato requerido na �ltima transforma��o)
 * 
 * O ficheiro de sa�da desta transforma��o xslt � o saida.xmi("C:/Users/JF/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/saida.xmi")
 * 
 * */

public class XSLT_last_transform {

	public static void main(String[] args) {
		
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource xsltStream = new StreamSource("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_files/xslt_original.xslt");
		
		StreamSource in = new StreamSource("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/output_2nd.xmi");
		StreamResult out = new StreamResult(new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/xslt_result/saida.xmi"));
		
		
		try {
			Transformer transformer = factory.newTransformer(xsltStream);
			transformer.transform(in, out);
		}catch(TransformerException e) {
			e.printStackTrace();
		}

	}

}
