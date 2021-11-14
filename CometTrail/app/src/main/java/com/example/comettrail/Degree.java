package com.example.comettrail;
import java.util.ArrayList;

public class Degree {

    private ArrayList<DegClass> major;
    public String name;

    public Degree() {
        name = "";
        major = new ArrayList<DegClass>();
    }

    public ArrayList<DegClass> getMajorClasses() {
        return major;
    }
    public void addMajorClass(DegClass degClass) {
        major.add(degClass);
    }

}
