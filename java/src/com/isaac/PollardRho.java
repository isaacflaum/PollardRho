package com.isaac;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PollardRho {

    BigInteger integerToFactor; // the number to factor

    private BigInteger polynomial(BigInteger x) { return x.multiply(x).add(BigInteger.ONE); } // f(x) = x^2 + 1

    private List<BigInteger> xs; // list to store iterations of polynomial

    private BigInteger seed; // starting seed for polynomial

    private int numberOfIterations; // number of times to run algorithm

    // Constructor
    public PollardRho(BigInteger integerToFactor, BigInteger seed, int numberOfIterations) {
        this.integerToFactor = integerToFactor;
        this.seed = seed;
        this.numberOfIterations = numberOfIterations;
        xs = new LinkedList<BigInteger>();
    }

    private void generateXs() {
        BigInteger result = seed;
        for (int i = 0; i < numberOfIterations; i++) {
            result = polynomial(result).mod(integerToFactor);
            xs.add(result);
        }
    }

    private BigInteger crunchXs() {
        BigInteger d = BigInteger.ONE; // the gcd of integerToFactor and (x_2i - x_i)
        for (int i = 0; i < numberOfIterations / 2; i++) {
            d = integerToFactor.gcd(xs.get(2*(i+1) - 1).subtract(xs.get(i)));
            if (d.compareTo(BigInteger.ONE) >= 1) {
                return d;
            }
        }
        return BigInteger.ONE;
    }

    public void pollardRho() {
        generateXs();
        BigInteger result = crunchXs();
        System.out.print("Found a factor: " + result);
    }

}
