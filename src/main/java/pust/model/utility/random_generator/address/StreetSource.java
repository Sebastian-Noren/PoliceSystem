package pust.model.utility.random_generator.address;

import pust.model.utility.random_generator.ISource;
import pust.model.utility.random_generator.ReadFile;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StreetSource implements ISource {

    private ArrayList<String> streets;

    private static final String STREET_PATH = "src/main/resources/files/random_generator/address/streets.txt";

    StreetSource() {
        streets = ReadFile.readFile(STREET_PATH);
    }

    @Override
    public String random() {
        return streets.get(ThreadLocalRandom.current().nextInt(streets.size()));
    }
}
