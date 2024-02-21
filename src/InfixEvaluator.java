/*************************************************
 File: InfixEvaluator.java
 By: Jeanine Nebrija
 Date: 2/20/24
 Compile: Open directory as IntelliJ project, compile and run.
 Usage: Run main() in ExpressionDriver.java
 System: Windows w/ Java
 Description: Evaluate a given infix string using two stacks.
 *************************************************/

import java.util.Iterator;

public class InfixEvaluator {
    private static final ListWithIteratorInterface<Double> operandStack = new LinkedListWithIterator<>();
    private static final ListWithIteratorInterface<Character> operatorStack = new LinkedListWithIterator<>();

    public static double evaluateInfix(String str){
        System.out.println("\u001B[31m///INFIX EVALUATION///\u001B[0m");

        for(char c : str.toCharArray()) {
            if(Character.isLetter(c)){
                System.out.println("Pushed " + ExpressionDriver.getInputValue(c) + " into operand stack.");
                operandStack.add(ExpressionDriver.getInputValue(c));
            }else if(c=='(') {
                System.out.println("Pushed '" + c + "' into the operator stack.");
                operatorStack.add(c);
            }else if(c==')'){
                //do process until "(" is encountered
                System.out.println("Evaluating because ')' was encountered:");
                boolean continueEval=true;
                while(continueEval) {continueEval = evaluateGroup();}
            }else{
                Iterator<Character> it = operatorStack.getIterator();

                if(operatorStack.isEmpty()) {
                    System.out.println("Operator stack is empty; pushed '" + c + "'.");
                    operatorStack.add(c);
                }else{
                    char stackTop= (char) operatorStack.getEntry(operatorStack.getLength());
                    if(stackTop=='(' || getPrecedence(c)>=getPrecedence(stackTop)) {
                        System.out.println("'"+c + "' is of higher/equal precedence to '" + stackTop + "'; pushed into operand stack.");
                        operatorStack.add(c);
                    }else{
                        System.out.println("'"+c + "' is of lower precedence to '" + stackTop + "'; evaluating.");
                        boolean continueEval=true;
                        while(continueEval) {continueEval = evaluateGroup();}
                        operatorStack.add(c);
                    }
                }
            }
        }

        /* Final evaluation loop until operand stack is empty */
        boolean continueEval=true;
        while(continueEval) { continueEval = evaluateGroup();}
        return (double) operandStack.getEntry(operandStack.getLength());
    }

    /********************************************
    Pop two operands and one operator then evaluate (to be used in a loop).
    Return false if loop is to be broken, otherwise true.
    ********************************************/
    public static boolean evaluateGroup(){
        double o2=operandStack.pop();
        double o1=operandStack.pop();
        System.out.println("Popped " + o2 + ".");
        System.out.println("Popped " + o1 + ".");
        char op=operatorStack.pop();

        double result = switch (op) {
            case '+' -> o1 + o2;
            case '-' -> o1 - o2;
            case '*' -> o1 * o2;
            case '/' -> o1 / o2;
            case '^' -> Math.pow(o1, o2);
            default -> 0;
        };

        System.out.println(Double.toString(o1)+op+Double.toString(o2)+" = "+result+"; pushed into operand stack.");
        operandStack.add(result);

        if(operatorStack.isEmpty()) {
            return false;
        }else{
            char stackTop=operatorStack.peek();
            if(stackTop=='(') {
                operatorStack.remove(operatorStack.getLength());
                System.out.println("Encountered '('; popped and halted evaluation.");
                return false;
            }
        }

        return true;
    }

    /********************************************
    Get precedence value of operator for comparison
    ********************************************/
    public static int getPrecedence(Character operator){
        switch (operator) {
            case '^' -> {return 2;}
            case '*', '/' -> {return 1;}
            case '+', '-' -> {return 0;}
            default -> {
                System.out.println("Unrecognized operator '" + operator + "'! Aborting.");
                System.exit(1);
                return 0;
            }
        }
    }
}