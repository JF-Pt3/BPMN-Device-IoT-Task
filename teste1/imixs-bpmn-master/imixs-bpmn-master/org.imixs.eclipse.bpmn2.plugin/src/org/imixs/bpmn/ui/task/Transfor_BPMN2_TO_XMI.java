package org.imixs.bpmn.ui.task;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class Transfor_BPMN2_TO_XMI {

    private Transformer xml2xmi;
    private Transformer xmi2xml;
    private Validator bpmnXmlValidator;

    @Before
    public void setUp() throws TransformerConfigurationException, SAXException {
        TransformerFactory transFact = TransformerFactory.newInstance();
        /* Carrega-se o ficheiro da transforma��o xslt, XMLToXMI.xslt, � o que cont�m todas as regras que permitem transformar o ficheiro xml do bpmn num ficheiro xmi com as informa��es da Device_Task devidamente isoladas*/
        this.xmi2xml = transFact.newTransformer(new StreamSource("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/result/XMIToXML.xslt"));
        this.xml2xmi = transFact.newTransformer(new StreamSource("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/result/XMLToXMI.xslt"));
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        //File schemaLocation = new File("res/BPMN20.xsd");
        System.out.println("Ola-2");
        File schemaLocation = new File(
                "C:/Users/Z1/Desktop/Tese/Experiencias_ATL/org.eclipse.bpmn2.tests/res/extensibility/BPMN20.xsd");//ficheiro schema do bpmn20 que cont�m toda a estrutura do bpmn,� uma esp�cie de modelo

        Schema schema = factory.newSchema(schemaLocation);

        bpmnXmlValidator = schema.newValidator();

    }

    private void processDirectory(File directory)
            throws TransformerException, SAXException, IOException {
        File[] content = directory.listFiles();

        int a = 3;
        for (File subFile : content) {
            if (subFile.isDirectory()) {
                processDirectory(subFile);
            } else if (subFile.getName().endsWith(".bpmn")) {
                processBpmnFile(subFile);
            } else if (subFile.getName().endsWith(".bpmn2xmi")) {
                processBpmnXmiFile(subFile);
            }
        }

    }

    private void processBpmnFile(File subFile)
            throws TransformerException, SAXException, IOException {

        StreamSource streamSource = new StreamSource(subFile.getAbsolutePath());
        //bpmnXmlValidator.validate(streamSource);
        xml2xmi.transform(streamSource, new StreamResult(subFile.getAbsolutePath() + ".bpmn2xmi"));
    }

    private void processBpmnXmiFile(File subFile) throws TransformerException, IOException {
        String resultFile = subFile.getAbsolutePath() + ".bpmn2";
        xmi2xml.transform(new StreamSource(subFile.getAbsolutePath()),
                new StreamResult(resultFile));
        StreamSource bpmn2File = new StreamSource(resultFile);
        try {
            bpmnXmlValidator.validate(bpmn2File);
        } catch (SAXException e) {
            System.out.println("Validation problems in " + resultFile);
            System.out.println(e.toString());
        }

    }

    @Test
    public void testTransform() throws TransformerException, SAXException, IOException {
        File resDirecty = new File("C:/Users/Z1/Desktop/Tese/teste1/imixs-bpmn-master/imixs-bpmn-master/org.imixs.eclipse.bpmn2.plugin/res");

        processDirectory(resDirecty);//esta fun��o permite processar a transforma��o de bpmn2xmi e o inverso,neste caso s� interessa a 1�

    }

}
