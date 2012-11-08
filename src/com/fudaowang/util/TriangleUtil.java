package com.fudaowang.util;

import com.fudaowang.graph.Point;
import com.fudaowang.graph.Triangle;

/**
 * 对三角形进行操作的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/7/12
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class TriangleUtil {
    /**
     * 计算三角形的面积
     * @param triangle 三角形
     * @return 三角形的面积
     */
    public static double getArea(Triangle triangle) {
        if (triangle == null) {
            return Double.NaN;
        }
        return getArea(triangle.getP1(), triangle.getP2(), triangle.getP3());
    }

    /**
     * 计算三角形的面积
     * @param p1 三角形的第一个点
     * @param p2 三角形的第二个点
     * @param p3 三角形的第三个点
     * @return 三角形的面积
     */
    public static double getArea(Point p1, Point p2, Point p3) {
        if (p1 == null || p2 == null || p3 == null) {
            return Double.NaN;
        }
        return getArea(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }

    /**
     * 计算三角形的面积
     * @param x1 三角形的第一个点的横坐标
     * @param y1 三角形的第一个点的纵坐标
     * @param x2 三角形的第二个点的横坐标
     * @param y2 三角形的第二个点的纵坐标
     * @param x3 三角形的第三个点的横坐标
     * @param y3 三角形的第三个点的纵坐标
     * @return 三角形的面积
     */
    public static double getArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = Util.distance(x1, y1, x2, y2);
        double b = Util.distance(x1, y1, x3, y3);
        double c = Util.distance(x2, y2, x3, y3);

        double p = (a + b + c) / 2.0;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }
}
