package com.example.comettrail;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class DatabaseParse {

    public static Degree parseDatabase(String filename) throws IOException {

        File classFile = new File(filename);
        Scanner inFile = new Scanner(classFile);

        Degree degreePlan = new Degree();

        String name = inFile.nextLine();
        degreePlan.name = name;

        while (inFile.hasNext()) {

            String line = inFile.nextLine();
            DegClass majClass = majorClass(line);
            degreePlan.addMajorClass(majClass);
        }


        inFile.close();

        return degreePlan;
    }

    public static DegClass majorClass(String line) {

        String[] splitClasses = line.split(" ");
        DegClass majorClass = new DegClass();

        //Parse the main major class
        majorClass.parseClass(splitClasses[0]);

        //Parse the req/pre-req/co-req
        for (int i = 1; i < splitClasses.length; i++) {

            majorClass.parseReq(splitClasses[i]);
        }


        return majorClass;
    }
}
