package pust.model.utility.random_generator.person;

import pust.model.utility.random_generator.ISource;
import pust.model.utility.random_generator.ReadFile;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SurnameSource implements ISource {

    private ArrayList<String> cachedSurnames;

    private static final String SURNAMES_PATH = "src/main/resources/files/random_generator/person/surnames.txt";

    SurnameSource() {
        cachedSurnames = ReadFile.readFile(SURNAMES_PATH);
    }

    @Override
    public String random() {
        return cachedSurnames.get(ThreadLocalRandom.current().nextInt(cachedSurnames.size()));
    }
}
