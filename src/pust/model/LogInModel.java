package pust.model;

import pust.controller.LogInController;

public class LogInModel {



    // this method takes the count of failed log in attempts and displays appropriate messsage
    public String passwordCounter(int wrongPass) {
        if (wrongPass > 0 && wrongPass < 3) {
            return "Incorrect username or password";
        } else if (wrongPass >= 3) {
            return "warning";
            //create method to lock out
        } else {
            return "";
        }
    }

    public void returner(){


    }
}
