package com.fudaowang.geometry.common.collection;

import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.util.NumberUtil;
import com.fudaowang.geometry.common.util.PointUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.*;

/**
 * 表示点集合的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/1/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointCollection {
    private final List list;

    /**
     * 构造方法
     */
    public PointCollection() {
        list = new ArrayList();
    }

    /**
     * 获得点的集合
     *
     * @return 点的数组
     */
    public Point[] getPoints() {
        return (Point[]) list.toArray(new Point[list.size()]);
    }

    /**
     * 获得点的数量
     *
     * @return 点集合的size
     */
    public int size() {
        return list.size();
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
        return CollectionUtils.exists(list, new Predicate() {
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
        return !contains(x, y, precision) && list.add(new Point(x, y));
    }

    /**
     * 对点集合按照给定的规则排序
     *
     * @param comparator 给定的规则
     * @return 排序后的点集合
     */
    public PointCollection sort(Comparator comparator) {
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        return this;
    }

    /**
     * 根据给定的横纵坐标在最小精度范围来查找点
     *
     * @param x 给定的横坐标
     * @param y 给定的纵坐标
     * @return 若存在与给定坐标重合的点, 则返回该点
     */
    public Point findPoint(double x, double y) {
        return findPoint(x, y, NumberUtil.MIN_VALUE);
    }

    /**
     * 根据给定的横纵坐标在给定的精度范围来查找点
     *
     * @param x         给定的横坐标
     * @param y         给定的纵坐标
     * @param precision 给定的精度
     * @return 若存在与给定坐标重合的点, 则返回该点
     */
    public Point findPoint(final double x, final double y, final double precision) {
        return (Point) CollectionUtils.find(list, new Predicate() {
            public boolean evaluate(Object o) {
                Point point = (Point) o;
                return PointUtil.coincide(x, y, point.getX(), point.getY(), precision);
            }
        });
    }
}
