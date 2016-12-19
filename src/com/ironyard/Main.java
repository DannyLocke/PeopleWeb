package com.ironyard;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    //array list to hold names and their data
    public static ArrayList<Person> peopleList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        //read csv file
        File f = new File("peopleRawData.csv");
        Scanner fileScanner = new Scanner (f);

        //add each person from csv to array list
        while (fileScanner.hasNext()){
            String person = fileScanner.nextLine();
            String [] attributes = person.split("\\,");

            //check for 1st line (column headers)
            if(!"id".equals(attributes[0])){
                Person eachPerson = new Person (attributes [0], attributes [1], attributes [2], attributes [3], attributes [4], attributes[5]);
                peopleList.add(eachPerson);
            }

        }//end while loop

        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {

                    String offsetString = request.queryParams("offset");
                    int offsetInt = 0;

                    if(offsetString != null && !offsetString.isEmpty()){
                        offsetInt = Integer.parseInt(offsetString);
                    }

                    ArrayList<Person> offsetList = new ArrayList<>();

                    for (int i=0; i<20; i++){
                        offsetList.add(peopleList.get(offsetInt+i));
                    }

                    HashMap list = new HashMap();
                    list.put("peopleRawData", offsetList);

                    //PREVIOUS
                    if(offsetInt-20 >= 0){
                        list.put("previousOffset", offsetInt - 20);
                    }

                    //NEXT
                    if(offsetInt+20 < peopleList.size()){
                        list.put("nextOffset", offsetInt + 20);
                    }
                    return new ModelAndView(list, "Home.html");
                }),
                new MustacheTemplateEngine()
        );//end "/"

        Spark.get(
                "/person",
                ((request, response) -> {
                    HashMap peopleHashMap = new HashMap();
                    int id = Integer.valueOf(request.queryParams("id"));
                    Person x = peopleList.get(id - 1);
                    peopleHashMap.put("person", x);
                    return new ModelAndView(peopleHashMap, "Person.html");
                }),

                new MustacheTemplateEngine()

        );//end "/person"

    }//end main()

}//end class Main
