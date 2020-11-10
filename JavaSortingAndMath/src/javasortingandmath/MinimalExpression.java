/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasortingandmath;

/**
 *
 * @author conno
 */
public class MinimalExpression {

    public int varEx;
    public int complEx;
    public int varVal;
    public int conVal;
    public char variableCharacter;

    public boolean checkFilled() {
        if(varEx==0 || complEx==0||varVal==0||conVal==0||variableCharacter=='\u0000'){
        return false;
    }
        return true;
    }
}
