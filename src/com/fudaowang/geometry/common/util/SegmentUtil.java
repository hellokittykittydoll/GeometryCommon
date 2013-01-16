package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.Coordinate;
import com.fudaowang.geometry.common.graph.Line;
import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.graph.Segment;

/**
 * 对线段操作的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/10/12
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class SegmentUtil {
    /**
     * 求两线段的交点
     *
     * @param segment1  线段1
     * @param segment2  线段2
     * @param inSegment 限定交点是否在线段内
     * @return 两线段的交点
     */
    public static Point intersect(Segment segment1, Segment segment2, boolean inSegment) {
        if (segment1 == null || segment2 == null) {
            return null;
        }

        return intersect(segment1.getP1(), segment1.getP2(), segment2.getP1(), segment2.getP2(), inSegment);
    }

    /**
     * 求线段(p1,p2)与线段(p3,p4)的交点
     *
     * @param p1        线段1的第一个端点
     * @param p2        线段1的第一个端点
     * @param p3        线段2的第二个端点
     * @param p4        线段2的第二个端点
     * @param inSegment 限定交点是否在线段内
     * @return 两线段的交点
     */
    public static Point intersect(Point p1, Point p2, Point p3, Point p4, boolean inSegment) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return null;
        }
        return intersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY(), inSegment);
    }

    /**
     * 求由点(x1,y1)和点(x2,y2)构成的线段,以及点(x3,y3)和点(x4,y4)构成的线段的交点
     *
     * @param x1        线段1的端点1的横坐标
     * @param y1        线段1的端点1的纵坐标
     * @param x2        线段1的端点2的横坐标
     * @param y2        线段1的端点2的纵坐标
     * @param x3        线段2的端点1的横坐标
     * @param y3        线段2的端点1的纵坐标
     * @param x4        线段2的端点2的横坐标
     * @param y4        线段2的端点2的纵坐标
     * @param inSegment 限定交点是否在线段内
     * @return 两线段的交点
     */
    public static Point intersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, boolean inSegment) {
        Line line1 = LineUtil.getLine(x1, y1, x2, y2);
        Line line2 = LineUtil.getLine(x3, y3, x4, y4);
        Point point = LineUtil.intersect(line1, line2);

        if (point == null) {
            return null;
        }

        if (inSegment) {
            if (inSegment(point.getX(), point.getY(), x1, y1, x2, y2)
                    && inSegment(point.getX(), point.getY(), x3, y3, x4, y4)) {
                return point;
            }
            return null;
        }

        return point;
    }

    /**
     * 求线段与直线的交点
     *
     * @param segment   线段
     * @param line      直线
     * @param inSegment 限定交点是否在线段内
     * @return 线段与直线的交点
     */
    public static Point intersect(Segment segment, Line line, boolean inSegment) {
        if (segment == null || line == null) {
            return null;
        }
        return intersect(segment.getP1(), segment.getP2(), line, inSegment);
    }

    /**
     * 求点p1与p2构成的线段与直线的交点
     *
     * @param p1        线段的端点1
     * @param p2        线段的端点2
     * @param line      直线
     * @param inSegment 限定交点是否在线段内
     * @return 线段与直线的交点
     */
    public static Point intersect(Point p1, Point p2, Line line, boolean inSegment) {
        if (p1 == null || p2 == null || line == null) {
            return null;
        }

        return intersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), line.getA(), line.getB(), line.getC(), inSegment);
    }

    /**
     * 求由点(x1,y1)和点(x2,y2)构成的线段,与直线ax+by+c=0的交点
     *
     * @param x1        线段的第一个点的横坐标
     * @param y1        线段的第一个点的纵坐标
     * @param x2        线段的第二个点的横坐标
     * @param y2        线段的第二个点的纵坐标
     * @param a         直线的系数a
     * @param b         直线的系数b
     * @param c         直线的系数c
     * @param inSegment 限定交点是否在线段内
     * @return 线段与直线的交点
     */
    public static Point intersect(double x1, double y1, double x2, double y2, double a, double b, double c, boolean inSegment) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        Point point = LineUtil.intersect(line.getA(), line.getB(), line.getC(), a, b, c);
        if (point == null) {
            return null;
        }
        if (inSegment) {
            if (inSegment(point.getX(), point.getY(), x1, y1, x2, y2)) {
                return point;
            }
            return null;
        }
        return point;
    }

    /**
     * 判断点(x,y)是否在由点(x1,y1)和点(x2,y2)构成的线段坐标范围内,不判断点到线段的距离
     *
     * @param x  给定点的横坐标
     * @param y  给定点的纵坐标
     * @param x1 线段的第一个点的横坐标
     * @param y1 线段的第一个点的纵坐标
     * @param x2 线段的第二个点的横坐标
     * @param y2 线段的第二个点的纵坐标
     * @return 若点在线段坐标范围内则返回true
     */
    public static boolean inSegment(double x, double y, double x1, double y1, double x2, double y2) {
        return NumberUtil.valueInRange(x1, x2, x) || NumberUtil.valueInRange(y1, y2, y);
    }

    /**
     * 不判断点到线段的距离,点是否在线段所在的直线上,单纯判断点是否在线段坐标范围内
     *
     * @param point   点
     * @param segment 线段
     * @return 若点在线段的坐标范围内则返回true
     */
    public static boolean inSegment(Point point, Segment segment) {
        if (segment == null || point == null) {
            return false;
        }
        return inSegment(point, segment.getP1(), segment.getP2());
    }

    /**
     * 判断点是否在线段(p1,p2)的两点范围之内,不判断点到线段的距离
     *
     * @param point 给定的点
     * @param p1    线段的端点1
     * @param p2    线段的端点2
     * @return 若点在线段的坐标范围内则返回true
     */
    public static boolean inSegment(Point point, Point p1, Point p2) {
        if (p1 == null || p2 == null || point == null) {
            return false;
        }
        return inSegment(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 求点到线段的距离
     *
     * @param point   点
     * @param segment 线段
     * @return 点到线段的距离
     */
    public static double distance(Point point, Segment segment) {
        if (point == null || segment == null) {
            return Double.NaN;
        }

        return java.awt.geom.Line2D.ptLineDist(segment.getP1().getX(), segment.getP1().getY(),
                segment.getP2().getX(), segment.getP2().getY(), point.getX(), point.getY());
    }

    /**
     * 求点point到线段(p1,p2)的距离
     *
     * @param point 给定的点
     * @param p2    线段的端点
     * @param p1    线段的端点
     * @return 点到线段的距离
     */
    public static double distance(Point point, Point p2, Point p1) {
        if (p1 == null || p2 == null || point == null) {
            return Double.NaN;
        }

        return java.awt.geom.Line2D.ptLineDist(p1.getX(), p1.getY(), p2.getX(), p2.getY(), point.getX(), point.getY());
    }

    /**
     * 求点(px,py)到以点(x1,y1)和点(x2,y2)为端点的线段的距离
     *
     * @param px 给定点的横坐标
     * @param py 给定点的纵坐标
     * @param x2 线段第二个端点的横坐标
     * @param y2 线段第二个端点的纵坐标
     * @param x1 线段第一个端点的横坐标
     * @param y1 线段第一个端点的纵坐标
     * @return 点到线段的距离
     */
    public static double distance(double px, double py, double x2, double y2, double x1, double y1) {
        return java.awt.geom.Line2D.ptLineDist(x1, y1, x2, y2, px, py);
    }

    /**
     * 过点求线段的垂线
     *
     * @param point   点
     * @param segment 线段
     * @return 垂线
     */
    public static Line verticalLine(Point point, Segment segment) {
        if (point == null || segment == null) {
            return null;
        }

        return verticalLine(point, segment.getP1(), segment.getP2());
    }

    /**
     * 过点求线段(p1,p2)的垂线
     *
     * @param point 点
     * @param p1    线段的端点1
     * @param p2    线段的端点2
     * @return 垂线
     */
    public static Line verticalLine(Point point, Point p1, Point p2) {
        if (point == null || p1 == null || p2 == null) {
            return null;
        }

        return verticalLine(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 过点(x,y)求由点(x1,y1)和点(x2,y2)构成的线段的垂线
     *
     * @param x  给定点的横坐标
     * @param y  给定点的纵坐标
     * @param x1 线段的第一个端点的横坐标
     * @param y1 线段的第一个端点的纵坐标
     * @param x2 线段的第二个端点的横坐标
     * @param y2 线段的第二个端点的纵坐标
     * @return 垂线
     */
    public static Line verticalLine(double x, double y, double x1, double y1, double x2, double y2) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        if (line == null) {
            return null;
        }

        return LineUtil.verticalLine(x, y, line.getA(), line.getB());
    }

    /**
     * 过点找出和线段垂直的线的交点
     *
     * @param point   点
     * @param segment 线段
     * @return 垂足
     */
    public static Point verticalPoint(Point point, Segment segment) {
        if (point == null || segment == null) {
            return null;
        }

        return verticalPoint(point, segment.getP1(), segment.getP2());
    }

    /**
     * 过点找出和线段(p1,p2)垂直的线的交点
     *
     * @param point 点
     * @param p1    线段的端点1
     * @param p2    线段的端点2
     * @return 垂足
     */
    public static Point verticalPoint(Point point, Point p1, Point p2) {
        if (point == null || p1 == null || p2 == null) {
            return null;
        }

        return verticalPoint(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 过点(x,y)找出由点(x1,y1)和点(x2,y2)构成的线段垂直的线的交点
     *
     * @param x  给定点的横坐标
     * @param y  给定点的纵坐标
     * @param x1 线段的第一个端点的横坐标
     * @param y1 线段的第一个端点的纵坐标
     * @param x2 线段的第二个端点的横坐标
     * @param y2 线段的第二个端点的纵坐标
     * @return 垂足
     */
    public static Point verticalPoint(double x, double y, double x1, double y1, double x2, double y2) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        return LineUtil.verticalPoint(x, y, line.getA(), line.getB(), line.getC());
    }

    /**
     * 过点做线段的平行线
     *
     * @param point   点
     * @param segment 线段
     * @return 平行线
     */
    public static Line parallelLine(Point point, Segment segment) {
        if (point == null || segment == null) {
            return null;
        }

        return parallelLine(point, segment.getP1(), segment.getP2());
    }

    /**
     * 过点做线段(p1,p2)的平行线
     *
     * @param point 点
     * @param p1    线段的端点1
     * @param p2    线段的端点2
     * @return 平行线
     */
    public static Line parallelLine(Point point, Point p1, Point p2) {
        if (point == null || p1 == null || p2 == null) {
            return null;
        }

        return parallelLine(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 过点(x,y)做由点(x1,y1)和点(x2,y2)构成的线段的平行线
     *
     * @param x  给定点的横坐标
     * @param y  给定点的纵坐标
     * @param x1 线段的第一个端点的横坐标
     * @param y1 线段的第一个端点的纵坐标
     * @param x2 线段的第二个端点的横坐标
     * @param y2 线段的第二个端点的纵坐标
     * @return 平行线
     */
    public static Line parallelLine(double x, double y, double x1, double y1, double x2, double y2) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        return LineUtil.parallelLine(x, y, line.getA(), line.getB());
    }

    /**
     * 将线段沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param segment 线段
     * @param x       横坐标的平移长度
     * @param y       纵坐标的平移长度
     * @return 平移后的线段
     */
    public static Segment translation(Segment segment, double x, double y) {
        if (segment == null) {
            return null;
        }
        return translation(segment.getP1(), segment.getP2(), x, y);
    }

    /**
     * 将线段(p1,p2)沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param p1 线段的端点一
     * @param p2 线段的端点二
     * @param x  横坐标平移的长度
     * @param y  纵坐标平移的长度
     * @return 平移后的线段
     */
    public static Segment translation(Point p1, Point p2, double x, double y) {
        if (p1 == null || p2 == null) {
            return null;
        }
        return translation(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y);
    }

    /**
     * 将由点()和点()构成的线段沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param x1 线段的第一个端点的横坐标
     * @param y1 线段的第一个端点的纵坐标
     * @param x2 线段的第二个端点的横坐标
     * @param y2 线段的第二个端点的纵坐标
     * @param x  横坐标平移的长度
     * @param y  纵坐标平移的长度
     * @return 平移后的线段
     */
    public static Segment translation(double x1, double y1, double x2, double y2, double x, double y) {
        Point p1 = new Point(x1 + x, y1 + y);
        Point p2 = new Point(x2 + x, y2 + y);
        return new Segment(p1, p2);
    }

    /**
     * 判断两条线段是否在线段范围内有交点
     *
     * @param segment1 第一条线段
     * @param segment2 第二条线段
     * @return 若线段内有交点则返回true
     */
    public static boolean linesIntersect(Segment segment1, Segment segment2) {
        if (segment1 == null || segment2 == null) {
            throw new NullPointerException("判断的线段为null");
        }
        return linesIntersect(segment1.getP1(), segment1.getP2(), segment2.getP1(), segment2.getP2());
    }

    /**
     * 判断线段(p1,p2)和线段(p3,p4)在线段范围内是否有交点
     *
     * @param p1 第一条线段的第一个端点
     * @param p2 第一条线段的第二个端点
     * @param p3 第二条线段的第一个端点
     * @param p4 第二条线段的第二个端点
     * @return 若线段内有交点则返回true
     */
    public static boolean linesIntersect(Point p1, Point p2, Point p3, Point p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            throw new NullPointerException("判断的点为null");
        }
        return linesIntersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY());
    }

    /**
     * 判断由点(x1,y1)和点(x2,y2)构成的线段,与点(x3,y3)和点(x4,y4)构成的线段,在范围内是否有交点
     *
     * @param x1 线段一的第一个端点的横坐标
     * @param y1 线段一的第二个端点的纵坐标
     * @param x2 线段二的第一个端点的横坐标
     * @param y2 线段二的第二个端点的纵坐标
     * @param x3 线段三的第一个端点的横坐标
     * @param y3 线段三的第二个端点的纵坐标
     * @param x4 线段四的第一个端点的横坐标
     * @param y4 线段四的第二个端点的纵坐标
     * @return 若线段内有交点则返回true
     */
    public static boolean linesIntersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return java.awt.geom.Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    /**
     * 将线段按照给定的中心点,逆时针旋转一个角度
     *
     * @param segment 给定的直线
     * @param center  旋转的中心点
     * @param angle   旋转的角度
     * @return 旋转后的线段
     */
    public static Segment rotate(Segment segment, Point center, double angle) {
        if (segment == null || center == null) {
            return null;
        }
        return rotate(segment.getP1(), segment.getP2(), center, angle);
    }

    /**
     * 将线段(p1,p2)按照给定的中心点,逆时针旋转一个角度
     *
     * @param p1     线段的第一个端点
     * @param p2     线段的第二个端点
     * @param center 旋转的中心点
     * @param angle  旋转的角度
     * @return 旋转后的线段
     */
    public static Segment rotate(Point p1, Point p2, Point center, double angle) {
        if (p1 == null || p2 == null || center == null) {
            return null;
        }
        return rotate(p1.getX(), p1.getY(), p2.getX(), p2.getY(), center.getX(), center.getY(), angle);
    }

    /**
     * 将由点(x1,y1)和点(x2,y2)构成的线段,按照点(cx,cy)逆时针旋转angle角度
     *
     * @param x1    线段一的第一个端点的横坐标
     * @param y1    线段一的第二个端点的纵坐标
     * @param x2    线段二的第一个端点的横坐标
     * @param y2    线段二的第二个端点的纵坐标
     * @param cx    旋转的中心点的横坐标
     * @param cy    旋转的中心点的纵坐标
     * @param angle 旋转的角度
     * @return 旋转后的线段
     */
    public static Segment rotate(double x1, double y1, double x2, double y2, double cx, double cy, double angle) {
        Point p1 = PointUtil.rotate(x1, y1, cx, cy, angle);
        Point p2 = PointUtil.rotate(x2, y2, cx, cy, angle);
        return new Segment(p1, p2);
    }

    /**
     * 获得线段相对于对称轴的轴对称线段
     *
     * @param segment 线段
     * @param axis    对称轴
     * @return 轴对称线段
     */
    public static Segment getAxialSymmetry(Segment segment, Line axis) {
        if (segment == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(segment.getP1(), segment.getP2(), axis);
    }

    /**
     * 获得线段(p1,p2)相对于对称轴的轴对称线段
     *
     * @param p1   线段的第一个端点
     * @param p2   线段的第二个端点
     * @param axis 对称轴
     * @return 轴对称线段
     */
    public static Segment getAxialSymmetry(Point p1, Point p2, Line axis) {
        if (p1 == null || p2 == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(p1.getX(), p1.getY(), p2.getX(), p2.getY(), axis.getA(), axis.getB(), axis.getC());
    }

    /**
     * 获得由点(x1,y1)和点(x2,y2)构成的线段,以直线ax+by+c=0为对称轴的轴对称线段
     *
     * @param x1 线段的第一个点的横坐标
     * @param y1 线段的第一个点的纵坐标
     * @param x2 线段的第二个点的横坐标
     * @param y2 线段的第二个点的纵坐标
     * @param a  直线的系数a
     * @param b  直线的系数b
     * @param c  直线的系数c
     * @return 轴对称线段
     */
    public static Segment getAxialSymmetry(double x1, double y1, double x2, double y2, double a, double b, double c) {
        Point p1 = PointUtil.getAxialSymmetry(x1, y1, a, b, c);
        Point p2 = PointUtil.getAxialSymmetry(x2, y2, a, b, c);
        return new Segment(p1, p2);
    }

    /**
     * 求线段相对于对称轴线段的轴对称图形
     *
     * @param segment 线段
     * @param axis    对称轴线段
     * @return 轴对称线段
     */
    public static Segment getAxialSymmetry(Segment segment, Segment axis) {
        if (segment == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(segment.getP1(), segment.getP2(), axis.getP1(), axis.getP2());
    }

    /**
     * 求线段(p1,p2)相对于对称轴(ap1,ap2)的轴对称线段
     *
     * @param p1  线段的第一个点
     * @param p2  线段的第二个点
     * @param ap1 轴对称线段的第一个点
     * @param ap2 轴对称线段的第二个点
     * @return 轴对称线段
     */
    public static Segment getAxialSymmetry(Point p1, Point p2, Point ap1, Point ap2) {
        if (p1 == null || p2 == null || ap1 == null || ap2 == null) {
            return null;
        }
        return getAxialSymmetry(p1.getX(), p1.getY(), p2.getX(), p2.getY(), ap1.getX(), ap1.getY(), ap2.getX(), ap2.getY());
    }

    /**
     * 求由点(x1,y1)和点(x2,y2)构成的线段,相对于由点(ax1,ay1)和点(ax2,ay2)构成的对称轴的轴对称线段
     *
     * @param x1  线段的第一个点的横坐标
     * @param y1  线段的第一个点的纵坐标
     * @param x2  线段的第二个点的横坐标
     * @param y2  线段的第二个点的纵坐标
     * @param ax1 对称轴线段的第一个点的横坐标
     * @param ay1 对称轴线段的第一个点的纵坐标
     * @param ax2 对称轴线段的第二个点的横坐标
     * @param ay2 对称轴线段的第二个点的纵坐标
     * @return 轴对称线段
     */
    public static Segment getAxialSymmetry(double x1, double y1, double x2, double y2, double ax1, double ay1, double ax2, double ay2) {
        Line line = LineUtil.getLine(ax1, ay1, ax2, ay2);
        return getAxialSymmetry(x1, y1, x2, y2, line.getA(), line.getB(), line.getC());
    }

    /**
     * 在最小精度范围内判断点是否在线段上
     *
     * @param point   点
     * @param segment 线段
     * @return 若点在线段山, 则返回true
     */
    public static boolean onSegment(Point point, Segment segment) {
        return onSegment(point, segment, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断点是否在线段上
     *
     * @param point     点
     * @param segment   线段
     * @param precision 给定的精度
     * @return 若点在线段山, 则返回true
     */
    public static boolean onSegment(Point point, Segment segment, double precision) {
        if (point == null) {
            throw new NullPointerException("点为null");
        }
        if (segment == null) {
            throw new NullPointerException("线段为null");
        }
        return onSegment(point, segment.getP1(), segment.getP2(), precision);
    }

    /**
     * 在最小精度范围内判断点是否在线段(p1,p2)上
     *
     * @param point 给定的点
     * @param p1    线段的第一个端点
     * @param p2    线段的第二个端点
     * @return 若点在线段上, 则返回true
     */
    public static boolean onSegment(Point point, Point p1, Point p2) {
        return onSegment(point, p1, p2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断点是否在线段(p1,p2)上
     *
     * @param point     给定的点
     * @param p1        线段的第一个端点
     * @param p2        线段的第二个端点
     * @param precision 给定的精度
     * @return 若点在线段上, 则返回true
     */
    public static boolean onSegment(Point point, Point p1, Point p2, double precision) {
        if (point == null || p1 == null || p2 == null) {
            throw new NullPointerException("点为null");
        }
        return onSegment(point.getX(), point.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY(), precision);
    }

    /**
     * 在最小精度范围内判断点(x,y)是否在由点(x1,y1)和点(x2,y2)构成的线段上
     *
     * @param x  给定点的横坐标
     * @param y  给定点的纵坐标
     * @param x1 线段的第一个点的横坐标
     * @param y1 线段的第一个点的纵坐标
     * @param x2 线段的第二个点的横坐标
     * @param y2 线段的第二个点的纵坐标
     * @return 若点在线段上, 则返回true
     */
    public static boolean onSegment(double x, double y, double x1, double y1, double x2, double y2) {
        return onSegment(x, y, x1, y1, x2, y2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断点(x,y)是否在由点(x1,y1)和点(x2,y2)构成的线段上
     *
     * @param x         给定点的横坐标
     * @param y         给定点的纵坐标
     * @param x1        线段的第一个点的横坐标
     * @param y1        线段的第一个点的纵坐标
     * @param x2        线段的第二个点的横坐标
     * @param y2        线段的第二个点的纵坐标
     * @param precision 给定的精度
     * @return 若点在线段上, 则返回true
     */
    public static boolean onSegment(double x, double y, double x1, double y1, double x2, double y2, double precision) {
        if (precision < NumberUtil.MIN_VALUE) {
            precision = NumberUtil.MIN_VALUE;
        }
        return distance(x, y, x1, y1, x2, y2) < precision;
    }

    /**
     * 在给定的精度范围内判断给定的两条线段在给定的精度是否重合
     *
     * @param s1        第一条线段
     * @param s2        第二条线段
     * @param precision 给定的精度
     * @return 若两条线段的端点都重合, 则返回true
     */
    public static boolean coincide(Segment s1, Segment s2, double precision) {
        if (s1 == null || s2 == null) {
            throw new NullPointerException("线段为null");
        }
        return coincide(s1.getP1(), s1.getP2(), s2.getP1(), s2.getP2(), precision);
    }

    /**
     * 在最小精度范围内判断给定的两条线段在给定的精度是否重合
     *
     * @param s1 第一条线段
     * @param s2 第二条线段
     * @return 若两条线段的端点都重合, 则返回true
     */
    public static boolean coincide(Segment s1, Segment s2) {
        return coincide(s1, s2, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断线段(p1,p2)和线段(p3,p4)在给定的精度下是否重合
     *
     * @param p1        第一个线段的第一个端点
     * @param p2        第一个线段的第二个端点
     * @param p3        第二个线段的第一个端点
     * @param p4        第二个线段的第二个端点
     * @param precision 给定的精度
     * @return 若两条线段的端点都重合, 则返回true
     */
    public static boolean coincide(Point p1, Point p2, Point p3, Point p4, double precision) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            throw new NullPointerException("线段端点为null");
        }
        return coincide(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY(), precision);
    }

    /**
     * 在最小精度范围内判断线段(p1,p2)和线段(p3,p4)在给定的精度下是否重合
     *
     * @param p1 第一个线段的第一个端点
     * @param p2 第一个线段的第二个端点
     * @param p3 第二个线段的第一个端点
     * @param p4 第二个线段的第二个端点
     * @return 若两条线段的端点都重合, 则返回true
     */
    public static boolean coincide(Point p1, Point p2, Point p3, Point p4) {
        return coincide(p1, p2, p3, p4, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断由点(x1,y1)和点(x2,y2)构成的线段与由点(x3,y3)和点(x4,y4)构成的线段是否重合
     *
     * @param x1        第一条线段的第一个端点的横坐标
     * @param y1        第一条线段的第一个端点的纵坐标
     * @param x2        第一条线段的第二个端点的横坐标
     * @param y2        第一条线段的第二个端点的纵坐标
     * @param x3        第二条线段的第一个端点的横坐标
     * @param y3        第二条线段的第一个端点的纵坐标
     * @param x4        第二条线段的第二个端点的横坐标
     * @param y4        第二条线段的第二个端点的纵坐标
     * @param precision 给定的精度
     * @return 若两条线段的端点都重合, 则返回true
     */
    public static boolean coincide(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double precision) {
        return PointUtil.coincide(x1, y1, x3, y3, precision) && PointUtil.coincide(x2, y2, x4, y4, precision)
                || PointUtil.coincide(x1, y1, x4, y4, precision) && PointUtil.coincide(x2, y2, x3, y3, precision);
    }

    /**
     * 在最小精度范围内判断由点(x1,y1)和点(x2,y2)构成的线段与由点(x3,y3)和点(x4,y4)构成的线段是否重合
     *
     * @param x1 第一条线段的第一个端点的横坐标
     * @param y1 第一条线段的第一个端点的纵坐标
     * @param x2 第一条线段的第二个端点的横坐标
     * @param y2 第一条线段的第二个端点的纵坐标
     * @param x3 第二条线段的第一个端点的横坐标
     * @param y3 第二条线段的第一个端点的纵坐标
     * @param x4 第二条线段的第二个端点的横坐标
     * @param y4 第二条线段的第二个端点的纵坐标
     * @return 若两条线段的端点都重合, 则返回true
     */
    public static boolean coincide(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return coincide(x1, y1, x2, y2, x3, y3, x4, y4, NumberUtil.MIN_VALUE);
    }

    /**
     * 将相对坐标下的线段,转化为绝对坐标下的线段
     *
     * @param segment    给定的线段
     * @param coordinate 相对坐标
     * @return 绝对坐标下的线段
     */
    public static Segment toAbsoluteCoordinate(Segment segment, Coordinate coordinate) {
        return segment == null || coordinate == null ? null : toAbsoluteCoordinate(segment.getP1(), segment.getP2(), coordinate);
    }

    /**
     * 将相对坐标下的线段(p1,p2)转化为绝对坐标下的线段
     *
     * @param p1         线段的第一个点
     * @param p2         线段的第二个点
     * @param coordinate 相对坐标
     * @return 绝对坐标下的线段
     */
    public static Segment toAbsoluteCoordinate(Point p1, Point p2, Coordinate coordinate) {
        return p1 == null || p2 == null || coordinate == null ? null :
                toAbsoluteCoordinate(p1.getX(), p1.getY(), p2.getX(), p2.getY(),
                        coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX(), coordinate.getSpaceY());
    }

    /**
     * 将相对坐标下的线段,转化为绝对坐标下的线段
     *
     * @param x1      线段的第一个端点的横坐标
     * @param y1      线段的第一个端点的纵坐标
     * @param x2      线段的第二个端点的横坐标
     * @param y2      线段的第二个端点的纵坐标
     * @param originX 相对坐标原点的横坐标
     * @param originY 相对坐标原点的纵坐标
     * @param spaceX  相对坐标x轴单位长度的间距
     * @param spaceY  相对坐标y轴单位长度的间距
     * @return 绝对坐标下的线段
     */
    public static Segment toAbsoluteCoordinate(double x1, double y1, double x2, double y2, double originX, double originY, double spaceX, double spaceY) {
        Point p1 = PointUtil.toAbsoluteCoordinate(x1, y1, originX, originY, spaceX, spaceY);
        Point p2 = PointUtil.toAbsoluteCoordinate(x2, y2, originX, originY, spaceX, spaceY);
        return new Segment(p1, p2);
    }

    /**
     * 将绝对坐标下的线段,转化为相对坐标下的线段
     *
     * @param segment    给定的线段
     * @param coordinate 相对的坐标
     * @return 相对坐标下的线段
     */
    public static Segment toRelativeCoordinate(Segment segment, Coordinate coordinate) {
        return segment == null || coordinate == null ? null : toRelativeCoordinate(segment.getP1(), segment.getP2(), coordinate);
    }

    /**
     * 将绝对坐标下的线段(p1,p2)转化为相对坐标下的线段
     *
     * @param p1         线段的第一个点
     * @param p2         线段的第二个点
     * @param coordinate 相对坐标
     * @return 相对坐标下的线段
     */
    public static Segment toRelativeCoordinate(Point p1, Point p2, Coordinate coordinate) {
        return p1 == null || p2 == null || coordinate == null ? null :
                toRelativeCoordinate(p1.getX(), p1.getY(), p2.getX(), p2.getY(),
                        coordinate.getOriginX(), coordinate.getOriginY(), coordinate.getSpaceX(), coordinate.getSpaceY());
    }

    /**
     * 将绝对坐标下的线段,转化为相对坐标下的线段
     *
     * @param x1      线段的第一个端点的横坐标
     * @param y1      线段的第一个端点的纵坐标
     * @param x2      线段的第二个端点的横坐标
     * @param y2      线段的第二个端点的纵坐标
     * @param originX 相对坐标原点的横坐标
     * @param originY 相对坐标原点的纵坐标
     * @param spaceX  相对坐标x轴单位长度的间距
     * @param spaceY  相对坐标y轴单位长度的间距
     * @return 相对坐标下的线段
     */
    public static Segment toRelativeCoordinate(double x1, double y1, double x2, double y2, double originX, double originY, double spaceX, double spaceY) {
        Point p1 = PointUtil.toRelativeCoordinate(x1, y1, originX, originY, spaceX, spaceY);
        Point p2 = PointUtil.toRelativeCoordinate(x2, y2, originX, originY, spaceX, spaceY);
        return new Segment(p1, p2);
    }

    /**
     * 获得线段的中点
     *
     * @param segment 给定的线段
     * @return 线段的中点
     */
    public static Point getMidpoint(Segment segment) {
        return segment == null ? null : getMidpoint(segment.getP1(), segment.getP2());
    }

    /**
     * 获得线段(p1,p2)的中点
     *
     * @param p1 线段的第一个点
     * @param p2 线段的第二个点
     * @return 线段的中点
     */
    public static Point getMidpoint(Point p1, Point p2) {
        return p1 == null || p2 == null ? null : getMidpoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 获得由点(x1,y1)与点(x2,y2)构成的线段的中点
     *
     * @param x1 线段的第一个点的横坐标
     * @param y1 线段的第一个点的纵坐标
     * @param x2 线段的第二个点的横坐标
     * @param y2 线段的第二个点的纵坐标
     * @return 线段的中点
     */
    public static Point getMidpoint(double x1, double y1, double x2, double y2) {
        double x = NumberUtil.average(x1 + x2);
        double y = NumberUtil.average(y1 + y2);
        return new Point(x, y);
    }
}
