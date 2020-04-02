/**
 * 11. Given three ascendingly sorted arrays, merge the elements of
 * these three arrays to obtain one ascendingly sorted array
 * Given following arrays:
 * 1 5 5
 * 2 6 7
 * 1 4 7 8
 * The output should be:
 * 1 1 2 4 5 5 6 7 7 8
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class MergeThreeArraysInOneSorted implements BaseTask {
    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::mergeAndSort);

        writeResults(inputFileName, outputMessages);
    }

    private void mergeAndSort(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int inputArray[] = parseStringWithSeveralLinesToArrayOfIntegers(input);
            int temp;

            for (int i = 0; i < inputArray.length - 1; i++) {
                for (int j = 0; j < (inputArray.length - i - 1); j++) {
                    if (inputArray[j] > inputArray[j + 1]) {
                        temp = inputArray[j + 1];
                        inputArray[j + 1] = inputArray[j];
                        inputArray[j] = temp;
                    }
                }
            }
            outputMessages.add(String.format(resultingArrayMessage, input.substring(0, input.toCharArray().length - 1), saveIntArrayAsString(inputArray)));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input.substring(0, input.toCharArray().length - 1), errorMessage));
        }

    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        int inputArray[] = parseStringWithSeveralLinesToArrayOfIntegers(line);

        softAssertions.assertThat(inputArray.length)
                .as("Arrays length should be greater than 3!")
                .isGreaterThan(3);

        for (int i = 0; i < inputArray.length; i++) {
            softAssertions.assertThat(isStringInteger(String.valueOf(inputArray[i])))
                    .as(String.format("{%s} is not numeric!", inputArray[i]))
                    .isTrue();
        }

        return collectErrorMessages(softAssertions);
    }
}
