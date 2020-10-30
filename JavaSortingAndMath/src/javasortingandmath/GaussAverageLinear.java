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
public class GaussAverageLinear {
    /**
     * Calculating the total of all numbers in a linear series of numbers. Very similair to how gauss calculated a liner series of 1..100.
     * In gauss's method I noticed he's actually calculating the medain and multiplying it by the total number iterations, thought this is
     * not explicitly how he described the method working. So here we will be doing the same though becuase we may operate on numbers incrementing by
     * values other than 1 and starting from numbers other than 0 we must calculate the iterations and median before we may process to calculate the total.
     * (may also be applicable to floating point numbers)
     * @param startingNum number the series starts at
     * @param endingNum number the series begins at. This number is checked to be valid with the given increment and 
     * starting number if it is not a valid ending number -1 is returned. I.e increment = 7 start= 0 end = 12 would be invalid
     * @param increment how much to increment by each time
     * @return median*iterations=total
     */
    public int calcTotalGauss(int startingNum, int endingNum, int increment){
        if((endingNum-startingNum)%increment!=0){
            return -1;    
            //alternatively if you wanted to just reduce the ending num down to a valid number
            //endingNum = endingNum-((endingNum-startingNum)%increment);
            
        }
        int iterations;//number of times from srating num must increment before it reaches the ending num
        int median;//becuase it is a linear series of numbers the median is also always the average
        iterations = (endingNum-startingNum)/increment;
        if(iterations%2==0){//even numbers of iterations
            median = ((startingNum+(increment*iterations/2))+(startingNum+(increment*iterations/2)+increment))/2;//average of the 2 middle numbers
        }
        else{
            median = (startingNum+(increment*iterations/2))+1;//middle of any odd number odd/2+1
        }
        
        return median*iterations;
        
    }
}
