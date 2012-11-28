package com.fudaowang.common.util;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Parabola;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Segment;
import org.apache.commons.collections.Predicate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 对抛物线进行操作的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/31/12
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParabolaUtil {
    /**
     * 用指定的三个点生成抛物线
     * 三个点的横坐标不能重复
     * 不保证一定能够生成抛物线
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @param p3 第三个点
     * @return 生成的抛物线
     */
    public static Parabola getParabola(Point p1, Point p2, Point p3) {
        if (p1 == null || p2 == null || p3 == null) {
            return null;
        }
        return getParabola(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }

    /**
     * 过点(x1,y1),点(x2,y2)和点(x3,y3)求抛物线
     * 当三个点的横坐标重复时无法生成抛物线
     * 即使不两两重复,也不保证能一定生成抛物线
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     * @param x3 第三个点的横坐标
     * @param y3 第三个点的纵坐标
     * @return 生成的抛物线
     */
    public static Parabola getParabola(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (NumberUtil.equal(x1, x2) || NumberUtil.equal(x2, x3) || NumberUtil.equal(x1, x3)) {
            return null; //任意两个横坐标相等的话都构造不出抛物线来
        }

        double denominator = (x1 - x2) * (x1 - x3) * (x2 - x3);
        double a = ((y1 - y2) * (x1 - x3) - (y1 - y3) * (x1 - x2)) / denominator; //极小的几率这里会让a为0,但不清楚此条件的几何意义
        double b = ((y1 - y2) * (x1 * x1 - x3 * x3) - (y1 - y3) * (x1 * x1 - x2 * x2)) / -denominator;
        double c = y1 - a * x1 * x1 - b * x1;
        return new Parabola(a, b, c);
    }

    /**
     * 已知抛物线的顶点和任意点,求抛物线
     *
     * @param vertex 顶点
     * @param point  不为顶点的任一点
     * @return 生成的抛物线
     */
    public static Parabola getParabola(Point vertex, Point point) {
        if (vertex == null || point == null) {
            return null;
        }
        return getParabola(vertex.getX(), vertex.getY(), point.getX(), point.getY());
    }

    /**
     * 已知抛物线的顶点(vx,vy),且抛物线过点(px,py),求抛物线的方程
     * 若两点重合或者横坐标相等,则无法生成抛物线
     *
     * @param vx 顶点的横坐标
     * @param vy 顶点的纵坐标
     * @param px 任意点的横坐标
     * @param py 任意点的纵坐标
     * @return 生成的抛物线
     */
    public static Parabola getParabola(double vx, double vy, double px, double py) {
        if (NumberUtil.equal(vx, px) || NumberUtil.equal(vy, py)) {
            return null;
        }

        double a = (py - vy) / Math.pow(px - vx, 2);
        double b = -2.0 * a * vx;
        double c = vy + b * b / (4.0 * a);
        return new Parabola(a, b, c);
    }

    /**
     * 求抛物线y=ax^2+bx+c的顶点坐标,a不能为0
     *
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     * @return 抛物线的顶点
     */
    public static Point vertexPoint(double a, double b, double c) {
        if (NumberUtil.isZero(a)) {
            return null;
        }

        double x = -b / (2.0 * a);
        double y = (4.0 * a * c - b * b) / (4.0 * a);
        return new Point(x, y);
    }

    /**
     * 求抛物线的顶点
     *
     * @param parabola 给定的抛物线
     * @return 抛物线的顶点
     */
    public static Point vertexPoint(Parabola parabola) {
        if (parabola == null) {
            return null;
        }
        return vertexPoint(parabola.getA(), parabola.getB(), parabola.getC());
    }

    /**
     * 已知x,求抛物线方程的y值
     *
     * @param parabola 给定的抛物线
     * @param x        给定的x值
     * @return 抛物线对应的y值
     */
    public static double getY(Parabola parabola, double x) {
        if (parabola == null) {
            return Double.NaN;
        }
        return getY(parabola.getA(), parabola.getB(), parabola.getC(), x);
    }

    /**
     * 已知x,求ax^2+bx+c的值
     *
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     * @param x 给定的x值
     * @return 抛物线对应的y值
     */
    public static double getY(double a, double b, double c, double x) {
        return a * x * x + b * x + c;
    }

    /**
     * 求直线与抛物线的交点
     *
     * @param line     直线
     * @param parabola 抛物线
     * @return 直线与抛物线的交点, 可能有0-2个点
     */
    public static Point[] intersect(Line line, Parabola parabola) {
        if (line == null || parabola == null) {
            return new Point[0];
        }

        return intersect(line.getA(), line.getB(), line.getC(), parabola.getA(), parabola.getB(), parabola.getC());
    }

    /**
     * 求直线lax+lby+lc=0与抛物线y=pax^2+pbx+pc的交点
     *
     * @param la 直线的系数a
     * @param lb 直线的系数b
     * @param lc 直线的系数c
     * @param pa 抛物线的系数a
     * @param pb 抛物线的系数b
     * @param pc 抛物线的系数c
     * @return 直线与抛物线的交点, 可能有0-2个点
     */
    public static Point[] intersect(double la, double lb, double lc, double pa, double pb, double pc) {
        if (NumberUtil.isZero(la) && NumberUtil.isZero(lb)) {
            return new Point[0];
        }

        if (NumberUtil.isZero(pa)) {
            return new Point[0];
        }

        if (NumberUtil.isZero(lb)) {
            double x = -lc / la;
            double y = getY(pa, pb, pc, x);
            return new Point[]{new Point(x, y)};
        }

        double delta = (Math.pow(pb * lb + la, 2) - 4.0 * pa * lb * (pc * lb + lc)) / Math.pow(lb, 2);

        if (delta < 0) {
            return new Point[0];
        }

        if (delta == 0) {
            double x = (pb + la / lb) / (-2.0 * pa);
            double y = getY(pa, pb, pc, x);
            return new Point[]{new Point(x, y)};
        }

        double x1 = (-pb - la / lb + Math.sqrt(delta)) / (2.0 * pa);
        double y1 = getY(pa, pb, pc, x1);
        double x2 = (-pb - la / lb - Math.sqrt(delta)) / (2.0 * pa);
        double y2 = getY(pa, pb, pc, x2);
        return new Point[]{new Point(x1, y1), new Point(x2, y2)};
    }

    /**
     * 求线段与抛物线的交点
     *
     * @param segment  线段
     * @param parabola 抛物线
     * @return 交点数组
     */
    public static Point[] intersect(Segment segment, Parabola parabola) {
        if (segment == null || parabola == null) {
            return new Point[0];
        }
        return intersect(segment.getP1(), segment.getP2(), parabola);
    }

    /**
     * 求线段(p1,p2)与抛物线的交点
     *
     * @param p1       线段的第一个端点
     * @param p2       线段的第二个端点
     * @param parabola 抛物线
     * @return 交点数组
     */
    public static Point[] intersect(Point p1, Point p2, Parabola parabola) {
        if (p1 == null || p2 == null || parabola == null) {
            return new Point[0];
        }
        return intersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), parabola.getA(), parabola.getB(), parabola.getC());
    }

    /**
     * 求由点(x1,y1)和点(x2,y2)构成的线段与抛物线y=pax^2+pbx+pc的交点
     *
     * @param x1 线段的第一个端点的横坐标
     * @param y1 线段的第一个端点的纵坐标
     * @param x2 线段的第二个端点的横坐标
     * @param y2 线段的第二个端点的纵坐标
     * @param pa 抛物线的系数a
     * @param pb 抛物线的系数b
     * @param pc 抛物线的系数c
     * @return 交点数组
     */
    public static Point[] intersect(final double x1, final double y1, final double x2, final double y2, double pa, double pb, double pc) {
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        Point[] points = intersect(line.getA(), line.getB(), line.getC(), pa, pb, pc);
        return PointUtil.filter(points, new Predicate() {
            public boolean evaluate(Object o) {
                Point p = (Point) o;
                return LineUtil.inSegment(p.getX(), p.getY(), x1, y1, x2, y2);
            }
        });
    }

    /**
     * 求点到抛物线的最短距离
     *
     * @param point    点
     * @param parabola 抛物线
     * @return 点到抛物线的最短距离
     */
    public static double distance(Point point, Parabola parabola) {
        if (point == null || parabola == null) {
            return Double.NaN;
        }
        return distance(point.getX(), point.getY(), parabola.getA(), parabola.getB(), parabola.getC());
    }

    /**
     * 求点(x,y)到抛物线y=ax^2+bx+c的最短距离
     *
     * @param x 点的横坐标
     * @param y 点的纵坐标
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     * @return 点到抛物线的最短距离
     */
    public static double distance(double x, double y, double a, double b, double c) {
        throw new NotImplementedException();
    }

    /**
     * 在指定的精度范围内,判定点是否在抛物线上
     *
     * @param point     点
     * @param parabola  抛物线
     * @param precision 指定的精度
     * @return 若点到抛物线的最短距离小于给定的精度, 则返回true
     */
    public static boolean onParabola(Point point, Parabola parabola, double precision) {
        if (point == null) {
            throw new NullPointerException("给定的点为null");
        }

        if (parabola == null) {
            throw new NullPointerException("抛物线为null");
        }

        return onParabola(point.getX(), point.getY(), parabola.getA(), parabola.getB(), parabola.getC(), precision);
    }

    /**
     * 在指定的精度范围内,判定点(x,y)是否在抛物线y=ax^2+bx+c上
     *
     * @param x         点的横坐标
     * @param y         点的纵坐标
     * @param a         抛物线的系数a
     * @param b         抛物线的系数b
     * @param c         抛物线的系数c
     * @param precision 指定的精度
     * @return 若点到抛物线的最短距离小于给定的精度, 则返回true
     */
    public static boolean onParabola(double x, double y, double a, double b, double c, double precision) {
        return distance(x, y, a, b, c) < precision;
    }

    /**
     * 将抛物线沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param parabola 抛物线
     * @param x        横坐标平移的长度
     * @param y        纵坐标平移的长度
     * @return 平移后的抛物线
     */
    public static Parabola translation(Parabola parabola, double x, double y) {
        if (parabola == null) {
            return null;
        }
        return translation(parabola.getA(), parabola.getB(), parabola.getC(), x, y);
    }

    /**
     * 将抛物线y=ax^2+bx+c沿横坐标正方向平移x,沿纵坐标正方向平移y
     *
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     * @param x 横坐标平移的长度
     * @param y 纵坐标平移的长度
     * @return 平移后的抛物线
     */
    public static Parabola translation(double a, double b, double c, double x, double y) {
        return new Parabola(a, b - 2.0 * a * x, a * x * x - b * x + c + y);
    }
}
