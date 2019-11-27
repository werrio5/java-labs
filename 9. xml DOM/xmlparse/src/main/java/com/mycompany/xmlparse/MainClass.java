/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xmlparse;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

/**
 *
 * @author werrio5
 */
public class MainClass {
    
    private static ArrayList<fio> fios = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document =  builder.parse(new File("/home/werrio5/java/xmltest/xml.xml"));
   
        
         // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList fioElements = document.getDocumentElement().getElementsByTagName("fio");        

        document.getDocumentElement().normalize();

        System.out.println("Root element :" + document.getDocumentElement().getNodeName());
        NodeList nList = document.getElementsByTagName("*");

        System.out.println("----------------------------");

        Node n=null;
        Element eElement=null;
        
        Node node = document.getDocumentElement().getElementsByTagName("contact_info").item(0);
        Node newNode = document.createElement("mobile_phone");
        newNode.setTextContent("1112223334");
        node.appendChild(newNode);
            
        node = document.getDocumentElement().getElementsByTagName("fio").item(0);
        node.setTextContent("фамилия имя отчество");
            
            
            
        for (int i = 0; i < nList.getLength(); i++) {   
          n= nList.item(i);                            
          System.out.println(n.getNodeName());
          
              System.out.println(" " +n.getTextContent());
        }
        
        
     // Перебор всех элементов employee
        for (int i = 0; i < fioElements.getLength(); i++) {
            Node curfio = fioElements.item(i);

            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            String s = curfio.getTextContent();
            fios.add(new fio(s));
            
        }

        // Вывод информации о каждом сотруднике
        for (fio f : fios)
            System.out.println(String.format("fio = %s", f.getFio()));
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Result output = new StreamResult(new File("/home/werrio5/java/xmltest/output.xml"));
        Source input = new DOMSource(document);

        transformer.transform(input, output);
    }
}


