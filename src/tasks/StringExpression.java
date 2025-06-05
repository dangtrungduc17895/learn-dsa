package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Cho 1 đoạn string ex = "1+2+3*4-4+2/4";
hãy tính ra kết quả của phép tính trên
 */
public class StringExpression {
    public static void main(String[] args) {
        String expression = "1+2+3*4-4+2/4";
        double result = evaluate(expression);
        System.out.println("Result: " + result);
    }

    // Hàm chính
    public static double evaluate(String expression) {
        List<String> postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    // Chuyển Infix -> Postfix
    private static List<String> infixToPostfix(String expression) {
        List<String> output = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder number = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                number.append(c); // hỗ trợ số thập phân
            } else {
                if (number.length() > 0) {
                    output.add(number.toString());
                    number.setLength(0);
                }

                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    output.add(Character.toString(operators.pop()));
                }
                operators.push(c);
            }
        }

        if (number.length() > 0) {
            output.add(number.toString());
        }

        while (!operators.isEmpty()) {
            output.add(Character.toString(operators.pop()));
        }

        return output;
    }

    // Đánh giá biểu thức postfix
    private static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                double result = applyOperator(token.charAt(0), a, b);
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    // Áp dụng toán tử
    private static double applyOperator(char op, double a, double b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    // Kiểm tra toán tử
    private static boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    // Độ ưu tiên toán tử
    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return 0;
        }
    }
}
