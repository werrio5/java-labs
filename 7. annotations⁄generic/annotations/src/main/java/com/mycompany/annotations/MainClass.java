/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.annotations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author werrio5
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        List<Figure> figList = new ArrayList<Figure>();
        Circle c = new Circle();
        Square s = new Square();
        figList.add(c);
        figList.add(s);
        for(Figure f:figList) f.draw();
    }
}
