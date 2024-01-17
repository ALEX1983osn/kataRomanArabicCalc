package kataAcademy;

import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter two numbers (Arabic or Roman): ");
        String expression = scn.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String exp) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean isRoman;
        String[] operands = exp.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("There must be two operands");
        operation = detectOperation(exp);
        if (operation == null) throw new Exception("Unsupported mathematical operation");

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {

            number1 = Roman.convertToArabian(operands[0]);
            number2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }

        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }

        else {
            throw new Exception("The numbers must be in the same format");
        }
        if (number1 > 10 || number2 > 10) {
            throw new Exception("The numbers must be from 1 to 10");
        }
        int arabic = calc(number1, number2, operation);
        if (isRoman) {

            if (arabic <= 0) {
                throw new Exception("The Roman number must be greater than zero");
            }

            result = Roman.convertToRoman(arabic);
        } else {

            result = String.valueOf(arabic);
        }

        return result;
    }

    static String detectOperation(String exp) {
        if (exp.contains("+")) return "+";
        else if (exp.contains("-")) return "-";
        else if (exp.contains("*")) return "*";
        else if (exp.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operation) {

        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}