package pust.model.personal_view;

public class CriminalRecord {
    private String convictionDate;
    private String criminalCase;
    private String crime;
    private String crimeDate;
    private String crimeStreet;
    private String crimeCity;
    private String term;

    CriminalRecord(String convictionDate, String criminalCase, String crime, String crimeDate, String crimeStreet, String crimeCity, String term) {
        this.convictionDate = convictionDate;
        this.criminalCase = criminalCase;
        this.crime = crime;
        this.crimeDate = crimeDate;
        this.crimeStreet = crimeStreet;
        this.crimeCity = crimeCity;
        this.term = term;
    }

    @Override
    public String toString() {
        return "Conviction:\t" + convictionDate + ",  " + criminalCase.toUpperCase() + "\n" +
                "Crime:\t" + crime.toUpperCase() + ", Date of crime: " + crimeDate + ", Place of crime: " + crimeStreet + ", " + crimeCity + "\n" +
                "Punishment:\t" + term.toUpperCase() + ".\n" +
                "-------------------------------------------------------------------------------------------------------------------------------------------------------------\n";

    }
}