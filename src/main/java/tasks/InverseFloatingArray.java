package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static tasks.StringStore.assertMessage;
import static tasks.StringStore.wrongInputMessage;

public class InverseFloatingArray implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::inversArray);

        writeResults(inputFileName, outputMessages);
    }

    private void inversArray(String input) {
        List<String> assertMessages = validateInputLine(input);

        if (assertMessages.size() == 0) {
            String[] inputArray = input.split(" ", -1);
            float[] floatArray = new float[inputArray.length];
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < inputArray.length; i++) {
                int index = (inputArray.length - (i + 1));
                floatArray[index] = Float.parseFloat(inputArray[i]);
                stringBuilder.insert(0, String.format("%s ", floatArray[index]));
            }

            outputMessages.add(String.format("Input array: %s\nResulting array: %s", input, stringBuilder.toString()));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input));
            for (String errorMessage : assertMessages) {
                outputMessages.add(String.format(assertMessage, input, errorMessage));
            }
        }
    }

    public List<String> validateInputLine(String line) {
        List<String> assertMessages = new ArrayList<>();
        SoftAssertions softAssertions = new SoftAssertions();

        String[] inputArray = line.split(" ", -1);

        for (int i = 0; i < inputArray.length; i++) {
            softAssertions.assertThat(isStringFloat(inputArray[i]))
                    .as(String.format("{%s} is a number with floating point", inputArray[i]))
                    .isTrue();
        }

        softAssertions.assertThat(inputArray.length)
                .as("Array size is equal or greater than 2!")
                .isGreaterThanOrEqualTo(2);

        return collectErrorMessages(softAssertions, assertMessages);
    }


}
