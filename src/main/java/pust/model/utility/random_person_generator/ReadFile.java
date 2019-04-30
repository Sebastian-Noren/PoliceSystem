package pust.model.utility.random_person_generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ReadFile {

    static ArrayList<String> readFile(String path) {
        ArrayList<String> temp = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String tempString;
            while ((tempString = br.readLine()) != null) {
                temp.add(tempString);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        return temp;
    }
}
