/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasortingandmath;

import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author conno
 */
public class GeneralMath {

    /**
     * calculates the binomial coeficient = n!/(k!(n-k)! this is used to find
     * the terms of a entire sequence such as (x+4)^4 i.e start from left to
     * right. Numbers found in brackets are the binomial coeffienct
     * (40)x^0+(41)x^1+(43)x^3 (40) n=4 and k =0 (0!=1) (42) will equal =6, but
     * now we must account for the +4 we multiple each value by 4^(n-k) so (42)
     * accounts for the +4 is 16*6=96 we then must account for the variable
     * value lets say it's 3x (42)=6 multiple by 3^k
     *
     * @param n exponant equation is being raised to the power of
     * @param k the value for the term in the equation you are looking for
     * @param integerValue value for constant in expression for (x+4)^3 it would
     * be 4
     * @param variableValue coeffiecent for variable in expression for (x+4)^3
     * it would be 1
     * @return
     */
    public int binomialCoefficient(int n, int k, int integerValue, int variableValue) {
        int biCo = recursiveFactorial(n) / (recursiveFactorial(k) * (recursiveFactorial(n - k)));
        int intCo = (int) Math.pow(integerValue, n - k);
        int variableCo = (int) Math.pow(variableValue, k);
        return biCo * intCo * variableCo;
    }

    /**
     * First parses the values needed from equation and then returns the total
     * coeffieient for the desired term.
     *
     * @param equation
     * @param k
     * @return
     */
    public int binomialCoefficient(String equation, int k) {

        return 0;
    }

    /**
     * Expands out equations such as (x+4)^4 or (x^2+4)^4 or (x-4)^2 Minimal
     * equation because it only has two terms a variable and a constant
     *
     * @param equation
     * @return
     */
    public String raiseMinimumExpressionToPower(String expression) {
        MinimalExpression min = new MinimalExpression();
        min = parseValues(expression);
        StringBuilder strB = new StringBuilder();
        int biCo = binomialCoefficient(min.complEx, 0, min.conVal, min.varVal);
        strB.append(biCo);//removes if else statement if first is added before loop
        for (int k = 1; k <= min.complEx; k++) {
            biCo = binomialCoefficient(min.complEx, k, min.conVal, min.varVal);

            strB.append(biCo < 0 ? '-' : '+');
            strB.append(biCo);
            strB.append(min.variableCharacter);
            strB.append('^');
            strB.append(min.varEx * k);
        }

        return strB.toString();
    }

    public int recursiveFactorial(int n) {
        if (n <= 2) {
            if (n == 0) {//special case becuase 0! = 1
                return 1;
            }
            return n;
        }
        return n * recursiveFactorial(n - 1);
    }

    /**
     * Parses the values for equations such as (x+4)^4 or (x^2+4)^4 or (x-4)^2
     * Negative exponents such as ^-4 are not accomodated for and will be turned
     * into positives in raiseMinimumExpression function which is calling this
     * function. This function has minimum error checking if you put in an extra
     * long expression such as (x+4+4)^8 you will get the same answer as
     * (x+4)^8, as it will not simplyfy the expression, the result you will get
     * for illigimate functions will vary becuase the loop will overright values
     * for the constant and variable with the most rightmost instance of
     * variable or constant inside the brackets.
     *
     * @param expression
     * @return a string array containing the variable exponent, complete
     * equation exponent, variable value and constant value in that order (would
     * be nice to have this as object instead of strings that have to be cast to
     * the proper type for processing later)
     */
    public MinimalExpression parseValues(String expression) {
        MinimalExpression min = new MinimalExpression();
        int currentPosition = 0;//used to pass through the string as we find each value         
        try {
            if (expression.charAt(currentPosition) != '(') {
                throw new InvalidExpressionException("Invalid expression");

            }
        } catch (InvalidExpressionException ex) {
            Logger.getLogger(GeneralMath.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        currentPosition++;
        StringBuilder genExp = new StringBuilder();
        genExp.append(expression.charAt(currentPosition));
        currentPosition++;

        while (expression.charAt(currentPosition) != ')') {
            //not sure if regex can be used for this becuase of ^ character
            if (expression.charAt(currentPosition) == '-' || expression.charAt(currentPosition) == '+' || expression.charAt(currentPosition + 1) == ')') {
                if (expression.charAt(currentPosition + 1) == ')') {
                    genExp.append(expression.charAt(currentPosition));
                }
                if (genExp.indexOf("^") != -1) {//true if it contains ^
                    String[] temp = genExp.toString().split("^");
                    if (temp[0].matches(".*[a-zA-Z]+.*")) {
                        min.varVal = Integer.parseInt(temp[0]);
                        min.varEx = Integer.parseInt(temp[1]);
                        min.variableCharacter = findFirstLetterCharacter(temp[0]);
                    } else {
                        //a constant has had an exponent applied to it so  we will simplify before assigning
                        min.conVal = (int) Math.pow(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
                    }
                } else {
                    if (genExp.toString().matches(".*[a-zA-Z]+.*")) {
                        min.varVal = Integer.parseInt(genExp.toString());
                        min.varEx = 1;
                        min.variableCharacter = findFirstLetterCharacter(genExp.toString());
                    } else {
                        min.conVal = Integer.parseInt(genExp.toString());
                    }
                }
                genExp.setLength(0);//clear the string 
            }
            genExp.append(expression.charAt(currentPosition));
            currentPosition++;
        }
        try {
            if (min.checkFilled()) {
                throw new InvalidExpressionException("Missing vvariable or constant");

            }
        } catch (InvalidExpressionException ex) {
            Logger.getLogger(GeneralMath.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        min.complEx = Integer.parseInt(expression.substring(currentPosition + 2));//current position is currently at ) so it should be +2 to pass the ^ and reach the numbers

        return min;
    }

    /**
     * Returns the first instance of a letter character.
     *
     * @param string
     * @return
     */
    public char findFirstLetterCharacter(String string) {

        for (int i = 0; i < string.length(); i++) {
            if (Character.toString(string.charAt(i)).matches("[a-zA-Z]")) {
                return string.charAt(i);
            }
        }
        return 'x';//default char if none are found

    }
}
