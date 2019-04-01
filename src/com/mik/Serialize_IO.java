package com.mik;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iit
 */
public class Serialize_IO {
    private static String filename = "serialize_data.txt";
    
    public Serialize_IO()
    {
    	
    }
        
    public void writeSerializeListObject(ArrayList list){       
        FileOutputStream fout = null;
        ObjectOutputStream object_out_for_serializable = null;
        try {
            fout = new FileOutputStream(filename);
            object_out_for_serializable = new ObjectOutputStream(fout);
            
            object_out_for_serializable.writeObject(list);
            object_out_for_serializable.flush();
            
            System.out.println("Success to write serializable");
                    
        } catch (FileNotFoundException ex) {
            System.out.println("FileOutputStream in " + ex.toString());
        } catch (IOException ex) {
            System.out.println("ObjectOutputStream in " + ex.toString());
        } 
        
    }    
    
    
    
    
    public boolean readDeserializeListObject(String accNum){
 
        try {
            ObjectInputStream object_in_for_deserializable =
                    new ObjectInputStream(new FileInputStream(filename));
            
            ArrayList<Account> listOfStudent = 
                    (ArrayList<Account>) object_in_for_deserializable.readObject();
            
            for (Account deserializeStudent : listOfStudent){
            	if (deserializeStudent.AccountNumber.equals(accNum))
            		return true; 
            	else if (deserializeStudent.password.equals(accNum))
            		return true;
            }
//            Student student1 = (Student) object_in_for_deserializable.readObject();
//            
//            System.out.println(student1.name + "; All:" + student1.toString());
            
        } 
        catch (IOException ex) {
            Logger.getLogger(Serialize_IO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Serialize_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
		return false;
        
    }
    
}

