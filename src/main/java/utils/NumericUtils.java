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
        for (int i = 0; i < intArray.length; i++) {
            stringBuilder.append(String.format("%d ", intArray[i]));
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

}
