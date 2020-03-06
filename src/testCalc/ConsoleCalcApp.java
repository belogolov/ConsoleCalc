package testCalc;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class ConsoleCalcApp {

    public static void main(String[] args) {
        System.out.println("Калькулятор выполняет операции вида \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b со строками и целыми числами из интервала [1; 10].");
        ConsoleCalc calc = new ConsoleCalc();
        String outputString = "";
        String inputString;
        int indexOfOperation;
        String aStr;
        String bStr;
        String opStr;

        while (true) {
            inputString = getString();
            indexOfOperation = getIndexOfOperation(inputString);

            System.out.print("Output: ");
            if (indexOfOperation == -1) {
                System.out.println("Действие не определено");
                continue;
            } else if (indexOfOperation < 2) {
                System.out.println("Первая строка не определена");
                continue;
            } else {
                aStr = inputString.substring(0, indexOfOperation);
                while (aStr.endsWith(" ")) {
                    aStr = aStr.substring(0, aStr.length() - 1);
                }
                bStr = inputString.substring(indexOfOperation + 1);
                while (bStr.startsWith(" ")) {
                    bStr = bStr.substring(1);
                }
                opStr = inputString.substring(indexOfOperation, indexOfOperation + 1);

                calc.initCalc(aStr);
                outputString = calculate(calc, opStr, bStr);

                if (outputString.length() > 42) {
                    outputString = outputString.substring(0, 42) + "...\"";
                }
                if (!outputString.equals("")) {
                    System.out.println(outputString);
                }
            }
        }
    }

    private static String calculate(ConsoleCalc calc, String operation, String str) {
        String res = "";
        switch (operation) {
            case "+":
                res = calc.plus(str);
                break;
            case "-":
                res = calc.minus(str);
                break;
            case "*":
                res = calc.multiply(convertToInt(str));
                break;
            case "/":
                res = calc.div(convertToInt(str));
                break;
            default:
                break;
        }
        return res;
    }

    private static int getIndexOfOperation(String str) {
        int index = -1;
        index = str.indexOf("+");
        if (index == -1) {
            index = str.indexOf("-");
            if (index == -1) {
                index = str.indexOf("*");
                if (index == -1) {
                    index = str.indexOf("/");
                }
            }
        }
        return index;
    }

    private static String getString() {
        System.out.print("Input: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static int convertToInt(String s) {
        int out;
        try {
            out = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Не корректное число для операции");
            return -1;
        }
        return out;
    }

}
