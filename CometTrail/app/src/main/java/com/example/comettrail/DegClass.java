package com.example.comettrail;
import java.util.ArrayList;

public class DegClass {

    public ArrayList<String> prereq;
    public ArrayList<String> coreq;
    public ArrayList<String> req;
    public String majorCode;
    public String courseCode;
    public int creditHours;

    public DegClass() {
        prereq = null;
        coreq = null;
        req = null;
        majorCode = "";
        courseCode = "";
        creditHours = 0;
    }

    public void parseClass(String deg_class) {

        int idx = 0;
        //Parse majorCode
        while (deg_class.charAt(idx) != '.') {
            majorCode = majorCode + deg_class.charAt(idx);
            idx++;
        }

        //courseCode, creditHours
        courseCode = deg_class.substring(idx + 1);
        creditHours = (int) (courseCode.charAt(1) - '0');

    }

    public void parseReq(String deg_class) {

        char reqChar = deg_class.charAt(0);
        String temp_str = deg_class.substring(2);

        int idx = 2;
        while (deg_class.charAt(idx) != '.') {
            idx++;
        }

        String[] course = new String[2];
        course[0] = deg_class.substring(2, idx);
        course[1] = deg_class.substring(idx + 1);


        switch (reqChar) {

            case 'R':

                if (req == null) {
                    req = new ArrayList<String>();
                }

                req.add(course[0] + course[1]);
                break;

            case 'P':

                if (prereq == null) {
                    prereq = new ArrayList<String>();
                }

                prereq.add(course[0] + course[1]);
                break;

            case 'C':

                if (coreq == null) {
                    coreq = new ArrayList<String>();
                }

                coreq.add(course[0] + course[1]);
                break;
        }
    }

    public ArrayList<String> getPreReq() {

        if (prereq != null) {
            return prereq;
        }

        return null;
    }

    public ArrayList<String> getCoReq() {

        if (coreq != null) {
            return coreq;
        }

        return null;
    }

    public ArrayList<String> getReq() {

        if (req != null) {
            return req;
        }

        return null;
    }

    public String toString() {
        return majorCode + courseCode;
    }
}
