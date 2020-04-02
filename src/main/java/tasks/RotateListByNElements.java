/**
 * 13 Write a function that rotates a list by k elements.
 * For example [1,2,3,4,5,6] rotated by two becomes [3,4,5,6,1,2].
 * Try solving this without creating a copy of the list
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class RotateListByNElements implements BaseTask {
    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::rotateList);

        writeResults(inputFileName, outputMessages);
    }

    private void rotateList(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            String[] inputStringDevision = input.split(delimeter);
            int[] inputArray = parseStringToIntArray(inputStringDevision[0]);
            int rotateArrayByN = Integer.parseInt(inputStringDevision[1]);

            for (int count = 0; count < rotateArrayByN; count++) {
                int index;
                int tempFirst = inputArray[0];

                for (index = 0; index < inputArray.length - 1; index++) {
                    inputArray[index] = inputArray[index + 1];
                }

                inputArray[index] = tempFirst;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int index = 0; index < inputArray.length; index++) {
                stringBuilder.append(inputArray[index] + " ");
            }

            outputMessages.add(String.format(resultMessage, input, stringBuilder.toString()));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        String[] inputStringDevision = line.split(delimeter);
        String[] inputArray = inputStringDevision[0].split(" ");
        int rotateArrayByN = Integer.parseInt(inputStringDevision[1]);

        for (String s : inputArray) {
            softAssertions.assertThat(isStringInteger(s))
                    .as(String.format("{%s} is not an integer!", s))
                    .isTrue();
        }

        softAssertions.assertThat(rotateArrayByN)
                .as(String.format("Rotation index {%s} is less than 0!", rotateArrayByN))
                .isGreaterThan(0);

        softAssertions.assertThat(rotateArrayByN)
                .as(String.format("Rotation index {%s} is greater than or equal to number of elements in the array!", rotateArrayByN))
                .isLessThan(inputArray.length);

        return collectErrorMessages(softAssertions);
    }
}
