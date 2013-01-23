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
        return contains(round.getX(), round.getY(), round.getRadius(), precision);
    }

    /**
     * 在给定的精度范围内判断集合内是否有重合的圆
     *
     * @param x         给定圆的圆心的横坐标
     * @param y         给定圆的圆心的纵坐标
     * @param radius    给定圆的半径
     * @param precision 给定的精度
     * @return 若集合内存在与给定圆重合的圆, 则返回true
     */
    public boolean contains(final double x, final double y, final double radius, final double precision) {
        if (!NumberUtil.isMoreThanZero(radius)) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Round r = (Round) o;
                return RoundUtil.coincide(x, y, radius, r.getX(), r.getY(), r.getRadius(), precision);
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
     * @param x      给定圆的圆心的横坐标
     * @param y      给定圆的圆心的纵坐标
     * @param radius 给定的半径
     * @return 若添加成功则返回true
     */
    public boolean add(double x, double y, double radius) {
        return add(x, y, radius, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合添加一个圆,若在给定精度范围内有重复,则不添加
     *
     * @param x         给定圆的圆心的横坐标
     * @param y         给定圆的圆心的纵坐标
     * @param radius    给定的半径
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(double x, double y, double radius, double precision) {
        return !contains(x, y, radius, precision) && set.add(new Round(x, y, radius));
    }

    /**
     * 获得圆的个数
     *
     * @return 圆的个数
     */
    public int size() {
        return set.size();
    }

    /**
     * 根据给定的圆心横纵坐标以及半径长,在最小精度范围内查找圆
     *
     * @param x      给定的圆心横坐标
     * @param y      给定的圆心纵坐标
     * @param radius 给定的半径长
     * @return 若存在和给定参数相同的圆, 则返回该圆
     */
    public Round findRound(double x, double y, double radius) {
        return findRound(x, y, radius, NumberUtil.MIN_VALUE);
    }

    /**
     * 根据给定的圆心横纵坐标以及半径长,在给定的精度范围内查找圆
     *
     * @param x         给定的圆心横坐标
     * @param y         给定的圆心纵坐标
     * @param radius    给定的半径长
     * @param precision 给定的精度
     * @return 若存在和给定参数相同的圆, 则返回该圆
     */
    public Round findRound(final double x, final double y, final double radius, final double precision) {
        return (Round) CollectionUtils.find(set, new Predicate() {
            public boolean evaluate(Object o) {
                Round round = (Round) o;
                return RoundUtil.coincide(x, y, radius, round.getX(), round.getY(), round.getRadius(), precision);
            }
        });
    }
}
