package utils;

public class NumericUtils {

    public boolean isStringFloat(String stringNumber) {
        try {
            Float.parseFloat(stringNumber);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public boolean isStringInteger(String stringNumber) {
        try {
            Integer.parseInt(stringNumber);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public String saveIntArrayAsString(int[] intArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int value : intArray) {
            stringBuilder.append(String.format("%d ", value));
        }
        return stringBuilder.toString();
    }

    public int[] parseStringToIntArray(String line) {
        String[] inputArray = line.split(" ", -1);
        int[] intArray = new int[inputArray.length];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt(inputArray[i]);
        }
        return intArray;
    }

    public int[][] parseStringToBidimensionalArrayOfIntegers(String line) {
        String rows[] = line.split("\n");
        int numberOfRows = rows.length;
        int numberOfColumns = rows[0].split(" ").length;
        int bidimensionalArray[][] = new int[numberOfRows][numberOfColumns];

        for (int row = 0; row < numberOfRows; row++) {
            String columnsFromRow[] = rows[row].split(" ");
            for (int column = 0; column < numberOfColumns; column++) {
                bidimensionalArray[row][column] = Integer.parseInt(columnsFromRow[column]);
            }
        }
        return bidimensionalArray;
    }

    public int[] parseStringWithSeveralLinesToArrayOfIntegers(String line){
        String initialString[] = line.replace("\n", " ").split(" ");
        int array[] = new int[initialString.length];

        for(int index = 0; index < initialString.length; index++){
            array[index] = Integer.parseInt(initialString[index]);
        }

        return array;
    }

}
