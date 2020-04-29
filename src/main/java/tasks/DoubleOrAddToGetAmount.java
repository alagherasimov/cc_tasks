/**
 * 16. If you start with $1 and, with each move, you can either double your money or add another $1,
 * what is the smallest number of moves you have to make to get to exactly n $
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class DoubleOrAddToGetAmount implements BaseTask {
    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::findNumberOfMoves);

        writeResults(inputFileName, outputMessages);
    }

    private void findNumberOfMoves(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int amout = Integer.parseInt(input);
            int temp = 1;
            int count = 0;
            StringBuilder stringBuilder = new StringBuilder();

            while (temp <= amout) {
                if ((temp * 2) <= amout) {
                    stringBuilder.append(String.format("%s * 2| ", temp));
                    temp = temp * 2;
                    count++;
                } else if ((temp + 1) <= amout) {
                    stringBuilder.append(String.format("%s + 1| ", temp));
                    temp++;
                    count++;
                }
                if (temp == amout) break;
            }

            outputMessages.add(String.format(resultMessage, input,
                    String.format("Number of moves needed is %s. Moves are: %s", count, stringBuilder)));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isStringInteger(line))
                .as(String.format("{%s} is not integer!", line))
                .isTrue();

        if (isStringInteger(line)) {
            softAssertions.assertThat(Integer.parseInt(line))
                    .as(String.format("{%s} is not greater than 1!", line))
                    .isGreaterThan(1);
        }
        return collectErrorMessages(softAssertions);
    }
}
