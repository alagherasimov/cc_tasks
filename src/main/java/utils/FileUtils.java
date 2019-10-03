package utils;

import java.io.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> readFromTxt(String className) throws IOException {
        List<String> inputStringList = new ArrayList<>();
        String line;
        File file = new File(Paths.get("src/main/resources/input/"+ className.toLowerCase() +".txt").toUri());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //saving each line from input file into a list
        while((line = bufferedReader.readLine()) != null){
            inputStringList.add(line);
        }

        bufferedReader.close();

        return inputStringList;
    }


    public static void writeToTxt(String className, List<String> messages) throws IOException {
        File file = new File(Paths.get(String.format("src/main/resources/logs/%s_%s.txt", className, LocalDate.now().toString())).toUri());
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        //writting results in a txt file
        for(int i = 0; i < messages.size(); i++) {
            bufferedWriter.write(messages.get(i));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

    }

}
