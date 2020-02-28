import tasks.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Palindrome palindrome = new Palindrome();
        palindrome.resolveTask();

        Staircase staircase = new Staircase();
        staircase.resolveTask();

        InverseFloatingArray inverseArray = new InverseFloatingArray();
        inverseArray.resolveTask();

        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.resolveTask();

        SumOddsAndCountEven sum = new SumOddsAndCountEven();
        sum.resolveTask();

    }

}
