package com.example.comettrail;

import java.util.ArrayList;

public class prioritizer {

    public prioritizer(){
        //constructor

    }

    public ArrayList<DegClass> separate(ArrayList<DegClass> checkForPreRecs, boolean wantOnlyImmediatePreRecs){
        int numChecking = checkForPreRecs.size();

        ArrayList<DegClass> onlyImmediatePreRecs = new ArrayList<DegClass>();
        ArrayList<DegClass> morePreRecs = new ArrayList<DegClass>();

        //all the ones we want to check the prerequisites of
        for(int i = 0; i < numChecking; i++) {
            DegClass checking = checkForPreRecs.get(i);
            ArrayList<String> preRecsToCheck = checking.getPreRecs();
            boolean nonImmediatePreRecs = false;
            //all of the prerecs for each one
            for (int j = 0; j < preRecsToCheck.size(); j++) {
                for(int k = 0; k < numChecking; k++){
                    //all courses being checked are ones the user doesn't have every prerec for
                    //if the prerec for a course being checked is another course being checked
                    //then they won't be able to take the first course in an immediate semester
                    //because there is a deeper layer of prerecs
                    if(preRecsToCheck.get(j).equals(checkForPreRecs.get(k).getCourseName())){
                        nonImmediatePreRecs = true;
                        break;
                    }
                }
                //once you know there's non-immediate prerecs, you don't have to keep searching
                if(nonImmediatePreRecs){
                    break;
                }
            }
            //place the current course you're checking in the correct list
            if(nonImmediatePreRecs){
                morePreRecs.add(checking);
            }
            else{
                onlyImmediatePreRecs.add(checking);
            }
        }
        //return the list called for
        if(wantOnlyImmediatePreRecs){
            return onlyImmediatePreRecs;
        }
        else{
            return morePreRecs;
        }
    }

    public int prerequisiteChainDepth(ArrayList<ArrayList<DegClass>> semestersAway, DegClass passedCourse){
        ArrayList<String> coursesInQuestion = new ArrayList<String>();
        coursesInQuestion.add(passedCourse.getCourseName());
        int minimumSemestersLeft = semestersAway.size();
        int priorityModifier = 0;
        boolean notTheEndOfChain = false;
        ArrayList<ArrayList<String>> distanceFromStart = new ArrayList<ArrayList<String>>();
        distanceFromStart.add(coursesInQuestion);

        for(int d = 0; d < distanceFromStart.size(); d++) {
            //for each class branching off of the root class
            coursesInQuestion = distanceFromStart.get(d);
            for (int i = 0; i < coursesInQuestion.size(); i++) {
                ArrayList<String> newCoursesInQuestion = new ArrayList<String>();
                //go through all of the semesters after it
                for (int j = 1; j < minimumSemestersLeft; j++) {
                    ArrayList<DegClass> currentSemesterConsidering = semestersAway.get(j);
                    //look through each class in those future semesters
                    for (int k = 0; k < currentSemesterConsidering.size(); k++) {
                        ArrayList<String> preRecsToConsider = currentSemesterConsidering.get(k).getPreRecs();
                        int numPreRecsToConsider = preRecsToConsider.size();
                        //look through all of the prerequisites of the next semesters
                        for (int l = 0; l < numPreRecsToConsider; l++) {
                            if (preRecsToConsider.get(l).equals(coursesInQuestion.get(i))) {
                                notTheEndOfChain = true;
                                newCoursesInQuestion.add(currentSemesterConsidering.get(k).getCourseName());
                                break;
                            }
                        }
                    }
                }
                distanceFromStart.add(newCoursesInQuestion);
            }
            if (notTheEndOfChain) {
                priorityModifier++;
            }
            notTheEndOfChain = false;
        }

        return priorityModifier;
    }


}
