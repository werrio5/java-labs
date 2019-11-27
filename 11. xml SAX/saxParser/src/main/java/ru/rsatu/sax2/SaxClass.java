/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.sax2;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Павел
 */
public class SaxClass extends DefaultHandler{
    // List to hold Employees object
	private List<User> userList = null;
	private User user = null;
        private GeneralInfo ginfo = null;
        private Parameters params = null;
        private ContactInfo cinfo = null;
	private StringBuilder data = null;

	// getter method for employee list
	public List<User> getUserList() {
		return userList;
	}

	boolean bfio = false;
	boolean bsex = false;
	boolean bbdate = false;
	boolean bWeight = false;
        boolean bHeight = false;
        boolean bphones = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (qName.equalsIgnoreCase("user")) {
                user = new User();
                params = new Parameters();
                ginfo = new GeneralInfo();
                cinfo = new ContactInfo();
                
                if (userList == null)
                    userList = new ArrayList<>();
            }
            if (qName.equalsIgnoreCase("general_info")) {
                ginfo = new GeneralInfo();
            }
            if (qName.equalsIgnoreCase("parameters")) {
                params = new Parameters();
            } 
            if (qName.equalsIgnoreCase("contact_info")) {
                cinfo = new ContactInfo();
            }
            if(qName.equalsIgnoreCase("fio")){
                 bfio = true;
            }
            if(qName.equalsIgnoreCase("sex")){
                bsex = true;
            }
            if(qName.equalsIgnoreCase("birthdate")){
                bbdate = true;
            }
            if(qName.equalsIgnoreCase("weight")){
             bWeight = true;   
            }
            if(qName.equalsIgnoreCase("height")){
                bHeight = true;
            }
            if(qName.equalsIgnoreCase("mobile_phone")){
                 bphones = true;
            }
            
            data = new StringBuilder();
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
            
            if (bfio) {
                bfio=false;
                ginfo.setFio(data.toString());
            }
            if (bsex) {
                bsex=false;
                ginfo.setSex(data.toString().charAt(0));
            }
            if (bbdate) {
                bbdate=false;
                ginfo.setBirthdate(data.toString());
            }
            if (bWeight) {
                bWeight=false;
                params.setWeight(Integer.parseInt(data.toString()));
            }
            if (bHeight) {
                bHeight=false;
                params.setHeight(Integer.parseInt(data.toString()));
            }
            if (bphones) {
                bphones=false;
                cinfo.addPhone(data.toString());
            }
            if (qName.equalsIgnoreCase("user")) {
			// add Employee object to list
                        user.setContactInfo(cinfo);
                        user.setGeneralInfo(ginfo);
                        user.setParameters(params);
			userList.add(user);
            }
	

	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
}
