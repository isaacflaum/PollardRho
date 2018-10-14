package com.isaac;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // create a new pollard rho algorithm to factor the integer 8051 with starting seed 2 and generate
        // 10 numbers to try and factor.
        PollardRho p = new PollardRho(
            new BigInteger("0320823808834580534803458035480354083540835083508380083508354803548023839589345823895345737945973532342344898384858324579385479834857825782785782789457982798587924578927894578924795729879857897895789279859782534832457835248787352897243879235489789787993427957283597832978459878051"),
            BigInteger.TWO,
            10
        );
        p.pollardRho(); // run the algorithm
    }
}
