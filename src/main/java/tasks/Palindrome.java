/**
 * 1. Given a string of characters, determine if it is a palindrome or not
 * https://en.wikipedia.org/wiki/Palindrome
 * ABABA - palindrome
 * BABA - not palindrome
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.StringConstants.*;

public class Palindrome implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::checkForPalinfrome);

        writeResults(inputFileName, outputMessages);
    }

    private void checkForPalinfrome(String input) {
        List<Character> charactersFromInput = new ArrayList<>();
        String errorMessage = validateInputLine(input);

        if (errorMessage.equals(emptyString)) {
            String formattedInput = input.toLowerCase().replaceAll("[^a-z]", "");
            int notPolidrome = 0;

            //adding each character from the string in a list
            for (char c : formattedInput.toCharArray()) {
                charactersFromInput.add(c);
            }

            int forward = 0;
            int backward = charactersFromInput.size() - 1;

            //checking if string is palindrome
            while (forward < backward) {
                if (charactersFromInput.get(forward++) != charactersFromInput.get(backward--)) {
                    notPolidrome = 1;
                    break;
                }
            }

            outputMessages.add(String.format(resultMessage, input, (notPolidrome < 1 ? "is a palindrome" : "is not a palindrome")));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input, errorMessage));
        }
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(line)
                .as(String.format("{%s} is empty!", line))
                .isNotEmpty();

        softAssertions.assertThat(line.toCharArray().length)
                .as(String.format("{%s} is not 3 characters long!", line))
                .isGreaterThan(2);

        softAssertions.assertThat(line)
                .as(String.format("{%s} should not contain digits!", line))
                .doesNotContainPattern("[0-9]");

        return collectErrorMessages(softAssertions);
    }

}
