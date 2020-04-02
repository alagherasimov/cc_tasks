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
            StringBuilder output = new StringBuilder();;
            int height = Integer.parseInt(staircaseHeight);

            for (int i = 0; i < height; i++) {

                for(int empty = 1; empty < height - i; empty++){
                    output.append(" ");
                }

                for (int j = (height - i); j <= height; j++) {
                    output.append("#");
                }
                output.append("\n");
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
