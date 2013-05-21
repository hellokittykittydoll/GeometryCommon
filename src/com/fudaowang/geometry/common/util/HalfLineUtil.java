package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.HalfLine;
import com.fudaowang.geometry.common.graph.Point;

/**
 * 射线的相关方法
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 5/16/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class HalfLineUtil {

    /**
     * 判断给定的点在最小精度范围内是否在射线上,不包括射线以外的部分
     *
     * @param point    给定的点
     * @param halfLine 给定的射线
     * @return 若点符合在射线上且在射线的范围内, 则返回true
     */
    public static boolean inHalfLine(Point point, HalfLine halfLine) {
        if (point == null) {
            throw new NullPointerException("point is null");
        }

        if (halfLine == null) {
            throw new NullPointerException("halfline is null");
        }

        return inHalfLine(point, halfLine, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断给定的点在最小精度范围内是否在射线上,不包括射线以外的部分
     *
     * @param px          给定的点的横坐标
     * @param py          给定的点的纵坐标
     * @param vx          射线端点的横坐标
     * @param vy          射线端点的纵坐标
     * @param a           射线所在直线的系数a
     * @param b           射线所在直线的系数b
     * @param c           射线所在直线的系数c
     * @param orientation 射线的方向
     * @return 若点符合在射线上且在射线的范围内, 则返回true
     */
    public static boolean inHalfLine(double px, double py, double vx, double vy,
                                     double a, double b, double c, HalfLine.Orientation orientation) {
        return inHalfLine(px, py, vx, vy, a, b, c, orientation, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断给定的点是否在最小精度范围内,位于射线所在的直线上
     *
     * @param point    给定的点
     * @param halfLine 给定的射线
     * @return 若点在射线上, 无论是否在射线范围内, 则返回true
     */
    public static boolean onHalfLine(Point point, HalfLine halfLine) {
        if (point == null) {
            throw new NullPointerException("point is null");
        }

        if (halfLine == null) {
            throw new NullPointerException("halfline is null");
        }

        return onHalfLine(point, halfLine, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断给定的点在指定精度范围内是否在射线上,不包括射线以外的部分
     *
     * @param point     给定的点
     * @param halfLine  给定的射线
     * @param precision 给定的精度
     * @return 若点符合在射线上且在射线的范围内, 则返回true
     */
    public static boolean inHalfLine(Point point, HalfLine halfLine, double precision) {
        if (point == null) {
            throw new NullPointerException("point is null");
        }

        if (halfLine == null) {
            throw new NullPointerException("halfline is null");
        }

        return inHalfLine(point.getX(), point.getY(), halfLine.getX(), halfLine.getY(),
                halfLine.getA(), halfLine.getB(), halfLine.getC(), halfLine.getOrientation(), precision);
    }

    /**
     * 判断给定的点在指定精度范围内是否在射线上,不包括射线以外的部分
     *
     * @param px          给定的点的横坐标
     * @param py          给定的点的纵坐标
     * @param vx          射线端点的横坐标
     * @param vy          射线端点的纵坐标
     * @param a           射线所在直线的系数a
     * @param b           射线所在直线的系数b
     * @param c           射线所在直线的系数c
     * @param orientation 射线的方向
     * @param precision   给定的精度
     * @return 若点符合在射线上且在射线的范围内, 则返回true
     */
    public static boolean inHalfLine(double px, double py, double vx, double vy,
                                     double a, double b, double c, HalfLine.Orientation orientation, double precision) {
        return LineUtil.onLine(px, py, a, b, c, precision) && orientation == getOrientation(vx, vy, px, py);
    }

    /**
     * 判断给定的点在指定精度范围内是否在射线上,不包括射线以外的部分
     *
     * @param point     给定的点
     * @param halfLine  给定的射线
     * @param precision 给定的精度
     * @return 若点符合在射线上且在射线的范围内, 则返回true
     */
    public static boolean onHalfLine(Point point, HalfLine halfLine, double precision) {
        if (point == null) {
            throw new NullPointerException("point is null");
        }

        if (halfLine == null) {
            throw new NullPointerException("halfline is null");
        }

        return LineUtil.onLine(point, halfLine, precision);
    }

    /**
     * 判断射线所在的主要方向
     * 判断射线上的点和端点的横纵坐标差值,若x方向差值较大,则射线主要方向为±X,反之则为±Y
     *
     * @param center 射线的端点
     * @param point  射线上的任意点
     * @return 射线的主要方向
     */
    public static HalfLine.Orientation getOrientation(Point center, Point point) {
        if (center == null) {
            throw new NullPointerException("center is null");
        }

        if (point == null) {
            throw new NullPointerException("point is null");
        }

        return getOrientation(center.getX(), center.getY(), point.getX(), point.getY());
    }

    /**
     * 判断射线所在的主要方向
     * 判断射线上的点和端点的横纵坐标差值,若x方向差值较大,则射线主要方向为±X,反之则为±Y
     *
     * @param cx 射线端点的横坐标
     * @param cy 射线端点的纵坐标
     * @param px 射线上点的横坐标
     * @param py 射线上点的纵坐标
     * @return 射线的主要方向
     */
    public static HalfLine.Orientation getOrientation(double cx, double cy, double px, double py) {
        double x = px - cx;
        double y = py - cy;

        if (x > y) {
            return x > 0 ? HalfLine.Orientation.X : HalfLine.Orientation.MINUS_X;
        }

        return y > 0 ? HalfLine.Orientation.Y : HalfLine.Orientation.MINUS_Y;
    }
}
