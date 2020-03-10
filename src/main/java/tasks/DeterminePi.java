/**
 * 6. Given an integer n, determine the number pi
 * with an error less than n decimal places
 */
package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeterminePi implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
    }

    public List<String> validateInputLine(String line) {
        List<String> assertMessages = new ArrayList<>();
        SoftAssertions softAssertions = new SoftAssertions();
        return collectErrorMessages( softAssertions, assertMessages);
    }
}
