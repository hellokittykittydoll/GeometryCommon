package com.fudaowang.common.util;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Round;
import com.fudaowang.common.graph.Segment;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * 对圆进行操作的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/1/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoundUtil {
    /**
     * 求直线与圆的交点
     *
     * @param line  直线
     * @param round 圆
     * @return 交点集合, 可能是0-2个点
     */
    public static List intersect(Line line, Round round) {
        if (line == null || round == null) {
            return new ArrayList(0);
        }
        return intersect(line.getA(), line.getB(), line.getC(),
                round.getCenter().getX(), round.getCenter().getY(), round.getRadius());
    }

    /**
     * 求直线lax+lby+lc=0与圆(x-cx)^2+(y-cy)^2=radius^2的交点
     *
     * @param la     直线的系数a
     * @param lb     直线的系数a
     * @param lc     直线的系数a
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @return 交点集合, 可能有0-2个点
     */
    public static List intersect(double la, double lb, double lc, double cx, double cy, double radius) {
        List list = new ArrayList(2);
        double distance = LineUtil.distance(cx, cy, la, lb, lc);
        if (distance > radius) {
            return list;
        }

        Point vertex = LineUtil.verticalPoint(cx, cy, la, lb, lc);

        if (distance == radius) {
            list.add(vertex);
            return list;
        }

        double angle = Math.acos(distance / radius);
        Point p1 = Util.rotateAndStretch(vertex.getX(), vertex.getY(), cx, cy, angle, radius / distance);
        Point p2 = Util.rotateAndStretch(vertex.getX(), vertex.getY(), cx, cy, -angle, radius / distance);

        list.add(p1);
        list.add(p2);

        return list;
    }

    /**
     * 求线段与圆的交点
     *
     * @param segment 线段
     * @param round   圆
     * @return 交点集合, 可能有0-2个点
     */
    public static List intersect(Segment segment, Round round) {
        if (segment == null || round == null) {
            return new ArrayList(0);
        }
        return intersect(segment.getP1(), segment.getP2(), round.getCenter(), round.getRadius());
    }

    /**
     * 求线段(p1,p2)与圆的交点
     *
     * @param p1     线段的第一个端点
     * @param p2     线段的第二个端点
     * @param center 圆心点
     * @param radius 圆的半径
     * @return 交点集合, 可能有0-2个点
     */
    public static List intersect(Point p1, Point p2, Point center, double radius) {
        if (p1 == null || p2 == null || center == null) {
            return new ArrayList(0);
        }
        return intersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), center.getX(), center.getY(), radius);
    }

    /**
     * 求由点()和点()构成的线段与圆(x-cx)^2+(y-cy)^2=radius^2的交点
     *
     * @param x1     线段的第一个点的横坐标
     * @param y1     线段的第一个点的纵坐标
     * @param x2     线段的第二个点的横坐标
     * @param y2     线段的第二个点的纵坐标
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @return 交点集合, 可能有0-2个点
     */
    public static List intersect(final double x1, final double y1, final double x2, final double y2, double cx, double cy, double radius) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        List list = intersect(line.getA(), line.getB(), line.getC(), cx, cy, radius);
        CollectionUtils.filter(list, new Predicate() {
            public boolean evaluate(Object o) {
                Point p = (Point) o;
                return LineUtil.inSegment(x1, y1, x2, y2, p.getX(), p.getY());
            }
        });
        return list;
    }

    /**
     * 求点到圆的最短距离
     *
     * @param point 点
     * @param round 圆
     * @return 到圆的最短距离
     */
    public static double distance(Point point, Round round) {
        if (point == null || round == null) {
            return Double.NaN;
        }
        return distance(point.getX(), point.getY(), round.getCenter().getX(), round.getCenter().getY(), round.getRadius());
    }

    /**
     * 求点(px,py)到圆(x-cx)^2+(y-cy)^2=radius^2的最短距离
     *
     * @param px     点的横坐标
     * @param py     点的纵坐标
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @return 点到圆的最短距离
     */
    public static double distance(double px, double py, double cx, double cy, double radius) {
        return Math.abs(radius - Util.distance(px, py, cx, cy));
    }

    /**
     * 在指定的精度范围内,判定点是否在圆上
     *
     * @param point     点
     * @param round     圆
     * @param precision 指定的精度
     * @return 若点到圆的距离小于精度则返回true
     */
    public static boolean onRound(Point point, Round round, double precision) {
        if (point == null) {
            throw new NullPointerException("给定的点为null");
        }
        if (point == null) {
            throw new NullPointerException("圆为null");
        }
        return onRound(point.getX(), point.getY(),
                round.getCenter().getX(), round.getCenter().getY(), round.getRadius(), precision);
    }

    /**
     * 在指定精度范围内,判定点是(px,py)否在圆(x-cx)^2+(y-cy)^2=radius^2上
     *
     * @param px        点的横坐标
     * @param py        点的纵坐标
     * @param cx        圆心的横坐标
     * @param cy        圆心的纵坐标
     * @param radius    圆的半径
     * @param precision 给定的精度
     * @return 若点到圆的最短距离在精度范围内, 则返回true
     */
    public static boolean onRound(double px, double py, double cx, double cy, double radius, double precision) {
        double distance = distance(px, py, cx, cy, radius);
        return distance < precision;
    }

    /**
     * 将圆沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param round 圆
     * @param x     横坐标的平移长度
     * @param y     纵坐标的平移长度
     * @return 平移后的圆
     */
    public static Round translation(Round round, double x, double y) {
        if (round == null) {
            return null;
        }
        return translation(round.getCenter().getX(), round.getCenter().getY(), round.getRadius(), x, y);
    }

    /**
     * 将圆沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @param x      横坐标的平移长度
     * @param y      纵坐标的平移长度
     * @return 平移后的圆
     */
    public static Round translation(double cx, double cy, double radius, double x, double y) {
        Point center = new Point(cx + x, cy + y);
        return new Round(center, radius);
    }

    /**
     * 判断给定的点是否在圆内
     *
     * @param point 点
     * @param round 圆
     * @return 若点在圆范围内则返回true
     */
    public static boolean inRound(Point point, Round round) {
        if (point == null) {
            throw new NullPointerException("判定的点为null");
        }
        if (round == null) {
            throw new NullPointerException("判定的圆为null");
        }
        return inRound(point, round.getCenter(), round.getRadius());
    }

    /**
     * 判断给定的点是否在圆内
     *
     * @param point  给定的点
     * @param center 圆心点
     * @param radius 圆的半径
     * @return 若点在圆范围内则返回true
     */
    public static boolean inRound(Point point, Point center, double radius) {
        if (point == null) {
            throw new NullPointerException("判定的点为null");
        }
        if (center == null) {
            throw new NullPointerException("圆心点为null");
        }
        return inRound(point.getX(), point.getY(), center.getX(), center.getY(), radius);
    }

    /**
     * 判断给定的点是否在圆内
     *
     * @param px     给定点的横坐标
     * @param py     给定点的纵坐标
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @return 若点在圆范围内则返回true
     */
    public static boolean inRound(double px, double py, double cx, double cy, double radius) {
        return Util.distance(px, py, cx, cy) < radius;
    }

    /**
     * 求给定点与圆的切线
     *
     * @param point 给定的点
     * @param round 圆
     * @return 若给定点在圆外, 则计算出两个对应的切点
     */
    public static Point[] tangentPoint(Point point, Round round) {
        if (point == null) {
            throw new NullPointerException("给定的点为null");
        }
        if (round == null) {
            throw new NullPointerException("给定的圆为null");
        }
        return tangentPoint(point, round.getCenter(), round.getRadius());
    }

    /**
     * 求给定点与圆的切线
     *
     * @param point  给定的点
     * @param center 圆心点
     * @param radius 圆的半径
     * @return 若给定点在圆外, 则计算出两个对应的切点
     */
    public static Point[] tangentPoint(Point point, Point center, double radius) {
        if (point == null) {
            throw new NullPointerException("给定的点为null");
        }
        if (center == null) {
            throw new NullPointerException("圆心点为null");
        }
        return tangentPoint(point.getX(), point.getY(), center.getX(), center.getY(), radius);
    }

    /**
     * 求给定点与圆的切线
     *
     * @param px     给定点的横坐标
     * @param py     给定点的纵坐标
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @return 若给定点在圆外, 则计算出两个对应的切点
     */
    public static Point[] tangentPoint(double px, double py, double cx, double cy, double radius) {
        double distance = Util.distance(px, py, cx, cy);

        if (distance <= radius) {
            return new Point[0];
        }

        double d = radius / distance;
        double theta = Math.acos(d);
        Point p1 = Util.rotateAndStretch(px, py, cx, cy, theta, d);
        Point p2 = Util.rotateAndStretch(px, py, cx, cy, -theta, d);
        return new Point[]{p1, p2};
    }
}
