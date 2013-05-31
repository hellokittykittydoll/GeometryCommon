package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.util.HalfLineUtil;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 表示射线的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 5/15/13
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class HalfLine extends Line {
    protected final double x;
    protected final double y;
    protected final Orientation orientation;

    /**
     * 利用射线所在直线的参数以及射线端点的横纵坐标,倾角来构造射线
     *
     * @param a           所在直线的系数a
     * @param b           所在直线的系数b
     * @param c           所在直线的系数c
     * @param x           端点的横坐标
     * @param y           端点的纵坐标
     * @param orientation 射线的主要增长方向
     */
    public HalfLine(double a, double b, double c, double x, double y, Orientation orientation) {
        super(a, b, c);
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    /**
     * 利用射线所在直线的参数组以及射线端点的横纵坐标组,倾角来构造射线
     *
     * @param triple      所在直线的参数组
     * @param pair        端点坐标参数组
     * @param orientation 射线的主要增长方向
     */
    public HalfLine(Triple<Double, Double, Double> triple, Pair<Double, Double> pair, Orientation orientation) {
        super(triple);
        this.orientation = orientation;
        this.x = pair.getLeft();
        this.y = pair.getRight();
    }

    /**
     * 利用射线的端点和射线上一点来构造射线
     *
     * @param vertical 射线的端点
     * @param point    射线上另一点
     */
    public HalfLine(Point vertical, Point point) {
        super(vertical, point);
        this.x = vertical.x;
        this.y = vertical.y;
        this.orientation = HalfLineUtil.getOrientation(vertical, point);
    }

    /**
     * 利用射线的端点和射线上一点来构造射线
     *
     * @param vx 射线端点的横坐标
     * @param vy 射线端点的纵坐标
     * @param px 射线上另一点的横坐标
     * @param py 射线上另一点的纵坐标
     */
    public HalfLine(double vx, double vy, double px, double py) {
        super(vx, vy, px, py);
        this.x = vx;
        this.y = vy;
        this.orientation = HalfLineUtil.getOrientation(vx, vy, px, py);
    }

    /**
     * 利用射线的端点和射线上一点来构造射线
     *
     * @param vertical 表示射线端点的横纵坐标组
     * @param point    表示射线上另一点的横纵坐标组
     */
    public HalfLine(Pair<Double, Double> vertical, Pair<Double, Double> point) {
        super(vertical, point);
        this.x = vertical.getLeft();
        this.y = vertical.getRight();
        this.orientation = HalfLineUtil.getOrientation(vertical.getLeft(), vertical.getRight(), point.getLeft(), point.getRight());
    }

    /**
     * 获得射线端点的横坐标
     *
     * @return 端点的横坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获得射线端点的纵坐标
     *
     * @return 端点的纵坐标
     */
    public double getY() {
        return y;
    }

    /**
     * 获得射线的主要增长方向
     *
     * @return 射线的主要增长方向
     */
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "HalfLine{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }

    /**
     * 射线的主要方向,分为X+,X-,Y+,Y-四大类
     */
    public enum Orientation {
        X, Y, MINUS_X, MINUS_Y;
    }

    /**
     * 获得射线的端点
     *
     * @return 射线的端点
     */
    public Point getPoint() {
        return new Point(x, y);
    }
}
