/**
 * 9. Given an integer n, determine all friendly number pairs smaller than n
 * https://en.wikipedia.org/wiki/Friendly_number
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.StringConstants.*;

public class FriendlyNumbers implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::determineFriendlyNumbers);

        writeResults(inputFileName, outputMessages);
    }

    private void determineFriendlyNumbers(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            StringBuilder abundantNumbers = new StringBuilder();
            int findAllFriendlyNumbersUntil = Integer.parseInt(input);
            Map<Integer, Float> numbersWithAbundanceIndex = new HashMap<>();
            Map<Integer, Float> usedKeys = new HashMap<>();

            //get abundance index for all numbers until input number
            for (int number = 1; number < findAllFriendlyNumbersUntil; number++) {
                numbersWithAbundanceIndex.put(number, getAbundanceIndex(number));
            }

            //find friendly numbers
            for (Map.Entry<Integer, Float> entry : numbersWithAbundanceIndex.entrySet()) {
                for (Map.Entry<Integer, Float> toBeComparedWith : numbersWithAbundanceIndex.entrySet()) {
                    if (!wasKeyIdentifiedAsFriendly(usedKeys, toBeComparedWith.getKey()) || usedKeys.size() == 0) {
                        if (!entry.getKey().equals(toBeComparedWith.getKey()) && entry.getValue().equals(toBeComparedWith.getValue())) {
                            abundantNumbers.append(String.format("(%s, %s) ", entry.getKey(), toBeComparedWith.getKey()));
                            usedKeys.put(entry.getKey(), entry.getValue());
                            usedKeys.put(toBeComparedWith.getKey(), toBeComparedWith.getValue());
                            break;
                        }
                    }
                }
            }

            if (abundantNumbers.toString().equals(emptyString)) {
                outputMessages.add(String.format(resultMessage, input, "Friendly numbers weren't found!"));
            } else {
                outputMessages.add(String.format(resultMessage, input, abundantNumbers.toString()));
            }
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    private float getAbundanceIndex(int abundantNumber) {
        int sumOfDivisors = 0;

        for (int divisor = 1; divisor <= abundantNumber; divisor++) {
            if ((abundantNumber % divisor) == 0) {
                sumOfDivisors += divisor;
            }
        }
        return ((float) sumOfDivisors / abundantNumber);
    }

    private boolean wasKeyIdentifiedAsFriendly(Map<Integer, Float> usedNumbers, int key) {
        boolean wasUsed = false;
        for (Map.Entry<Integer, Float> usedNumber : usedNumbers.entrySet()) {
            if (usedNumber.getKey().equals(key)) {
                wasUsed = true;
                break;
            }
        }

        return wasUsed;
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
