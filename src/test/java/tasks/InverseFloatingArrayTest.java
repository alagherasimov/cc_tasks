package tasks;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class InverseFloatingArrayTest {

    private static String className = InverseFloatingArray.class.getSimpleName();

    @BeforeClass
    public static void beforeClass(){
        Assert.assertTrue(String.format("[%s.txt] input file exists!", className.toLowerCase()),
                new File(Paths.get("src/main/resources/input/"+ className.toLowerCase() +".txt").toUri()).exists());
    }

    @Test
    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);
        SoftAssertions softAssertions = new SoftAssertions();

        input.forEach(line -> {

            String[] inputArray = line.split(" ", -1);

            for(int i = 0; i < inputArray.length; i++){
                assertTrue(String.format("{%s} is a number with floating point", inputArray[i]), isFloat(inputArray[i]));
            }

            softAssertions.assertThat(inputArray.length).as("Array size is equal or greater than 2!").isGreaterThanOrEqualTo(2);
        });

        softAssertions.assertAll();
    }

    private static boolean isFloat(String stringNumber) {
        try {
            Float.parseFloat(stringNumber);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}