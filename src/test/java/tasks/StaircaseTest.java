package tasks;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tasks.Staircase;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class StaircaseTest {

    private static String className = Staircase.class.getSimpleName();

    @BeforeClass
    public static void beforeClass() {
        Assert.assertTrue(String.format("[%s.txt] input file exists!", className.toLowerCase()),
                new File(Paths.get("src/main/resources/input/" + className.toLowerCase() + ".txt").toUri()).exists());
    }

    @Test
    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);
        SoftAssertions softAssertions = new SoftAssertions();

        input.forEach(p -> {

            assertTrue(String.format("Staircase height {%s} is an integer!", p), isInteger(p));

            softAssertions.assertThat(Integer.parseInt(p)).as("Staircase height is greater than or equals to 2!").isGreaterThanOrEqualTo(2);
        });

        softAssertions.assertAll();

    }

    private static boolean isInteger(String stringNumber) {
        try {
            Integer.parseInt(stringNumber);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}