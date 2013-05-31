package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.Coordinate;
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
     * @param line      直线
     * @param hyperbola 反比例函数
     * @return 反比例函数与直线的交点
     */
    public static Point[] intersect(Line line, Hyperbola hyperbola) {
        if (hyperbola == null || line == null) {
            return new Point[0];
        }
        return intersect(line.getA(), line.getB(), line.getC(), hyperbola.getK(), hyperbola.getX(), hyperbola.getY());
    }

    /**
     * 求反比例函数y=k/x与直线ax+by+c=0的交点
     *
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c    @return 反比例函数与直线的交点
     * @param k 反比例函数的系数k
     * @param x 反比例函数原点的横坐标
     * @param y 反比例函数原点的纵坐标
     */
    public static Point[] intersect(double a, double b, double c, double k, double x, double y) {
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

        List<Point> list = new ArrayList<Point>();
        if (!Double.isNaN(py1)) {
            list.add(new Point(px1, py1));
        }

        if (!Double.isNaN(py2)) {
            list.add(new Point(px2, py2));
        }

        return PointUtil.toArray(list);
    }

    /**
     * 求反比例函数对应x的y值
     *
     * @param hyperbola 反比例函数
     * @param x         x值
     * @return y值
     */
    public static double getY(Hyperbola hyperbola, double x) {
        if (hyperbola == null) {
            return Double.NaN;
        }
        return getY(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), x);
    }

    /**
     * 求反比例函数对应x的y值
     *
     * @param k  反比例函数的系数k
     * @param x  反比例函数的原点横坐标
     * @param y  反比例函数的原点横坐标
     * @param x0 x值
     * @return y值
     */
    public static double getY(double k, double x, double y, double x0) {
        if (NumberUtil.isZero(k)) {
            return Double.NaN;
        }

        if (NumberUtil.equal(x, x0)) {
            return Double.NaN;
        }

        return k / (x0 - x) + y;
    }

    /**
     * 求反比例函数对应y的x值
     *
     * @param hyperbola 反比例函数
     * @param y         y值
     * @return x值
     */
    public static double getX(Hyperbola hyperbola, double y) {
        if (hyperbola == null) {
            return Double.NaN;
        }
        return getX(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), y);
    }

    /**
     * 求反比例函数对应y的x值
     *
     * @param k  反比例函数的系数k
     * @param x  反比例函数的原点横坐标
     * @param y  反比例函数的原点横坐标
     * @param y0 y值
     * @return x值
     */
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
     * @param hyperbola  给定的双曲线
     * @param coordinate 相对坐标
     * @return 绝对坐标下的双曲线
     */
    public static Hyperbola toAbsoluteCoordinate(Hyperbola hyperbola, Coordinate coordinate) {
        return hyperbola == null || coordinate == null || !coordinate.isSymmetrical() ? null :
                toAbsoluteCoordinate(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX());
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
     * @param hyperbola  给定的双曲线
     * @param coordinate 相对坐标
     * @return 相对坐标下的双曲线
     */
    public static Hyperbola toRelativeCoordinate(Hyperbola hyperbola, Coordinate coordinate) {
        return hyperbola == null || coordinate == null || !coordinate.isSymmetrical() ? null :
                toRelativeCoordinate(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX());
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

    /**
     * 在最小精度范围内判断两个双曲线是否重合
     *
     * @param h1 第一个双曲线
     * @param h2 第二个双曲线
     * @return 若两个双曲线重合则返回true
     */
    public static boolean coincide(Hyperbola h1, Hyperbola h2) {
        return coincide(h1, h2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定精度范围内判断两个双曲线是否重合
     *
     * @param h1        第一个双曲线
     * @param h2        第二个双曲线
     * @param precision 给定的精度
     * @return 若两个双曲线重合则返回true
     */
    public static boolean coincide(Hyperbola h1, Hyperbola h2, double precision) {
        if (h1 == null || h2 == null) {
            throw new NullPointerException("双曲线为null");
        }
        return coincide(h1.getK(), h1.getX(), h1.getY(), h2.getK(), h2.getX(), h2.getY(), precision);
    }

    public static boolean coincide(double k1, double x1, double y1, double k2, double x2, double y2) {
        return coincide(k1, x1, y1, k2, x2, y2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定精度范围内判断两个双曲线是否重合
     *
     * @param k1        第一个双曲线的系数k
     * @param x1        第一个双曲线原点的横坐标
     * @param y1        第一个双曲线原点的纵坐标
     * @param k2        第二个双曲线的系数k
     * @param x2        第二个双曲线原点的横坐标
     * @param y2        第二个双曲线原点的纵坐标
     * @param precision 给定的精度
     * @return 若两个双曲线重合则返回true
     */
    public static boolean coincide(double k1, double x1, double y1, double k2, double x2, double y2, double precision) {
        if (NumberUtil.isZero(k1) || NumberUtil.isZero(k2)) {
            throw new IllegalArgumentException("双曲线的系数k不能为0");
        }
        return PointUtil.coincide(x1, y1, x2, y2, precision) && NumberUtil.equal(k1, k2, precision);
    }
}
