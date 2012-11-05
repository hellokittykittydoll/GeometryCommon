package com.fudaowang.graph;

/**
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/1/12
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Round {
    private Point center;
    private double radius;

    public Round(Point center, double radius) {
        if (center == null) {
            throw new NullPointerException("center");
        }
        this.center = center;
        this.radius = radius;
    }

    public Round(double x,double y ,double radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

}
