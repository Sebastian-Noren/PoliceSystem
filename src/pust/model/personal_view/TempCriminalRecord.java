package pust.model.personal_view;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TempCriminalRecord {
    private String convictionDate;
    private String criminalCase;
    private String crime;
    private String crimeDate;
    private String crimeStreet;
    private String crimeCity;
    private String term;

    public TempCriminalRecord(String convictionDate, String criminalCase, String crime, String crimeDate, String crimeStreet, String crimeCity, String term) {
        this.convictionDate = convictionDate;
        this.criminalCase = criminalCase;
        this.crime = crime;
        this.crimeDate = crimeDate;
        this.crimeStreet = crimeStreet;
        this.crimeCity = crimeCity;
        this.term = term;
    }

    public String getConvictionDate() {
        return convictionDate;
    }

    public void setConvictionDate(String convictionDate) {
        this.convictionDate = convictionDate;
    }

    public String getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(String criminalCase) {
        this.criminalCase = criminalCase;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getCrimeDate() {
        return crimeDate;
    }

    public void setCrimeDate(String crimeDate) {
        this.crimeDate = crimeDate;
    }

    public String getCrimeStreet() {
        return crimeStreet;
    }

    public void setCrimeStreet(String crimeStreet) {
        this.crimeStreet = crimeStreet;
    }

    public String getCrimeCity() {
        return crimeCity;
    }

    public void setCrimeCity(String crimeCity) {
        this.crimeCity = crimeCity;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Section:\t1\n" +
                "Conviction:\t"+convictionDate+",  "+criminalCase.toUpperCase()+"\n" +
                "Crime:\t"+crime.toUpperCase()+", Date of crime: "+crimeDate+", Place of crime: "+crimeStreet+", "+crimeCity+"\n" +
                "Punishment:\t"+term.toUpperCase()+".\n"+
                "-------------------------------------------------------------------------------------------------------------------------------------------------------------\n";

    }
}