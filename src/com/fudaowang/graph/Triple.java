package com.fudaowang.graph;

import java.io.PipedReader;

/**
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/31/12
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class Triple {
    private double a;
    private double b;
    private double c;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public Triple(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;
    }
}
