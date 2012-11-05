package com.fudaowang.graph;

/**
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/25/12
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Parabola {
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

    public Parabola(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("a == 0");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
