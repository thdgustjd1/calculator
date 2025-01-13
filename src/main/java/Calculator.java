import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    Scanner sc;
    Calculator(Scanner scanner) {
        this.sc = scanner;
    }

    int add(int i, int j) {
        return i + j;
    }
    int add(String input){
        if(input==null || input.isEmpty())return 0;
        String[] numbers = input.split(",|:");
        int result = 0;
        for(String number : numbers){
            result += Integer.parseInt(number);
        }
        return result;
    }

    int subtract(int i, int j) {
        return i - j;
    }

    int multiply(int i, int j) {
        return i * j;
    }

    int divide(int i, int j) {
        if(j==0)throw new ArithmeticException("Divide by zero");
        return i / j;
    }

    int cal(){
        String input = sc.nextLine();
        List<String> parts = new ArrayList<>();
        int num = 0;
        boolean isPreviousOperator = false;
        for(char c : input.toCharArray()){
            if(c >= '0' && c <= '9'){
                num = num * 10 + (c - '0');
                isPreviousOperator = false;
            }
            else{
                if(isPreviousOperator || !isOperator(c)){
                    throw new IllegalArgumentException("Invalid input");
                }
                parts.add(String.valueOf(num));
                parts.add(String.valueOf(c));
                num = 0;
                isPreviousOperator = true;
            }
        }

        if(isPreviousOperator){
            throw new IllegalArgumentException("Invalid input");
        }

        parts.add(String.valueOf(num));

        int result = Integer.parseInt(parts.getFirst());
        for(int i = 2; i<parts.size(); i+=2){
            result = operate(result, parts.get(i-1), Integer.parseInt(parts.get(i)));
        }
        return result;
    }

    int operate(int a,  String operator, int b){
        return switch(operator){
            case "+" -> add(a, b);
            case "-" -> subtract(a, b);
            case "*" -> multiply(a, b);
            case "/" -> divide(a, b);
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator cal = new Calculator(sc);
        System.out.println(cal.cal());
    }

}