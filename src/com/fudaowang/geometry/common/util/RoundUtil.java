package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.*;
import org.apache.commons.collections.Predicate;

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
     * @return 交点集, 可能是0-2个点
     */
    public static Point[] intersect(Line line, Round round) {
        if (line == null || round == null) {
            return new Point[0];
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
     * @return 交点集, 可能有0-2个点
     */
    public static Point[] intersect(double la, double lb, double lc, double cx, double cy, double radius) {
        double distance = LineUtil.distance(cx, cy, la, lb, lc);
        Point vertex = LineUtil.verticalPoint(cx, cy, la, lb, lc);

        if (NumberUtil.equal(distance, radius)) {
            return new Point[]{vertex};
        }

        if (distance > radius) {
            return new Point[0];
        }

        double side = Math.sqrt(distance * distance + radius * radius);
        double angle = LineUtil.getAngle(la, lb);

        double x = side * Math.cos(angle);
        double y = side * Math.cos(angle);
        Point p1 = PointUtil.translation(vertex, x, y);
        Point p2 = PointUtil.translation(vertex, -x, -y);

        return new Point[]{p1, p2};
    }

    /**
     * 求线段与圆的交点
     *
     * @param segment 线段
     * @param round   圆
     * @return 交点集, 可能有0-2个点
     */
    public static Point[] intersect(Segment segment, Round round) {
        if (segment == null || round == null) {
            return new Point[0];
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
     * @return 交点集, 可能有0-2个点
     */
    public static Point[] intersect(Point p1, Point p2, Point center, double radius) {
        if (p1 == null || p2 == null || center == null) {
            return new Point[0];
        }
        return intersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), center.getX(), center.getY(), radius);
    }

    /**
     * 求由点(x1,x2)和点(x2,y2)构成的线段与圆(x-cx)^2+(y-cy)^2=radius^2的交点
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
    public static Point[] intersect(final double x1, final double y1, final double x2, final double y2, double cx, double cy, double radius) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        Point[] points = intersect(line.getA(), line.getB(), line.getC(), cx, cy, radius);
        return PointUtil.filter(points, new Predicate() {
            public boolean evaluate(Object o) {
                Point p = (Point) o;
                return SegmentUtil.inSegment(p.getX(), p.getY(), x1, y1, x2, y2);
            }
        });
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
        return Math.abs(radius - PointUtil.distance(px, py, cx, cy));
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
        if (round == null) {
            throw new NullPointerException("圆为null");
        }
        return onRound(point.getX(), point.getY(),
                round.getCenter().getX(), round.getCenter().getY(), round.getRadius(), precision);
    }

    /**
     * 在最小精度范围内,判定点是否在圆上
     *
     * @param point 点
     * @param round 圆
     * @return 若点到圆的距离小于精度则返回true
     */
    public static boolean onRound(Point point, Round round) {
        return onRound(point, round, NumberUtil.MIN_VALUE);
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
        if (precision < NumberUtil.MIN_VALUE) {
            precision = NumberUtil.MIN_VALUE;
        }
        double distance = distance(px, py, cx, cy, radius);
        return distance < precision;
    }

    /**
     * 在最小精度范围内,判定点是(px,py)否在圆(x-cx)^2+(y-cy)^2=radius^2上
     *
     * @param px     点的横坐标
     * @param py     点的纵坐标
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     * @param radius 圆的半径
     * @return 若点到圆的最短距离在精度范围内, 则返回true
     */
    public static boolean onRound(double px, double py, double cx, double cy, double radius) {
        return onRound(px, py, cx, cy, radius, NumberUtil.MIN_VALUE);
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
        return NumberUtil.isLessThan(PointUtil.distance(px, py, cx, cy), radius);
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
        double distance = PointUtil.distance(px, py, cx, cy);
        if (NumberUtil.equal(distance, radius)) {
            return new Point[]{new Point(px, py)};
        }

        if (distance < radius) {
            return new Point[0];
        }

        double d = radius / distance;
        double theta = Math.acos(d);
        Point p1 = PointUtil.rotateAndStretch(px, py, cx, cy, theta, d);
        Point p2 = PointUtil.rotateAndStretch(px, py, cx, cy, -theta, d);
        return new Point[]{p1, p2};
    }

    /**
     * 在给定的精度范围内求两圆的位置关系
     *
     * @param r1        第一个圆
     * @param r2        第二个圆
     * @param precision 给定的精度
     * @return 两圆的位置关系
     */
    public static RoundEnum getRelationship(Round r1, Round r2, double precision) {
        if (r1 == null || r2 == null) {
            throw new NullPointerException("圆为null");
        }
        return getRelationship(r1.getCenter(), r1.getRadius(), r2.getCenter(), r2.getRadius(), precision);
    }

    /**
     * 在最小精度范围内求两圆的位置关系
     *
     * @param r1 第一个圆
     * @param r2 第二个圆
     * @return 两圆的位置关系
     */
    public static RoundEnum getRelationship(Round r1, Round r2) {
        return getRelationship(r1, r2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内求两圆的位置关系
     *
     * @param c1        第一个圆的圆心
     * @param r1        第一个圆的半径
     * @param c2        第二个圆的圆心
     * @param r2        第二个圆的半径
     * @param precision 给定的精度
     * @return 两圆的位置关系
     */
    public static RoundEnum getRelationship(Point c1, double r1, Point c2, double r2, double precision) {
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("圆心点为null");
        }
        return getRelationship(c1.getX(), c1.getY(), r1, c2.getX(), c2.getY(), r2, precision);
    }

    /**
     * 在最小精度范围内求两圆的位置关系
     *
     * @param c1 第一个圆的圆心
     * @param r1 第一个圆的半径
     * @param c2 第二个圆的圆心
     * @param r2 第二个圆的半径
     * @return 两圆的位置关系
     */
    public static RoundEnum getRelationship(Point c1, double r1, Point c2, double r2) {
        return getRelationship(c1, r1, c2, r2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内求两圆的位置关系
     *
     * @param x1        第一个圆的圆心的横坐标
     * @param y1        第一个圆的圆心的纵坐标
     * @param r1        第一个圆的半径
     * @param x2        第二个圆的圆心的横坐标
     * @param y2        第二个圆的圆心的纵坐标
     * @param r2        第一个圆的半径
     * @param precision 给定的精度
     * @return 两圆的位置关系
     */
    public static RoundEnum getRelationship(double x1, double y1, double r1, double x2, double y2, double r2, double precision) {
        if (NumberUtil.isMoreThanZero(r1) && NumberUtil.isMoreThanZero(r2)) {
            double distance = PointUtil.distance(x1, y1, x2, y2);

            if (NumberUtil.equal(distance, r1 + r2, precision)) {
                return RoundEnum.EXTERNAL;
            }

            if (distance > r1 + r2) {
                return RoundEnum.SEPARATE;
            }

            if (NumberUtil.equal(distance, Math.abs(r1 - r2), precision)) {
                return RoundEnum.INTERNAL;
            }

            if (distance < Math.abs(r1 - r2)) {
                return RoundEnum.CONTAIN;
            }

            return RoundEnum.INTERSECT;
        }

        throw new IllegalArgumentException("圆的半径长度必须大于0");
    }

    /**
     * 在最小精度范围内求两圆的位置关系
     *
     * @param x1 第一个圆的圆心的横坐标
     * @param y1 第一个圆的圆心的纵坐标
     * @param r1 第一个圆的半径
     * @param x2 第二个圆的圆心的横坐标
     * @param y2 第二个圆的圆心的纵坐标
     * @param r2 第一个圆的半径
     * @return 两圆的位置关系
     */
    public static RoundEnum getRelationship(double x1, double y1, double r1, double x2, double y2, double r2) {
        return getRelationship(x1, y1, r1, x2, y2, r2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两圆在最小精度范围内是否重合
     *
     * @param r1 第一个圆
     * @param r2 第二个圆
     * @return 若两圆重合, 则返回true
     */
    public static boolean coincide(Round r1, Round r2) {
        return coincide(r1, r2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两圆在给定的精度范围内是否重合
     *
     * @param r1        第一个圆
     * @param r2        第二个圆
     * @param precision 给定的精度
     * @return 若两圆重合, 则返回true
     */
    public static boolean coincide(Round r1, Round r2, double precision) {
        if (r1 == null || r2 == null) {
            throw new NullPointerException("判断的圆为null");
        }
        return coincide(r1.getCenter(), r1.getRadius(), r2.getCenter(), r2.getRadius(), precision);
    }

    /**
     * 判断两圆在最小精度范围内是否重合
     *
     * @param p1 第一个圆的圆心
     * @param r1 第一个圆的半径
     * @param p2 第二个圆的圆心
     * @param r2 第二个圆的半径
     * @return 若两圆重合, 则返回true
     */
    public static boolean coincide(Point p1, double r1, Point p2, double r2) {
        return coincide(p1, r1, p2, r2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两圆在给定的精度范围内是否重合
     *
     * @param p1        第一个圆的圆心
     * @param r1        第一个圆的半径
     * @param p2        第二个圆的圆心
     * @param r2        第二个圆的半径
     * @param precision 给定的精度
     * @return 若两圆重合, 则返回true
     */
    public static boolean coincide(Point p1, double r1, Point p2, double r2, double precision) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("圆的圆心为null");
        }
        return coincide(p1.getX(), p1.getY(), r1, p2.getX(), p2.getY(), r2, precision);
    }

    /**
     * 判断两圆是否在最小精度范围内重合
     *
     * @param x1 第一个圆的圆心的横坐标
     * @param y1 第一个圆的圆心的纵坐标
     * @param r1 第一个圆的半径
     * @param x2 第二个圆的圆心的横坐标
     * @param y2 第二个圆的圆心的纵坐标
     * @param r2 第二个圆的半径
     * @return 若两圆重合, 则返回true
     */
    public static boolean coincide(double x1, double y1, double r1, double x2, double y2, double r2) {
        return coincide(x1, y1, r1, x2, y2, r2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两圆是否在给定的精度范围内重合
     *
     * @param x1        第一个圆的圆心的横坐标
     * @param y1        第一个圆的圆心的纵坐标
     * @param r1        第一个圆的半径
     * @param x2        第二个圆的圆心的横坐标
     * @param y2        第二个圆的圆心的纵坐标
     * @param r2        第二个圆的半径
     * @param precision 给定的精度
     * @return 若两圆重合, 则返回true
     */
    public static boolean coincide(double x1, double y1, double r1, double x2, double y2, double r2, double precision) {
        return PointUtil.coincide(x1, y1, x2, y2, precision) && NumberUtil.equal(r1, r2, precision);
    }

    /**
     * 将相对坐标下的圆转化为绝对坐标
     *
     * @param round  给定的圆
     * @param origin 相对坐标的原点
     * @param space  相对坐标单位长度的间隔
     * @return 绝对坐标下的圆
     */
    public static Round toAbsoluteCoordinate(Round round, Point origin, double space) {
        return round == null || origin == null ? null :
                toAbsoluteCoordinate(round.getCenter(), round.getRadius(), origin, space);
    }

    /**
     * 将相对坐标下的圆转化为绝对坐标
     *
     * @param center 给定的圆的圆心
     * @param origin 相对坐标的原点
     * @param space  相对坐标单位长度的间隔
     * @return 绝对坐标下的圆
     */
    public static Round toAbsoluteCoordinate(Point center, double radius, Point origin, double space) {
        return center == null || origin == null ? null :
                toAbsoluteCoordinate(center.getX(), center.getY(), radius, origin.getX(), origin.getY(), space);
    }

    /**
     * 将相对坐标下的圆转化为绝对坐标
     *
     * @param centerX 给定的圆的圆心的横坐标
     * @param centerY 给定的圆的圆心的纵坐标
     * @param originX 相对坐标的原点的横坐标
     * @param originY 相对坐标的原点的纵坐标
     * @param space   相对坐标单位长度的间隔
     * @return 相对坐标下的圆
     */
    public static Round toAbsoluteCoordinate(double centerX, double centerY, double radius, double originX, double originY, double space) {
        if (!NumberUtil.isMoreThanZero(radius)) {
            throw new IllegalArgumentException("圆的半径必须大于0");
        }

        Point point = PointUtil.toAbsoluteCoordinate(centerX, centerY, originX, originY, space);
        return new Round(point, radius / space);
    }

    /**
     * 将绝对坐标下的圆转化为相对坐标
     *
     * @param round  给定的圆
     * @param origin 相对坐标的原点
     * @param space  相对坐标单位长度的间隔
     * @return 相对坐标下的圆
     */
    public static Round toRelativeCoordinate(Round round, Point origin, double space) {
        return round == null || origin == null ? null :
                toRelativeCoordinate(round.getCenter(), round.getRadius(), origin, space);
    }

    /**
     * 将绝对坐标下的圆转化为相对坐标
     *
     * @param center 给定的圆的圆心
     * @param origin 相对坐标的原点
     * @param space  相对坐标单位长度的间隔
     * @return 相对坐标下的圆
     */
    public static Round toRelativeCoordinate(Point center, double radius, Point origin, double space) {
        return center == null || origin == null ? null :
                toRelativeCoordinate(center.getX(), center.getY(), radius, origin.getX(), origin.getY(), space);
    }

    /**
     * 将绝对坐标下的圆转化为相对坐标
     *
     * @param centerX 给定的圆的圆心的横坐标
     * @param centerY 给定的圆的圆心的纵坐标
     * @param originX 相对坐标的原点的横坐标
     * @param originY 相对坐标的原点的纵坐标
     * @param space   相对坐标单位长度的间隔
     * @return 相对坐标下的圆
     */
    public static Round toRelativeCoordinate(double centerX, double centerY, double radius, double originX, double originY, double space) {
        if (!NumberUtil.isMoreThanZero(radius)) {
            throw new IllegalArgumentException("圆的半径必须大于0");
        }

        Point point = PointUtil.toRelativeCoordinate(centerX, centerY, originX, originY, space);
        return new Round(point, radius * space);
    }
}
