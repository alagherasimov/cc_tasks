/**
 * Given an integer n, determine all prime numbers smaller than n
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class PrimeNumbers implements BaseTask {
    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::findAllPrimeNumbers);

        writeResults(inputFileName, outputMessages);
    }

    private void findAllPrimeNumbers(String input) {
        String errorMessage = validateInputLine(input);
        if (errorMessage.equals(emptyString)) {
            int inputN = Integer.parseInt(input);
            StringBuilder stringBuilder = new StringBuilder();

            for (int j = 2; j < inputN; j++) {
                int counter = 0;
                for (int i = j; i >= 1; i--) {
                    if (j % i == 0) {
                        counter++;
                    }
                }
                if (counter == 2) {
                    stringBuilder.append(String.format("%s ", j));
                }
            }
            outputMessages.add(String.format(resultingArrayMessage, input, stringBuilder.toString()));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    public String validateInputLine(String input) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(isStringInteger(input))
                .as(String.format("Input {%s} is not an integer!", input))
                .isTrue();

        softAssertions.assertThat(Integer.parseInt(input))
                .as(String.format("{%s} is not equal or greater than 2!", input))
                .isGreaterThanOrEqualTo(2);

        return collectErrorMessages(softAssertions);
    }
}
