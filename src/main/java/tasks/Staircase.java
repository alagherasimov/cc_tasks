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

import static utils.StringConstants.*;

public class Staircase implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(p -> drawStaircase(p));

        writeResults(inputFileName, outputMessages);
    }

    public void drawStaircase(String staircaseHeight) {
        String errorMessage = validateInputLine(staircaseHeight);

        if (errorMessage.equals(emptyString)) {
            String output = "";

            for (int i = 0; i < Integer.parseInt(staircaseHeight); i++) {
                for (int j = 0; j <= i; j++) {
                    output = output.concat("#");
                }
                output = output.concat("\n");
            }

            outputMessages.add(String.format("For n = [%s], the output is:\n%s", staircaseHeight, output));
        } else {
            outputMessages.add(String.format(wrongInputMessage, staircaseHeight, errorMessage));
        }
    }


    public String validateInputLine(String staircaseHeight) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(isStringInteger(staircaseHeight))
                .as("Staircase height is not an integer!")
                .isTrue();

        softAssertions.assertThat(Integer.parseInt(staircaseHeight))
                .as("Staircase height is not greater than or equals to 2!")
                .isGreaterThanOrEqualTo(2);

        return collectErrorMessages(softAssertions);
    }

}
