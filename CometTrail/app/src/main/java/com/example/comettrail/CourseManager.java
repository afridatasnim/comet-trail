// Patch notes: I fixed it - Erik
// If you were curious, your JDK (Java Dev Kit) was downloaded and worked perfectly
// fine, the src file (the "source" file) was not set as the Source root directory
// Right click on src folder and click "Mark Directory as" and then Source Root
// Enjoy!

// Here is a fun tip: If something is red, try hovering over it and it might say
// hit "Alt-Enter", that solves 98.4% of my problems

package main.java.com.example.comettrail;

import com.example.comettrail.DegClass;
import com.example.comettrail.prioritizer;

import java.util.ArrayList;

public class CourseManager {
    private Degree degreePlanList;
    private Degree coursesTaken;

    public CourseManager(Degree degreePlan, Degree currentCredit){
        // Constructor
        this.degreePlanList = degreePlan;
        this.coursesTaken = currentCredit;
    }


    public Degree compare(){
        int numCoursesTaken = coursesTaken.size(); //get number of courses taken
        int numCoursesLeft = degreePlanList.size(); //get total number of courses required to graduate
        Degree coursesLeft = degreePlanList; //variable to store courses left to take
        boolean match = false; // keep track of matches
        int matchAt = 0;

        //run through every course already taken
        for(int i = 0; i < numCoursesTaken; i++){
            //run through every course you have left to take
            for(int j = 0; j < numCoursesLeft; j++){
                //look for a match between the course you've taken and what you need to graduate
                //if you find a match, note down where you found it
                //this is assuming degree plan list has no redundancy on it
                if(coursesTaken.get(i).equals(coursesLeft.get(j))){
                    match = true;
                    matchAt = j;
                }

            }
            //if you found a match, remove it from the classes you have left to take
            //and reset your match searching
            if(match == true){
                coursesLeft.remove(matchAt);
                numCoursesLeft = coursesLeft.size();
                match = false;
                matchAt = 0;
            }
        }
        //return the list of courses you still need to take

        return coursesLeft;
    }

    //decide  what classes the user can take next semester
    public ArrayList<DegClass> ableToTake(ArrayList<DegClass> coursesLeft, boolean wantCoursesAbleToTake){
        ArrayList<DegClass> coursesMeetPreRecs = new ArrayList<DegClass>(); //classes you currently meet prerecs for
        ArrayList<DegClass> coursesDontMeetPreRecs = new ArrayList<DegClass>(); //classes you don't currently meet prerecs for
        int numCoursesLeft = coursesLeft.size();
        int numCoursesTaken = coursesTaken.size();

        //consider each course you have left to take
        for(int i = 0; i < numCoursesLeft; i++){
            boolean canTake = true; //are you able to take the course?
            //for the current course being considered
            DegClass canITake = coursesLeft.get(i);
            //get the prerecs
            ArrayList<String> coursePreRecs = canITake.getPreRecs();
            //count how many prerecs you have
            int numPreRecs = coursePreRecs.size();
            //for each prerec
            for(int j = 0; j < numPreRecs; j++){
                boolean foundMatch = false; //to look for a match
                //go through all of the courses taken
                for(int k = 0; k < numCoursesTaken; k++){
                    //if you've taken the course, note it and stop searching
                    if(coursePreRecs.get(j).equals(coursesTaken.get(k))){
                        foundMatch = true;
                        break;
                    }
                }
                //if you haven't taken the class before
                if(!foundMatch){
                    //you can't take the class
                    canTake = false;
                }

            }
            //add the course to the correct list
            if(canTake){
                coursesMeetPreRecs.add(canITake);
                //TEST OUTPUT System.out.println("you CAN take left" + (i+1));
            }
            else{
                coursesDontMeetPreRecs.add(canITake);
                //TEST OUTPUT System.out.println("you can NOT take left" + (i+1));
            }
        }
        //return either the list of classes you can take or the list of classes you can't
        if(wantCoursesAbleToTake){
            return coursesMeetPreRecs;
        }
        else{
            return coursesDontMeetPreRecs;
        }
    }

