package org.matsim.project;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XmlMatsimToOpenDS {

	
	

	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException {

		File xmlFile = new File("scenarios\\equil\\network.xml");

		

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();

		
		NodeList nodelist = doc.getElementsByTagName("node");
		NodeList linklist = doc.getElementsByTagName("link");
		
		
		
		for (int i = 0; i < linklist.getLength(); i++) {

			Node link = linklist.item(i);

			

			if (link.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) link;
				String id = elem.getAttribute("id");
				System.out.printf("Link id: %s%n", id);

				
				int fromInt = Integer.parseInt(elem.getAttribute("from"));
				int toInt = Integer.parseInt(elem.getAttribute("to"));
				
				
				//System.out.printf("to: %s%n", toInt);
				
				Node FromNode = nodelist.item(fromInt-1);
				Node ToNode = nodelist.item(toInt-1);
				
				if (FromNode.getNodeType() == Node.ELEMENT_NODE && ToNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element fromElem = (Element) FromNode;
					
					int fromX = Integer.parseInt(fromElem.getAttribute("x"));
					int fromY = Integer.parseInt(fromElem.getAttribute("y"));
					
					System.out.printf("from: %s%n", fromInt);
					
					Element toElem = (Element) ToNode;
					
					int toX = Integer.parseInt(toElem.getAttribute("x"));
					int toY = Integer.parseInt(toElem.getAttribute("y"));
					
					System.out.printf("to: %s%n", toInt);
					
					//mittelpunkt berechnen
					double MittelpunktX = (double)(fromX + toX)/2;
					double MittelpunktY = (double)(fromY + toY)/2;
					
					//durchmesser
					double XX = (double)(fromX-toX);
					double YY = (double)(fromY-toY);
					
					double Durchmesser = Math.sqrt(Math.pow(XX,2)+Math.pow(YY, 2));
					System.out.printf("Durchmesser: %s%n", Durchmesser);
					
					//Winkel
					if((toX-fromX)==0) {
						double anstieg = 90;
						System.out.printf("anstieg: %s%n", anstieg);
					}
					else {
						
					double anstieg = (Math.atan((toY-fromY)/(toX-fromX))*360)/(2*Math.PI);
					System.out.printf("anstieg: %s%n", anstieg);
					}
					
				}
				
				
				
			}
		}
		
		/*
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			System.out.println("\nCurrent Element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String id = elem.getAttribute("id");
				String x = elem.getAttribute("x");
				String y = elem.getAttribute("y");
				
				System.out.printf("id: %s%n", id);
				System.out.printf("x: %s%n", x);
				System.out.printf("y: %s%n", y);
				
			}
		}
		*/

	}
}
