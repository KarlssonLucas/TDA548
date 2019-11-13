package calc;

import javax.swing.plaf.IconUIResource;
import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.pow;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   This is not the program, it's a class declaration (with methods) in it's
 *   own file (which must be named Calculator.java)
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
class Calculator {

    // Here are the only allowed instance variables!
    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";

    // Method used in REPL
    double eval(String expr) {
        if (expr.length() == 0) {
            return NaN;
        }
        List<String> tokens = tokenize(expr);
        List<String> postfix = infix2Postfix(tokens);
        System.out.println(tokens);
        System.out.println(postfix);
        return evalPostfix(postfix);
    }

    // ------  Evaluate RPN expression -------------------

    public double evalPostfix(List<String> postfix) {
        int i = 0;
        while (postfix.size() >= 3) {
            if (!isNumber(postfix.get(i + 2))) {
                double d2 = Double.parseDouble(postfix.get(i));
                double d1 = Double.parseDouble(postfix.get(i + 1));

                String result = String.valueOf(applyOperator(postfix.get(i + 2), d1, d2));
                postfix.set(i, result);
                postfix.remove(i + 2);
                postfix.remove(i + 1);
                i = 0;

            } else i++;
        }
        return Double.parseDouble(postfix.get(0));
    }


    double applyOperator(String op, double d1, double d2) {
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d2 - d1;
            case "*":
                return d1 * d2;
            case "/":
                if (d1 == 0) {
                    throw new IllegalArgumentException(DIV_BY_ZERO);
                }
                return d2 / d1;
            case "^":
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);
    }

    // ------- Infix 2 Postfix ------------------------

    public List<String> infix2Postfix(List<String> tokens) {
        List<String> rList = new ArrayList<>();
        Stack <String> opStack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++){
            String s = tokens.get(i);
            if (s.equals(")")){
                throw new IllegalArgumentException(MISSING_OPERATOR);
            } else if (s.equals("(")){
                i = parenthesis(i,tokens,rList);
            } else if (isNumber(s)){
                rList.add(s);
            }else if (!opStack.empty() && getPrecedence(opStack.peek()) >= getPrecedence(s)){
                if (getAssociativity(s) == Assoc.LEFT){
                    while (!opStack.empty() && getPrecedence(opStack.peek()) >= getPrecedence(s)) {
                        rList.add(opStack.pop());
                    }
                    opStack.push(s);
                }else opStack.push(s);
            } else {
                opStack.push(s);
            }
        }
        while (!opStack.empty()){
            rList.add(opStack.pop());
        }
        setMissingOperand(rList);
        return rList;
    }

    int parenthesis(int a, List<String> tokens, List<String> rList){
        Stack <String> opStack = new Stack<>();
        for (int i = a+1; i < tokens.size(); i++){
            String s = tokens.get(i);
            if (s.equals(")")){
                while (!opStack.isEmpty()){
                    rList.add(opStack.pop());
                }
                return  i;
            }else if (s.equals("(")){
                i = parenthesis(i,tokens,rList);
            }else if (isNumber(s)){
                rList.add(s);
            }else if (!opStack.empty() && getPrecedence(opStack.peek()) >= getPrecedence(s)){
                if (getAssociativity(s) == Assoc.LEFT){
                    while (getPrecedence(opStack.peek()) > getPrecedence(s)) {
                        rList.add(opStack.pop());
                    }
                    opStack.push(s);
                }else opStack.push(s);
            } else {
                opStack.push(s);
            }
        }
        throw new IllegalArgumentException(MISSING_OPERATOR);
    }

    void setMissingOperand(List<String>postfix){
        int noOP = 0;
        int noNo = 0;
        for (int i = 0; i < postfix.size(); i++){
            if (isNumber(postfix.get(i))) noNo++;
            else noOP++;
        }
        if (noOP >= noNo)throw new IllegalArgumentException(MISSING_OPERAND);
    }

    boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }



    int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {
        LEFT,
        RIGHT
    }

    // ---------- Tokenize -----------------------

    public List<String> tokenize(String expr) {
        StringBuilder sb = new StringBuilder();
        List <String> afterTokenize = new ArrayList<>();
        int noWhite = 0;
        int noOP = 0;
        for (int i = 0; i < expr.length(); i++){
            if (Character.isWhitespace(expr.charAt(i))){
                noWhite = 1;
            }else if (Character.isDigit(expr.charAt(i))){
                sb.append(expr.charAt(i));
            }else {
                if (sb.length() > 0) {
                    afterTokenize.add(sb.toString());
                    sb.setLength(0);
                }
                noOP++;
                afterTokenize.add(String.valueOf(expr.charAt(i)));
            }
        }
        if (sb.length() > 0)afterTokenize.add(sb.toString());
        if (noWhite > noOP) throw new IllegalArgumentException(MISSING_OPERATOR);
        else return afterTokenize;
    }
}
