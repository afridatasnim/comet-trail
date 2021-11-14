package com.example.comettrail;

import java.util.ArrayList;
import java.util.Random;

public class DegClass {
    private ArrayList<String> coursePreRecs;
    private String courseName;

    public DegClass(ArrayList<String> preRecs, String name){
        //constructor
        this.coursePreRecs = preRecs;
        this.courseName = name;
    }

    public String getCourseName(){
        return courseName;
    }

    public ArrayList<String> getPreRecs() {
        return coursePreRecs;
    }

/*
        //generate and return random prerecs
        ArrayList<String> preRecs = new ArrayList<String>();
        Random rand = new Random();
        String courseName;
        int rand_numPreRecs = rand.nextInt(3);
        int rand_courseNumber = rand.nextInt(10);
        int lastNum =  11;
        System.out.println("has " + rand_numPreRecs + " pre recs, which are");

        for(int i = 0; i < rand_numPreRecs; i++) {
            do {
                if (rand_courseNumber == lastNum) {
                    rand_courseNumber = rand.nextInt(10);
                }
            } while (rand_courseNumber == lastNum);
            preRecs.add("course" + rand_courseNumber);
            System.out.println("course" + rand_courseNumber);

        }
 */





}
