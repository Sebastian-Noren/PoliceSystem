package pust.model;

public class LogInModel {

    // this method takes the count of failed log in attempts and displays appropriate messsage
    public String passwordCounter(int wrongPass) {
        if (wrongPass > 0 && wrongPass < 3) {
            return "Incorrect username or password";
        } else if (wrongPass >= 3) {
            return "warning";
        } else {
            return "";
        }
    }

    public void returner() {

    }
}
