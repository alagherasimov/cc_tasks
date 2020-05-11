/**
 * 16. If you start with $1 and, with each move, you can either double your money or add another $1,
 * what is the smallest number of moves you have to make to get to exactly n $
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static utils.StringConstants.*;

public class DoubleOrAddToGetAmount implements BaseTask {
    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::findNumberOfMoves);

        writeResults(inputFileName, outputMessages);
    }

    private void findNumberOfMoves(String input) {
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            int amount = Integer.parseInt(input);
            Map<String, Integer> results = new HashMap<>();

            //make all possible moves
            for (int iterator = 1; iterator <= (amount / 2); iterator++) {
                Map.Entry<String, Integer> entry = makeTheMove(iterator, amount);
                results.put(entry.getKey(), entry.getValue());
            }

            //sorting results by key
            Map<String, Integer> sortedMap = results.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            //output
            Map.Entry<String, Integer> entry = sortedMap.entrySet().iterator().next();
            outputMessages.add(String.format(resultMessage, input,
                    String.format("Number of moves needed is %s. Moves are: %s", entry.getValue(), entry.getKey())));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    private int multiplyByTwoMove(int numberToMultiply) {
        return numberToMultiply * 2;
    }

    private int addOneMove(int numberToAddTo) {
        return numberToAddTo + 1;
    }

    private Map.Entry<String, Integer> makeTheMove(int current, int neededResult) {
        int temp = 1;
        int moves = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (temp <= neededResult) {
            //to simulate n + 1 * 2, go inside of this while
            while (temp <= current) {
                if (multiplyByTwoMove(temp) <= current) {
                    stringBuilder.append(String.format("%s * 2| ", temp));
                    temp = multiplyByTwoMove(temp);
                    moves++;
                } else if (addOneMove(temp) <= current) {
                    stringBuilder.append(String.format("%s + 1| ", temp));
                    temp = addOneMove(temp);
                    moves++;
                }
                if (temp == current) break;
            }

            // this will multiply by 2 until it's possible and then will be adding 1
             if (multiplyByTwoMove(temp) <= neededResult) {
                stringBuilder.append(String.format("%s * 2| ", temp));
                temp = multiplyByTwoMove(temp);
                moves++;
            } else  if (addOneMove(temp) <= neededResult) {
                stringBuilder.append(String.format("%s + 1| ", temp));
                temp = addOneMove(temp);
                moves++;
            }
            if (temp == neededResult) break;
        }
        return new java.util.AbstractMap.SimpleEntry<String, Integer>(stringBuilder.toString(), moves);
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isStringInteger(line))
                .as(String.format("{%s} is not integer!", line))
                .isTrue();

        if (isStringInteger(line)) {
            softAssertions.assertThat(Integer.parseInt(line))
                    .as(String.format("{%s} is not greater than 1!", line))
                    .isGreaterThan(1);
        }
        return collectErrorMessages(softAssertions);
    }
}
