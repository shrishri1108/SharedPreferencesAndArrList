package com.example.sharedpreferencesandarrlist;

public class ModelClass {

    String username,userAge;
    public ModelClass(String username, String userAge) {
        this.username = username;
        this.userAge = userAge;
    }

    public String getUsername() {
        return username;
    }

    public String getUserAge() {
        return userAge;
    }
}
