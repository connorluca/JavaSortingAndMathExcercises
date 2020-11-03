/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasortingandmath;
import java.lang.Math;
/**
 *
 * @author conno
 */
public class GeneralMath {
    /**
     * calculates the binomial coeficient = n!/(k!(n-k)!
     * this is used to find the terms of a entire sequence such as  (x+4)^4
     * i.e start from left to right. Numbers found in brackets are the binomial coeffienct
     * (40)x^0+(41)x^1+(43)x^3
     * (40) n=4 and k =0 (0!=1)
     * (42) will equal =6, but now we must account for the +4
     * we multiple each value by 4^(n-k) so (42) accounts for the +4 is 16*6=96
     * we then must account for the variable value lets say it's 3x (42)=6 multiple by 3^k
     * @param n exponant equation is being raised to the power of
     * @param k the value for the term in the equation you are looking for
     * @param integerValue value for constant in expression for (x+4)^3 it would be 4
     * @param variableValue coeffiecent for variable in expression for (x+4)^3 it would be 1
     * @return 
     */
    public int binomialCoefficient(int n, int k, int integerValue, int variableValue){
        int biCo= recursiveFactorial(n)/(recursiveFactorial(k)*(recursiveFactorial(n-k)));
        int intCo = (int) Math.pow(integerValue, n-k);
        int variableCo = (int) Math.pow(variableValue, k);
        return biCo*intCo*variableCo;
    }
    /**
     * First parses the values needed from equation and then returns the total coeffieient for the desired term.
     * @param equation
     * @param k
     * @return 
     */
    public int binomialCoefficient(String equation, int k){
        return 0;
    }
    /**
     * Expands out equations such as (x+4)^4
     * Minimal equation because it only has two terms a variable and a constant
     *
     * @param equation
     * @return 
     */
    public String raiseMinimumExpressionToPower(String expression){
        return null;
    }
    public int recursiveFactorial(int n){
        if(n<=2){
            if(n==0){//special case becuase 0! = 1
                return 1;
            }
            return n;
        }
        return n*recursiveFactorial(n-1);
    }
}
