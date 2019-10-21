import tasks.InverseFloatingArray;
import tasks.Palindrome;
import tasks.Staircase;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Palindrome palindrome = new Palindrome();
        palindrome.readInput();

        Staircase staircase = new Staircase();
        staircase.readInput();

        InverseFloatingArray inverseArray = new InverseFloatingArray();
        inverseArray.readInput();

    }

}
