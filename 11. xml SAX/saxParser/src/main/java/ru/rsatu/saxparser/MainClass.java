/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.saxparser;

/**
 *
 * @author Павел
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

//import ru.rsatu.saxparser.Employee;

public class MainClass {

    public static void main(String[] args) {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try {
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SaxClass handler = new SaxClass();
        saxParser.parse(new File("C:/Users/Павел/Documents/NetBeansProjects/saxParser/src/main/java/ru/rsatu/saxparser/employees.xml"), handler);
        //Get Employees list
        List<Employee> empList = handler.getEmpList();
        //print employee information
        for(Employee emp : empList)
            System.out.println(emp);
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
    }
    }

}
