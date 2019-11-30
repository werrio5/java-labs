/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kodogenerator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.DuplicateMemberException;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Mnemonic;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author POJIJITOH
 */
public class NewClass {

    public static void main(String[] args) throws NotFoundException, DuplicateMemberException, BadBytecode, CannotCompileException, FileNotFoundException, IOException {

        NewClass nc = new NewClass();
        boolean load = false;
        if (load) {
            //read from file
            ClassFile readcf = nc.readfromfile();
            nc.listfileds(readcf);
        } else {
            //new class, adding field, list all fields        
            ClassFile cf = nc.createclassfile();
            nc.listfileds(cf);
            //save to file
            nc.savetofile(cf);
        }
    }

    public ClassFile createclassfile() throws DuplicateMemberException {
        ClassFile cf = new ClassFile(false, "com.baeldung.JavassistGeneratedClass", null);
        cf.setInterfaces(new String[]{"java.lang.Cloneable"});
        FieldInfo f = new FieldInfo(cf.getConstPool(), "id", "I");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f);
        return cf;
    }

    public void listfileds(ClassFile cf) throws CannotCompileException, NotFoundException {
        ClassPool classPool = ClassPool.getDefault();
        //CtClass ctc = classPool.makeClass(cf);
        Field[] fields = classPool.makeClass(cf, false).toClass().getFields();
        List<String> fieldsList = Stream.of(fields).map(Field::getName).collect(Collectors.toList());
        Assert.assertTrue(fieldsList.contains("id"));
        for (Field fld : fields) {
            System.out.println(fld.getName());
        }
    }

    public void savetofile(ClassFile cf) throws IOException {
        File output = new File("C:\\Users\\Павел\\Desktop\\java-labs-master\\1.txt");
        FileOutputStream fos = new FileOutputStream(output);
        DataOutputStream dos = new DataOutputStream(fos);
        cf.write(dos);
    }

    public ClassFile readfromfile() throws FileNotFoundException, IOException {
        File input = new File("C:\\Users\\Павел\\Desktop\\java-labs-master\\1.txt");
        FileInputStream fis = new FileInputStream(input);
        DataInputStream dis = new DataInputStream(fis);
        ClassFile readcf = new ClassFile(dis);
        return readcf;
    }
}
