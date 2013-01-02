package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.Hyperbola;
import com.fudaowang.geometry.common.graph.Line;
import com.fudaowang.geometry.common.graph.Point;

import java.util.ArrayList;
import java.util.List;

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
        return intersect(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), line.getA(), line.getB(), line.getC());
    }

    /**
     * 求反比例函数y=k/x与直线ax+by+c=0的交点
     *
     * @param k 反比例函数的系数k
     * @param x 反比例函数原点的横坐标
     * @param y 反比例函数原点的纵坐标
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c    @return 反比例函数与直线的交点
     */
    public static Point[] intersect(double k, double x, double y, double a, double b, double c) {
        if (NumberUtil.isZero(k)) {
            return new Point[0];
        }

        if (NumberUtil.isZero(a) && NumberUtil.isZero(b)) {
            return new Point[0];
        }

        double b0 = b * y - a * x + c;
        double delta = Math.pow(b0, 2) - 4 * a * (b * k - b * x * y - c * x);
        if (NumberUtil.isZero(delta)) {
            double px = -b0 / (2.0 * a);
            if (NumberUtil.equal(px, x) || Double.isNaN(px)) {
                return new Point[0];
            }
            double py = getY(k, x, y, px);
            return new Point[]{new Point(px, py)};
        }

        if (delta < 0) {
            return new Point[0];
        }

        double px1 = (-b0 + Math.sqrt(delta)) / (2.0 * a);
        double py1 = getY(k, x, y, px1);
        double px2 = (-b0 - Math.sqrt(delta)) / (2.0 * a);
        double py2 = getY(k, x, y, px2);

        List list = new ArrayList();
        if (!Double.isNaN(py1)) {
            list.add(new Point(px1, py1));
        }

        if (!Double.isNaN(py2)) {
            list.add(new Point(px2, py2));
        }

        return PointUtil.toArray(list);
    }

    public static double getY(Hyperbola hyperbola, double x) {
        if (hyperbola == null) {
            return Double.NaN;
        }
        return getY(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), x);
    }

    public static double getY(double k, double x, double y, double x0) {
        if (NumberUtil.isZero(k)) {
            return Double.NaN;
        }

        if (NumberUtil.equal(x, x0)) {
            return Double.NaN;
        }

        return k / (x0 - x) + y;
    }

    public static double getX(Hyperbola hyperbola, double y) {
        if (hyperbola == null) {
            return Double.NaN;
        }
        return getX(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), y);
    }

    public static double getX(double k, double x, double y, double y0) {
        if (NumberUtil.isZero(k)) {
            return Double.NaN;
        }

        if (NumberUtil.equal(y, y0)) {
            return Double.NaN;
        }

        return k / (y0 - y) + x;
    }

    /**
     * 将相对坐标下的双曲线转化为绝对坐标
     *
     * @param hyperbola 给定的双曲线
     * @param origin    相对坐标的原点
     * @param space     相对坐标单位长度的间隔
     * @return 绝对坐标下的双曲线
     */
    public static Hyperbola toAbsoluteCoordinate(Hyperbola hyperbola, Point origin, double space) {
        return hyperbola == null || origin == null ? null :
                toAbsoluteCoordinate(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), origin.getX(), origin.getY(), space);
    }

    /**
     * 将相对坐标下的双曲线转化为绝对坐标
     *
     * @param k       双曲线的系数k
     * @param x       双曲线原点的横坐标
     * @param y       双曲线原点的纵坐标
     * @param originX 相对坐标的原点的横坐标
     * @param originY 相对坐标的原点的纵坐标
     * @param space   相对坐标单位长度的间隔
     * @return 绝对坐标下的双曲线
     */
    public static Hyperbola toAbsoluteCoordinate(double k, double x, double y, double originX, double originY, double space) {
        if (!NumberUtil.isZero(k)) {
            throw new IllegalArgumentException("双曲线的系数k必须不0");
        }

        Point point = PointUtil.toAbsoluteCoordinate(x, y, originX, originY, space);
        return new Hyperbola(k, point);
    }

    /**
     * 将绝对坐标下的双曲线转化为相对坐标
     *
     * @param hyperbola 给定的双曲线
     * @param origin    相对坐标的原点
     * @param space     相对坐标单位长度的间隔
     * @return 相对坐标下的双曲线
     */
    public static Hyperbola toRelativeCoordinate(Hyperbola hyperbola, Point origin, double space) {
        return hyperbola == null || origin == null ? null :
                toRelativeCoordinate(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), origin.getX(), origin.getY(), space);
    }

    /**
     * 将绝对坐标下的双曲线转化为相对坐标
     *
     * @param k       双曲线的系数k
     * @param x       双曲线原点的横坐标
     * @param y       双曲线原点的纵坐标
     * @param originX 相对坐标的原点的横坐标
     * @param originY 相对坐标的原点的纵坐标
     * @param space   相对坐标单位长度的间隔
     * @return 相对坐标下的双曲线
     */
    public static Hyperbola toRelativeCoordinate(double k, double x, double y, double originX, double originY, double space) {
        if (!NumberUtil.isZero(k)) {
            throw new IllegalArgumentException("双曲线的系数k必须不0");
        }

        Point point = PointUtil.toRelativeCoordinate(x, y, originX, originY, space);
        return new Hyperbola(k, point);
    }
}
