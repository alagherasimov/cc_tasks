/**
 * 18
 * Given n people playing hide and seek and given number p that defines
 * the counting step, compute the last person remaining after n-1 steps
 * if the counting follows following rules: at 1st step the counting starts with 1st person
 * and continues throughout p-th person that is removed; counting then continues from the current
 * position over the remaining people.
 * Example:
 * Given n = 7 and p = 3, the following output should be produced: 4
 * The rationale is described by the list of intermediate list of people remaining after step 1, 2, …, n-1:
 * 1 2 3 4 5 6 7
 * 1 2 4 5 6 7
 * 1 2 4 5 7
 * 1 4 5 7
 * 1 4 5
 * 1 4
 * 4
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HideAndSeek implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::determineLastRemaining);

        writeResults(inputFileName, outputMessages);
    }

    private void determineLastRemaining(String input){

    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        return collectErrorMessages(softAssertions);
    }
}