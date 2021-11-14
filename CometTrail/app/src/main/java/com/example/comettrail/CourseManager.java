// Patch notes: I fixed it - Erik
// If you were curious, your JDK (Java Dev Kit) was downloaded and worked perfectly
// fine, the src file (the "source" file) was not set as the Source root directory
// Right click on src folder and click "Mark Directory as" and then Source Root
// Enjoy!

// Here is a fun tip: If something is red, try hovering over it and it might say
// hit "Alt-Enter", that solves 98.4% of my problems

package main.java.com.example.comettrail;

import java.util.ArrayList;

import dalvik.annotation.TestTarget;

public class CourseManager {
    private ArrayList<String> degreePlanList;
    private ArrayList<String> coursesTaken;

    public CourseManager(ArrayList<String> degreePlan, ArrayList<String> currentCredit){
        // Constructor
        this.degreePlanList = degreePlan;
        this.coursesTaken = currentCredit;
    }


    public ArrayList<String> compare(){
        int numCoursesTaken = coursesTaken.size(); //get number of courses taken
        int numCoursesLeft = degreePlanList.size(); //get total number of courses required to graduate
        ArrayList<String> coursesLeft = degreePlanList; //variable to store courses left to take
        boolean match = false; // keep track of matches
        int matchAt = 0;

        //run through every course already taken
        for(int i = 0; i < numCoursesTaken; i++){
            //run through every course you have left to take
            for(int j = 0; j < numCoursesLeft; j++){
                //look for a match between the course you've taken and what you need to graduate
                //if you find a match, note down where you found it
                //this is assuming degree plan list has no redundancy on it
                if(coursesLeft.get(j) == coursesTaken.get(i)){
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

    public void ableToTake(ArrayList<String> coursesLeft){

    }

    @Test
    public void testClass(){

        ArrayList<String> test1 = new ArrayList<String>();
        ArrayList<String> test2 = new ArrayList<String>();

        for(int i = 0; i < 15; i++){
            String a = "element" + i;
            test1.add(i, a);
        }

        test2.add("element2");
        test2.add("element13");
        test2.add("element5");

        //test1.add(0, "math1");


        CourseManager cm = new CourseManager(test1,  test2);

        ArrayList<String> subtractedTests = cm.compare();

        //print test1
        for(int i = 0; i < 15; i++){
            System.out.println(test1.get(i));
        }
        System.out.println(" ");
        //print test2
        for(int i = 0; i < 3; i++){
            System.out.println(test2.get(i));
        }
        System.out.println(" ");
        //print test1 - test2
        for(int i = 0; i < subtractedTests.size(); i++){
            System.out.println(subtractedTests.get(i));
        }
        System.out.println(" ");

        assertEquals(4,2+2);
    }

}