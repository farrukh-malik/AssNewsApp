package com.example.farrukhmalik.aasnewsapp.MyCustomClass;

/**
 * Created by Farrukh Malik on 01/06/2017.
 */

public class UserPersonalRecords {




    String key, uid, firstName, lastName, bloodGroup, email;

    public UserPersonalRecords() {
    }

    public UserPersonalRecords(String key, String uid, String firstName, String lastName, String email ) {
        this.key = key;
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public String getKey() {
        return key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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


}

