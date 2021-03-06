package pust.controller.main_window;

public class RandomCrimeSpot {

    public MarkerInformation[] crimeMark = new MarkerInformation[6];


    public RandomCrimeSpot() {

        MarkerInformation mark = new MarkerInformation("Suspect causing or attempting to cause serious bodily harm to hotel staff",
                "Description: White male, 174 cm, early 30s, blond hair, no facial hair, suit", "56.031200,14.154950","Aggravated assault");
        crimeMark[0] = mark;

        MarkerInformation mark1 = new MarkerInformation("Suspects causing vandalism, spray painting walls at HKR and breaking windows",
                "Description: Multiple people, wearing masks, dressed in black", "56.046864,14.146155","Vandalism");
        crimeMark[1] = mark1;
        MarkerInformation mark2 = new MarkerInformation("suspect caught stealing clothes and is currently under citizen arrest",
                "Description: Black male 169 cm, early 20s, black hair, beard","56.027812,14.153537","Theft");
        crimeMark[2] = mark2;

    }

    public MarkerInformation[] getCrimeMark() {
        return crimeMark;
    }


}
