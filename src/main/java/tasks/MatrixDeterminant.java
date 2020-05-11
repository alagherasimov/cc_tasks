/**
 * 17.
 * Given a matrix of integers with size n*n, determine the determinant of the matrix
 * https://en.wikipedia.org/wiki/Determinant
 */

package tasks;

import org.assertj.core.api.SoftAssertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixDeterminant implements BaseTask {

    private List<String> outputMessages = new ArrayList<>();

    private String inputFileName = getInputFileName(getClass());

    public void resolveTask() throws IOException {
        List<String> input = readInput(inputFileName);

        input.forEach(this::determineDeterminant);

        writeResults(inputFileName, outputMessages);
    }

    private void determineDeterminant(String input){

    }

    public String validateInputLine(String line) {
        SoftAssertions softAssertions = new SoftAssertions();
        return collectErrorMessages(softAssertions);
    }
}
