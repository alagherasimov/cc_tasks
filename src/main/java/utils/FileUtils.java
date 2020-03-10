package utils;

import java.io.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class FileUtils {

    public List<String> readFromTxt(String className) throws IOException {
        File file = new File(Paths.get(StringConstants.taskInputPath + className.toLowerCase() + ".txt").toUri());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> inputStringList = new ArrayList<>();
        String line;

        //saving each line from input file into a list
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equalsIgnoreCase(startReadingMatrix)) {
                String tempLine = emptyString;
                while (!(line = bufferedReader.readLine()).equalsIgnoreCase(endReadingMatrix)) {
                    tempLine = tempLine + line + "\n";
                }
                inputStringList.add(tempLine);
            } else {
                inputStringList.add(line);
            }
        }

        bufferedReader.close();

        return inputStringList;
    }

    public void writeToTxt(String className, List<String> messages) throws IOException {
        File directory = new File(Paths.get(StringConstants.taskOutputPath).toUri());

        //create a folder if it doesn't exists
        directory.mkdirs();

        File file = new File(Paths.get(String.format("%s/%s_%s.txt", directory, className, LocalDate.now().toString())).toUri());

        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        //writing results in a txt file
        for (int i = 0; i < messages.size(); i++) {
            bufferedWriter.write(messages.get(i));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

}
