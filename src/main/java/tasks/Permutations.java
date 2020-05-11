/**
 * 19
 * Given an integer n, generate permutations of n elements - note that the
 * amount of permutations of n elements is n!
 * https://en.wikipedia.org/wiki/Permutation
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Permutations implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::generatePermutations);

        writeResults(inputFileName, outputMessages);
    }

    private void generatePermutations(String input) {
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        return collectErrorMessages(softAssertions);
    }
}