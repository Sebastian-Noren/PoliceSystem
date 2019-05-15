package pust.random_person_generator;

import org.junit.Test;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.Suspect;
import pust.model.enumerations.PersonType;
import pust.model.utility.random_person_generator.RandomPerson;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPersonTest {

    @Test
    public void devCreateRandomPerson() {
        ArrayList<Person> randomPersons = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 9; j++) {
                switch (j) {
                    case 1:
                        randomPersons.add(new RandomPerson(PersonType.ITADMINISTRATOR).generateRandomPerson());
                        break;
                    case 2:
                        randomPersons.add(new RandomPerson(PersonType.POLICE).generateRandomPerson());
                        break;
                    case 3:
                        randomPersons.add(new RandomPerson(PersonType.POLICECHIEF).generateRandomPerson());
                        break;
                    case 4:
                        randomPersons.add(new RandomPerson(PersonType.MISSINGPERSON).generateRandomPerson());
                        break;
                    case 5:
                        randomPersons.add(new RandomPerson(PersonType.SUSPECT).generateRandomPerson());
                        break;
                    case 6:
                        randomPersons.add(new RandomPerson(PersonType.NOTIFIER).generateRandomPerson());
                        break;
                    case 7:
                        randomPersons.add(new RandomPerson(PersonType.WITNESS).generateRandomPerson());
                        break;
                    case 8:
                        randomPersons.add(new RandomPerson(PersonType.VISITOR).generateRandomPerson());
                        break;

                }
            }
        }
        for (int i = 0; i < randomPersons.size(); i++) {
            Person person = randomPersons.get(i);
            System.out.println("\n---------------------------------");
            System.out.println(person.getClass().getSimpleName());
            System.out.println("---------------------------------");
            System.out.println("Personal number:    "
                    + person.getPersonalNumber().getPersonalNumber());
            System.out.println("First name:         "
                    + person.getFirstName());
            System.out.println("Surname:            "
                    + person.getSurname());
            System.out.println("Address:            "
                    + person.getAddress().getStreetName()
                    + " " + person.getAddress().getStreetNumber());
            System.out.println("Zip code:           "
                    + person.getAddress().getZipCode());
            System.out.println("City:               "
                    + person.getAddress().getCity());
            System.out.println("Country:            "
                    + person.getAddress().getCountry());
            if (person instanceof Employee) {
                System.out.println("ID:                 "
                        + ((Employee) person).getId());
                System.out.println("Title:              "
                        + ((Employee) person).getTitle());
                System.out.println("Salary:             "
                        + ((Employee) person).getSalary());
                System.out.println("User name:          "
                        + ((Employee) person).getUserName());
                System.out.println("Password:           "
                        + ((Employee) person).getPassword());
            } else if (person instanceof Suspect) {
                System.out.println("Gender:             "
                        + ((Suspect) person).getGender());
                System.out.println("Ethnicity:          "
                        + ((Suspect) person).getEthnicity());
                System.out.println("Hair color:         "
                        + ((Suspect) person).getHairColor());
                System.out.println("Eye color:          "
                        + ((Suspect) person).getEyeColor());
                System.out.println("Body type:          "
                        + ((Suspect) person).getBuild());
                System.out.println("Weight:             "
                        + ((Suspect) person).getWeight());
                System.out.println("Characteristics     "
                        + ((Suspect) person).getCharacteristic());
            }
            System.out.println("---------------------------------\n");

        }
    }

    @Test
    public void devCreateRandomItAdministrator() {
        for (int i = 0; i < 10; i++) {
            RandomPerson admin = new RandomPerson(PersonType.values()[ThreadLocalRandom.current().nextInt(PersonType.values().length)]);
            Person itAdmin = admin.generateRandomPerson();
            System.out.println("First name: " + itAdmin.getFirstName());
            System.out.println("Last name: " + itAdmin.getSurname());
            System.out.println("SSN: " + itAdmin.getPersonalNumber().getSerialNumber());
            System.out.println("Adress: " + itAdmin.getAddress().getCity());
        }


    }

}