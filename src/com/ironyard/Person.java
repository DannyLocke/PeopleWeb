package com.ironyard;

/**
 * Created by dlocke on 12/11/16.
 */
public class Person {
    //variables
    String id;
    String firstName;
    String lastName;
    String email;
    String country;
    String ipAddress;

    //constructor
    public Person(String id, String firstName, String lastName, String email, String country, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ipAddress = ipAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpAdress() {
        return ipAddress;
    }

    public void setIpAdress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}//end class Person
