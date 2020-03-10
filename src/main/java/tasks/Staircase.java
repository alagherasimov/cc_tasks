/**
 * 2. Drawing a staircase structure - given the required height,
 * output a staircase as shown in the example
 * Given n = 6, the output should be:
 * #
 * ##
 * ###
 * ####
 * #####
 * ######
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.assertMessage;
import static utils.StringConstants.wrongInputMessage;

public class Staircase implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(p -> drawStaircase(p));

        writeResults(inputFileName, outputMessages);
    }

    public void drawStaircase(String staircaseHeight) {
        List<String> assertMessages = validateInputLine(staircaseHeight);

        if (assertMessages.size() == 0) {
            String output = "";

            for (int i = 0; i < Integer.parseInt(staircaseHeight); i++) {
                for (int j = 0; j <= i; j++) {
                    output = output.concat("#");
                }
                output = output.concat("\n");
            }

            outputMessages.add(String.format("For n = [%s], the output is:\n%s", staircaseHeight, output));
        } else {
            outputMessages.add(String.format(wrongInputMessage, staircaseHeight));
            for (String errorMessage : assertMessages) {
                outputMessages.add(String.format(assertMessage, staircaseHeight, errorMessage));
            }
        }
    }

    public List<String> validateInputLine(String staircaseHeight) {
        List<String> assertMessages = new ArrayList<>();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(isStringInteger(staircaseHeight))
                .as("Staircase height is an integer!")
                .isTrue();

        softAssertions.assertThat(Integer.parseInt(staircaseHeight))
                .as("Staircase height is greater than or equals to 2!")
                .isGreaterThanOrEqualTo(2);

        return collectErrorMessages(softAssertions, assertMessages);
    }

}
