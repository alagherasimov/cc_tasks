import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import tasks.*;
import utils.TaskRunner;

import java.io.IOException;

@RunWith(TaskRunner.class)
public class Runner {
    Palindrome palindromeTask = new Palindrome();

    Staircase staircaseTask = new Staircase();

    InverseFloatingArray inverseFloatingArrayTask = new InverseFloatingArray();

    BubbleSort bubbleSortTask = new BubbleSort();

    SumOddsAndCountEven sumOddsAndCountEvenTask = new SumOddsAndCountEven();

    DeterminePi determinePiTask = new DeterminePi();

    @Ignore
    @Test
    public void runAllTasks() throws IOException {
        palindromeTask.resolveTask();
        staircaseTask.resolveTask();
        inverseFloatingArrayTask.resolveTask();
        bubbleSortTask.resolveTask();
        sumOddsAndCountEvenTask.resolveTask();
        determinePiTask.resolveTask();
    }

    @Ignore
    @Test
    public void runPalindromeTask() throws IOException {
        palindromeTask.resolveTask();
    }

    @Ignore
    @Test
    public void runStaircaseTask() throws IOException {
        staircaseTask.resolveTask();
    }

    @Ignore
    @Test
    public void runInverseFloatingArrayTask() throws IOException {
        inverseFloatingArrayTask.resolveTask();
    }

    @Ignore
    @Test
    public void runBubbleSort() throws IOException {
        bubbleSortTask.resolveTask();
    }

    @Ignore
    @Test
    public void runSumOddsAndCountEvenTask() throws IOException {
        sumOddsAndCountEvenTask.resolveTask();
    }

    @Ignore
    @Test
    public void runDeterminePiTask() throws IOException {
        determinePiTask.resolveTask();
    }

}
