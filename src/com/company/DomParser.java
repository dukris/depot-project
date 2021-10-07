package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private final String xmlFileName;
    private final List<Train> trains = new ArrayList<>();
    private DocumentBuilder documentBuilder;

    public DomParser(String xmlFile) {
        this.xmlFileName = xmlFile;
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Parser configuration error!");
        }
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void createList() {
        try {
            Document document = documentBuilder.parse(this.xmlFileName);
            Element root = document.getDocumentElement();
            NodeList trainsList = root.getElementsByTagName("element");
            for (int i = 0; i < trainsList.getLength(); i++) {
                Element trainElement = (Element) trainsList.item(i);
                Train train = createTrain(trainElement);
                trains.add(train);
            }
        } catch (IOException e) {
            System.err.println("File error!");
        } catch (SAXException e) {
            System.err.println("Parsing error!");
        }
    }

    private Train createTrain(Element trainElement) {
        Train train = new Train();
        train.setLoadCapacity(Double.parseDouble(getElementTextContent(trainElement, "capacity")));
        train.setPassengers(Integer.parseInt(getElementTextContent(trainElement, "passengers")));
        return train;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

}
