package com.ironyard;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    //array list to hold names and their data
    public static ArrayList<Person> peopleList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        //read csv file
        File f = new File("peopleRawData.csv");
        Scanner csvRead = new Scanner (f);

        //add each person from csv to array list
        while (csvRead.hasNext()){
            String person = csvRead.nextLine();
            String [] attributes = person.split("\\,");
            Person eachPerson = new Person (attributes [0], attributes [1], attributes [2], attributes [3], attributes [4], attributes[5]);
            peopleList.add(eachPerson);
        }//end while loop

        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {

                    String offsetString;
                    int offsetInt;
                    offsetString = request.queryParams("offset");
                    if(offsetString == null){
                        offsetString = "0";
                    }

                    offsetInt = Integer.parseInt(offsetString);
                    List<Person> offsetList;

                    offsetList = peopleList.subList(offsetInt, (offsetInt + 20));

                    HashMap list = new HashMap();
                    list.put("peopleRawData", offsetList);

                    list.put("nextOffset", (offsetInt + 20));
                    list.put("previousOffset", (offsetInt +- 20));
                    return new ModelAndView(list, "Home.html");
                }),
                new MustacheTemplateEngine()
        );

        Spark.get(
                "/person",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    Integer id = Integer.valueOf(request.queryParams("id"));
                    Person x = peopleList.get(id - 1);
                    m.put("person", id);
                    return new ModelAndView(x, "person.html");
                }),

                new MustacheTemplateEngine()

        );
    }
}
