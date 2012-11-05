package com.fudaowang.graph;

/**
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Line {
    private double a;
    private double b;
    private double c;

    public Line(double a, double b, double c) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("a,b == 0");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
