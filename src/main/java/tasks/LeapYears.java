/**
 * 10. Write a program that outputs the next k leap years
 * https://en.wikipedia.org/wiki/Leap_year
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class LeapYears implements BaseTask {
    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::outputNextLeapYears);

        writeResults(inputFileName, outputMessages);
    }

    private void outputNextLeapYears(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int outputNext = Integer.parseInt(input);
            List<Integer> nextLeapYears = new ArrayList<>();
            StringBuilder leapYears = new StringBuilder();
            int year = LocalDate.now().getYear() + 1;

            while (nextLeapYears.size() < outputNext) {
                boolean isLeap;
                if ((year % 4) != 0) {
                    isLeap = false;
                } else if ((year % 100) != 0) {
                    isLeap = true;
                } else if ((year % 400) != 0) {
                    isLeap = false;
                } else {
                    isLeap = true;
                }

                if (isLeap) {
                    nextLeapYears.add(year);
                    if (nextLeapYears.size() == outputNext) {
                        leapYears.append(year).append(" ");
                    } else {
                        leapYears.append(year).append(", ");
                    }
                }

                year++;
            }
            outputMessages.add(String.format(resultMessage, input, leapYears.toString()));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(isStringInteger(line))
                .as(String.format("Input {%s} is not an integer!", line))
                .isTrue();

        softAssertions.assertThat(Integer.parseInt(line))
                .as(String.format("{%s} is not greater than 0!", line))
                .isGreaterThan(0);

        return collectErrorMessages(softAssertions);
    }
}
