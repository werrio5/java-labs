/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.sax2;

/**
 *
 * @author Павел
 */
public class User {

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    private GeneralInfo generalInfo;
    private Parameters parameters;
    private ContactInfo contactInfo;
    
 
    
    @Override
    public String toString() {
        return "User::GeneralInfo::"+this.generalInfo.getGeneralInfo()
                +"\nUser::Parameters::" + this.parameters.getParameters()
                + "\nUser::ContactInfo::" + this.contactInfo.getContactInfo();
    }
    
}