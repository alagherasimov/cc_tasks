package tasks;

import org.assertj.core.api.SoftAssertions;
import utils.FileUtils;
import utils.NumericUtils;

import java.io.IOException;
import java.util.List;

import static utils.StringConstants.emptyString;

public interface BaseTask {

    FileUtils fileUtils = new FileUtils();

    NumericUtils numericUtils = new NumericUtils();

    void resolveTask() throws IOException;

    String validateInputLine(String inputLine);

    default String getInputFileName(Class obj) {
        return obj.getSimpleName();
    }

    default List<String> readInput(String className) throws IOException {
        return fileUtils.readFromTxt(className);
    }

    default void writeResults(String className, List<String> messages) throws IOException {
        fileUtils.writeToTxt(className, messages);
    }

    default String collectErrorMessages(SoftAssertions softAssertions) {
        String assertMessages = emptyString;
        for (Throwable errors : softAssertions.errorsCollected()) {
            assertMessages = assertMessages + errors.getMessage().split("\r\n")[0].replaceAll("[\\[\\]\"]", "");
        }
        return assertMessages;
    }

    default boolean isStringInteger(String input){
        return numericUtils.isStringInteger(input);
    }

    default boolean isStringFloat(String input){
        return numericUtils.isStringFloat(input);
    }

    default String saveIntArrayAsString(int[] intArray) {
        return numericUtils.saveIntArrayAsString(intArray);
    }

    default int[] parseStringToIntArray(String line){
        return  numericUtils.parseStringToIntArray(line);
    }

    default int[][] parseStringToBidimensionalArrayOfIntegers(String line){
        return numericUtils.parseStringToBidimensionalArrayOfIntegers(line);
    }

    default int[] parseStringWithSeveralLinesToArrayOfIntegers(String line){
        return numericUtils.parseStringWithSeveralLinesToArrayOfIntegers(line);
    }

    default List<int[]> parseStringsToListOfIntegerArrays(String line){
        return numericUtils.parseStringsToListOfIntegerArrays(line);
    }

}
