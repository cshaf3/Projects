package edu.pitt.cs.as3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * This class uses two stacks to evaluate an infix arithmetic expression from an
 * InputStream. It should not create a full postfix expression along the way; it
 * should convert and evaluate in a pipelined fashion, in a single pass.
 */
public class InfixExpressionEvaluator {
    // Tokenizer to break up our input into tokens
    StreamTokenizer tokenizer;

    // Stacks for operators (for converting to postfix) and operands (for
    // evaluating)
    StackInterface<Character> operatorStack;
    StackInterface<Double> operandStack;

    /**
     * Initializes the evaluator to read an infix expression from an input
     * stream.
     * @param input the input stream from which to read the expression
     */
    public InfixExpressionEvaluator(InputStream input) {
        // Initialize the tokenizer to read from the given InputStream
        tokenizer = new StreamTokenizer(new BufferedReader(
                new InputStreamReader(input)));

        // StreamTokenizer likes to consider - and / to have special meaning.
        // Tell it that these are regular characters, so that they can be parsed
        // as operators
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');

        // Allow the tokenizer to recognize end-of-line, which marks the end of
        // the expression
        tokenizer.eolIsSignificant(true);

        // Initialize the stacks
        operatorStack = new ArrayStack<Character>();
        operandStack = new ArrayStack<Double>();
    }

