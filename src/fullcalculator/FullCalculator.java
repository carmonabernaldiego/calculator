package fullcalculator;

import java.util.Scanner;

public class FullCalculator {

    private Stack<String> operatorStack;
    private Stack<Double> valueStack;
    private boolean error;

    public FullCalculator() {
        operatorStack = new Stack<String>();
        valueStack = new Stack<Double>();
        error = false;
    }

    private boolean isOperator(String ch) {
        return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
    }

    private int getPrecedence(String ch) {
        if (ch.equals("+") || ch.equals("-")) {
            return 1;
        }
        if (ch.equals("*") || ch.equals("/")) {
            return 2;
        }
        return 0;
    }

    private void processOperator(String t) {
        double a, b;
        if (valueStack.empty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            b = valueStack.peek();
            valueStack.pop();
        }
        if (valueStack.empty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            a = valueStack.peek();
            valueStack.pop();
        }
        double r;
        if (t.contains("+")) {
            r = a + b;
        } else if (t.contains("-")) {
            r = a - b;
        } else if (t.contains("*")) {
            r = a * b;
        } else if(t.contains("/")) {
            r = a / b;
        } else {
            System.out.println("Operator error.");
            error = true;
            return;
        }
        valueStack.push(r);
    }
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    public void processInput(String input) {
        // The tokens that make up the input
        String[] tokens = input.split(" ");

        // Main loop - process all input tokens
        for (int n = 0; n < tokens.length; n++) {
            String nextToken = tokens[n];
            //System.out.println(nextToken);
            String ch = nextToken;
            //System.out.println(ch);

            if (isNumeric(ch)) {
                double value = Double.parseDouble(ch);
                valueStack.push(value);
                System.out.println(ch);

            } else {
                System.out.println(ch);
                if (operatorStack.empty() || getPrecedence(ch) > getPrecedence(operatorStack.peek())) {
                    operatorStack.push(ch);
                    System.out.println(ch);
                } else {
                    while (!operatorStack.empty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
                        String toProcess = operatorStack.peek();
                        operatorStack.pop();
                        processOperator(toProcess);
                        System.out.println(toProcess);
                    }
                    operatorStack.push(ch);
                    System.out.println(ch);
                }
            }

            if (ch.equals("(")) {
                operatorStack.push(ch);
            } else if (ch.equals(")")) {
                while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                    String toProcess = operatorStack.peek();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.empty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                } else {
                    System.out.println("Error: unbalanced parenthesis.");
                    error = true;
                }
            }

        }

        // Empty out the operator stack at the end of the input
        while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
            String toProcess = operatorStack.peek();
            operatorStack.pop();
            processOperator(toProcess);
        }
        // Print the result if no error has been seen.
        if (error == false) {
            double result = valueStack.peek();
            valueStack.pop();
            if (!operatorStack.empty() || !valueStack.empty()) {
                System.out.println("Expression error.");
            } else {
                System.out.println("The result is " + result);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // The original input
        System.out.print("Enter an expression to compute: ");
        String userInput = input.nextLine();

        FullCalculator calc = new FullCalculator();
        calc.processInput(userInput);
    }
}
