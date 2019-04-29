package pust.model.utility.random_person_generator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StreetSource implements ISource {

    private ArrayList<String> streets;

    private static final String STREET_PATH = "src/pust/random_person_generator/files/streets.txt";

    StreetSource() {
        streets = ReadFile.readFile(STREET_PATH);
    }

    @Override
    public String random() {
        return streets.get(ThreadLocalRandom.current().nextInt(streets.size()));
    }
}
