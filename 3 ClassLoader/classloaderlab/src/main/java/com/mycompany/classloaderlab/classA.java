
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class classA{
    public static void procA(){
        System.out.println("classA.procA() ");
    }
    
    public void callMethod(Class in){
        System.out.println("from classA.callMethod()");
        if(in!=null) {
            //System.out.println(m.getInterfaces()[0].getName());
            Method Bm = in.getMethods()[0];
            try {                
                // System.out.println(Bm.getName());
                Bm.invoke(null, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(classA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else this.procA();
    }
   
}

