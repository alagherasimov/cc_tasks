package utils;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palindrome {

    private List<String> outputMessages = new ArrayList<>();

    private String className = Palindrome.class.getSimpleName();

    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);

        input.forEach(this::validatePalindrome);

        FileUtils.writeToTxt(className, outputMessages);
    }

    private void validatePalindrome(String inputPalindrome) {
        List<Character> charactersFromInput = new ArrayList<>();

        for (char c : inputPalindrome.toCharArray()) {
            charactersFromInput.add(c);
        }

        List<Character> reversedCharactersList = new ArrayList<>();

        for (int i = (charactersFromInput.size() - 1); i >= 0; i--) {
            reversedCharactersList.add(charactersFromInput.get(i));
        }

        int notPolidrome = 0;

        for (int i = 0; i < reversedCharactersList.size(); i++) {
            if (!reversedCharactersList.get(i).equals(charactersFromInput.get(i))) {
                notPolidrome = 1;
                break;
            }
        }
        outputMessages.add(inputPalindrome + (notPolidrome < 1 ? " : is a polidrome" : " : is not a polidrome"));
    }


}
