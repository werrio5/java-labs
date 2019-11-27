/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classinfov2;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author werrio5
 */

public class NewClass {
    public static final Map<String, String> EXAMPLE_MAP = new HashMap(){
        {
        put("STR1", "мама");
        put("STR2", "мыла");
        put("STR3", "раму");
        }
    
};
    public static void main(String[] args){
        System.out.println(EXAMPLE_MAP);
    }
    
}