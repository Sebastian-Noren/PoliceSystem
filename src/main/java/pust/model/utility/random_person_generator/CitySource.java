package pust.model.utility.random_person_generator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CitySource implements ISource {

    private ArrayList<String> cachedCities;

    private static final String CITY_PATH = "src/main/resources/files/cities.txt";

    CitySource() {
        cachedCities = ReadFile.readFile(CITY_PATH);
    }

    @Override
    public String random() {
        return cachedCities.get(ThreadLocalRandom.current().nextInt(cachedCities.size()));
    }
}
