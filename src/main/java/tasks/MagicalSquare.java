/**
 * Write a program that will check if a two-dimensional arrays is magical square or not
 * https://en.wikipedia.org/wiki/Magic_square
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class MagicalSquare implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::determineIfMagicalSquare);

        writeResults(inputFileName, outputMessages);
    }

    public void determineIfMagicalSquare(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int square[][] = parseStringToBidimensionalArrayOfIntegers(input);
            int numberOfRows = square.length;
            int numberOfColumns = square[0].length;
            int isNotMagicalSquare = 0;

            if (numberOfColumns == numberOfRows) {
                double magicConstant = (numberOfRows * (Math.pow(numberOfRows, 2) + 1)) / 2;
                double diagonalSum = 0;
                for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
                    double rowSum = 0;
                    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                        //calculate sum on row
                        rowSum = rowSum + square[rowIndex][columnIndex];
                        //calculate sum on diagonal
                        if (rowIndex == columnIndex) {
                            diagonalSum = diagonalSum + square[rowIndex][columnIndex];
                        }
                    }
                    if (rowSum != magicConstant) isNotMagicalSquare++;
                }
                if (diagonalSum != magicConstant) isNotMagicalSquare++;

                //calculate sum on column
                for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                    double columnSum = 0;
                    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
                        columnSum = columnSum + square[rowIndex][columnIndex];
                    }
                    if (columnSum != magicConstant) isNotMagicalSquare++;
                }
            } else {
                isNotMagicalSquare++;
            }
            outputMessages.add(String.format(bidimensionalArrayResultMessage,
                    (isNotMagicalSquare == 0 ? "is a magical square" : "is not magical square"), input));
        } else {
            outputMessages.add(String.format(wrongArrayInputMessage, errorMessage, input));
        }

    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        int square[][] = parseStringToBidimensionalArrayOfIntegers(line);
        int numberOfRows = square.length;
        int numberOfColumns = square[0].length;

        softAssertions.assertThat(numberOfRows)
                .as(String.format("Number of rows {%s} is not equal to number of columns {%s}!", numberOfRows, numberOfColumns))
                .isEqualTo(numberOfColumns);

        softAssertions.assertThat(numberOfRows)
                .as(String.format("Number of rows {%s} is less than 3!", numberOfRows))
                .isGreaterThanOrEqualTo(3);

        softAssertions.assertThat(numberOfColumns)
                .as(String.format("Number of columns {%s} is less than 3!", numberOfColumns))
                .isGreaterThanOrEqualTo(3);

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                softAssertions.assertThat(square[row][column])
                        .as(String.format("{%s} is not greater than 0!", square[row][column]))
                        .isGreaterThan(0);
            }
        }
        return collectErrorMessages(softAssertions);
    }
}
