package com.example.comettrail;

import java.util.ArrayList;

public class ScheduleMaker {
    private ArrayList<String> courses;
    private ArrayList<Integer> priority;
    private int maxHours;

    private ArrayList<Schedule> allSchedules;

    // Constructors
    public ScheduleMaker() {}
    public ScheduleMaker (ArrayList<String> myCourses, ArrayList<Integer> myPriority, int mH) {
        courses = myCourses;
        priority = myPriority;
        maxHours = mH;
        allSchedules = new ArrayList<>();

        // Insert code for making schedules
        // Example course from myCourses: ECS1100
        int curHours = 0;
        int courseHours;
        String curCourse;
        Schedule mySchedule = new Schedule();

        for (int i = 0; i < courses.size(); i++) {
            curCourse = myCourses.get(i);
            courseHours = curCourse.charAt(curCourse.length() - 2);
            if (maxHours > curHours + courseHours) {
                mySchedule.addCourse(curCourse);
                curHours += courseHours;
            }
        }
        this.allSchedules.add(mySchedule);
    }

    // Getters
    public ArrayList<String> getCourses() {return courses;}
    public ArrayList<Integer> getPriority() {return priority;}
    public int getMaxHours() {return maxHours;}
    public ArrayList<Schedule> getAllSchedules() {return allSchedules;}

    // Setters
    public void setCourses(ArrayList<String> myArr) {courses = myArr;}
    public void setPriority(ArrayList<Integer> myArr) {priority = myArr;}
    public void setMaxHours(int mH) {maxHours = mH;}
    public void setAllSchedules(ArrayList<Schedule> scheduleList) {allSchedules = scheduleList;}
}
