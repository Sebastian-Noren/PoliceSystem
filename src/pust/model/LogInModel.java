package pust.model;

public class LogInModel {

    /*
     * FIXME The name of the class could be more descriptive
     */


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

    public void returner() {


    }
}
