package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import java.util.*;

/**
 * 通用基础方法
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointUtil {

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
    public static Point getCentralSymmetry(Point point, Point center) {
        if (point == null || center == null) {
            return null;
        }

        return getCentralSymmetry(point.getX(), point.getY(), center.getX(), center.getY());
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
    public static Point getCentralSymmetry(double px, double py, double cx, double cy) {
        double x = 2 * cx - px;
        double y = 2 * cy - py;
        return new Point(x, y);
    }

    /**
     * 求点相对于直线的轴对称点
     *
     * @param point 点
     * @param axis  直线
     * @return 轴对称点
     */
    public static Point getAxialSymmetry(Point point, Line axis) {
        if (point == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(point.getX(), point.getY(), axis.getA(), axis.getB(), axis.getC());
    }

    /**
     * 求点相对于线段的轴对称点
     *
     * @param point 点
     * @param axis  对称轴
     * @return 轴对称点
     */
    public static Point getAxialSymmetry(Point point, Segment axis) {
        if (point == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(point, axis.getP1(), axis.getP2());
    }

    /**
     * 求点相对于线段(p1,p2)的轴对称点
     *
     * @param point 点
     * @param p1    对称轴线段的第一个点
     * @param p2    对称轴线段的第二个点
     * @return 轴对称点
     */
    public static Point getAxialSymmetry(Point point, Point p1, Point p2) {
        if (point == null || p1 == null || p2 == null) {
            return null;
        }
        return getAxialSymmetry(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 求点(x,y)相对于由点(x1,y1)和点(x2,y2)构成的线段的轴对称点
     *
     * @param x  对称轴线段的第一个点的横坐标
     * @param y  对称轴线段的第一个点的纵坐标
     * @param x1 对称轴线段的第二个点的横坐标
     * @param y1 对称轴线段的第二个点的纵坐标
     * @param x2 对称轴线段的第三个点的横坐标
     * @param y2 对称轴线段的第三个点的纵坐标
     * @return 轴对称点
     */
    public static Point getAxialSymmetry(double x, double y, double x1, double y1, double x2, double y2) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        return getAxialSymmetry(x, y, line.getA(), line.getB(), line.getC());
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
    public static Point getAxialSymmetry(double x, double y, double a, double b, double c) {
        Point point = LineUtil.verticalPoint(x, y, a, b, c);
        return getCentralSymmetry(x, y, point.getX(), point.getY());
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
     * 将点(px,py)以点(cx,cy)为中心,逆时针旋转angle角度
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
     * 将point以center为中心,逆时针旋转angle角度,并将距离拉伸为ratio倍后的坐标值
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

    /**
     * 将point以center为中心,逆时针旋转angle角度,并将距离拉伸为length后的坐标值
     *
     * @param point  给定的点
     * @param center 给定的中心点
     * @param angle  想要旋转的角度,此处为弧度值
     * @param length 拉伸后的长度
     * @return 将point以center为中心旋转angle角度并拉伸到ratio倍距离后的坐标值
     */
    public static Point rotateAndStretchTo(Point point, Point center, double angle, double length) {
        if (point == null || center == null) {
            return null;
        }

        return rotateAndStretch(point.getX(), point.getY(), center.getX(), center.getY(), angle, length);
    }

    /**
     * 将点(px,py)以点(cx,cy)为中心,逆时针旋转angle角度,并将两点之间的距离拉伸到ratio倍
     *
     * @param px    给定点的横坐标
     * @param py    给定点的纵坐标
     * @param cx    中心点的横坐标
     * @param cy    中心点的纵坐标
     * @param angle 旋转的角度
     * @param ratio 拉伸的倍数
     * @return 经过旋转和拉伸后得到的点
     */
    public static Point rotateAndStretch(double px, double py, double cx, double cy, double angle, double ratio) {
        double length = distance(px, py, cx, cy) * ratio;
        return rotateAndStretchTo(px, py, cx, cy, angle, length);
    }

    /**
     * 将点(px,py)以点(cx,cy)为中心,逆时针旋转angle角度,并将两点之间的距离拉伸到length
     *
     * @param px     给定点的横坐标
     * @param py     给定点的纵坐标
     * @param cx     中心点的横坐标
     * @param cy     中心点的纵坐标
     * @param angle  旋转的角度
     * @param length 拉伸后的长度
     * @return 经过旋转和拉伸后得到的点
     */
    public static Point rotateAndStretchTo(double px, double py, double cx, double cy, double angle, double length) {
        if (coincide(px, py, cx, cy)) {
            return new Point(px, py);
        }

        double theta = Math.atan2(py - cy, px - cx);
        double rotateTheta = theta + angle;
        double x = length * Math.cos(rotateTheta);
        double y = length * Math.sin(rotateTheta);
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
     * 将point以center为中心,将距离拉伸到length
     *
     * @param point  给定的点
     * @param center 给定的中心点
     * @param length 拉伸后的长度
     * @return 拉伸后的点
     */
    public static Point stretchTo(Point point, Point center, double length) {
        return rotateAndStretchTo(point, center, 0, length);
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
     * 将点(px,py)以点(cx,cy)为中心,将距离拉伸到length
     *
     * @param px     给定点的横坐标
     * @param py     给定点的纵坐标
     * @param cx     中心点的横坐标
     * @param cy     中心点的纵坐标
     * @param length 拉伸后的长度
     * @return 拉伸后的点
     */
    public static Point stretchTo(double px, double py, double cx, double cy, double length) {
        return rotateAndStretchTo(px, py, cx, cy, 0, length);
    }

    /**
     * 判定两个点是否重合
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 只有当两点的横纵坐标完全相同时, 才返回true
     */
    public static boolean coincide(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("点为null");
        }
        return coincide(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 判定点(p1x,p1y)与点(p2x,p2y)是否重合时
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     * @return 只有当两点的横纵坐标之差都在最小精度范围内时, 才返回true
     */
    public static boolean coincide(double x1, double y1, double x2, double y2) {
        return NumberUtil.equal(x1, x2) && NumberUtil.equal(y1, y2);
    }

    /**
     * 判断两个点在精度范围内是否重合
     *
     * @param p1        第一个点
     * @param p2        第二个点
     * @param precision 给定的精度
     * @return 若两点横纵坐标之差均小于给定精度, 则返回true
     */
    public static boolean coincide(Point p1, Point p2, double precision) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("点为null");
        }
        return coincide(p1.getX(), p1.getY(), p2.getX(), p2.getY(), precision);
    }

    /**
     * 判断两个点在精度范围内是否重合
     *
     * @param x1        第一个点的横坐标
     * @param y1        第一个点的纵坐标
     * @param x2        第二个点的横坐标
     * @param y2        第二个点的纵坐标
     * @param precision 给定的精度
     * @return 若两点横纵坐标之差均小于给定精度, 则返回true
     */
    public static boolean coincide(double x1, double y1, double x2, double y2, double precision) {
        return NumberUtil.equal(x1, x2, precision) && NumberUtil.equal(y1, y2, precision);
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
        CollectionUtils.addAll(list, points);
        return list;
    }

    /**
     * 将Point的list转化为数组
     *
     * @param collection 点集合
     * @return Point的数组
     */
    public static Point[] toArray(Collection collection) {
        if (collection == null) {
            return new Point[0];
        }

        return (Point[]) collection.toArray(new Point[collection.size()]);
    }

    /**
     * 根据给定的过滤条件对点的数组进行过滤
     *
     * @param points    点的数组
     * @param predicate 给定的过滤条件
     * @return 过滤后的点数组
     */
    public static Point[] filter(Point[] points, Predicate predicate) {
        if (points == null || predicate == null) {
            return points;
        }

        List list = toList(points);
        CollectionUtils.filter(list, predicate);
        return toArray(list);
    }

    /**
     * 根据给定的方法对点的数组进行变换
     *
     * @param points      点的数组
     * @param transformer 给定的操作方法
     * @return 变换后的点数组
     */
    public static Point[] collect(Point[] points, Transformer transformer) {
        if (points == null || transformer == null) {
            return points;
        }

        List list = toList(points);
        Collection collection = CollectionUtils.collect(list, transformer);
        return toArray(collection);
    }

    /**
     * 根据给定的判定方法,判断是否存在符合要求的点
     *
     * @param points    点集合
     * @param predicate 判定的方法
     * @return 若存在符合条件的点, 则返回true
     */
    public static boolean exists(Point[] points, Predicate predicate) {
        if (points == null) {
            throw new NullPointerException("点集为null");
        }
        if (predicate == null) {
            throw new NullPointerException("判定方法为null");
        }
        return CollectionUtils.exists(toList(points), predicate);
    }

    /**
     * 对一组点按照指定的规则进行排序
     *
     * @param points     点集
     * @param comparator 排序规则
     * @return 排序后的点集
     */
    public static Point[] sort(Point[] points, Comparator comparator) {
        if (points == null || comparator == null) {
            return points;
        }

        List list = toList(points);
        Collections.sort(list, comparator);
        return toArray(list);
    }

    /**
     * 将相对坐标下的点,转化为绝对坐标下的点
     *
     * @param point   给定的点
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度的间隔
     * @param spaceY  纵坐标单位长度的间隔
     * @return 绝对坐标下的点
     */
    public static Point toAbsoluteCoordinate(Point point, double originX, double originY, double spaceX, double spaceY) {
        return point == null ? null : toAbsoluteCoordinate(point.getX(), point.getY(), originX, originY, spaceX, spaceY);
    }

    /**
     * 将相对坐标下的点(x,y)转化为绝对坐标下的点
     *
     * @param x       点的横坐标
     * @param y       点的纵坐标
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度的间隔
     * @param spaceY  纵坐标单位长度的间隔
     * @return 绝对坐标下的点
     */
    public static Point toAbsoluteCoordinate(double x, double y, double originX, double originY, double spaceX, double spaceY) {
        if (NumberUtil.isMoreThanZero(spaceX) && NumberUtil.isMoreThanZero(spaceY)) {
            double absoluteX = originX + x * spaceX;
            double absoluteY = originY - y * spaceY;
            return new Point(absoluteX, absoluteY);
        }

        throw new IllegalArgumentException("单位长度的坐标间隔必须大于0");
    }

    /**
     * 将绝对坐标下的点,转化为相对坐标下的点
     *
     * @param point   给定的点
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度的间隔
     * @param spaceY  纵坐标单位长度的间隔
     * @return 相对坐标下的点
     */
    public static Point toRelativeCoordinate(Point point, double originX, double originY, double spaceX, double spaceY) {
        return point == null ? null : toRelativeCoordinate(point.getX(), point.getY(), originX, originY, spaceX, spaceY);
    }

    /**
     * 将绝对坐标下的点(x,y)转化为相对坐标下的点
     *
     * @param x       点的横坐标
     * @param y       点的纵坐标
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度的间隔
     * @param spaceY  纵坐标单位长度的间隔
     * @return 相对坐标下的点
     */
    public static Point toRelativeCoordinate(double x, double y, double originX, double originY, double spaceX, double spaceY) {
        if (NumberUtil.isMoreThanZero(spaceX) && NumberUtil.isMoreThanZero(spaceY)) {
            double relativeX = (x - originX) / spaceX;
            double relativeY = (originY - y) / spaceY;
            return new Point(relativeX, relativeY);
        }

        throw new IllegalArgumentException("单位长度的坐标间隔必须大于0");
    }

    /**
     * 获得两点关于x坐标的Comparator
     *
     * @return 两点关于x坐标的Comparator
     */
    public static Comparator xComparator() {
        return new Comparator() {
            public int compare(Object o1, Object o2) {
                Point p1 = (Point) o1;
                Point p2 = (Point) o2;
                return Double.compare(p1.getX(), p2.getX());
            }
        };
    }

    /**
     * 获得两点关于y坐标的Comparator
     *
     * @return 两点关于y坐标的Comparator
     */
    public static Comparator yComparator() {
        return new Comparator() {
            public int compare(Object o1, Object o2) {
                Point p1 = (Point) o1;
                Point p2 = (Point) o2;
                return Double.compare(p1.getY(), p2.getY());
            }
        };
    }
}
