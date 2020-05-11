/**
 * 11. Given three ascendingly sorted arrays, merge the elements of
 * these three arrays to obtain one ascendingly sorted array
 * Given following arrays:
 * 1 5 5
 * 2 6 7
 * 1 4 7 8
 * The output should be:
 * 1 1 2 4 5 5 6 7 7 8
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class MergeThreeArraysInOneSorted implements BaseTask {
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
            List<int[]> listOfInputArrays = parseStringsToListOfIntegerArrays(input);

            String tempResultForFirstTwoArraysMerged = mergeTwoSortedArrays(listOfInputArrays.get(0), listOfInputArrays.get(1));
            String result = mergeTwoSortedArrays(parseStringToIntArray(tempResultForFirstTwoArraysMerged), listOfInputArrays.get(2));

            outputMessages.add(String.format(resultingArrayMessage, input.substring(0, input.toCharArray().length - 1), result));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input.substring(0, input.toCharArray().length - 1), errorMessage));
        }

    }

    private String mergeTwoSortedArrays(int[] firstArray, int[] secondArray) {
        int firstIndex = 0;
        int secondIndex = 0;
        StringBuilder tempStringBuilder = new StringBuilder();

        while (firstIndex < firstArray.length && secondIndex < secondArray.length) {
            if (firstArray[firstIndex] < secondArray[secondIndex]) {
                tempStringBuilder.append(firstArray[firstIndex] + " ");
                firstIndex++;
            } else {
                tempStringBuilder.append(secondArray[secondIndex] + " ");
                secondIndex++;
            }
        }

        while (firstIndex < firstArray.length) {
            tempStringBuilder.append(firstArray[firstIndex] + " ");
            firstIndex++;
        }

        while (secondIndex < secondArray.length) {
            tempStringBuilder.append(secondArray[secondIndex] + " ");
            secondIndex++;
        }

        return tempStringBuilder.toString().substring(0, tempStringBuilder.lastIndexOf(" "));
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        int inputArray[] = parseStringWithSeveralLinesToArrayOfIntegers(line);
        List<int[]> listOfArrays = parseStringsToListOfIntegerArrays(line);
        int numberOfArrays = listOfArrays.size();

        for (int index = 0; index < numberOfArrays; index++) {
            String array = saveIntArrayAsString(listOfArrays.get(index));
            softAssertions.assertThat(listOfArrays.get(index).length)
                    .as("Array {%s} length is not greater or equal to 2!", array.substring(0, array.lastIndexOf(" ")))
                    .isGreaterThanOrEqualTo(2);

            boolean isSorted = true;
            int[] tempArray = listOfArrays.get(index);
            for (int elementIndex = 0; elementIndex < tempArray.length; elementIndex++) {
                if (elementIndex != (tempArray.length - 1)) {
                    if (tempArray[elementIndex + 1] < tempArray[elementIndex]) {
                        isSorted = false;
                        break;
                    }
                }
            }

            softAssertions.assertThat(isSorted)
                    .as(String.format("Array {%s} is not sorted!", array.substring(0, array.lastIndexOf(" "))))
                    .isTrue();

        }

        softAssertions.assertThat(numberOfArrays)
                .as(String.format("There should be 3 arrays for this task, not %s!", numberOfArrays))
                .isEqualTo(3);

        for (int i = 0; i < inputArray.length; i++) {
            softAssertions.assertThat(isStringInteger(String.valueOf(inputArray[i])))
                    .as(String.format("{%s} is not numeric!", inputArray[i]))
                    .isTrue();
        }

        return collectErrorMessages(softAssertions);
    }
}
