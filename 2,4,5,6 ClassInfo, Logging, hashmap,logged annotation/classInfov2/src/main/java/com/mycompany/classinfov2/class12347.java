/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classinfov2;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werrio5
 */

/*
annotation demo
*/

public class class12347 {

    
   private Long ID; 

   public static void main(String[] args){
       try {
           System.out.println("annotations");
           class12347 q = new class12347(); 
           Method m=q.getClass().getMethod("setID");
           
       } catch (NoSuchMethodException | SecurityException ex) {
           Logger.getLogger(class12347.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
   
   @logged(value=logType.ERROR,
           loggedClass=class12347.class)
    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }
    
}
