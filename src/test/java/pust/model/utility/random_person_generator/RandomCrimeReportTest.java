package pust.model.utility.random_person_generator;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class RandomCrimeReportTest {

    @Test
    public void randomRef() {
        for (int i = 0; i < 100; i++) {
            String sb = "I-" +
                    ThreadLocalRandom.current().nextInt(10, 100) +
                    "-" +
                    ThreadLocalRandom.current().nextInt(10000000, 100000000) +
                    "-" +
                    ThreadLocalRandom.current().nextInt(10, 100);
            System.out.println(sb);
        }
    }

}