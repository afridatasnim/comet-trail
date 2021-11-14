package com.example.comettrail;

import java.util.ArrayList;

public class Schedule {
    private ArrayList<String> mySchedule;

    // Constructors
    public Schedule() {}
    public Schedule(int i) {mySchedule = new ArrayList<>();}

    // Getter and Setter
    public ArrayList<String> getMySchedule() {return mySchedule;}
    public void setMySchedule(ArrayList<String> myArr) {mySchedule = myArr;}

    public void addCourse(String course) {
        mySchedule.add(course);
    }

}
