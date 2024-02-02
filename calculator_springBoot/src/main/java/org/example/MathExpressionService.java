package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class MathExpressionService {

    private ExpressionClass expression;

    public ExpressionClass getExpression() {
        return expression;
    }

    public void setExpression(ExpressionClass expression) {
        this.expression = expression;
    }

    public String multiInverse() {

        double result;

        try {
            result = 1 / Double.parseDouble(this.expression.getExpression());
        }catch (Exception e) {
            return "E";
        }

        return String.valueOf(result);
    }

    public String power2() {

        double result;

        try {
            result = Double.parseDouble(this.expression.getExpression()) * Double.parseDouble(this.expression.getExpression());
        }catch (Exception e) {
            return "E";
        }

        return String.valueOf(result);
    }

    public String square2() {

        double result;

        result = Math.sqrt(Double.parseDouble(this.expression.getExpression()));

        return String.valueOf(result);
    }

    public String precent() {

        double result;

        result = Double.parseDouble(this.expression.getExpression()) / 100.0;

        return String.valueOf(result);
    }

    public String changeSign() {

        double result;

        result = -1 * Double.parseDouble(this.expression.getExpression());

        return String.valueOf(result);
    }

    public static String findOperation(String exp){

        if( exp.contains("+") )
            return "+";
        else if ( exp.contains("*"))
            return "*";
        else if ( exp.contains("/"))
            return "/";
        else if ( exp.contains("-"))
            return "-";

        return "";
    }

    public String computeResult() {

        Double result = (double) 0;

        String exp = this.expression.getExpression();

        try {

            String operation = findOperation(exp);

            if( operation.equals("") )
                return exp;

            String operand1 = exp.substring( 0, exp.indexOf(operation) );
            String operand2 = exp.substring(exp.indexOf(operation)+1);

            if( operation.equals("-") ) {
                int indexOfMinus = exp.indexOf("-",1);
                operand1 = exp.substring( 0, indexOfMinus );
                operand2 = exp.substring( indexOfMinus+1 );
            }

            double a = Double.parseDouble(operand1);
            double b = Double.parseDouble(operand2);


            switch (operation) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0)
                        return "E";
                    result = a / b;
                    break;
            }

        }catch (Exception e) {
            try {
                Expression expression = new ExpressionBuilder(this.expression.getExpression()).build();
                result = expression.evaluate();
            }catch (Exception ex) {
                return "E";
            }
        }

        return String.valueOf(result);
    }


}
