/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import com.mycompany.jaxb.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/**
 *
 * @author werrio5
 */
public class MainClass {

    public static void main(String[] args) throws JAXBException, FileNotFoundException, InstantiationException, IllegalAccessException, DatatypeConfigurationException {
        JAXBContext jaxbContext = JAXBContext.newInstance("com.mycompany.jaxb");
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        User userObj = new User();
        userObj.generalInfo = new User.GeneralInfo();
        userObj.contactInfo = new User.ContactInfo();
        userObj.parameters = new User.Parameters();
        userObj.generalInfo.fio = "name";
        userObj.generalInfo.sex = "M";
        LocalDate date = LocalDate.now();
        GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        userObj.generalInfo.birthdate = xcal;
        userObj.parameters.height = 1000;
        userObj.parameters.weight = 123;
        userObj.contactInfo.mobilePhone = new ArrayList<BigInteger>();
        userObj.contactInfo.mobilePhone.add(BigInteger.valueOf(1234567891));

//Overloaded methods to marshal to different outputs
        OutputStream os = new FileOutputStream("/home/werrio5/NetBeansProjects/jaxb/target/output.xml");
        jaxbMarshaller.marshal(userObj, os);
        jaxbMarshaller.marshal(userObj, new PrintWriter(System.out));

//unmarshal
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        InputStream is = new FileInputStream("/home/werrio5/NetBeansProjects/jaxb/target/output.xml");
        User usr = (User) jaxbUnmarshaller.unmarshal(is);
        System.out.println("unmarshal");
        System.out.println(usr.getClass().getName());
        System.out.println(usr.generalInfo.fio);
    }
}
