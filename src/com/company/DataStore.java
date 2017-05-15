package com.company;

import java.io.Serializable;

/**
 * Created by adrian on 5/15/17.
 */
public class DataStore implements Serializable{
    private String name;
    private Integer age;

    public DataStore(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "DataStore{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
