/**
 * 5. Given an array of integers, determine the sum of odd numbers
 * and the amount of even numbers
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.emptyString;
import static utils.StringConstants.wrongInputMessage;

public class SumOddsAndCountEven implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::sumAndCount);

        writeResults(inputFileName, outputMessages);
    }

    public void sumAndCount(String line) {
        String errorMessage = validateInputLine(line);

        if (errorMessage.equals(emptyString)) {
            int[] intArray = parseStringToIntArray(line);
            int sumOfOdds = 0;
            int countEven = 0;

            for (int i = 0; i < intArray.length; i++) {
                if ((intArray[i] % 2) == 0) {
                    countEven++;
                } else {
                    sumOfOdds += intArray[i];
                }
            }
            outputMessages.add(String.format("[%s]: sum of odd numbers is %s and count of even number is %s",
                    line, sumOfOdds, countEven));
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
