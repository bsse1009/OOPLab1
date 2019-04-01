package com.mik;

import java.io.Serializable;

public class Person implements Serializable {

    String name;
    transient String university;
    String accountNumber;
    String password;
    double amount;
    
protected static int number_Of_DU_students = 0;
    
    public Person() {
        
        this("Unknown", "Unknown", 0.0);    
    }

    public Person(String name, String university, double amount) {
        this.name = name;
        this.university = university;
        this.amount = amount;
        
        if (university.equalsIgnoreCase("DU"))
                number_Of_DU_students++;
        
    }
    
    public void printName(){
        System.out.println("Hello " + this.name);
    }
    
    public static int getNumberOfDUStudents(){
        return number_Of_DU_students;
    }
    
}