/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classloaderlab;

import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werrio5
 */

//b импл интерфейс
//делаем обьект
//даем как интерфейс в А
//вызов по имени

//2 generic исп + вывод
public class mainClass {
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
    loader1 l1 = new loader1();
    loader2 l2 = new loader2();
    
    Class<?> A = l1.loadClass("classA");
    Class<?> B = l2.loadClass("classB");

    
    System.out.println("----reflection-------");
    Method Am = null ;
    Am = A.getMethod("procA");
    B.getMethod("callMethod", Method.class).invoke(B.newInstance(),Am);
    System.out.println("------------------");
    System.out.println("----interface-------");
    
    //Object in = B.getInterfaces()[0].newInstance();
    
    A.getMethod("callMethod", Class.class).invoke(A.newInstance(),B.getInterfaces()[0]);
        
        
    }
}

