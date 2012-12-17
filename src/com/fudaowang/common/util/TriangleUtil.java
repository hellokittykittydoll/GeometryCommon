package com.fudaowang.common.util;

import com.fudaowang.common.graph.Line;
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

        Point[] points = PointUtil.sort(new Point[]{p1, p2, p3}, new Comparator() {
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

        Segment s1 = new Segment(points[0], points[1]);
        Segment s2 = new Segment(point, points[2]);

        return !SegmentUtil.linesIntersect(s1, s2);
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

    /**
     * 将三角形以center为中心,逆时针旋转angle角度
     *
     * @param triangle 给定的三角形
     * @param center   旋转的中心点
     * @param angle    逆时针旋转的角度
     * @return 旋转后的三角形
     */
    public static Triangle rotate(Triangle triangle, Point center, double angle) {
        if (triangle == null) {
            return null;
        }
        return rotate(triangle.getP1(), triangle.getP2(), triangle.getP3(), center, angle);
    }

    /**
     * 将三角形(p1,p2,p3)以center为中心,逆时针旋转angle角度
     *
     * @param p1     三角形的第一个点
     * @param p2     三角形的第二个点
     * @param p3     三角形的第三个点
     * @param center 旋转的中心
     * @param angle  逆时针旋转的角度
     * @return 旋转后的三角形
     */
    public static Triangle rotate(Point p1, Point p2, Point p3, Point center, double angle) {
        if (p1 == null || p2 == null || p3 == null) {
            return null;
        }
        return rotate(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), center.getX(), center.getY(), angle);
    }

    /**
     * 将由点(x1,y1),点(x2,y2)和点(x3,y3)构成的三角形,以点(cx,cy)为中心,逆时针旋转angle角度
     *
     * @param x1    三角形第一个点的横坐标
     * @param y1    三角形第一个点的纵坐标
     * @param x2    三角形第二个点的横坐标
     * @param y2    三角形第二个点的纵坐标
     * @param x3    三角形第三个点的横坐标
     * @param y3    三角形第三个点的纵坐标
     * @param cx    旋转中心点的横坐标
     * @param cy    旋转中心点的纵坐标
     * @param angle 旋转的角度
     * @return 旋转后的三角形
     */
    public static Triangle rotate(double x1, double y1, double x2, double y2, double x3, double y3, double cx, double cy, double angle) {
        Point p1 = PointUtil.rotate(x1, y1, cx, cy, angle);
        Point p2 = PointUtil.rotate(x2, y2, cx, cy, angle);
        Point p3 = PointUtil.rotate(x3, y3, cx, cy, angle);
        return new Triangle(p1, p2, p3);
    }

    /**
     * 获得三角形的轴对称图形
     *
     * @param triangle 三角形
     * @param axis     对称轴
     * @return 轴对称三角形
     */
    public static Triangle getAxialSymmetry(Triangle triangle, Line axis) {
        if (triangle == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(triangle.getP1(), triangle.getP2(), triangle.getP3(), axis);
    }

    /**
     * 获得三角形(p1,p2,p3)的轴对称图形
     *
     * @param p1   三角形的第一个点
     * @param p2   三角形的第二个点
     * @param p3   三角形的第三个点
     * @param axis 对称轴
     * @return 轴对称三角形
     */
    public static Triangle getAxialSymmetry(Point p1, Point p2, Point p3, Line axis) {
        if (p1 == null || p2 == null || p3 == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), axis.getA(), axis.getB(), axis.getC());
    }

    /**
     * 获得由点(x1,y1),点(x2,y2)和点(x3,y3)构成的三角形,以直线ax+by+c=0为对称轴的轴对称图形
     *
     * @param x1 三角形的第一个点的横坐标
     * @param y1 三角形的第一个点的纵坐标
     * @param x2 三角形的第二个点的横坐标
     * @param y2 三角形的第二个点的纵坐标
     * @param x3 三角形的第三个点的横坐标
     * @param y3 三角形的第三个点的纵坐标
     * @param a  直线的系数a
     * @param b  直线的系数b
     * @param c  直线的系数c
     * @return 轴对称三角形
     */
    public static Triangle getAxialSymmetry(double x1, double y1, double x2, double y2, double x3, double y3, double a, double b, double c) {
        Point p1 = PointUtil.getAxialSymmetry(x1, y1, a, b, c);
        Point p2 = PointUtil.getAxialSymmetry(x2, y2, a, b, c);
        Point p3 = PointUtil.getAxialSymmetry(x3, y3, a, b, c);
        return new Triangle(p1, p2, p3);
    }

    /**
     * 求三角形相对于线段的轴对称图形
     *
     * @param triangle 三角形
     * @param axis     对称轴
     * @return 轴对称三角形
     */
    public static Triangle getAxialSymmetry(Triangle triangle, Segment axis) {
        if (triangle == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(triangle.getP1(), triangle.getP2(), triangle.getP3(), axis.getP1(), axis.getP2());
    }

    /**
     * 求三角形(p1,p2,p3)相对于线段(ap1,ap2)的轴对称图形
     *
     * @param p1  三角形的第一个点
     * @param p2  三角形的第二个点
     * @param p3  三角形的第三个点
     * @param ap1 对称轴线段的第一个点
     * @param ap2 对称轴线段的第二个点
     * @return 轴对称三角形
     */
    public static Triangle getAxialSymmetry(Point p1, Point p2, Point p3, Point ap1, Point ap2) {
        if (p1 == null || p2 == null || p3 == null || ap1 == null || ap2 == null) {
            return null;
        }
        return getAxialSymmetry(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), ap1.getX(), ap1.getY(), ap2.getX(), ap2.getY());
    }

    /**
     * 求由点(x1,y1),点(x2,y2)和点(x3,y3)构成的三角形,相对于由点(ax1,ay1)和点(ax2,ay2)构成的线段的轴对称图形
     *
     * @param x1  三角形的第一个点的横坐标
     * @param y1  三角形的第一个点的纵坐标
     * @param x2  三角形的第二个点的横坐标
     * @param y2  三角形的第二个点的纵坐标
     * @param x3  三角形的第三个点的横坐标
     * @param y3  三角形的第三个点的纵坐标
     * @param ax1 对称轴线段的第一个点的横坐标
     * @param ay1 对称轴线段的第一个点的纵坐标
     * @param ax2 对称轴线段的第二个点的横坐标
     * @param ay2 对称轴线段的第二个点的纵坐标
     * @return 轴对称三角形
     */
    public static Triangle getAxialSymmetry(double x1, double y1, double x2, double y2, double x3, double y3, double ax1, double ay1, double ax2, double ay2) {
        Line line = LineUtil.getLine(ax1, ay1, ax2, ay2);
        return getAxialSymmetry(x1, y1, x2, y2, x3, y3, line.getA(), line.getB(), line.getC());
    }
}
