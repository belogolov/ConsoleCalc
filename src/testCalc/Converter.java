package testCalc;

class Converter {
    //private String

    static int convertToInt(String s) {
        int out;
        try {
            out = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Не корректное число для операции");
        }
        return isCorrectNumber(out) ? out : 0;
    }

    static String removeQuotationMarks(String s, String title) {
        if (isCorrectString(s, title)) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    private static boolean isCorrectString(String s, String title) {
        if (s.charAt(0) != '"' || s.charAt(s.length() - 1) != '"') {
            throw new RuntimeException(title + " - должен быть заключен в кавычки");
        }
        if (s.length() > 12) {
            throw new RuntimeException(title+ " - длина должна быть не более 10 символов");
        }
        return true;
    }

    private static boolean isCorrectNumber(int num) {
        if (num < 1 || num > 10) {
            throw new RuntimeException("Число не входит в интервал [1; 10]");
        }
        return true;
    }

}
