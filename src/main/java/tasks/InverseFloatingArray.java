package tasks;

import utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InverseFloatingArray {

    private List<String> outputMessages = new ArrayList<>();

    private String className = InverseFloatingArray.class.getSimpleName();

    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);

        input.forEach(this::inverseArray);

        FileUtils.writeToTxt(className, outputMessages);
    }

    private void inverseArray(String input){
        String[] inputArray = input.split(" ", -1);
        float[] floatArray = new float[inputArray.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < inputArray.length; i++){
            int index = (inputArray.length - (i + 1));
            floatArray[index] = Float.parseFloat(inputArray[i]);
            stringBuilder.insert(0, String.format("%s ", floatArray[index]));
        }

        outputMessages.add(String.format("Input array: %s\nResulting array: %s", input, stringBuilder.toString()));
    }

}
