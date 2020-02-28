package tasks;

import org.assertj.core.api.SoftAssertions;
import utils.FileUtils;

import java.io.IOException;
import java.util.List;

public interface BaseTask {

    FileUtils fileUtils = new FileUtils();

    NumericUtils numericUtils = new NumericUtils();

    void resolveTask() throws IOException;

    List<String> validateInputLine(String inputLine);

    default String getInputFileName(Class obj) {
        return obj.getSimpleName();
    }

    default List<String> readInput(String className) throws IOException {
        return fileUtils.readFromTxt(className);
    }

    default void writeResults(String className, List<String> messages) throws IOException {
        fileUtils.writeToTxt(className, messages);
    }

    default List<String> collectErrorMessages(SoftAssertions softAssertions, List<String> assertMessagesList) {
        for (Throwable errors : softAssertions.errorsCollected()) {
            assertMessagesList.add(errors.getMessage().split("\r\n")[0].replaceAll("[\\[\\]\"]", ""));
        }
        return assertMessagesList;
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

}
