package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BuilderXml {
    private final String xmlFileName;
    private DocumentBuilder documentBuilder;

    public BuilderXml(String xmlFile) {
        this.xmlFileName = xmlFile;
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Parser configuration error!");
        }
    }

    public void buildXml(List<Train> trains) {
        Document document = documentBuilder.newDocument();
        String root = "train";
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);
        for (Train train : trains) {
            Element elementCapacity = document.createElement("capacity");
            elementCapacity.appendChild(document.createTextNode(Double.toString(train.getLoadCapacity())));
            Element elementPassengers = document.createElement("passengers");
            elementPassengers.appendChild(document.createTextNode(Integer.toString(train.getPassengers())));
            rootElement.appendChild(elementCapacity);
            rootElement.appendChild(elementPassengers);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(xmlFileName));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            System.err.println("Transformation error!");
        } catch (IOException e) {
            System.err.println("File error!");
        }
    }
}
