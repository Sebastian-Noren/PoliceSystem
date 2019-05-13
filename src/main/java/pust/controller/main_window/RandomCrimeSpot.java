package pust.controller.main_window;

public class RandomCrimeSpot {

    public MarkerInformation[] crimeMark = new MarkerInformation[6];


    public RandomCrimeSpot() {

        MarkerInformation mark = new MarkerInformation(" Causing or attempting to cause serious bodily harm to another",
                "Aggravated Assault:", "56.031200,14.154950");
        crimeMark[0] = mark;

        MarkerInformation mark1 = new MarkerInformation("Bla bla bla bla bla next Text", "Bla bla", "56.046864,14.146155");
        crimeMark[1] = mark1;


    }

    public MarkerInformation[] getCrimeMark() {
        return crimeMark;
    }


}
