/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classinfov2;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author werrio5
 */
public class MainClass implements intrfc{  

    private static void javassistMain() throws NotFoundException, CannotCompileException, IOException {
       ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
    }
    
    final Logger logger = LogManager.getLogger(MainClass.class);
    
     private String spaceChar;    
     private String tabChar;
     private String EOLChar;
     
     int x;
     boolean b;
    /**
     * Метод выводит информацию о методах, полях, интерфейсах класса, общую информацию о нем и указывает его объект-предок.
     * @param args 
     * @throws ClassNotFoundException в случае указанния несуществующего класса.
     */
    @logged(value=logType.INFO,
           loggedClass=MainClass.class)
    public static void main(String[] args) throws ClassNotFoundException, NotFoundException, CannotCompileException, IOException {
        
        javassistMain();
                     
        Class<?> clazz = Class.forName("com.mycompany.classinfov2.MainClass");
        StringBuilder stringBuilder = new StringBuilder();
        MainClass mainClass = new MainClass(' ','\t','\n');
        mainClass.printClass(clazz, stringBuilder);
        intrfc.dataOutput(System.out, stringBuilder);
    }     
    /**
     * Сохраняет информацию о классе для последующего вывода.
     * @param clazz исследуемый класс
     * @param stringBuilder переменная для хранения собранной информации.
     * @throws java.lang.ClassNotFoundException в случае, когда объект не имеет предка, в частности - класс Object.
     */
    public void printClass(Class clazz, StringBuilder stringBuilder) throws ClassNotFoundException{
        // logger
        logger.info("method printclass was called");
        //logger
        
        // напечатать общую информацию п классе
        this.printClassCommonInfo(clazz, stringBuilder);
        // напечатать поля
        Field[] fields = clazz.getDeclaredFields();
        this.printFields(fields, stringBuilder);
        // напечатать методы
        Method[] methods = clazz.getDeclaredMethods();
        this.printMethods(methods, stringBuilder);
        // напечатать интерфейсы
        Class<?>[] interfaces = clazz.getInterfaces();
        this.printInterfaces(interfaces, stringBuilder);
        //для красоты
        this.addChar('}', stringBuilder);
        this.printEOL(stringBuilder);
        // напечатать парент объект
        try{
            String parentName = clazz.getSuperclass().getName();
            Class<?> parentObject = Class.forName(parentName);
            printClass(parentObject, stringBuilder);
        }
        catch(Exception e){};
        
    }    
    /**
     * Собирает общую информацию об указанном классе -- модификаторы и имя.
     * @param clazz класс, информация о котором будет собрана.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printClassCommonInfo(Class clazz, StringBuilder stringBuilder){
        //аннотации
        Annotation[] annotations = clazz.getAnnotations();
        this.printAnnotations(annotations, stringBuilder);
        //модификаторы
        stringBuilder.append(Modifier.toString(clazz.getModifiers()));
        this.printSpace(stringBuilder);
        //имя класса
        stringBuilder.append(getShortName(clazz.getName()));
        this.addChar('{', stringBuilder);
        this.printEOL(stringBuilder);
    }
    
    /**
     * Вызывает метод для сбора информации по каждому элементу из переданного массива.
     * @param fields массив полей класса.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printFields(Field[] fields, StringBuilder stringBuilder){
        for (Field field:fields){
            this.printTab(stringBuilder);
            this.printField(field, stringBuilder);
        }            
        this.printEOL(stringBuilder);
    } 
    /**
     * Метод для для сохранения модификаторов, типа данных и имени поля.
     * @param field поле класса.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printField(Field field, StringBuilder stringBuilder){
        //аннотации
        Annotation[] annotations = field.getAnnotations();
        this.printAnnotations(annotations, stringBuilder);
        //модификаторы
        stringBuilder.append(Modifier.toString(field.getModifiers()));
        this.printSpace(stringBuilder);
        //тип 
        stringBuilder.append(getShortName(field.getType().toString()));
        this.printSpace(stringBuilder);
        //название
        stringBuilder.append(getShortName(field.getName()));
        this.addChar(';', stringBuilder);
        this.printEOL(stringBuilder);
    }    
    /**
     * Вызывает метод для сбора информации по каждому элементу из переданного массива.
     * @param methods массив методов класса.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printMethods(Method[] methods, StringBuilder stringBuilder){
        for (Method method:methods){
            this.printTab(stringBuilder);
            this.printMethod(method, stringBuilder);
        }
        this.printEOL(stringBuilder);
    }
    /**
     * Метод для для сохранения модификаторов, типа возвращаемого значения, имени и параметров метода.
     * @param method метод класса.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printMethod(Method method, StringBuilder stringBuilder){
        //аннотации
        Annotation[] annotations = method.getAnnotations();
        this.printAnnotations(annotations, stringBuilder);
        //модификаторы
        stringBuilder.append(Modifier.toString(method.getModifiers()));
        this.printSpace(stringBuilder);
        //тип возвращаемого значения
        stringBuilder.append(getShortName(method.getReturnType().toString()));
        this.printSpace(stringBuilder);
        //название
        StringBuilder fullName = new StringBuilder();
        fullName.append(method.getName());
        stringBuilder.append(getShortName(method.getName()));
        this.printSpace(stringBuilder);
        //параметры 
        Parameter[] parameters = method.getParameters();
        printParameters(parameters, stringBuilder);
        this.printRB(stringBuilder);
        this.printEOL(stringBuilder);
    }  
    /**
     * Вызывает метод для сбора информации по каждому элементу из переданного массива.
     * @param parameters массив параметров метода.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printParameters(Parameter[] parameters, StringBuilder stringBuilder) {
        this.printLB(stringBuilder);
        for (Parameter parameter:parameters){
            printParameter(parameter, stringBuilder);
        }
    }
    /**
     * Метод для для сохранения параметра метода.
     * @param parameter параметр метода.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printParameter(Parameter parameter, StringBuilder stringBuilder) {      
        stringBuilder.append(getShortName(parameter.toString()));
        this.addChar(',',stringBuilder);
        this.printSpace(stringBuilder);
    }
    /**
     * Вызывает метод для сбора информации по каждому элементу из переданного массива.
     * @param interfaces массив интерфесов.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printInterfaces(Class<?>[] interfaces, StringBuilder stringBuilder){
        for (Class<?> Interface:interfaces){
            this.printTab(stringBuilder);
            this.printInterface(Interface, stringBuilder);
        }
        this.printEOL(stringBuilder);
    }
    /**
     * Метод для для сохранения модификаторов, имени и методов интерфейса.
     * @param Interface описываемый интерфейс.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printInterface(Class<?> Interface, StringBuilder stringBuilder){
        //модификаторы
        stringBuilder.append(Modifier.toString(Interface.getModifiers()));
        this.printSpace(stringBuilder);
        //название
        stringBuilder.append(getShortName(Interface.getName()));
        //для красоты
        this.addChar('{', stringBuilder);
        this.printEOL(stringBuilder);
        //методы
        Method[] methods = Interface.getDeclaredMethods();
        this.printMethods(methods, stringBuilder);
        //для красоты
        this.printTab(stringBuilder);
        this.addChar('}', stringBuilder);
        this.printEOL(stringBuilder);
    }
    /**
     * Добавляет пробел в конец хранилища.
     * @param stringBuilder переменная для хранения собранной информации.
     */    
    private void printSpace(StringBuilder stringBuilder) {
        stringBuilder.append(this.spaceChar);
    }
    /**
     * Добавляет символ табуляции в конец хранилища.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printTab(StringBuilder stringBuilder) {
        stringBuilder.append(this.tabChar);
    }
    /**
     * Добавляет символ конца строки. Для форматирования выводимого текста.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printEOL(StringBuilder stringBuilder) {
        stringBuilder.append(this.EOLChar);
    }
    /**
     * Добавляет '(' в начале перечисления параметров. Для красоты.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printLB(StringBuilder stringBuilder) {
        stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "(");
    }
    /**
     * Добавляет ')' в конце перечисления параметров. Для красоты.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void printRB(StringBuilder stringBuilder) {
        if(stringBuilder.charAt(stringBuilder.length()-2) == ',')
            stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length(), ");");
        else
            stringBuilder.replace(stringBuilder.length(), stringBuilder.length(), ");");
    }
    /**
     * Это птица? Это вертолет? А вот и нет, это конструктор.
     * @param spaceChar символ пробела (строка).
     * @param tabChar символ табуляции (строка).
     * @param EOLChar символ конца строки (строка).
     */    
    public MainClass(String spaceChar, String tabChar, String EOLChar){
        this.spaceChar = spaceChar;
        this.tabChar = tabChar;
        this.EOLChar = EOLChar;
    }    
    /**
     * Конструктор. Ну, да.
     * @param spaceChar символ пробела.
     * @param tabChar символ табуляции.
     * @param EOLChar символ конца строки.
     */
    public MainClass(char spaceChar, char tabChar, char EOLChar){
        this.spaceChar = Character.toString(spaceChar);
        this.tabChar = Character.toString(tabChar);
        this.EOLChar = Character.toString(EOLChar);
    } 
    /**
     * Удаляет имя пакета (всё, до последнего символа точки включительно).
     * @param fullName полное имя.
     * @return имя не содержащее часть относящуюся к пакету.
     */
    private String getShortName(String fullName) {
        int lastDotPos = fullName.lastIndexOf(".");
        if(lastDotPos != -1)
            return fullName.substring(lastDotPos + 1);
        else 
           return fullName.toString(); 
    }
    /**
     * Добавляет символ в конец хранилища.
     * @param c добавляемый символ.
     * @param stringBuilder переменная для хранения собранной информации.
     */
    private void addChar(char c, StringBuilder stringBuilder) {
        stringBuilder.append(c);
    }
    
    private void printAnnotations(Annotation[] annotations, StringBuilder stringBuilder) {
         for (Annotation annotation:annotations){
             this.printAnnotation(annotation, stringBuilder);
         }
    }

    private void printAnnotation(Annotation annotation, StringBuilder stringBuilder) {
     stringBuilder.append(annotation);
     this.printSpace(stringBuilder);
    }
}
/**
 * interface example 
 * @author werrio5
 */
interface intrfc{
    /**
     * эта штука ничё не делает \_☺_/
     */
    public static void idk(){

    }
    /**
     * Вывод собранных данных.
     * @param printStream поток для вывода.
     * @param stringBuilder переменная хранящяясобранную информацию.
     */
    public static void dataOutput(PrintStream printStream, StringBuilder stringBuilder){
        printStream.print(stringBuilder);
    }
} 
