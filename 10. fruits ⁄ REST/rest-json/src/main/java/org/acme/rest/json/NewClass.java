/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.rest.json;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author werrio5
 */
@ApplicationScoped
public class NewClass {
    
    private String name;
    
    
    public void method(){
      this.name = "OBOLLIb";
    }
    
    public String getName(){
        return this.name;
    }
}
