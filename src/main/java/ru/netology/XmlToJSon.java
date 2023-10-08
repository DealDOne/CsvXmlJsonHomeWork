package ru.netology;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlToJSon {
    public static Node getRoot(String file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(file));
            Node root = doc.getDocumentElement();
            return root;
        } catch (ParserConfigurationException configurationException ){
            configurationException.printStackTrace();
        } catch( IOException ioException){
            ioException.printStackTrace();
        } catch (SAXException saxException){
            saxException.printStackTrace();
        } return null;
    }

    public static List<Employee> parseXML(String file){
        Node node = getRoot(file);
        NodeList nodeList = node.getChildNodes();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeItem = node.getChildNodes().item(i);
            if (nodeItem.ELEMENT_NODE == nodeItem.getNodeType() && nodeItem.getNodeName() == "employee"){
                Element element = (Element) nodeItem;
                int id = Integer. parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                String country = element.getElementsByTagName("country").item(0).getTextContent();
                int age =  Integer. parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                Employee employee = new Employee(id, firstName, lastName, country, age);
                employees.add(employee);
            }
        }
        return employees;
    }
}
