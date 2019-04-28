package pust.model.utility.random_person_generator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FirstNameSource implements IFirstNameSource {

    private ArrayList<String> cachedFemaleNames;
    private ArrayList<String> cachedMaleNames;

    private static final String FEMALE_PATH = "src/pust/random_person_generator/files/female_names.txt";
    private static final String MALE_PATH = "src/pust/random_person_generator/files/male_names.txt";

    FirstNameSource() {
        cachedFemaleNames = ReadFile.readFile(FEMALE_PATH);
        cachedMaleNames = ReadFile.readFile(MALE_PATH);
    }

    @Override
    public String random(int serialNumber) {
        if (isFemale(serialNumber)) {
            return cachedFemaleNames.get(ThreadLocalRandom.current().nextInt(cachedFemaleNames.size()));
        } else {
            return cachedMaleNames.get(ThreadLocalRandom.current().nextInt(cachedMaleNames.size()));
        }
    }

    static boolean isFemale(int serialNumber) {
        return serialNumber % 2 == 0;
    }
}
