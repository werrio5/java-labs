
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

//package com.mycompany.classloaderlab;
//not included

public class classB implements int1{

    
    
    
       public void callMethod(Method m){
           System.out.println("from classB.callMethod()");
        if(m!=null) try {
            m.invoke(null, null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(classA.class.getName()).log(Level.SEVERE, null, ex);
        }
        else int1.procB();
    }
}

interface int1{
    static void procB(){System.out.println("classB.procB()");};
}