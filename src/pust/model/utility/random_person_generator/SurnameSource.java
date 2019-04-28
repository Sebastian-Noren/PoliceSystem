package pust.model.utility.random_person_generator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SurnameSource implements ISource {

    private ArrayList<String> cachedSurnames;

    private static final String SURNAMES_PATH = "src/pust/random_person_generator/files/surnames.txt";

    SurnameSource() {
        cachedSurnames = ReadFile.readFile(SURNAMES_PATH);
    }

    @Override
    public String random() {
        return cachedSurnames.get(ThreadLocalRandom.current().nextInt(cachedSurnames.size()));
    }
}
