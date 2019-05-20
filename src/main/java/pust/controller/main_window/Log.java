package pust.controller.main_window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {

    public void saveToFile(String logText){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");



        try(FileWriter fileWriter = new FileWriter("pgisLog.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter)){

        printWriter.println(simpleDateFormat.format(calendar.getTime()) +": "+ logText);



        }catch (IOException ex){

        }
    }

    public void saveToFileDate(String logText){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");



        try(FileWriter fileWriter = new FileWriter("pgisLog.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter)){

            printWriter.println(simpleDateFormat.format(calendar.getTime()) +": "+ logText);

        }catch (IOException ex){

        }
    }




}
