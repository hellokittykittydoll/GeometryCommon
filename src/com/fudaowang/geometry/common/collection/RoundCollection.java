package com.fudaowang.geometry.common.collection;

import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.graph.Round;
import com.fudaowang.geometry.common.util.NumberUtil;
import com.fudaowang.geometry.common.util.RoundUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 圆的集合类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12-12-27
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class RoundCollection {
    private final Set set;

    /**
     * 初始化构造方法
     */
    public RoundCollection() {
        set = new HashSet();
    }

    /**
     * 获得所有圆
     *
     * @return 圆的数组
     */
    public Round[] getRounds() {
        return (Round[]) set.toArray(new Round[set.size()]);
    }

    /**
     * 在最小精度范围内判断集合内是否有重合的圆
     *
     * @param round 给定的圆
     * @return 若集合内存在与给定圆重合的圆, 则返回true
     */
    public boolean contains(Round round) {
        return contains(round, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断集合内是否有重合的圆
     *
     * @param round     给定的圆
     * @param precision 给定的精度
     * @return 若集合内存在与给定圆重合的圆, 则返回true
     */
    public boolean contains(Round round, double precision) {
        if (round == null) {
            throw new NullPointerException("圆为null");
        }
        return contains(round.getCenter(), round.getRadius(), precision);
    }

    /**
     * 在最小精度范围内判断集合内是否有重合的圆
     *
     * @param center 给定圆的圆心
     * @param radius 给定圆的半径
     * @return 若集合内存在与给定圆重合的圆, 则返回true
     */
    public boolean contains(Point center, double radius) {
        return contains(center, radius, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断集合内是否有重合的圆
     *
     * @param center    给定圆的圆心
     * @param radius    给定圆的半径
     * @param precision 给定的精度
     * @return 若集合内存在与给定圆重合的圆, 则返回true
     */
    public boolean contains(final Point center, final double radius, final double precision) {
        if (center == null) {
            throw new NullPointerException("圆心为null");
        }
        if (!NumberUtil.isMoreThanZero(radius)) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Round r = (Round) o;
                return RoundUtil.coincide(r.getCenter(), r.getRadius(), center, radius, precision);
            }
        });
    }

    /**
     * 向集合添加一个圆,若在最小精度范围内有重复,则不添加
     *
     * @param round 给定的圆
     * @return 若添加成功则返回true
     */
    public boolean add(Round round) {
        return add(round, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合添加一个圆,若在给定精度范围内有重复,则不添加
     *
     * @param round     给定的圆
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Round round, double precision) {
        if (round == null) {
            return false;
        }

        if (contains(round, precision)) {
            return false;
        }

        return set.add(round);
    }

    /**
     * 向集合添加一个圆,若在最小精度范围内有重复,则不添加
     *
     * @param center 给定圆的圆心
     * @param radius 给定的半径
     * @return 若添加成功则返回true
     */
    public boolean add(Point center, double radius) {
        return add(center, radius, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合添加一个圆,若在给定精度范围内有重复,则不添加
     *
     * @param center    给定圆的圆心
     * @param radius    给定的半径
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Point center, double radius, double precision) {
        if (center == null || !NumberUtil.isMoreThanZero(radius)) {
            return false;
        }

        if (contains(center, radius, precision)) {
            return false;
        }

        return set.add(new Round(center, radius));
    }

    /**
     * 获得圆的个数
     * @return 圆的个数
     */
    public int size() {
        return set.size();
    }
}