    public int[] prioritize(ArrayList<DegClass> coursesMeetPreRecs, ArrayList<DegClass> coursesDontMeetPreRecs){
        int numCoursesMeetPreRecs = coursesMeetPreRecs.size();
        int numCoursesDontMeetPreRecs = coursesDontMeetPreRecs.size();

        int[] priorityModifier = new int[numCoursesMeetPreRecs];
        for(int i = 0; i < numCoursesMeetPreRecs; i++){
            priorityModifier[i] = 0;
        }

        //get the prioritizer
        prioritizer prio = new prioritizer();

        //keep track of how many semesters out you can take a set of classes.
        ArrayList<ArrayList<DegClass>> semestersAway = new ArrayList<ArrayList<DegClass>>();
        semestersAway.add(coursesMeetPreRecs);

        ArrayList<DegClass> oneSemesterOut = prio.separate(coursesDontMeetPreRecs, true);

        while(prio.separate(oneSemesterOut, false).size() != 0){
            semestersAway.add(prio.separate(coursesDontMeetPreRecs, true));

        }

        for(int i = 0; i < numCoursesMeetPreRecs; i++){
            priorityModifier[i] = prio.prerequisiteChainDepth(semestersAway, coursesMeetPreRecs.get(i));
        }

        return priorityModifier;
    }

    public void testClass(){

        //TEST SORTing BY WHETHER PRERECS MET
        //GENERATING RANDOM PRERECS
        //generate and return random prerecs
        ArrayList<String> preRecs = new ArrayList<String>();
        String courseName = " ";

        //create a list of "courses you have left" to test with
        ArrayList<DegClass> coursesLeftToTake = new ArrayList<DegClass>();


        //create the CourseManager
        //a degree plan of 26 courses
        ArrayList<String> test1 = new ArrayList<String>();
        for(int i = 0; i < 26; i++){
            test1.add("course" + i);
        }
        //student has already taken 6 courses
        ArrayList<String> test2 = new ArrayList<String>();
        for(int i = 0; i < 6; i++){ //set which prerecs you have
            test2.add("course" + i);
        }
        CourseManager cm = new CourseManager(test1, test2);

        ArrayList<DegClass> classesCanTake = cm.ableToTake(coursesLeftToTake, true);
        ArrayList<DegClass> classesCantTake = cm.ableToTake(coursesLeftToTake, false);

        cm.prioritize(classesCanTake, classesCantTake);





/* FOR MAKING RANDOM PRERECS
Random rand = new Random();
        for(int i = 0; i < 3; i++){
            int rand_numPreRecs = rand.nextInt(3);
            int rand_courseNumber = rand.nextInt(10);
            int lastNum =  11;


            for(int j = 0; j < rand_numPreRecs; j++) {
                do {
                    if (rand_courseNumber == lastNum) {
                        rand_courseNumber = rand.nextInt(10);
                    }
                } while (rand_courseNumber == lastNum);
                preRecs.add("course" + rand_courseNumber);
                lastNum = rand_courseNumber;
            }
            courseName = "left" + (i+1);
            dummyClass left = new dummyClass(preRecs, courseName);
            testList1.add(left);
        }

         */

/* TEST FOR CREATING COURSES LEFT LIST
        ArrayList<String> test1 = new ArrayList<String>();
        ArrayList<String> test2 = new ArrayList<String>();


        for(int i = 0; i < 15; i++){
            String a = "element" + i;
            test1.add(i, a);
        }

        //print test1
        System.out.println("printing test1");
        for(int i = 0; i < 15; i++){
            System.out.println(test1.get(i));
        }
        System.out.println("test1 printed");

        test2.add("element2");
        test2.add("element13");
        test2.add("element5");


        //print test2
        System.out.println("printing test2");
        for(int i = 0; i < 3; i++){
            System.out.println(test2.get(i));
        }
        System.out.println("test2 printed");

        //test1.add(0, "math1");


        CourseManager cm = new CourseManager(test1,  test2);

        ArrayList<String> subtractedTests = cm.compare();


        //print test1 - test2
        for(int i = 0; i < subtractedTests.size(); i++){
            System.out.println(subtractedTests.get(i));
        }
        System.out.println(" ");
        */


    }

}