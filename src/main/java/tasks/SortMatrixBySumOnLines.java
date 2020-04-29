/**
 * 12. Given a matrix of integers with size n*n,
 * sort the lines of the matrix by the sum of the elements on those lines
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static utils.StringConstants.*;

public class SortMatrixBySumOnLines implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::mergeAndSort);

        writeResults(inputFileName, outputMessages);
    }

    private void mergeAndSort(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            String[] inputArray = input.split("\n");
            Map<String, Integer> mapOfLinesAndSum = new HashMap<>();
            int lineLenght = inputArray[0].split(" ").length;

            for (int mapIndex = 0; mapIndex < inputArray.length; mapIndex++) {
                int elementSum = 0;
                int[] lineArray = parseStringToIntArray(inputArray[mapIndex]);

                for (int lineIndex = 0; lineIndex < lineLenght; lineIndex++) {
                    elementSum = elementSum + lineArray[lineIndex];
                }
                mapOfLinesAndSum.put(inputArray[mapIndex], elementSum);
            }

            Map<String, Integer> sortedMap = mapOfLinesAndSum.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<String, Integer> sortedEntry : sortedMap.entrySet()) {
                stringBuilder.append(sortedEntry.getKey() + "\n");
            }

            outputMessages.add(String.format(bidimensionalArrayResultMessage, input.substring(0, input.toCharArray().length - 1), stringBuilder.toString()));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input.substring(0, input.toCharArray().length - 1), errorMessage));
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        String[] lines = line.split("\n");

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String[] row = lines[lineIndex].split(" ");

            softAssertions.assertThat(row.length)
                    .as("Number of rows and columns does not match!")
                    .isEqualTo(lines.length);

            for (int rowIndex = 0; rowIndex < row.length; rowIndex++) {
                softAssertions.assertThat(isStringInteger(row[rowIndex]))
                        .as(String.format("{%s} is not an integer!", row[rowIndex]))
                        .isTrue();
            }
        }

        return collectErrorMessages(softAssertions);
    }
}
