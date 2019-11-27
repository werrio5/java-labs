/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.sax2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Павел
 */
public class ContactInfo {
    
    List<String> number;
    
    public void addPhone(String number){
        if(this.number==null)
            this.number = new ArrayList<String>();
        this.number.add(number);
    }
    
    public String getContactInfo(){
        return "\nnumber list = "+this.number.toString();
    }
}
