/**
 * 20
 * Choose a sequence of the numbers from 1 to n such that no two
 * adjacent entries are adjacent numbers
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindNotAdjacentNumbers implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::findNumbers);

        writeResults(inputFileName, outputMessages);
    }

    private void findNumbers(String input) {
    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        return collectErrorMessages(softAssertions);
    }
}