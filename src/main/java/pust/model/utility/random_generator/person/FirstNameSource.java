package pust.model.utility.random_generator.person;

import pust.model.utility.AppConstant;
import pust.model.utility.random_generator.ReadFile;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FirstNameSource implements IFirstNameSource {

    private ArrayList<String> cachedFemaleNames;
    private ArrayList<String> cachedMaleNames;

    private static final String FEMALE_PATH = "src/main/resources/files/random_generator/person/female_names.txt";
    private static final String MALE_PATH = "src/main/resources/files/random_generator/person/male_names.txt";

    FirstNameSource() {
        cachedFemaleNames = ReadFile.readFile(FEMALE_PATH);
        cachedMaleNames = ReadFile.readFile(MALE_PATH);
    }

    @Override
    public String random(int serialNumber) {
        if (AppConstant.isFemale(serialNumber)) {
            return cachedFemaleNames.get(ThreadLocalRandom.current().nextInt(cachedFemaleNames.size()));
        } else {
            return cachedMaleNames.get(ThreadLocalRandom.current().nextInt(cachedMaleNames.size()));
        }
    }

}
