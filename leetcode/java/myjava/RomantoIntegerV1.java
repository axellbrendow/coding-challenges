package myjava;

class RomantoIntegerV1 {
    public int romanToInt(String s) {
        int total = 0;
        int cursor = 0;
        int remainingLength;

        while (cursor < s.length()) {
            remainingLength = s.length() - cursor;
            switch (s.charAt(cursor)) {
                case 'I':
                    if (remainingLength > 1) {
                        if (s.charAt(cursor + 1) == 'V') {
                            total += 4;
                            cursor += 2;
                        } else if (s.charAt(cursor + 1) == 'X') {
                            total += 9;
                            cursor += 2;
                        } else {
                            total += 1;
                            cursor += 1;
                        }
                    } else {
                        total += 1;
                        cursor += 1;
                    }
                    break;
                case 'V':
                    total += 5;
                    cursor += 1;
                    break;
                case 'X':
                    if (remainingLength > 1) {
                        if (s.charAt(cursor + 1) == 'L') {
                            total += 40;
                            cursor += 2;
                        } else if (s.charAt(cursor + 1) == 'C') {
                            total += 90;
                            cursor += 2;
                        } else {
                            total += 10;
                            cursor += 1;
                        }
                    } else {
                        total += 10;
                        cursor += 1;
                    }
                    break;
                case 'L':
                    total += 50;
                    cursor += 1;
                    break;
                case 'C':
                    if (remainingLength > 1) {
                        if (s.charAt(cursor + 1) == 'D') {
                            total += 400;
                            cursor += 2;
                        } else if (s.charAt(cursor + 1) == 'M') {
                            total += 900;
                            cursor += 2;
                        } else {
                            total += 100;
                            cursor += 1;
                        }
                    } else {
                        total += 100;
                        cursor += 1;
                    }
                    break;
                case 'D':
                    total += 500;
                    cursor += 1;
                    break;
                case 'M':
                    total += 1000;
                    cursor += 1;
                    break;
            }
        }

        return total;
    }
}