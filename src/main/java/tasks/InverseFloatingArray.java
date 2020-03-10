/**
 * 3. Given an array of floating point numbers, inverse the elements of the array
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class InverseFloatingArray implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::inversArray);

        writeResults(inputFileName, outputMessages);
    }

    private void inversArray(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            String[] inputArray = input.split(" ", -1);
            float[] floatArray = new float[inputArray.length];
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < inputArray.length; i++) {
                int index = (inputArray.length - (i + 1));
                floatArray[index] = Float.parseFloat(inputArray[i]);
                stringBuilder.insert(0, String.format("%s ", floatArray[index]));
            }

            outputMessages.add(String.format(resultingArrayMessage, input, stringBuilder.toString()));
        } else {
                outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();

        String[] inputArray = line.split(" ", -1);

        for (int i = 0; i < inputArray.length; i++) {
            softAssertions.assertThat(isStringFloat(inputArray[i]))
                    .as(String.format("{%s} is not a number with floating point!", inputArray[i]))
                    .isTrue();
        }

        softAssertions.assertThat(inputArray.length)
                .as("Array size is not equal or greater than 2!")
                .isGreaterThanOrEqualTo(2);

        return collectErrorMessages(softAssertions);
    }


}
