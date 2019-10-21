package tasks;

import utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Staircase {

    private List<String> outputMessages = new ArrayList<>();

    private String className = Staircase.class.getSimpleName();

    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);

        input.forEach(p -> drawStaircase(Integer.parseInt(p)));

        FileUtils.writeToTxt(className, outputMessages);
    }

    private void drawStaircase(int staircaseHeight) {
        String output = "";

        for (int i = 0; i < staircaseHeight; i++) {
            for (int j = 0; j <= i; j++) {
                output = output.concat("#");
            }
            output = output.concat("\n");
        }

        outputMessages.add(String.format("For n = [%s], the output is:\n%s", staircaseHeight, output));
    }


}
