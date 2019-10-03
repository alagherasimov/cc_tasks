package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Palindrome {

    private List<String> outputMessages = new ArrayList<>();

    private String className = Palindrome.class.getSimpleName();

    public void readInput() throws IOException {
        List<String> input = FileUtils.readFromTxt(className);

        input.forEach(this::validatePalindrome);

        FileUtils.writeToTxt(className, outputMessages);
    }

    private void validatePalindrome(String input) {
        List<Character> charactersFromInput = new ArrayList<>();
        List<Character> reversedCharactersList = new ArrayList<>();
        int notPolidrome = 0;
        String formattedInput = input.toLowerCase().replaceAll("[^a-z]", "");

        //adding each character from the string in a list
        for (char c : formattedInput.toCharArray()) {
            charactersFromInput.add(c);
        }

        //adding characters in a reversing order into a list
        for (int i = (charactersFromInput.size() - 1); i >= 0; i--) {
            reversedCharactersList.add(charactersFromInput.get(i));
        }

        //checking if string is palindrome
        for (int i = 0; i < reversedCharactersList.size(); i++) {
            if (!reversedCharactersList.get(i).equals(charactersFromInput.get(i))) {
                notPolidrome = 1;
                break;
            }
        }

        outputMessages.add(String.format("[%s]: %s", input, (notPolidrome < 1 ? "is a palindrome" : "is not a palindrome")));
    }


}
