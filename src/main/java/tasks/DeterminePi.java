/**
 * 6. Given an integer n, determine the number pi
 * with an error less than n decimal places
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.emptyString;
import static utils.StringConstants.wrongInputMessage;

public class DeterminePi implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::determinePiUntilNthDigit);

        writeResults(inputFileName, outputMessages);
    }

    private void determinePiUntilNthDigit(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            BigDecimal pi = BigDecimal.ZERO;
            StringBuilder printingPattern = new StringBuilder();
            int decimalX = 1;
            int inputN = Integer.parseInt(input);

            //forming the printing pattern
            printingPattern.append("0.");
            for(int z = 1; z <= inputN; z++){
                printingPattern.append("0");
            }

            for (int j = 0; j <= inputN; j++) {
                decimalX *= 10;
            }

            for (int i = decimalX; i > 0; i--) {
                pi = pi.add(BigDecimal.valueOf((Math.pow(-1, i + 1) / (2 * i - 1))));
                if (i == 1) pi = pi.multiply(BigDecimal.valueOf(4.0));
            }

            outputMessages.add(String.format("[%d]: Pi with an error less than %d digits is: %s", inputN, inputN, new DecimalFormat(printingPattern.toString()).format(pi)));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }

    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(isStringInteger(line))
                .as("Input number is not an integer!")
                .isTrue();

        softAssertions.assertThat(Integer.parseInt(line))
                .as("Input number is not greater than or equal to 1!")
                .isGreaterThanOrEqualTo(1);

        softAssertions.assertThat(Integer.parseInt(line))
                .as("Input number is greater than 15!")
                .isLessThanOrEqualTo(15);

        return collectErrorMessages(softAssertions);
    }
}
