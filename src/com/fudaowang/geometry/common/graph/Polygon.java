package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.util.PointUtil;

import java.util.Arrays;
import java.util.Collection;

/**
 * 表示多边形的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/8/12
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Polygon {
    protected Point[] points;

    /**
     * 利用已知点集来构造多边形,点集的长度不能小于3
     *
     * @param points 给定的点集
     */
    public Polygon(Point[] points) {
        if (points == null) {
            throw new NullPointerException("多边形点集不能为空");
        }
        if (points.length < 3) {
            throw new IllegalArgumentException("多边形点的个数必须大于2");
        }
        this.points = points;
    }

    /**
     * 利用已知点集合来构造多边形,点的数量不能小于3
     *
     * @param collection 点的集合
     */
    public Polygon(Collection collection) {
        points = PointUtil.toArray(collection);
        if (points == null) {
            throw new NullPointerException("多边形点集不能为空");
        }
        if (points.length > 3) {
            throw new IllegalArgumentException("多边形点的个数必须大于2");
        }
    }

    /**
     * 获得多边形的点集
     *
     * @return 点集
     */
    public Point[] getPoints() {
        return points;
    }

    public String toString() {
        return "Polygon{" +
                "points=" + (points == null ? null : Arrays.asList(points)) +
                '}';
    }
}
