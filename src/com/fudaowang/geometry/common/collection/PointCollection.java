package com.fudaowang.geometry.common.collection;

import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.util.NumberUtil;
import com.fudaowang.geometry.common.util.PointUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 表示点集合的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/1/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointCollection {
    private final Set set;

    /**
     * 构造方法
     */
    public PointCollection() {
        set = new HashSet();
    }

    /**
     * 获得点的集合
     *
     * @return 点的数组
     */
    public Point[] getPoints() {
        return (Point[]) set.toArray(new Point[set.size()]);
    }

    /**
     * 获得点的数量
     *
     * @return 点集合的size
     */
    public int size() {
        return set.size();
    }

    /**
     * 在最小精度范围内判断集合中是否有重复的点
     *
     * @param point 给定的点
     * @return 若存在与给定点重复的点, 则返回true
     */
    public boolean contains(Point point) {
        return contains(point, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断集合中是否有重复的点
     *
     * @param point     给定的点
     * @param precision 给定的精度
     * @return 若存在与给定点重复的点, 则返回true
     */
    public boolean contains(final Point point, final double precision) {
        if (point == null) {
            throw new NullPointerException("点为null");
        }

        return contains(point.getX(), point.getY(), precision);
    }

    /**
     * 在最小精度范围内判断集合中是否有重复的点
     *
     * @param x 给定点的横坐标
     * @param y 给定点的纵坐标
     * @return 若存在与给定点重复的点, 则返回true
     */
    public boolean contains(double x, double y) {
        return contains(x, y, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断集合中是否有重复的点
     *
     * @param x         给定点的横坐标
     * @param y         给定点的纵坐标
     * @param precision 给定的精度
     * @return 若存在与给定点重复的点, 则返回true
     */
    public boolean contains(final double x, final double y, final double precision) {
        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Point point = (Point) o;
                return PointUtil.coincide(point.getX(), point.getY(), x, y, precision);
            }
        });
    }

    /**
     * 向集合中添加点,若在最小精度范围内存在重复的点,则不予添加
     *
     * @param point 给定的点
     * @return 若添加成功则返回true
     */
    public boolean add(Point point) {
        return add(point, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加点,若在给定的精度范围内存在重复的点,则不予添加
     *
     * @param point     给定的点
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Point point, double precision) {
        return point != null && add(point.getX(), point.getY(), precision);
    }

    /**
     * 向集合中添加点,若在最小精度范围内存在重复的点,则不予添加
     *
     * @param x 给定点的横坐标
     * @param y 给定点的纵坐标
     * @return 若添加成功则返回true
     */
    public boolean add(double x, double y) {
        return add(x, y, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加点,若在给定的精度范围内存在重复的点,则不予添加
     *
     * @param x         给定点的横坐标
     * @param y         给定点的纵坐标
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(double x, double y, double precision) {
        return !contains(x, y, precision) && set.add(new Point(x, y));
    }
}
