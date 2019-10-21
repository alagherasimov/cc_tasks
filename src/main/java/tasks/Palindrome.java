package tasks;

import utils.FileUtils;

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
        String formattedInput = input.toLowerCase().replaceAll("[^a-z]", "");
        int notPolidrome = 0;

        //adding each character from the string in a list
        for (char c : formattedInput.toCharArray()) {
            charactersFromInput.add(c);
        }

        int forward = 0;
        int backward = charactersFromInput.size() - 1;

        //checking if string is palindrome
        while(forward < backward){
            if(charactersFromInput.get(forward++) != charactersFromInput.get(backward--)){
                notPolidrome = 1;
                break;
            }
        }

        outputMessages.add(String.format("[%s]: %s", input, (notPolidrome < 1 ? "is a palindrome" : "is not a palindrome")));
    }


}
