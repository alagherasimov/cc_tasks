package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static tasks.StringStore.assertMessage;
import static tasks.StringStore.wrongInputMessage;

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
        List<String> assertMessages = validateInputLine(input);

        if (assertMessages.size() == 0) {

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

            outputMessages.add(String.format("[%s]: %s", input, (notPolidrome < 1 ? "is a palindrome" : "is not a palindrome")));
        } else {
            outputMessages.add(String.format(wrongInputMessage, input));
            for (String errorMessage : assertMessages) {
                outputMessages.add(String.format(assertMessage, input, errorMessage));
            }
        }
    }

    public List<String> validateInputLine(String line) {

        List<String> assertMessages = new ArrayList<>();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(line)
                .as(String.format("{%s} is not empty!", line))
                .isNotEmpty();

        softAssertions.assertThat(line.toCharArray().length)
                .as(String.format("{%s} is at least 3 characters long!", line))
                .isGreaterThan(2);

        softAssertions.assertThat(line)
                .as(String.format("{%s} does not contain digits!", line))
                .doesNotContainPattern("[0-9]");

        return collectErrorMessages(softAssertions, assertMessages);
    }

}
