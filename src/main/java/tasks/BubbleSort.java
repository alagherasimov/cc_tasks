package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static tasks.StringStore.assertMessage;
import static tasks.StringStore.wrongInputMessage;

public class BubbleSort implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::sort);

        writeResults(inputFileName, outputMessages);
    }

    public void sort(String line) {
        List<String> assertMessages = validateInputLine(line);

        if (assertMessages.size() == 0) {
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
                outputMessages.add(String.format("Input array: %s\nSorted array: %s", line, saveIntArrayAsString(intArray)));
            }
        } else {
            outputMessages.add(String.format(wrongInputMessage, line));
            for (String errorMessage : assertMessages) {
                outputMessages.add(String.format(assertMessage, line, errorMessage));
            }
        }
    }

    public List<String> validateInputLine(String line) {
        List<String> assertMessages = new ArrayList<>();
        SoftAssertions softAssertions = new SoftAssertions();

        String[] inputArray = line.split(" ", -1);

        for (int i = 0; i < inputArray.length; i++) {
            softAssertions.assertThat(isStringInteger(inputArray[i]))
                    .as(String.format("{%s} is numeric", inputArray[i]))
                    .isTrue();
        }

        softAssertions.assertThat(inputArray.length)
                .as("Array size is equal or greater than 3!")
                .isGreaterThanOrEqualTo(3);

        return collectErrorMessages(softAssertions, assertMessages);
    }
}
