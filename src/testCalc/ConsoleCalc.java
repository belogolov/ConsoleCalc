package testCalc;

class ConsoleCalc implements Calculator {
    private String result;

    ConsoleCalc() {
        result = "";
    }

    void initCalc(String a) {
        result = a;
    }


    @Override
    public String plus(String b) {

        if (!isCorrectString(result, "Первая строка: ") || !isCorrectString(b, "Вторая строка: ")) {
            return "";
        }

        String a = removeQ(result);

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(a);
        sb.append(removeQ(b));
        sb.append("\"");
        result = sb.toString();
        return result;
    }

    @Override
    public String minus(String b) {

        if (!isCorrectString(result, "Первая строка: ") || !isCorrectString(b, "Вторая строка: ")) {
            return "";
        }

        String a = removeQ(result);
        String searchStr = removeQ(b);
        int index = a.indexOf(searchStr);

        if (index == -1) {
            return result;
        }

        String a1 = "";
        if (index != 0) {
            a1 = a.substring(0, index);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(a1);
        sb.append(a.substring(index + searchStr.length()));
        sb.append("\"");
        result = sb.toString();
        return result;
    }

    @Override
    public String multiply(int b) {

        if (!isCorrectString(result, "Первая строка: ") || !isCorrectNumber(b)) {
            return "";
        }

        String a = removeQ(result);

        if (b < 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (int i = 0; i < b; i++) {
            sb.append(a);
        }
        sb.append("\"");
        result = sb.toString();
        return result;
    }

    @Override
    public String div(int b) {

        if (!isCorrectString(result, "Первая строка: ") || !isCorrectNumber(b)) {
            return "";
        }

        String a = removeQ(result);

        if (b < 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        if (b > 0) {
            int len = a.length() / b;
            sb.append(a.substring(0, len));
        }
        sb.append("\"");
        result = sb.toString();
        return result;
    }


    private String removeQ(String s) {
        if (s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    private boolean isCorrectString(String s, String title) {
        if (s.charAt(0) != '"' || s.charAt(s.length() - 1) != '"') {
            System.out.println(title + "должна быть заключена в кавычки");
            return false;
        }
        if (s.length() > 12) {
            System.out.println(title+ "длина должна быть не более 10 символов");
            return false;
        }
        return true;
    }

    private boolean isCorrectNumber(int num) {
        if (num == -1) {
            return false;
        }
        if (num < 1 || num > 10) {
            System.out.println("Число не входит в интервал [1; 10]");
            return false;
        }
        return true;
    }

}
