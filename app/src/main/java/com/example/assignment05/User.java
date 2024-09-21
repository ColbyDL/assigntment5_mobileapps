package com.example.assignment05;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String email;
    int age;
    String country;
    String dob;

    public User(String name, String email, Integer age, String country, String dob){
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.dob = dob;
    }

    public User(){

    }
}
