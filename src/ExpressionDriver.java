/*************************************************
 File: ExpressionDriver.java
 By: Jeanine Nebrija
 Date: 2/20/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Main driver for ExpressionEvaluator app.
 Evaluates a predefined infix and postfix expression using user-provided variable values.
 *************************************************/

import java.util.*;

public class ExpressionDriver {
    private static final String infixExpression="(a+b)*(c+d)";
    private static final String postfixExpression="ac-b^d+";
    private static double a,b,c,d;  /* Values for expression variables */

    public static void main (String args[]) {
        String computeAgain="yes";
        while(computeAgain.equals("yes")) {
            /* Get user input */
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter value for a:");
            a = sc.nextDouble();
            System.out.print("Enter value for b:");
            b = sc.nextDouble();
            System.out.print("Enter value for c:");
            c = sc.nextDouble();
            System.out.print("Enter value for d:");
            d = sc.nextDouble();

            /* Compute and print output */
            System.out.println("Value of infix string " + infixExpression + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + InfixEvaluator.evaluateInfix(infixExpression) + ".");
            System.out.println("Value of postfix string " + postfixExpression + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + PostfixEvaluator.evaluatePostfix(postfixExpression) + ".");

            System.out.println("Compute again? (yes/no)");
            computeAgain = sc.next().toLowerCase();
        }
    }

    /********************************************
     Get stored value for corresponding variable
     ********************************************/
    public static double getInputValue(Character variable){
        switch (variable) {
            case 'a' -> {return a;}
            case 'b'-> {return b;}
            case 'c'-> {return c;}
            case 'd'-> {return d;}
            default -> {
                System.out.println("Unrecognized variable '" + variable + "'! Aborting.");
                System.exit(1);
                return 0;
            }
        }
    }
}