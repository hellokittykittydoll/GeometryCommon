package com.fudaowang.common.util;

import com.fudaowang.common.graph.Angle;
import com.fudaowang.common.graph.Point;

/**
 * 对角进行操作的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/7/12
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class AngleUtil {
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
        Util.validateCoincide(cx, cy, p1x, p1y);
        Util.validateCoincide(cx, cy, p2x, p2y);

        double a = Util.distance(p1x, p1y, p2x, p2y);
        double b = Util.distance(cx, cy, p1x, p1y);
        double c = Util.distance(cx, cy, p2x, p2y);

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
     * 判断点是否在角的范围内
     * @param point 点
     * @param angle 角
     * @return 若点在夹角内,则返回true
     */
    public static boolean pointInAngle(Point point, Angle angle) {
        if (point == null) {
            throw new NullPointerException("给定的点为null");
        }

        if (angle == null) {
            throw new NullPointerException("判断的角为null");
        }
        return pointInAngle(point, angle.getVertex(), angle.getP1(), angle.getP2());
    }

    /**
     * 判断点是否在角的范围内
     * @param point 点
     * @param vertex 角的顶点
     * @param p1 角的第一个端点
     * @param p2 角的第二个端点
     * @return 若点在夹角内,则返回true
     */
    public static boolean pointInAngle(Point point, Point vertex, Point p1, Point p2) {
        if (point == null) {
            throw new NullPointerException("给定的点为null");
        }

        if (vertex == null) {
            throw new NullPointerException("角的顶点为null");
        }

        if (p1 == null || p2 == null) {
            throw new NullPointerException("角的端点为null");
        }

        return pointInAngle(point.getX(), point.getY(), vertex.getX(), vertex.getY(),
                p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 判断点是否在角的范围内
     * @param x 点的横坐标
     * @param y 点的纵坐标
     * @param vx 角的顶点的横坐标
     * @param vy 角的顶点的纵坐标
     * @param x1 角的第一个端点的横坐标
     * @param y1 角的第一个端点的纵坐标
     * @param x2 角的第二个端点的横坐标
     * @param y2 角的第二个端点的纵坐标
     * @return 若点在夹角内,则返回true
     */
    public static boolean pointInAngle(double x, double y, double vx, double vy, double x1, double y1, double x2, double y2) {
        double angle1 = getAngleRadians(vx, vy, x, y, x1, y1);
        double angle2 = getAngleRadians(vx, vy, x, y, x2, y2);
        double angle = getAngleRadians(vx, vy, x1, y1, x2, y2);

        return angle > angle1 && angle > angle2;
    }
}
