package com.fudaowang.common.util;

import com.fudaowang.common.graph.Hyperbola;
import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;

/**
 * 反比例函数相关的工具类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/13/12
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class HyperbolaUtil {
    /**
     * 求反比例函数与直线的交点
     *
     * @param hyperbola 反比例函数
     * @param line      直线
     * @return 反比例函数与直线的交点
     */
    public static Point[] intersect(Hyperbola hyperbola, Line line) {
        if (hyperbola == null || line == null) {
            return new Point[0];
        }
        return intersect(hyperbola.getK(), line.getA(), line.getB(), line.getC());
    }

    /**
     * 求反比例函数y=k/x与直线ax+by+c=0的交点
     *
     * @param k 反比例函数的系数k
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @return 反比例函数与直线的交点
     */
    public static Point[] intersect(double k, double a, double b, double c) {
        if (k == 0) {
            return new Point[0];
        }

        if (a == 0 && b == 0) {
            return new Point[0];
        }

        if (a == 0) {
            if (c == 0) {
                return new Point[0];
            }
            double x = -k * b / c;
            double y = -c / b;
            return new Point[]{new Point(x, y)};
        } else if (b == 0) {
            if (c == 0) {
                return new Point[0];
            }
            double x = -c / a;
            double y = -k * a / c;
            return new Point[]{new Point(x, y)};
        }
        double delta = c * c - 4.0 * a * b * k;
        if (delta < 0) {
            return new Point[0];
        }

        if (delta == 0) {
            double x = (-c + Math.sqrt(delta)) / (2.0 * a);
            double y = k / x;
            return new Point[]{new Point(x, y)};
        }

        double x1 = (-c + Math.sqrt(delta)) / (2.0 * a);
        double y1 = k / x1;
        double x2 = (-c - Math.sqrt(delta)) / (2.0 * a);
        double y2 = k / x2;

        return new Point[]{new Point(x1, y1), new Point(x2, y2)};
    }
}
