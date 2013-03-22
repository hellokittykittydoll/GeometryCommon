package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.tuple.DoubleTriple;
import com.fudaowang.geometry.common.util.NumberUtil;

/**
 * 表示反比例函数的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/13/12
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hyperbola {
    protected final double k;
    protected final double x;
    protected final double y;

    /**
     * 利用系数k构造反比例函数
     *
     * @param k     系数k
     * @param point 函数的原点
     */
    public Hyperbola(double k, Point point) {
        if (NumberUtil.isZero(k)) {
            throw new IllegalArgumentException("反比例函数的系数k不能为0");
        }
        if (point == null) {
            throw new NullPointerException("原点为null");
        }

        this.k = k;
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * 构造反比例函数
     *
     * @param k 系数k
     * @param x 原点的横坐标
     * @param y 原点的纵坐标
     */
    public Hyperbola(double k, double x, double y) {
        if (NumberUtil.isZero(k)) {
            throw new IllegalArgumentException("反比例函数的系数k不能为0");
        }
        this.k = k;
        this.x = x;
        this.y = y;
    }

    /**
     * 利用double组合来构造反比例函数
     *
     * @param triple 参数的double组合
     */
    public Hyperbola(DoubleTriple triple) {
        if (triple == null) {
            throw new NullPointerException("参数为null");
        }

        if (NumberUtil.isZero(triple.getNumber1())) {
            throw new IllegalArgumentException("反比例函数的系数k不能为0");
        }

        this.k = triple.getNumber1();
        this.x = triple.getNumber2();
        this.y = triple.getNumber3();
    }

    /**
     * 获得系数k
     *
     * @return 系数k
     */
    public double getK() {
        return k;
    }

    /**
     * 获得原点的横坐标
     *
     * @return 原点的横坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获得原点的纵坐标
     *
     * @return 原点的纵坐标
     */
    public double getY() {
        return y;
    }

    public String toString() {
        return "Hyperbola{" +
                "k=" + k +
                '}';
    }
}
