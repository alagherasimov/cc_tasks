package utils;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class PalindromeTest {

    private static String className = Palindrome.class.getSimpleName();

    @BeforeClass
    public static void beforeClass(){
        Assert.assertTrue(String.format("[%s.txt] input file exists!", className.toLowerCase()),
                new File(Paths.get("src/main/resources/input/"+ className.toLowerCase() +".txt").toUri()).exists());
    }

    @Test
    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);
        SoftAssertions softAssertions = new SoftAssertions();

        input.forEach(s -> {
            softAssertions.assertThat(s).as(String.format("{%s} is not empty", s)).isNotEmpty();

            softAssertions.assertThat(s.toCharArray().length).as(String.format("{%s} is at least 3 characters long!", s)).isGreaterThan(2);

            softAssertions.assertThat(s).as(String.format("{%s} does not contain digits", s)).doesNotContainPattern("[0-9]");
        });

        softAssertions.assertAll();
    }
}