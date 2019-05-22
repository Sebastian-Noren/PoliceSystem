package pust.model.utility.random_generator.crime;

import pust.model.utility.random_generator.ISource;
import pust.model.utility.random_generator.ReadFile;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DescriptionSource implements ISource {
    private ArrayList<String> descriptions;

    private static final String DESCRIPTIONS_PATH = "src/main/resources/files/random_generator/crime/descriptions.txt";

    DescriptionSource() {
        descriptions = ReadFile.readFile(DESCRIPTIONS_PATH);
    }

    @Override
    public String random() {
        return descriptions.get(ThreadLocalRandom.current().nextInt(descriptions.size()));
    }

}
