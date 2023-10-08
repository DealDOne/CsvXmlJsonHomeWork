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

    private static List<HashMap<String,String>> read(Node node, int employeesNumbers, List<HashMap<String,String>> list){
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node nodeItem = node.getChildNodes().item(i);
            if (nodeItem.ELEMENT_NODE == nodeItem.getNodeType()){
                if (nodeItem.getChildNodes().getLength() != 1) {
                    list.add(new HashMap<>());
                    employeesNumbers += 1;
                }
                else if (nodeItem.getAttributes() != null){
                    NamedNodeMap map = nodeItem.getAttributes();
                    for (int j = 0; j < map.getLength(); j++){
                    }
                }
                read(nodeItem, employeesNumbers, list);
            } else if (node.getChildNodes().getLength() == 1){

                String key = node.getNodeName();
                String value = nodeItem.getNodeValue();

                list.get(employeesNumbers - 1).put(key, value);

            }
        } return list;
    }

    public static List<Employee> parseXML(String file){
        Node node = getRoot(file);
        List<HashMap<String,String>> list = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        for (HashMap<String,String> map: read(node, 0, list)){
            Employee employee = new Employee(Integer. parseInt(map.get("id")), map.get("firstName"), map.get("lastName"), map.get("country"), Integer. parseInt(map.get("age")));
            employees.add(employee);
            } return employees;
        }
}
