package com.fudaowang.common.util;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Polygon;
import com.fudaowang.common.graph.Segment;
import org.apache.commons.collections.Transformer;

/**
 * 对多边形的操作类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/6/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class PolygonUtil {
    /**
     * 将多边形按照中心点逆时针旋转
     *
     * @param polygon 多边形
     * @param center  中心点
     * @param angle   旋转的角度
     * @return 旋转后的多边形
     */
    public Polygon rotate(Polygon polygon, Point center, double angle) {
        if (polygon == null || center == null) {
            return null;
        }
        return rotate(polygon.getPoints(), center, angle);
    }

    /**
     * 将多边形按照中心点逆时针旋转
     *
     * @param points 多边形的点集
     * @param center 中心点
     * @param angle  旋转的角度
     * @return 旋转后的多边形
     */
    public Polygon rotate(Point[] points, final Point center, final double angle) {
        if (points == null || center == null) {
            return null;
        }
        Point[] result = PointUtil.collect(points, new Transformer() {
            public Object transform(Object o) {
                Point p = (Point) o;
                return PointUtil.rotate(p, center, angle);
            }
        });
        return new Polygon(result);
    }

    /**
     * 计算多边形的轴对称图形
     *
     * @param polygon 多边形
     * @param axis    直线对称轴
     * @return 对称的多边形
     */
    public static Polygon getAxialSymmetry(Polygon polygon, Line axis) {
        if (polygon == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(polygon.getPoints(), axis);
    }

    /**
     * 计算多边形的轴对称图形
     *
     * @param points 多边形的点集
     * @param axis   直线对称轴
     * @return 对称的多边形
     */
    public static Polygon getAxialSymmetry(Point[] points, final Line axis) {
        if (points == null || axis == null) {
            return null;
        }
        Point[] result = PointUtil.collect(points, new Transformer() {
            public Object transform(Object o) {
                Point p = (Point) o;
                return PointUtil.getAxialSymmetry(p, axis);
            }
        });
        return new Polygon(result);
    }

    /**
     * 计算多边形的轴对称图形
     *
     * @param polygon 多边形
     * @param axis    线段对称轴
     * @return 对称的多边形
     */
    public static Polygon getAxialSymmetry(Polygon polygon, Segment axis) {
        if (polygon == null || axis == null) {
            return null;
        }
        return getAxialSymmetry(polygon.getPoints(), axis);
    }

    /**
     * 计算多边形的轴对称图形
     *
     * @param points 多边形的点集
     * @param axis   线段对称轴
     * @return 对称的多边形
     */
    public static Polygon getAxialSymmetry(Point[] points, final Segment axis) {
        if (points == null || axis == null) {
            return null;
        }
        Point[] result = PointUtil.collect(points, new Transformer() {
            public Object transform(Object o) {
                Point p = (Point) o;
                return PointUtil.getAxialSymmetry(p, axis);
            }
        });
        return new Polygon(result);
    }

}
