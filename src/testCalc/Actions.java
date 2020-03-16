package testCalc;

//описывает допустимые действия в калькуляторе
interface Actions {

    String plus(String a, String b);
    String minus(String a, String b);
    String multiply(String a, int b);
    String div(String a, int b);

}
