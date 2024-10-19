package myjava;

import java.util.Map;

class RomantoIntegerV3 {
    public int romanToInt(String s) {
        final var lettersValues = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000);

        int total = 0, currentLetterValue = 0, lastLetterValue = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            currentLetterValue = lettersValues.get(s.charAt(i));

            if (lastLetterValue < currentLetterValue) {
                total -= lastLetterValue * 2;
            }

            total += currentLetterValue;
            lastLetterValue = currentLetterValue;
        }

        return total;
    }
}