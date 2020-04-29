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
    MagicalSquare magicalSquareTask = new MagicalSquare();
    PrimeNumbers primeNumbersTask = new PrimeNumbers();
    FriendlyNumbers friendlyNumbersTask = new FriendlyNumbers();
    LeapYears leapYearsTask = new LeapYears();
    MergeThreeArraysInOneSorted mergeThreeArraysInOneSortedTask = new MergeThreeArraysInOneSorted();
    SortMatrixBySumOnLines sortMatrixBySumOnLinesTask = new SortMatrixBySumOnLines();
    RotateListByNElements rotateListByNElementsTask = new RotateListByNElements();
    MinimumAmountOfCoins minimumAmountOfCoinsTask = new MinimumAmountOfCoins();
    RotateBidimensionalArrayClockwise rotateBidimensionalArrayClockwiseTask = new RotateBidimensionalArrayClockwise();
    DoubleOrAddToGetAmount doubleOrAddToGetAmountTask = new DoubleOrAddToGetAmount();

    @Ignore
    @Test
    public void runAllTasks() throws IOException {
        palindromeTask.resolveTask();
        staircaseTask.resolveTask();
        inverseFloatingArrayTask.resolveTask();
        bubbleSortTask.resolveTask();
        sumOddsAndCountEvenTask.resolveTask();
        determinePiTask.resolveTask();
        magicalSquareTask.resolveTask();
        primeNumbersTask.resolveTask();
        friendlyNumbersTask.resolveTask();
        leapYearsTask.resolveTask();
        mergeThreeArraysInOneSortedTask.resolveTask();
        sortMatrixBySumOnLinesTask.resolveTask();
        rotateListByNElementsTask.resolveTask();
        minimumAmountOfCoinsTask.resolveTask();
        rotateBidimensionalArrayClockwiseTask.resolveTask();
        doubleOrAddToGetAmountTask.resolveTask();
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

    @Ignore
    @Test
    public void runMagicalSquareTask() throws IOException {
        magicalSquareTask.resolveTask();
    }

    @Ignore
    @Test
    public void runPrimeNumbersTask() throws IOException {
        primeNumbersTask.resolveTask();
    }

    @Ignore
    @Test
    public void runFriendlyNumbersTask() throws IOException {
        friendlyNumbersTask.resolveTask();
    }

    @Ignore
    @Test
    public void runLeapYearsTask() throws IOException {
        leapYearsTask.resolveTask();
    }

    @Ignore
    @Test
    public void runMergeThreeArraysInOneSortedTask() throws IOException {
        mergeThreeArraysInOneSortedTask.resolveTask();
    }

    @Ignore
    @Test
    public void runSortMatrixBySumOnLinesTaskTask() throws IOException {
        sortMatrixBySumOnLinesTask.resolveTask();
    }

    @Ignore
    @Test
    public void runRotateListByNElementsTask() throws IOException {
        rotateListByNElementsTask.resolveTask();
    }

    @Ignore
    @Test
    public void runMinimumAmountOfCoinsTask() throws IOException {
        minimumAmountOfCoinsTask.resolveTask();
    }

    @Ignore
    @Test
    public void runRotateBidimensionalArrayClockwiseTask() throws IOException {
        rotateBidimensionalArrayClockwiseTask.resolveTask();
    }

    @Ignore
    @Test
    public void runDoubleOrAddToGetAmountTask() throws IOException {
        doubleOrAddToGetAmountTask.resolveTask();
    }

}
