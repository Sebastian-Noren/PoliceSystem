package pust.model.utility.random_generator.crime;

import pust.model.utility.random_generator.ISource;
import pust.model.utility.random_generator.ReadFile;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TrackSource implements ISource {

    private ArrayList<String> tracks;

    private static final String TRACK_PATH = "src/main/resources/files/random_generator/crime/tracks.txt";

    TrackSource() {
        tracks = ReadFile.readFile(TRACK_PATH);
    }

    @Override
    public String random() {
        return tracks.get(ThreadLocalRandom.current().nextInt(tracks.size()));
    }
}
