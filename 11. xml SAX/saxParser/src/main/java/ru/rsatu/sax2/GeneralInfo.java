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
public class GeneralInfo {
    
    private String fio;
    private char sex;
    private String birthdate;

    public String getGeneralInfo(){        
        return ("\nfio = "+this.fio+"sex = "+this.sex+" birthdate = "+this.birthdate);
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
   
}
