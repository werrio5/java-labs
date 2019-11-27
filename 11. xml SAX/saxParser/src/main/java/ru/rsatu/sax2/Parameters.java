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
public class Parameters {
    
    private int height;
    private int weight;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getParameters() {
        return ("\nheight = "+this.height+" weight = "+this.weight);
    }
    
}
