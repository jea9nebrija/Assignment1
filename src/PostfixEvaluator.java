/*************************************************
 File: PostfixEvaluator.java
 By: Jeanine Nebrija
 Date: 2/20/24
 Compile: Open directory as IntelliJ project, compile and run.
 Usage: Run main() in ExpressionDriver.java
 System: Windows w/ Java
 Description: Evaluate a given postfix string using a stack.
 *************************************************/

public class PostfixEvaluator {
    private static final ListWithIteratorInterface<Double> stack = new LinkedListWithIterator<>();

    public static double evaluatePostfix(String str) {

        System.out.println("\u001B[31m///POSTFIX EVALUATION///\u001B[0m");

        for(char c : str.toCharArray()) {
            if(Character.isLetter(c)){
                System.out.println("Pushed " + ExpressionDriver.getInputValue(c) + " into stack.");
                stack.add(ExpressionDriver.getInputValue(c));
            }else{
                double o2=stack.pop();
                double o1=stack.pop();
                System.out.println("Popped " + o2 + ".");
                System.out.println("Popped " + o1 + ".");

                double result = switch (c) {
                    case '+' -> o1 + o2;
                    case '-' -> o1 - o2;
                    case '*' -> o1 * o2;
                    case '/' -> o1 / o2;
                    case '^' -> Math.pow(o1, o2);
                    default -> 0;
                };

                System.out.println(Double.toString(o1)+c+Double.toString(o2)+" = "+result+"; pushed into stack.");
                stack.add(result);
            }
        }

        return (double) stack.remove(1);
    }
}