    /**
     * Parses and evaluates the expression read from the provided input stream,
     * then returns the resulting value
     * @return the value of the infix expression that was parsed
     */
    public Double evaluate() throws ExpressionError {
        // Get the first token. If an IO exception occurs, replace it with a
        // runtime exception, causing an immediate crash.
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Continue processing tokens until we find end-of-line
        while (tokenizer.ttype != StreamTokenizer.TT_EOL) {
            // Consider possible token types
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    // If the token is a number, process it as a double-valued
                    // operand
                    handleOperand((double)tokenizer.nval);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    // If the token is any of the above characters, process it
                    // is an operator
                    handleOperator((char)tokenizer.ttype);
                    break;
                case '(':
                case '<':
                    // If the token is open bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleOpenBracket((char)tokenizer.ttype);
                    break;
                case ')':
                case '>':
                    // If the token is close bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleCloseBracket((char)tokenizer.ttype);
                    break;
                case StreamTokenizer.TT_WORD:
                    // If the token is a "word", throw an expression error
                    throw new ExpressionError("Unrecognized token: " +
                            tokenizer.sval);
                default:
                    // If the token is any other type or value, throw an
                    // expression error
                    throw new ExpressionError("Unrecognized token: " +
                            String.valueOf((char)tokenizer.ttype));
            }

            // Read the next token, again converting any potential IO exception
            try {
                tokenizer.nextToken();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
        // Almost done now, but we may have to process remaining operators in
        // the operators stack
        handleRemainingOperators();

        // Return the result of the evaluation
        // TODO: Fix this return statement
        return result;
    }
    /**
     * This method is called when the evaluator encounters an operand. It
     * manipulates operatorStack and/or operandStack to process the operand
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operand the operand token that was encountered
     */
    void handleOperand(double operand) 
    {
        // TODO: Complete this method
        operandStack.push(operand);
    }
    /**
     * This method is called when the evaluator encounters an operator. It
     * manipulates operatorStack and/or operandStack to process the operator
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operator the operator token that was encountered
     */
    void handleOperator(char operator) 
    {
        // TODO: Complete this method
        if(operatorStack.isEmpty())
        {
            operatorStack.push(operator);
            return;
        }
        char current = operatorStack.peek();
        if(current == '+' || current == '-' || current == '*' || current == '/' || current == '^')
        {
            //handles precedence
            if(precedence(current, operator))
            {
                double total = calculate(operatorStack.pop(), operandStack.pop(), operandStack.pop());
                operandStack.push(total);
                operatorStack.push(operator);
            }
            else
            {
                operatorStack.push(operator);
            }
        }
        else
        {
            operatorStack.push(operator);
        }
    }
    /**
     * This method is called when the evaluator encounters an open bracket. It
     * manipulates operatorStack and/or operandStack to process the open bracket
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param openBracket the open bracket token that was encountered
     */
    void handleOpenBracket(char openBracket) 
    {
        // TODO: Complete this method
        operatorStack.push(openBracket);
    }
    /**
     * This method is called when the evaluator encounters a close bracket. It
     * manipulates operatorStack and/or operandStack to process the close
     * bracket according to the Infix-to-Postfix and Postfix-evaluation
     * algorithms.
     * @param closeBracket the close bracket token that was encountered
     */
    void handleCloseBracket(char closeBracket) 
    {
        // TODO: Complete this method
        boolean match = false;
        char otherBracket;
        if(closeBracket == ')')
        {
            otherBracket = '(';
        }
        else
        {
            otherBracket = '<';
        }
        while(!operatorStack.isEmpty() && !match) 
        {
            char current = operatorStack.pop();
            //determines if brackets match
            if(current == otherBracket) 
            {
                match = true;
            } 
            else 
            {
                if(current == '+' || current == '-' || current == '*' || current == '/' || current == '^')
                {
                    operatorStack.push(current);
                    //calculates inside brackets
                    double total = calculate(operatorStack.pop(), operandStack.pop(), operandStack.pop());
                    operandStack.push(total);
                }
                //handles incorrect brackets
                else
                {
                    throw new ExpressionError("Incorrect Brackets");
                }
            }
        }
        //handles incorrect brackets
        if (!match) 
        {
            throw new ExpressionError("Incorrect Brackets");
        }
    }
    /**
     * This method is called when the evaluator encounters the end of an
     * expression. It manipulates operatorStack and/or operandStack to process
     * the operators that remain on the stack, according to the Infix-to-Postfix
     * and Postfix-evaluation algorithms.
     */
    void handleRemainingOperators() 
    {
        // TODO: Complete this method
        while(!operatorStack.isEmpty())
        {
            char bracket = operatorStack.peek();
            //determines if missing closed bracket
            if(bracket == '(' || bracket == '<')
            {
                throw new ExpressionError("No Closed Bracket");
            }
            //determines if missing operand
            if(operandStack.isEmpty()) 
            {
                throw new ExpressionError("Missing Operand");
            } 
            else 
            {
                double first = operandStack.pop();
                //determines if missing operand
                if(operandStack.isEmpty()) 
                {
                    throw new ExpressionError("Missing Operand");
                } 
                else 
                {
                    operandStack.push(first);
                    double total = calculate(operatorStack.pop(), operandStack.pop(), operandStack.pop());
                    operandStack.push(total);
                }
            }
        }
    }
    //determines precedence
    boolean precedence(char operator, char operatorTwo)
    {
        if(operator == '^')
        {
            return true;
        }
        if(operatorTwo == '+' || operatorTwo == '-')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    double result = 0;
    //calculate method
    double calculate(char operator, double first, double second) throws ExpressionError
    {
        if(operator == '+')
        {
            result = second + first;
        }
        else if(operator == '-')
        {
            result = second - first;
        }
        else if(operator == '*')
        {
            result = second * first;
        }
        else if(operator == '^')
        {
            result = Math.pow(second, first);
        }
        else if(operator == '/')
        {
            //extra credit: throws error when dividing by zero
            if(first == 0)
            {
                throw new ExpressionError("Cannot divide by zero");
            }
            result = second/first;
        }
        return result;
    }
    /**
     * Creates an InfixExpressionEvaluator object to read from System.in, then
     * evaluates its input and prints the result.
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("Infix expression:");
        InfixExpressionEvaluator evaluator =
                new InfixExpressionEvaluator(System.in);
        Double value = null;
        try {
            value = evaluator.evaluate();
        } catch (ExpressionError e) {
            System.out.println("ExpressionError: " + e.getMessage());
        }
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Evaluator returned null");
        }
    }
}