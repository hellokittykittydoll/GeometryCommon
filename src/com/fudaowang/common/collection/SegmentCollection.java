package com.fudaowang.common.collection;

import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Segment;
import com.fudaowang.common.util.NumberUtil;
import com.fudaowang.common.util.SegmentUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.*;

/**
 * 存放Segment类的集合
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12-12-27
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class SegmentCollection {
    private final Set set;

    /**
     * 默认构造方法
     */
    public SegmentCollection() {
        set = new HashSet();
    }

    /**
     * 获得所有的线段
     *
     * @return 线段的数组
     */
    public Segment[] getSegments() {
        return (Segment[]) set.toArray(new Segment[set.size()]);
    }

    /**
     * 添加一条由p1和p2构成的线段,若在最小精度范围内与现有的线段重合,则不予添加
     *
     * @param p1 线段的第一个端点
     * @param p2 线段的第二个端点
     * @return 若添加成功则返回true
     */
    public boolean add(Point p1, Point p2) {
        return add(p1, p2, NumberUtil.MIN_VALUE);
    }

    /**
     * 添加一条由p1和p2构成的线段,若在给定精度范围内与现有的线段重合,则不予添加
     *
     * @param p1        线段的第一个端点
     * @param p2        线段的第二个端点
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Point p1, Point p2, double precision) {
        if (p1 == null || p2 == null) {
            return false;
        }

        if (contains(p1, p2, precision)) {
            return false;
        }

        return set.add(new Segment(p1, p2));
    }

    /**
     * 添加一条线段,若在最小精度范围内与现有的线段重合,则不予添加
     *
     * @param segment 要添加的线段
     * @return 若添加成功则返回true
     */
    public boolean add(final Segment segment) {
        return add(segment, NumberUtil.MIN_VALUE);
    }

    /**
     * 添加一条线段,若在给定的精度范围内与现有的线段重合,则不予添加
     *
     * @param segment   要添加的线段
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(final Segment segment, double precision) {
        if (segment == null) {
            return false;
        }

        if (contains(segment, precision)) {
            return false;
        }

        return set.add(segment);
    }

    /**
     * 添加一条由点p1和点p2构成的线段,若在最小精度范围内与现有的线段重合,则不予添加
     *
     * @param p1 线段的第一个点
     * @param p2 线段的第二个点
     * @return 若添加成功则返回true
     */
    public boolean contains(Point p1, Point p2) {
        return contains(p1, p2, NumberUtil.MIN_VALUE);
    }

    /**
     * 添加一条由点p1和点p2构成的线段,若在给定的精度范围内与现有的线段重合,则不予添加
     *
     * @param p1        线段的第一个点
     * @param p2        线段的第二个点
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean contains(final Point p1, final Point p2, final double precision) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("线段的端点为null");
        }
        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Segment s = (Segment) o;
                return SegmentUtil.coincide(s.getP1(), s.getP2(), p1, p2, precision);
            }
        });
    }

    /**
     * 在最小精度范围内判断集合内是否有重合的线段
     *
     * @param segment 给定的线段
     * @return 若集合内存在与给定线段重合的线段, 则返回true
     */
    public boolean contains(Segment segment) {
        return contains(segment, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断集合内是否有重合的线段
     *
     * @param segment   给定的线段
     * @param precision 给定的精度
     * @return 若集合内存在与给定线段重合的线段, 则返回true
     */
    public boolean contains(Segment segment, double precision) {
        if (segment == null) {
            throw new NullPointerException("线段为null");
        }

        return contains(segment.getP1(), segment.getP2(), precision);
    }

    /**
     * 获得集合内线段的个数
     *
     * @return 线段的个数
     */
    public int size() {
        return set.size();
    }
}
