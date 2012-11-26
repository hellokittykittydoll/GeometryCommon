package com.fudaowang.common.util;

import com.fudaowang.common.graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用基础方法
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    /**
     * 计算从点(x1,y1)到点(x2,y2)的距离
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     * @return 两点之间的距离
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        return java.awt.geom.Point2D.distance(x1, y1, x2, y2);
    }

    /**
     * 计算两点之间的距离
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 两点之间的距离
     */
    public static double distance(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            return Double.NaN;
        }
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 获得两点之间的中点
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 两点之间的中点
     */
    public static Point getMidPoint(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            return null;
        }

        return getMidPoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 获得点(x1,y1)与点(x2,y2)之间的中点
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点横坐标
     * @param y2 第二个点的纵坐标
     * @return 两点之间的中点
     */
    public static Point getMidPoint(double x1, double y1, double x2, double y2) {
        double x = (x1 + x2) / 2;
        double y = (y1 + y2) / 2;
        return new Point(x, y);
    }

    /**
     * 以center为中心,获得point点的中心对称点
     *
     * @param point  给定的点
     * @param center 中心点
     * @return 给定点相对于中心点的中心对称点
     */
    public static Point getCenterSymmetricalPoint(Point point, Point center) {
        if (point == null || center == null) {
            return null;
        }

        return getCenterSymmetricalPoint(point.getX(), point.getY(), center.getX(), center.getY());
    }

    /**
     * 以点(cx,cy)为中心,获得点(px,py)的中心对称点
     *
     * @param px 给定点的横坐标
     * @param py 给定点的纵坐标
     * @param cx 中心点的横坐标
     * @param cy 中心点的纵坐标
     * @return 给定点相对于中心点的中心对称点
     */
    public static Point getCenterSymmetricalPoint(double px, double py, double cx, double cy) {
        double x = 2 * cx - px;
        double y = 2 * cy - py;
        return new Point(x, y);
    }

    /**
     * 求点相对于直线的轴对称点
     *
     * @param point 点
     * @param line  直线
     * @return 轴对称点
     */
    public static Point getAxialSymmetricalPoint(Point point, Line line) {
        if (point == null || line == null) {
            return null;
        }
        return getAxialSymmetricalPoint(point.getX(), point.getY(), line.getA(), line.getB(), line.getC());
    }

    /**
     * 求点(x,y)相对于直线ax+by+c=0的轴对称点
     *
     * @param x 点的横坐标
     * @param y 点的纵坐标
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @return 轴对称点
     */
    public static Point getAxialSymmetricalPoint(double x, double y, double a, double b, double c) {
        Point point = LineUtil.verticalPoint(x, y, a, b, c);
        return getCenterSymmetricalPoint(x, y, point.getX(), point.getY());
    }

    /**
     * 判断两个数值之差是否小于给定的精度值
     *
     * @param d1        第一个数值
     * @param d2        第二个数值
     * @param precision 给定的精度值
     * @return 若两数值之差小于精度, 则返回true
     */
    public static boolean equal(double d1, double d2, double precision) {
        return Math.abs(d1 - d2) < precision;
    }

    /**
     * 判断两个点在精度范围内是否重合
     *
     * @param p1        第一个点
     * @param p2        第二个点
     * @param precision 给定的精度
     * @return 若两点横纵坐标之差均小于给定精度, 则返回true
     */
    public static boolean equal(Point p1, Point p2, double precision) {
        return !(p1 == null || p2 == null)
                && equal(p1.getX(), p2.getX(), precision) && equal(p1.getY(), p2.getY(), precision);
    }

    /**
     * 将point以center为中心旋转angle角度后的坐标值
     *
     * @param point  给定的点
     * @param center 给定的中心点
     * @param angle  想要旋转的角度,此处为弧度值
     * @return 旋转后的点
     */
    public static Point rotate(Point point, Point center, double angle) {
        return rotateAndStretch(point, center, angle, 1);
    }

    /**
     * 将点(px,py)以点(cx,cy)为中心,旋转angle角度
     *
     * @param px    给定点的横坐标
     * @param py    给定点的纵坐标
     * @param cx    中心点的横坐标
     * @param cy    中心点的纵坐标
     * @param angle 想要旋转的角度,此处为弧度值
     * @return 旋转后的点
     */
    public static Point rotate(double px, double py, double cx, double cy, double angle) {
        return rotateAndStretch(px, py, cx, cy, angle, 1);
    }

    /**
     * 将point以center为中心,旋转angle角度,并将距离拉伸为ratio倍后的坐标值
     *
     * @param point  给定的点
     * @param center 给定的中心点
     * @param angle  想要旋转的角度,此处为弧度值
     * @param ratio  想要拉伸的比率
     * @return 将point以center为中心旋转angle角度并拉伸到ratio倍距离后的坐标值
     */
    public static Point rotateAndStretch(Point point, Point center, double angle, double ratio) {
        if (point == null || center == null) {
            return null;
        }

        return rotateAndStretch(point.getX(), point.getY(), center.getX(), center.getY(), angle, ratio);
    }

    public static Point rotateAndStretch(double px, double py, double cx, double cy, double angle, double ratio) {
        if (coincide(px, py, cx, cy)) {
            return null;
        }

        double p = distance(px, py, cx, cy) * ratio;
        double theta = Math.atan2(py - cy, px - cx);
        double rotateTheta = theta + angle;
        double x = p * Math.cos(rotateTheta);
        double y = p * Math.sin(rotateTheta);
        return new Point(x + cx, y + cy);
    }

    /**
     * 将point以center为中心,将距离拉伸到ratio倍
     *
     * @param point  给定的点
     * @param center 给定的中心点
     * @param ratio  想要拉伸的比率
     * @return 拉伸后的点
     */
    public static Point stretch(Point point, Point center, double ratio) {
        return rotateAndStretch(point, center, 0, ratio);
    }

    /**
     * 将点(px,py)以点(cx,cy)为中心,将距离拉伸到ratio倍
     *
     * @param px    给定点的横坐标
     * @param py    给定点的纵坐标
     * @param cx    中心点的横坐标
     * @param cy    中心点的纵坐标
     * @param ratio 想要拉伸的比例
     * @return 拉伸后的点
     */
    public static Point stretch(double px, double py, double cx, double cy, double ratio) {
        return rotateAndStretch(px, py, cx, cy, 0, ratio);
    }

    /**
     * 判定两个点是否重合
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 只有当两点的横纵坐标完全相同时, 才返回true
     */
    public static boolean coincide(Point p1, Point p2) {
        return coincide(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 判定点(p1x,p1y)与点(p2x,p2y)是否重合时
     *
     * @param p1x 第一个点的横坐标
     * @param p1y 第一个点的纵坐标
     * @param p2x 第二个点的横坐标
     * @param p2y 第二个点的纵坐标
     * @return 只有当两点的横纵坐标完全相同时, 才返回true
     */
    public static boolean coincide(double p1x, double p1y, double p2x, double p2y) {
        return p1x == p2x && p1y == p2y;
    }

    /**
     * 判断给定的数值大小是否在给定的范围内
     *
     * @param d1 数值的上限或者下限
     * @param d2 数值的上限或者下限
     * @param d  需要判断的数值
     * @return 若给定的数值在范围之内, 则返回true
     */
    public static boolean valueInRange(double d1, double d2, double d) {
        return d > Math.min(d1, d2) && d < Math.max(d1, d2);
    }

    /**
     * 将给定的点沿横坐标正方向平移x,沿纵坐标方向平移y
     *
     * @param point 点
     * @param x     横坐标平移的长度
     * @param y     纵坐标平移的长度
     * @return 平移后得到的点
     */
    public static Point translation(Point point, double x, double y) {
        if (point == null) {
            return null;
        }
        return translation(point.getX(), point.getY(), x, y);
    }

    /**
     * 将点(x0,y0)沿横坐标正方向平移x,沿纵坐标方向平移y
     *
     * @param x0 原横坐标
     * @param y0 原纵坐标
     * @param x  横坐标平移的长度
     * @param y  纵坐标平移的长度
     * @return 平移后得到的点
     */
    public static Point translation(double x0, double y0, double x, double y) {
        return new Point(x0 + x, y0 + y);
    }

    /**
     * 将Point数组转化为list
     *
     * @param points Point的数组
     * @return Point的list
     */
    public static List toList(Point[] points) {
        List list = new ArrayList();
        if (points == null) {
            return list;
        }
        for (int i = 0; i < points.length; i++) {
            list.add(points[i]);
        }
        return list;
    }

    /**
     * 将Point的list转化为数组
     *
     * @param list Point的list
     * @return Point的数组
     */
    public static Point[] toArray(List list) {
        if (list == null) {
            return new Point[0];
        }
        return (Point[]) list.toArray(new Point[list.size()]);
    }
}
