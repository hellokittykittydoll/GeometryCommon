package com.fudaowang.util;

import com.fudaowang.graph.*;

/**
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
     * @param point 点
     * @param line 直线
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
     * 求以center为顶点,p1p2为端点的角度
     *
     * @param center 顶点
     * @param p1     第一个端点
     * @param p2     第二个端点
     * @return 角的弧度值
     */
    public static double getAngleRadians(Point center, Point p1, Point p2) {
        if (center == null || p1 == null || p2 == null) {
            return Double.NaN;
        }

        return getAngleRadians(center.getX(), center.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 求以点(cx,cy)为顶点,点(p1x,p1y)和点(p2x,p2y)为端点的角度值
     *
     * @param cx  顶点的横坐标
     * @param cy  顶点的纵坐标
     * @param p1x 第一个端点的横坐标
     * @param p1y 第一个端点的纵坐标
     * @param p2x 第二个端点的横坐标
     * @param p2y 第二个端点的纵坐标
     * @return 角的弧度值
     */
    public static double getAngleRadians(double cx, double cy, double p1x, double p1y, double p2x, double p2y) {
        validateCoincide(cx, cy, p1x, p1y);
        validateCoincide(cx, cy, p2x, p2y);

        double a = distance(p1x, p1y, p2x, p2y);
        double b = distance(cx, cy, p1x, p1y);
        double c = distance(cx, cy, p2x, p2y);

        double cos = (b * b + c * c - a * a) / 2 * b * c;
        return Math.acos(cos);
    }

    /**
     * 求以center为顶点,p1p2为端点的角度
     *
     * @param center 顶点
     * @param p1     第一个端点
     * @param p2     第二个端点
     * @return 角的度数
     */
    public static double getAngleDegrees(Point center, Point p1, Point p2) {
        double arc = getAngleRadians(center, p1, p2);
        return Math.toDegrees(arc);
    }

    /**
     * 求以点(cx,cy)为顶点,点(p1x,p1y)和点(p2x,p2y)为端点的角度值
     *
     * @param cx  顶点的横坐标
     * @param cy  顶点的纵坐标
     * @param p1x 第一个端点的横坐标
     * @param p1y 第一个端点的纵坐标
     * @param p2x 第二个端点的横坐标
     * @param p2y 第二个端点的纵坐标
     * @return 角的度数值
     */
    public static double getAngleDegrees(double cx, double cy, double p1x, double p1y, double p2x, double p2y) {
        double arc = getAngleRadians(cx, cy, p1x, p1y, p2x, p2y);
        return Math.toDegrees(arc);
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
        validateCoincide(px, py, cx, cy);

        double p = distance(px, py, cx, cy);
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
     * 当两个点重合时,抛出IllegalArgumentException异常
     *
     * @param p1 第一个点
     * @param p2 第二个点
     */
    public static void validateCoincide(Point p1, Point p2) {
        validateCoincide(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 当点(p1x,p1y)与点(p2x,p2y)重合时,抛出IllegalArgumentException异常
     *
     * @param p1x 第一个点的横坐标
     * @param p1y 第一个点的纵坐标
     * @param p2x 第二个点的横坐标
     * @param p2y 第二个点的纵坐标
     */
    public static void validateCoincide(double p1x, double p1y, double p2x, double p2y) {
        if (p1x == p2x && p1y == p2y) {
            throw new IllegalArgumentException("两个点不能重合");
        }
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
        return !(d < Math.min(d1, d2) || d > Math.max(d1, d2));
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
}
