package testCalc;

//реализация действий определенных для калькулятора
class Controller implements Actions {
    private String result;

    Controller() {
        result = "";
    }

    @Override
    public String plus(String a, String b) {

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(a);
        sb.append(b);
        sb.append("\"");
        result = sb.toString();
        return result;
    }

    @Override
    public String minus(String a, String b) {

        int index = a.indexOf(b);

        if (index == -1) {
            return a;
        }

        String a1 = "";
        if (index != 0) {
            a1 = a.substring(0, index);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(a1);
        sb.append(a.substring(index + b.length()));
        sb.append("\"");
        result = sb.toString();
        return result;
    }

    @Override
    public String multiply(String a, int b) {

        if (b <= 0) {
            return "\"\"";
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
    public String div(String a, int b) {

        if (b <= 0) {
            return "\"\"";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(a, 0, a.length() / b);
        sb.append("\"");
        result = sb.toString();
        return result;
    }

}
