package com.fudaowang.common.util;

import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Segment;
import com.fudaowang.common.graph.Triangle;

import java.util.*;

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
     *
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
     *
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
     *
     * @param x1 三角形的第一个点的横坐标
     * @param y1 三角形的第一个点的纵坐标
     * @param x2 三角形的第二个点的横坐标
     * @param y2 三角形的第二个点的纵坐标
     * @param x3 三角形的第三个点的横坐标
     * @param y3 三角形的第三个点的纵坐标
     * @return 三角形的面积
     */
    public static double getArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = PointUtil.distance(x1, y1, x2, y2);
        double b = PointUtil.distance(x1, y1, x3, y3);
        double c = PointUtil.distance(x2, y2, x3, y3);

        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * 判断给定的点是否在三角形内
     *
     * @param point    点
     * @param triangle 三角形
     * @return 若点在三角形内则返回true
     */
    public static boolean inTriangle(Point point, Triangle triangle) {
        if (point == null) {
            throw new NullPointerException("判断的点为null");
        }

        if (triangle == null) {
            throw new NullPointerException("判断的三角形为null");
        }

        return inTriangle(point, triangle.getP1(), triangle.getP2(), triangle.getP3());
    }

    /**
     * 判断给定的点是否在三角形内
     *
     * @param point 给定的点
     * @param p1    三角形的顶点
     * @param p2    三角形的顶点
     * @param p3    三角形的顶点
     * @return 若点在三角形内则返回true
     */
    public static boolean inTriangle(final Point point, final Point p1, final Point p2, final Point p3) {
        if (point == null || p1 == null || p2 == null || p3 == null) {
            throw new NullPointerException("判断的点为null");
        }

        ArrayList list = new ArrayList(3);
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                double d1 = PointUtil.distance(point, (Point) o1);
                double d2 = PointUtil.distance(point, (Point) o2);

                if (d1 < d2) {
                    return -1;
                }
                if (d1 > d2) {
                    return 1;
                }
                return 0;
            }
        });

        Segment s1 = new Segment((Point) list.get(0), (Point) list.get(1));
        Segment s2 = new Segment(point, (Point) list.get(2));

        list = null;

        return !LineUtil.linesIntersect(s1, s2);
    }

    /**
     * 判断给定的点是否在三角形内
     *
     * @param x  给定点的横坐标
     * @param y  给定点的纵坐标
     * @param x1 三角形的第一个端点的横坐标
     * @param y1 三角形的第一个端点的纵坐标
     * @param x2 三角形的第二个端点的横坐标
     * @param y2 三角形的第二个端点的纵坐标
     * @param x3 三角形的第三个端点的横坐标
     * @param y3 三角形的第三个端点的纵坐标
     * @return 若点在三角形内则返回true
     */
    public static boolean inTriangle(double x, double y, double x1, double y1, double x2, double y2, double x3, double y3) {
        return inTriangle(new Point(x, y), new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }
}
