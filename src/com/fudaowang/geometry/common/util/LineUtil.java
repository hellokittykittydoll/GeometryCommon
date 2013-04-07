package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.Coordinate;
import com.fudaowang.geometry.common.graph.Line;
import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.graph.Segment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 对直线进行操作的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/26/12
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineUtil {

    /**
     * x坐标轴
     */
    public static final Line COORDINATE_AXIS_X = new Line(0, 1, 0);

    /**
     * y坐标轴
     */
    public static final Line COORDINATE_AXIS_Y = new Line(1, 0, 0);


    /**
     * 求直线a1x+b1y+c1=0与直线a2x+b2y+c2=0的交点
     *
     * @param a1 第一条直线的系数a
     * @param b1 第一条直线的系数b
     * @param c1 第一条直线的系数c
     * @param a2 第二条直线的系数a
     * @param b2 第二条直线的系数b
     * @param c2 第二条直线的系数c
     * @return 两直线的交点
     */
    public static Point intersect(double a1, double b1, double c1, double a2, double b2, double c2) {
        double denominator = a1 * b2 - a2 * b1;
        if (NumberUtil.isZero(denominator)) {
            return null;
        }

        double x = (c2 * b1 - c1 * b2) / denominator;
        double y = (c1 * a2 - a1 * c2) / denominator;
        return new Point(x, y);
    }

    /**
     * 找出两条直线的交点,若两线平行则返回null;
     * 不能用来作判定两线平行的依据,实际上几乎总是会找出交点,绝对精度上的平行是不太可能的.
     *
     * @param line1 第一条直线
     * @param line2 第二条直线
     * @return 两直线的交点
     */
    public static Point intersect(Line line1, Line line2) {
        if (line1 == null || line2 == null) {
            return null;
        }

        return intersect(line1.getA(), line1.getB(), line1.getC(), line2.getA(), line2.getB(), line2.getC());
    }

    /**
     * 求线段与直线的交点
     *
     * @param line      直线
     * @param segment   线段
     * @param inSegment 限定交点是否在线段内
     * @return 线段与直线的交点
     */
    public static Point intersect(Line line, Segment segment, boolean inSegment) {
        if (segment == null || line == null) {
            return null;
        }
        return intersect(line, segment.getP1(), segment.getP2(), inSegment);
    }

    /**
     * 求点p1与p2构成的线段与直线的交点
     *
     * @param line      直线
     * @param p1        线段的端点1
     * @param p2        线段的端点2
     * @param inSegment 限定交点是否在线段内
     * @return 线段与直线的交点
     */
    public static Point intersect(Line line, Point p1, Point p2, boolean inSegment) {
        if (p1 == null || p2 == null || line == null) {
            return null;
        }

        return intersect(line.getA(), line.getB(), line.getC(), p1.getX(), p1.getY(), p2.getX(), p2.getY(), inSegment);
    }

    /**
     * 求由点(x1,y1)和点(x2,y2)构成的线段,与直线ax+by+c=0的交点
     *
     * @param a         直线的系数a
     * @param b         直线的系数b
     * @param c         直线的系数c
     * @param x1        线段的第一个点的横坐标
     * @param y1        线段的第一个点的纵坐标
     * @param x2        线段的第二个点的横坐标
     * @param y2        线段的第二个点的纵坐标
     * @param inSegment 限定交点是否在线段内
     * @return 线段与直线的交点
     */
    public static Point intersect(double a, double b, double c, double x1, double y1, double x2, double y2, boolean inSegment) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        Point point = LineUtil.intersect(line.getA(), line.getB(), line.getC(), a, b, c);
        if (point == null) {
            return null;
        }
        if (inSegment) {
            if (SegmentUtil.inSegment(point.getX(), point.getY(), x1, y1, x2, y2)) {
                return point;
            }
            return null;
        }
        return point;
    }

    /**
     * 求点(px,py)到直线ax+by+c=0的距离
     *
     * @param px 点的横坐标
     * @param py 点的纵坐标
     * @param a  直线的系数a
     * @param b  直线的系数b
     * @param c  直线的系数c
     * @return 点到直线的距离
     */
    public static double distance(double px, double py, double a, double b, double c) {
        return Math.abs(a * px + b * py + c) / Math.sqrt(a * a + b * b);
    }

    /**
     * 求点到直线的距离
     *
     * @param point 点
     * @param line  直线
     * @return 点到直线的距离
     */
    public static double distance(Point point, Line line) {
        if (line == null || point == null) {
            return Double.NaN;
        }
        return distance(point.getX(), point.getY(), line.getA(), line.getB(), line.getC());
    }

    /**
     * 在指定的精度范围内,判断点是否在直线上
     *
     * @param point     点
     * @param line      直线
     * @param precision 精度
     * @return 若点在直线上则返回true
     */
    public static boolean onLine(Point point, Line line, double precision) {
        if (point == null || line == null) {
            return false;
        }
        return onLine(point.getX(), point.getY(), line.getA(), line.getB(), line.getC(), precision);
    }

    /**
     * 在指定的精度范围内,判断点是否在直线上
     *
     * @param point 点
     * @param line  直线
     * @return 若点在直线上则返回true
     */
    public static boolean onLine(Point point, Line line) {
        return onLine(point, line, NumberUtil.MIN_VALUE);
    }

    /**
     * 在指定的精度范围内,判断点(x,y)是否在直线ax+by+c=0上
     *
     * @param x         点的横坐标
     * @param y         点的纵坐标
     * @param a         直线的系数a
     * @param b         直线的系数b
     * @param c         直线的系数c
     * @param precision 精度
     * @return 若点在直线上则返回true
     */
    public static boolean onLine(double x, double y, double a, double b, double c, double precision) {
        if (precision < NumberUtil.MIN_VALUE) {
            precision = NumberUtil.MIN_VALUE;
        }
        return distance(x, y, a, b, c) < precision;
    }

    /**
     * 在最小精度范围内,判断点(x,y)是否在直线ax+by+c=0上
     *
     * @param x 点的横坐标
     * @param y 点的纵坐标
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @return 若点在直线上则返回true
     */
    public static boolean onLine(double x, double y, double a, double b, double c) {
        return onLine(x, y, a, b, c, NumberUtil.MIN_VALUE);
    }

    /**
     * 过点做直线的垂线
     *
     * @param point 点
     * @param line  直线
     * @return 垂线
     */
    public static Line verticalLine(Point point, Line line) {
        if (point == null || line == null) {
            return null;
        }

        return verticalLine(point.getX(), point.getY(), line.getA(), line.getB());
    }

    /**
     * 过点(x,y)做直线ax+by+c=0的垂线
     *
     * @param x 点的横坐标
     * @param y 点纵坐标
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @return 垂线
     */
    public static Line verticalLine(double x, double y, double a, double b) {
        return new Line(b, -a, a * y - b * x);
    }

    /**
     * 过点(x,y)求与直线ax+by+c=0垂直的垂线的交点
     *
     * @param x 点的横坐标
     * @param y 点纵坐标
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @return 垂点
     */
    public static Point verticalPoint(double x, double y, double a, double b, double c) {
        Line line = verticalLine(x, y, a, b);
        return intersect(line.getA(), line.getB(), line.getC(), a, b, c);
    }

    /**
     * 过点求与直线垂直的垂点
     *
     * @param point 点
     * @param line  直线
     * @return 垂点
     */
    public static Point verticalPoint(Point point, Line line) {
        if (point == null || line == null) {
            return null;
        }
        Line l = verticalLine(point, line);
        return intersect(l, line);
    }

    /**
     * 过点求直线的平行线
     *
     * @param point 点
     * @param line  直线
     * @return 平行线
     */
    public static Line parallelLine(Point point, Line line) {
        if (point == null || line == null) {
            return null;
        }
        return parallelLine(point.getX(), point.getY(), line.getA(), line.getB());
    }

    /**
     * 过点(x,y)求直线ax+by+c=0的平行线
     *
     * @param x 点的横坐标
     * @param y 点纵坐标
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @return 平行线
     */
    public static Line parallelLine(double x, double y, double a, double b) {
        return new Line(a, b, -1 * a * x - b * y);
    }

    /**
     * 过给定的两点求直线方程,两点不能为同一点
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 过两点的直线方程
     */
    public static Line getLine(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            return null;
        }

        return getLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 用点集生成直线
     *
     * @param points 直线上的点集
     * @return 生成的直线
     */
    public static Line getLine(List<Point> points) {
        return points == null ? null : getLine(points.toArray(new Point[points.size()]));
    }

    /**
     * 用点集生成直线
     *
     * @param points 直线上的点集
     * @return 生成的直线
     */
    public static Line getLine(Point[] points) {
        return points == null || points.length < 2 ? null : getLine(points[0], points[1]);
    }

    /**
     * 过点(x1,y1)和点(x2,y2)求直线方程,两点不能为同一点
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     * @return 过两点的直线方程
     */
    public static Line getLine(double x1, double y1, double x2, double y2) {
        if (PointUtil.coincide(x1, y1, x2, y2)) {
            return null;
        }

        double a = y2 - y1;
        double b = x1 - x2;
        double c = -b * y1 - a * x1;
        return new Line(a, b, c);
    }

    /**
     * 根据给定的点和直线倾角来构造直线
     *
     * @param point 给定的点
     * @param angle 直线的倾角
     * @return 直线
     */
    public static Line getLine(Point point, double angle) {
        if (point == null) {
            return null;
        }
        return getLine(point.getX(), point.getY(), angle);
    }

    /**
     * 求过点(x,y)并且倾角为angle的直线方程
     *
     * @param x     点的横坐标
     * @param y     点的纵坐标
     * @param angle 直线的倾角
     * @return 直线
     */
    public static Line getLine(double x, double y, double angle) {
        if (NumberUtil.equal(Math.abs(angle), Math.PI / 2)) {
            return new Line(1, 0, -x);
        }
        double k = Math.tan(angle);
        if (NumberUtil.isZero(k)) {
            return new Line(0, 1, -y);
        }
        return new Line(k, -1, y - k * x);
    }

    /**
     * 将直线沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param line 直线
     * @param x    横坐标的平移长度
     * @param y    纵坐标平移的长度
     * @return 平移后的直线
     */
    public static Line translation(Line line, double x, double y) {
        if (line == null) {
            return null;
        }
        return translation(line.getA(), line.getB(), line.getC(), x, y);
    }

    /**
     * 将直线ax+by+c=0沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @param x 横坐标平移的长度
     * @param y 横坐标平移的长度
     * @return 平移后的直线
     */
    public static Line translation(double a, double b, double c, double x, double y) {
        return new Line(a, b, c - a * x - b * y);
    }

    /**
     * 计算point相对于center的倾角
     *
     * @param center 中心点
     * @param point  给定的点
     * @return 给定点相对于中心点的倾角
     */
    public static double getAngle(Point center, Point point) {
        if (center == null || point == null) {
            return Double.NaN;
        }
        return getAngle(center.getX(), center.getY(), point.getX(), point.getY());
    }

    /**
     * 计算点(px,py)相对于点(cx,cy)的倾角
     *
     * @param cx 中心点的横坐标
     * @param cy 中心点的纵坐标
     * @param px 给定点的横坐标
     * @param py 给定点的纵坐标
     * @return 给定点相对于中心点的倾角
     */
    public static double getAngle(double cx, double cy, double px, double py) {
        return Math.atan2(py - cy, px - cx);
    }

    /**
     * 求直线的倾角
     *
     * @param line 给定的直线
     * @return 直线的倾角
     */
    public static double getAngle(Line line) {
        if (line == null) {
            return Double.NaN;
        }
        return getAngle(line.getA(), line.getB());
    }

    /**
     * 求直线ax+by+c=0的倾角
     *
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @return 直线的倾角
     */
    public static double getAngle(double a, double b) {
        if (NumberUtil.isZero(a) && NumberUtil.isZero(b)) {
            return Double.NaN;
        }
        return Math.atan(-a / b);
    }

    /**
     * 将直线按照中心点逆时针旋转一个角度
     *
     * @param line   给定的直线
     * @param center 旋转的中心点
     * @param angle  旋转的角度
     * @return 旋转后的直线
     */
    public static Line rotate(Line line, Point center, double angle) {
        if (line == null || center == null) {
            return null;
        }
        return rotate(line.getA(), line.getB(), line.getC(), center.getX(), center.getY(), angle);
    }

    /**
     * 将直线ax+by+c=0按照点(x,y)为中心,逆时针旋转angle角度
     *
     * @param a     直线的系数a
     * @param b     直线的系数b
     * @param c     直线的系数c
     * @param x     中心点的横坐标
     * @param y     中心点的纵坐标
     * @param angle 旋转的角度
     * @return 旋转后的直线
     */
    public static Line rotate(double a, double b, double c, double x, double y, double angle) {
        Point vertex = LineUtil.verticalPoint(x, y, a, b, c);
        vertex = PointUtil.rotate(vertex.getX(), vertex.getY(), x, y, angle);
        double lineAngle = getAngle(a, b);
        return getLine(vertex, lineAngle + angle - Math.PI);
    }

    /**
     * 根据给定的y值,求直线的x值
     *
     * @param line 直线
     * @param y    y值
     * @return x值
     */
    public static double getX(Line line, double y) {
        if (line == null) {
            return Double.NaN;
        }
        return getX(line.getA(), line.getB(), line.getC(), y);
    }

    /**
     * 根据给定的y值,求直线的x值
     *
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @param y y值
     * @return x值
     */
    public static double getX(double a, double b, double c, double y) {
        if (NumberUtil.isZero(a) || NumberUtil.isZero(b)) {
            return Double.NaN;
        }
        return (-c - b * y) / a;
    }

    /**
     * 根据给定的x值,求直线的y值
     *
     * @param line 直线
     * @param x    x值
     * @return y值
     */
    public static double getY(Line line, double x) {
        if (line == null) {
            return Double.NaN;
        }
        return getY(line.getA(), line.getB(), line.getC(), x);
    }

    /**
     * 根据给定的x值,求直线的y值
     *
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @param c 直线的系数c
     * @param x x值
     * @return y值
     */
    public static double getY(double a, double b, double c, double x) {
        if (NumberUtil.isZero(a) || NumberUtil.isZero(b)) {
            return Double.NaN;
        }
        return (-c - a * x) / b;
    }


    /**
     * 判断两直线是否在最小精度范围内平行
     *
     * @param l1 第一条直线
     * @param l2 第二条直线
     * @return 若两直线平行, 则返回true
     */
    public static boolean parallel(Line l1, Line l2) {
        return parallel(l1, l2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两直线是否在给定的精度范围内平行
     *
     * @param l1        第一条直线
     * @param l2        第二条直线
     * @param precision 给定的精度
     * @return 若两直线平行, 则返回true
     */
    public static boolean parallel(Line l1, Line l2, double precision) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException("直线为null");
        }
        return parallel(l1.getA(), l1.getB(), l2.getA(), l2.getB(), precision);
    }

    /**
     * 判断两直线是否在最小精度范围内平行
     *
     * @param a1 第一条直线的系数a
     * @param b1 第一条直线的系数b
     * @param a2 第二条直线的系数a
     * @param b2 第二条直线的系数b
     * @return 若两直线平行, 则返回true
     */
    public static boolean parallel(double a1, double b1, double a2, double b2) {
        return parallel(a1, b1, a2, b2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两直线是否在给定的精度范围内平行
     *
     * @param a1        第一条直线的系数a
     * @param b1        第一条直线的系数b
     * @param a2        第二条直线的系数a
     * @param b2        第二条直线的系数b
     * @param precision 给定的精度
     * @return 若两直线平行, 则返回true
     */
    public static boolean parallel(double a1, double b1, double a2, double b2, double precision) {
        return NumberUtil.equal(a1 * b2, a2 * b1, precision);
    }

    /**
     * 判断两直线是否在最小精度范围内垂直
     *
     * @param l1 第一条直线
     * @param l2 第二条直线
     * @return 若两直线垂直则返回true
     */
    public static boolean vertical(Line l1, Line l2) {
        return vertical(l1, l2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两直线是否在给定的精度范围内垂直
     *
     * @param l1        第一条直线
     * @param l2        第二条直线
     * @param precision 给定的精度
     * @return 若两直线垂直则返回true
     */
    public static boolean vertical(Line l1, Line l2, double precision) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException("直线为null");
        }
        return vertical(l1.getA(), l1.getB(), l2.getA(), l2.getB(), precision);
    }

    /**
     * 判断两直线是否在最小精度内垂直
     *
     * @param a1 第一条直线的系数a
     * @param b1 第一条直线的系数b
     * @param a2 第二条直线的系数a
     * @param b2 第二条直线的系数b
     * @return 若两直线垂直, 则返回true
     */
    public static boolean vertical(double a1, double b1, double a2, double b2) {
        return vertical(a1, b1, a2, b2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两直线是否在给定的精度内垂直
     *
     * @param a1        第一条直线的系数a
     * @param b1        第一条直线的系数b
     * @param a2        第二条直线的系数a
     * @param b2        第二条直线的系数b
     * @param precision 给定的精度
     * @return 若两直线垂直, 则返回true
     */
    public static boolean vertical(double a1, double b1, double a2, double b2, double precision) {
        return NumberUtil.isZero(a1 * a2 + b1 * b2, precision);
    }

    /**
     * 判断两直线是否在最小精度范围内重合
     *
     * @param l1 第一条直线
     * @param l2 第二条直线
     * @return 若两直线重合则返回true
     */
    public static boolean coincide(Line l1, Line l2) {
        return coincide(l1, l2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两直线是否在给定的精度范围内重合
     *
     * @param l1        第一条直线
     * @param l2        第二条直线
     * @param precision 给定的精度
     * @return 若两直线重合则返回true
     */
    public static boolean coincide(Line l1, Line l2, double precision) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException("直线为null");
        }
        return coincide(l1.getA(), l1.getB(), l1.getC(), l2.getA(), l2.getB(), l2.getC(), precision);
    }

    /**
     * 判断两直线是否在最小精度内重合
     *
     * @param a1 第一条直线的系数a
     * @param b1 第一条直线的系数b
     * @param a2 第二条直线的系数a
     * @param b2 第二条直线的系数b
     * @return 若两直线重合, 则返回true
     */
    public static boolean coincide(double a1, double b1, double c1, double a2, double b2, double c2) {
        return coincide(a1, b1, c1, a2, b2, c2, NumberUtil.MIN_VALUE);
    }

    /**
     * 判断两直线是否在给定的精度内重合
     *
     * @param a1        第一条直线的系数a
     * @param b1        第一条直线的系数b
     * @param a2        第二条直线的系数a
     * @param b2        第二条直线的系数b
     * @param precision 给定的精度
     * @return 若两直线重合, 则返回true
     */
    public static boolean coincide(double a1, double b1, double c1, double a2, double b2, double c2, double precision) {
        if (NumberUtil.isZero(a1) && NumberUtil.isZero(a2) && NumberUtil.equal(b1 * c2, b2 * c1, precision)) {
            return true;
        }

        if (NumberUtil.isZero(b1) && NumberUtil.isZero(b2) && NumberUtil.equal(a1 * c2, a2 * c1, precision)) {
            return true;
        }

        if (NumberUtil.isZero(a1) && NumberUtil.isZero(a2) && NumberUtil.isZero(b1) && NumberUtil.isZero(b2)) {
            if (NumberUtil.equal(b1 * c2, b2 * c1, precision) && NumberUtil.equal(a1 * c2, a2 * c1, precision)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断一条直线是否合理
     *
     * @param line 给定的直线
     * @return 若直线的系数a与系数b均在最小精度范围内等于0, 或者直线为null, 则返回false
     */
    public static boolean isLogical(Line line) {
        return line != null && isLogical(line.getA(), line.getB());
    }

    /**
     * 判断一条直线是否合理
     *
     * @param a 直线的系数a
     * @param b 直线的系数b
     * @return 若直线的系数a与系数b均在最小精度范围内等于0, 则返回false
     */
    public static boolean isLogical(double a, double b) {
        return !((NumberUtil.isZero(a) && NumberUtil.isZero(b)));
    }

    /**
     * 将相对坐标下的直线,转化为绝对坐标下的直线
     *
     * @param line       给定的直线
     * @param coordinate 相对坐标
     * @return 绝对坐标下的直线
     */
    public static Line toAbsoluteCoordinate(Line line, Coordinate coordinate) {
        if (line == null || coordinate == null) {
            return null;
        }
        if (coordinate.isSymmetrical()) {
            return toAbsoluteCoordinate(line.getA(), line.getB(), line.getC(),
                    coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX());
        }
        return toAbsoluteCoordinate(line.getA(), line.getB(), line.getC(),
                coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX(), coordinate.getSpaceY());
    }

    /**
     * 将相对坐标下的直线(p1,p2)转化为绝对坐标下的直线
     *
     * @param a       直线的系数a
     * @param b       直线的系数b
     * @param c       直线的系数c
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度的间隔
     * @param spaceY  纵坐标单位长度的间隔
     * @return 绝对坐标下的直线
     */
    public static Line toAbsoluteCoordinate(double a, double b, double c, double originX, double originY, double spaceX, double spaceY) {
        if (!isLogical(a, b)) {
            throw new IllegalArgumentException("直线的系数a和b不能同时为0");
        }

        List<Point> list = new ArrayList<Point>();

        Point p = LineUtil.intersect(a, b, c, 0, 1, 0);
        if (p != null) {
            list.add(p);
        }

        p = LineUtil.intersect(a, b, c, 1, 0, 0);
        if (p != null) {
            list.add(p);
        }

        p = LineUtil.intersect(a, b, c, 1, 0, originX / spaceX);
        if (p != null) {
            list.add(p);
        }

        p = LineUtil.intersect(a, b, c, 0, 1, -originY / spaceY);
        if (p != null) {
            list.add(p);
        }

        Collections.sort(list, PointUtil.xComparator());
        Collections.sort(list, PointUtil.yComparator());

        Point[] points = list.toArray(new Point[list.size()]);
        if (points.length < 2) {
            return null;
        }

        Point p1 = PointUtil.toAbsoluteCoordinate(points[0].getX(), points[0].getY(), originX, originY, spaceX, spaceY);
        int last = points.length - 1;
        Point p2 = PointUtil.toAbsoluteCoordinate(points[last].getX(), points[last].getY(), originX, originY, spaceX, spaceY);
        return getLine(p1, p2);
    }

    /**
     * 将相对坐标下的直线(p1,p2)转化为绝对坐标下的直线
     *
     * @param a       直线的系数a
     * @param b       直线的系数b
     * @param c       直线的系数c
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param space   相对坐标单位长度的间隔
     * @return 绝对坐标下的直线
     */
    public static Line toAbsoluteCoordinate(double a, double b, double c, double originX, double originY, double space) {
        if (!isLogical(a, b)) {
            throw new IllegalArgumentException("直线的系数a和b不能同时为0");
        }
        c = c * space;
        double realA = -a;
        double realC = a * originX - b * originY - c;
        return new Line(realA, b, realC);
    }

    /**
     * 将绝对坐标下的直线,转化为相对坐标下的直线
     *
     * @param line       给定的直线
     * @param coordinate 相对坐标
     * @return 相对坐标下的直线
     */
    public static Line toRelativeCoordinate(Line line, Coordinate coordinate) {
        if (line == null || coordinate == null) {
            return null;
        }
        if (coordinate.isSymmetrical()) {
            return toRelativeCoordinate(line.getA(), line.getB(), line.getC(),
                    coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX());
        }
        return toRelativeCoordinate(line.getA(), line.getB(), line.getC(),
                coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX(), coordinate.getSpaceY());
    }

    /**
     * 将绝对坐标下的直线(p1,p2)转化为相对坐标下的直线
     *
     * @param a       直线的系数a
     * @param b       直线的系数b
     * @param c       直线的系数c
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度的间隔
     * @param spaceY  纵坐标单位长度的间隔
     * @return 相对坐标下的直线
     */
    public static Line toRelativeCoordinate(double a, double b, double c, double originX, double originY, double spaceX, double spaceY) {
        if (!isLogical(a, b)) {
            throw new IllegalArgumentException("直线的系数a和b不能同时为0");
        }

        List<Point> list = new ArrayList<Point>();
        Point p = LineUtil.intersect(a, b, c, 0, 1, 0);
        if (p != null) {
            list.add(p);
        }

        p = LineUtil.intersect(a, b, c, 1, 0, 0);
        if (p != null) {
            list.add(p);
        }

        p = LineUtil.intersect(a, b, c, 1, 0, -originX);
        if (p != null) {
            list.add(p);
        }

        p = LineUtil.intersect(a, b, c, 0, 1, -originY);
        if (p != null) {
            list.add(p);
        }

        Collections.sort(list, PointUtil.xComparator());
        Collections.sort(list, PointUtil.yComparator());

        Point[] points = list.toArray(new Point[list.size()]);
        if (points.length < 2) {
            return null;
        }

        Point p1 = PointUtil.toRelativeCoordinate(points[0].getX(), points[0].getY(), originX, originY, spaceX, spaceY);
        int last = points.length - 1;
        Point p2 = PointUtil.toRelativeCoordinate(points[last].getX(), points[last].getY(), originX, originY, spaceX, spaceY);
        return getLine(p1, p2);
    }

    /**
     * 将绝对坐标下的直线(p1,p2)转化为相对坐标下的直线
     *
     * @param a       直线的系数a
     * @param b       直线的系数b
     * @param c       直线的系数c
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param space   相对坐标单位长度的间隔
     * @return 相对坐标下的直线
     */
    public static Line toRelativeCoordinate(double a, double b, double c, double originX, double originY, double space) {
        if (!isLogical(a, b)) {
            throw new IllegalArgumentException("直线的系数a和b不能同时为0");
        }
        return toRelativeCoordinate(a, b, c, originX, originY, space, space);
    }
}
