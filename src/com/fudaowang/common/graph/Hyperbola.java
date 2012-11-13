package com.fudaowang.common.graph;

/**
 * 表示反比例函数的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/13/12
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hyperbola {
    private double k;

    /**
     * 利用系数k构造反比例函数
     * @param k 系数k
     */
    public Hyperbola(double k) {
        this.k = k;
    }

    /**
     * 获得系数k
     * @return 系数k
     */
    public double getK() {
        return k;
    }

    public String toString() {
        return "Hyperbola{" +
                "k=" + k +
                '}';
    }
}
