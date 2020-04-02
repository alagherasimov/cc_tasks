/**
 * Given a list of coins with different values (1, 3, 10, 25, 50), determine the minimum
 * amount of coins needed to sum up a certain amount of money
 * Given input n = 33, the output should be: 4 (due to 33 = 10+10+10+3)
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static utils.StringConstants.*;

public class MinimumAmountOfCoins implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::findMinimumAmountOfCoins);

        writeResults(inputFileName, outputMessages);
    }

    private void findMinimumAmountOfCoins(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int numberToBeDevided = Integer.parseInt(input);
            List<Integer> coinValues = new ArrayList<>();
            List<Integer> usedCoinValues = new ArrayList<>();
            Map<String, Integer> possibleDevisionList = new HashMap<>();
            //insert all coins value that were given in task condition
            coinValues.add(50);
            coinValues.add(25);
            coinValues.add(10);
            coinValues.add(3);
            coinValues.add(1);

            //look for all combinations and save them in map
            for (int index = 0; index < coinValues.size(); index++) {
                //if coin value is greater than input value - skip
                if (numberToBeDevided < coinValues.get(index)) {
                    usedCoinValues.add(coinValues.get(index));
                } else {
                    String coinCombination = devideByCoins(numberToBeDevided, coinValues, usedCoinValues);
                    possibleDevisionList.put(coinCombination, coinCombination.split(" ").length);
                    usedCoinValues.add(coinValues.get(index));
                }
            }

            //sorting results by key
            Map<String, Integer> sortedMap = possibleDevisionList.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            //output
            Map.Entry<String, Integer> entry = sortedMap.entrySet().iterator().next();
            String result = String.format("Minimum amount of coins needed is %s for combination of coins %s ", entry.getValue(), entry.getKey());
            outputMessages.add(String.format(resultMessage, input, result));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    private String devideByCoins(int numberToDevide, List<Integer> coinValues, List<Integer> usedCoinValues) {
        StringBuilder stringBuilder = new StringBuilder();
        while (numberToDevide > 0) {
            for (Integer coinValue : coinValues) {
                //if it was used - skip
                if (wasNotUsed(coinValue, usedCoinValues)) {
                    //subtract current coin value from input value as long as it is greater or equal to zero
                    do {
                        if (numberToDevide > 0 && numberToDevide >= coinValue) {
                            numberToDevide = numberToDevide - coinValue;
                            stringBuilder.append(coinValue + " ");
                        }
                    } while ((numberToDevide - coinValue) >= 0);
                }
            }
        }
        return stringBuilder.toString();
    }

    private boolean wasNotUsed(int numberToLookFor, List<Integer> usedCoinValues) {
        if (usedCoinValues.size() == 0) {
            return true;
        } else {
            for (int i = 0; i < usedCoinValues.size(); i++) {
                if (numberToLookFor == usedCoinValues.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(isStringInteger(line))
                .as(String.format("{%s} is not integer!", line))
                .isTrue();

        if (isStringInteger(line)) {
            softAssertions.assertThat(Integer.parseInt(line))
                    .as(String.format("{%s} is not greater than 0!", line))
                    .isGreaterThan(0);
        }

        return collectErrorMessages(softAssertions);
    }

}
