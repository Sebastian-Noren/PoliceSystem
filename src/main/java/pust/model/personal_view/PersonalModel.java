package pust.model.personal_view;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class PersonalModel {
    PersonalDatabase perData = new PersonalDatabase();

    public TempPerson getPersonalInformation(String ssn) {
        String print = perData.getPerson(ssn);
        boolean wanted, missing;

        // lagrar varje line i array, splittar allt vid :
        String[] attr = print.split(":");
        // lagra allt i olika värden
        String personSSN = attr[0];
        String firstname = attr[1];
        String lastname = attr[2];
        String gender = attr[3];
        String streetAddres = attr[4];
        String zipcode = attr[5];
        String city = attr[6];
        String country = attr[7];
        String wantedtemp = attr[8];
        int temp = Integer.parseInt(wantedtemp);
        if (temp == 0) {
            wanted = false;
        } else {
            wanted = true;
        }
        String missingtemp = attr[9];
        int temp2 = Integer.parseInt(missingtemp);
        if (temp2 == 0) {
            missing = false;
        } else {
            missing = true;
        }
        //skicka tillbaka personen
        return new TempPerson(personSSN, firstname, lastname, gender, new TempAddres(streetAddres, zipcode, city, country), wanted, missing);
    }

    public ArrayList<TempCriminalRecord> getPersonalCrimeRecord(String ssn) {
        ArrayList<TempCriminalRecord> crimRecords = new ArrayList<>();
        ArrayList<String> records;
        records = perData.getCrimeRecord(ssn);
        for (String prepare : records) {
            String[] attr = prepare.split(":");
            String convictionDate = attr[0];
            String criminalCase = attr[1];
            String crimeType = attr[2];
            String crimeDate = attr[3];
            String streetAddres = attr[4];
            String city = attr[5];
            String term = attr[6];
            crimRecords.add(new TempCriminalRecord(convictionDate,criminalCase,crimeType,crimeDate,streetAddres,city,term));
        }
        System.out.println("leaving");
        return crimRecords;
    }
}
