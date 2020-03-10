/**
 * 4. Given an array of integers, implement the BubbleSort algorithm
 * for sorting the elements of the array
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class BubbleSort implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::sort);

        writeResults(inputFileName, outputMessages);
    }

    public void sort(String line) {
        String errorMessage = validateInputLine(line);

        if (errorMessage.equals(emptyString)) {
            int[] intArray = parseStringToIntArray(line);
            int temp;
            int isNotSorted = 0;

            for (int i = 0; i < intArray.length - 1; i++) {
                for (int j = 0; j < (intArray.length - i - 1); j++) {
                    if (intArray[j] > intArray[j + 1]) {
                        temp = intArray[j + 1];
                        intArray[j + 1] = intArray[j];
                        intArray[j] = temp;
                        isNotSorted++;
                    }

                }
                if (isNotSorted == 0) break;
            }
            if (isNotSorted == 0) {
                outputMessages.add(String.format("[%s]: Is already sorted!", line));
            } else {
                outputMessages.add(String.format(resultingArrayMessage, line, saveIntArrayAsString(intArray)));
            }
        } else {
            outputMessages.add(String.format(wrongInputMessage, line, errorMessage));
        }
    }


    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();

        String[] inputArray = line.split(" ", -1);

        for (int i = 0; i < inputArray.length; i++) {
            softAssertions.assertThat(isStringInteger(inputArray[i]))
                    .as(String.format("{%s} is not numeric!", inputArray[i]))
                    .isTrue();
        }

        softAssertions.assertThat(inputArray.length)
                .as("Array size is not equal or greater than 3!")
                .isGreaterThanOrEqualTo(3);

        return collectErrorMessages(softAssertions);
    }
}
