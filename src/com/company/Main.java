package com.company;

public class Main {

    public static void main(String[] args) {
        DomParser domParser = new DomParser("depot.xml");
        domParser.createList();
        for (Train train : domParser.getTrains()) {
            System.out.println("Load capacity: " + train.getLoadCapacity() + " Amount of passengers: " + train.getPassengers());
        }
        BuilderXml builderXml = new BuilderXml("new_depot.xml");
        builderXml.buildXml(domParser.getTrains());
    }
}