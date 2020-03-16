package testCalc;

import java.util.Scanner;

class Calculator {

    void run() {
        System.out.println("Калькулятор выполняет операции вида \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b со строками и целыми числами из интервала [1; 10].");

        String inputString;
        String outputString;

        while (true) {
            //получим входящую строку
            System.out.print("Input: ");
            inputString = getString();

            //парсим входящую строку. Получим массив из 3-х строк: 0-первое выражение, 1-действие, 2-второе выражение
            String[] expressions = getOperands(inputString);

            //вычисляем
            outputString = calculate(expressions[0], expressions[1], expressions[2]);

            //выводим результат
            System.out.print("Output: ");
            if (outputString.length() > 42) {
                outputString = outputString.substring(0, 42) + "...\"";
            }
            if (!outputString.equals("")) {
                System.out.println(outputString);
            }

        }
    }


    private String getString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private int getIndexOfOperation(String str) {
        int index;
        index = str.indexOf("*");
        if (index == -1) {
            index = str.indexOf("/");
            if (index == -1) {
                index = str.indexOf("+");
                if (index == -1) {
                    index = str.indexOf("-");
                }
            }
        }
        return index;
    }

    private String[] getOperands(String input) {
        String[] expressions = new String[3];
        String operand;
        int indexOfOperation = getIndexOfOperation(input);

        if (indexOfOperation == -1) {
            throw new RuntimeException("Действие не определено");
//        } else if (indexOfOperation < 2) {
//            throw new RuntimeException("Первый операнд (строка) не определен");
        } else {
            operand = input.substring(0, indexOfOperation);
            while (operand.endsWith(" ")) {
                operand = operand.substring(0, operand.length() - 1);
            }
            expressions[0] = operand;
            expressions[1] = input.substring(indexOfOperation, indexOfOperation + 1);
            operand = input.substring(indexOfOperation + 1);
            while (operand.startsWith(" ")) {
                operand = operand.substring(1);
            }
            expressions[2] = operand;
        }

        return expressions;
    }

    private String calculate(String strA, String operation, String strB) {
        String result = "";
        Controller controller = new Controller();

        switch (operation) {
            case "+":
                result = controller.plus(Converter.removeQuotationMarks(strA, "Первый операнд"),
                        Converter.removeQuotationMarks(strB, "Второй операнд"));
                break;
            case "-":
                result = controller.minus(Converter.removeQuotationMarks(strA, "Первый операнд"),
                        Converter.removeQuotationMarks(strB, "Второй операнд"));
                break;
            case "*":
                result = controller.multiply(Converter.removeQuotationMarks(strA, "Первый операнд"),
                        Converter.convertToInt(strB));
                break;
            case "/":
                result = controller.div(Converter.removeQuotationMarks(strA, "Первый операнд"),
                        Converter.convertToInt(strB));
                break;
            default:
                break;
        }
        return result;
    }
}
