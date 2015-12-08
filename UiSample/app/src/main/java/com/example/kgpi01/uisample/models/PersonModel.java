package com.example.kgpi01.uisample.models;

/**
 * Created by KGPI01 on 2015/12/09.
 */
public class PersonModel {

    public PersonModel(int id, int no, String lastName, int age) {
        setId(id);
        setNo(no);
        setLastName(lastName);
        setAge(age);
    }

    public int getId() {
        return idValue;
    }
    public void setId(int id) {
        idValue = id;
    }
    private int idValue;

    public int getNo() {
        return noValue;
    }
    public void setNo(int no) {
        noValue = no;
    }
    private int noValue;

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String name) {
        lastName = name == null ? "" : name;
    }
    private String lastName = "";

    public int getAge() {
        return noValue;
    }
    public void setAge(int age) {
        ageValue = age;
    }
    private int ageValue;
}
