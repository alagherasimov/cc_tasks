/**
 * Write a program that will rotate a two-dimensional array clockwise
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class RotateBidimensionalArrayClockwise implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::rotateArray);

        writeResults(inputFileName, outputMessages);
    }

    private void rotateArray(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int square[][] = parseStringToBidimensionalArrayOfIntegers(input);
            int rows = square.length;
            int columns = square[0].length;
            int arrayToBePopulated[][] = new int[columns][rows];

            for (int columnIndex = 0, rowRotatedIndex = 0; columnIndex < columns && rowRotatedIndex < columns; columnIndex++, rowRotatedIndex++) {
                for (int rowIndex = rows - 1, columnRotatedIndex = 0; rowIndex >= 0 && columnRotatedIndex < rows; rowIndex--, columnRotatedIndex++) {
                    arrayToBePopulated[rowRotatedIndex][columnRotatedIndex] = square[rowIndex][columnIndex];
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    stringBuilder.append(arrayToBePopulated[i][j] + " ");
                }
                stringBuilder.append("\n");
            }

            outputMessages.add(String.format(bidimensionalArrayResultMessage, input.substring(0, input.toCharArray().length - 1), stringBuilder.toString()));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input.substring(0, input.toCharArray().length - 1), errorMessage));
        }
    }

    public String validateInputLine(String input) {
        SoftAssertions softAssertions = new SoftAssertions();
        String square[] = input.split("\n");
        int rows = square.length;
        int elemntsInFirstRow = square[0].split(" ").length;

        for (int i = 0; i < rows; i++) {
            int elemntsInRow = square[i].split(" ").length;

            softAssertions.assertThat(elemntsInRow)
                    .as("Number of elements in rows does not match, row index : %s!", i)
                    .isEqualTo(elemntsInFirstRow);

            String[] rowElements = square[i].split(" ");
            for (int j = 0; j < elemntsInRow; j++) {
                softAssertions.assertThat(isStringInteger(rowElements[j]))
                        .as(String.format("{%s} is not an integer!", rowElements[j]))
                        .isTrue();
            }
        }

        return collectErrorMessages(softAssertions);
    }

}