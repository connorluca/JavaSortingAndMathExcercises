/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasortingandmath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

/**
 *This class is in charge of methods to find all prime numbers up to an input integer and return an integer array of them.
 * @author conno
 */
public class PrimeNumberFinder {
    /**
     * Implementation of eratosthenes sieve to find all prime numbers up until top.
     * When a number is found to not be prime it is replaced with it's opposite negative value i.e 1=-1
     * If a number is already negative it will skip that iteration of the loop as all it's factors have already been processed 
     * beforehand and therefore all possible multiples of said number will already have been eliminated if it reaches a number and said number is not negative
     * it will be added to the arrayList of prime numbers to be returned.
     * If this is ever to be adapted to a very performance sensitive area complexity analysis and research must be done to see whether the usage
     * of math.abs is more costly than storing a boolean with the array to indicate whether it has already been eliminated.
     * This implementation would be best used if there was a heavy memory restriction in comparison to cpu usage.
     * @param top the number to find all prime numbers up to and including
     * @return arraylist containing all prime numbers up until top(inclusive)
     */
    public ArrayList<Integer> eratosthenesSieve(int top){
        int numbers[] =  IntStream.range(0, top).toArray();//starts from zero so that the integer value and it's index may line up
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for(int i =2; i<=top; i++){//start from 2 as we do not need to consider 1 
            if(numbers[i]<0){
                continue;
            }
            primes.add(numbers[i]);
            for(int j=numbers[i]*2; j<=top; j=j+numbers[i]){
                numbers[j]=-1*Math.abs(numbers[j]);//using math abs to make sure it isn't marked twice
                
            }
        }
        
        return primes;
    }
    /**
     * Returns the number found to have the most prime factors in a given range, uses a modified implementation of eratosthenes sieve to count the 
     * factors as it finds the prime number under max at the same time.
     * @param min value to start looking from (must not be negative)
     * @param max
     * @return the number with the most factors
     */
    public int findMostPrimeFactors(int min, int max){
        int numbers[] =  IntStream.range(0, max).toArray();
        int hits[] = new int[max-min++];//arrays indexs line up with numbers each time a prime number marks a value +1 is added
        int i;
        for(i=2; i<=max; i++ ){
            if(numbers[i]<0){
                continue;
            }
            for(int j=numbers[i]*2; j<=max; j=j+numbers[i]){
                numbers[j]=-1*Math.abs(numbers[j]);//using math abs to make sure it isn't marked twice
                if(numbers[j]>=min){
                    hits[j-min]++;
                }    
            }
        }
        int maxFactors =0;//the index of the element with the most factors
        /*
        Simply linear search as where the element with the most prime factors varies so much based on the range.
        */
        for(int j=0; j<hits.length; j++){
            if(hits[j]>hits[maxFactors]){
                maxFactors=j;
            }
        }
        //hits[maxFactors] hold the total number of prime factors the winner has
        return maxFactors+min;
    }
    /**
     * Finds all prime factors for a given number, we will call eratosthenesSieve to find all prime numbers less than the number to factor
     * @param numberToFactor
     * @return 
     */
    public ArrayList<Integer> findPrimeFactors(int numberToFactor){
        ArrayList<Integer> primesUnder = eratosthenesSieve(numberToFactor);
        ArrayList<Integer> primeFactor = new ArrayList<Integer>();
        while(numberToFactor !=1){
            for(int prime: primeFactor){
                if(numberToFactor%prime==0){
                    numberToFactor=numberToFactor/prime;
                    primeFactor.add(prime);
                    break;
                }
            }
        }
        
        return null;
    }
}
