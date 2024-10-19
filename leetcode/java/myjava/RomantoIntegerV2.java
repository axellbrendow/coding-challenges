package myjava;

class RomantoIntegerV2 {
    public int romanToInt(String s) {
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    total += 1;
                    break;
                case 'V':
                    total += 5;
                    if (i > 0 && s.charAt(i - 1) == 'I')
                        total -= 2;
                    break;
                case 'X':
                    total += 10;
                    if (i > 0 && s.charAt(i - 1) == 'I')
                        total -= 2;
                    break;
                case 'L':
                    total += 50;
                    if (i > 0 && s.charAt(i - 1) == 'X')
                        total -= 20;
                    break;
                case 'C':
                    total += 100;
                    if (i > 0 && s.charAt(i - 1) == 'X')
                        total -= 20;
                    break;
                case 'D':
                    total += 500;
                    if (i > 0 && s.charAt(i - 1) == 'C')
                        total -= 200;
                    break;
                case 'M':
                    total += 1000;
                    if (i > 0 && s.charAt(i - 1) == 'C')
                        total -= 200;
                    break;
            }
        }

        return total;
    }
}