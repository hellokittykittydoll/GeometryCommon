package com.fudaowang.geometry.common.collection;

import com.fudaowang.geometry.common.graph.Hyperbola;
import com.fudaowang.geometry.common.util.HyperbolaUtil;
import com.fudaowang.geometry.common.util.NumberUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 表示双曲线集合的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/4/13
 * Time: 12:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class HyperbolaCollection {
    private final Set set;

    /**
     * 构造方法
     */
    public HyperbolaCollection() {
        set = new HashSet();
    }

    /**
     * 获得所有的双曲线
     *
     * @return 双曲线数组
     */
    public Hyperbola[] getHyperbolas() {
        return (Hyperbola[]) set.toArray(new Hyperbola[set.size()]);
    }

    /**
     * 在最小精度范围内判定是否存在和给定双曲线重复的双曲线
     *
     * @param hyperbola 给定的双曲线
     * @return 若存在重复的双曲线则返回true
     */
    public boolean contains(Hyperbola hyperbola) {
        return contains(hyperbola, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判定是否存在和给定双曲线重复的双曲线
     *
     * @param hyperbola 给定的双曲线
     * @param precision 给定的精度
     * @return 若存在重复的双曲线则返回true
     */
    public boolean contains(Hyperbola hyperbola, double precision) {
        if (hyperbola == null) {
            throw new NullPointerException("双曲线为null");
        }
        return contains(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), precision);
    }

    /**
     * 在最小精度范围内判定是否存在和给定双曲线重复的双曲线
     *
     * @param k 给定的双曲线的系数k
     * @param x 给定的双曲线原点的横坐标
     * @param y 给定的双曲线原点的纵坐标
     * @return 若存在重复的双曲线则返回true
     */
    public boolean contains(double k, double x, double y) {
        return contains(k, x, y, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判定是否存在和给定双曲线重复的双曲线
     *
     * @param k         给定的双曲线的系数k
     * @param x         给定的双曲线原点的横坐标
     * @param y         给定的双曲线原点的纵坐标
     * @param precision 给定的精度
     * @return 若存在重复的双曲线则返回true
     */
    public boolean contains(final double k, final double x, final double y, final double precision) {
        if (NumberUtil.isZero(k)) {
            throw new IllegalArgumentException("双曲线的系数k不能为0");
        }
        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Hyperbola hyperbola = (Hyperbola) o;
                return HyperbolaUtil.coincide(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), k, x, y, precision);
            }
        });
    }

    /**
     * 向集合中添加一条双曲线,若在最小精度范围内存在重复的双曲线,则不予添加
     *
     * @param hyperbola 给定的双曲线
     * @return 若添加成功则返回true
     */
    public boolean add(Hyperbola hyperbola) {
        return add(hyperbola, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加一条双曲线,若在给定的精度范围内存在重复的双曲线,则不予添加
     *
     * @param hyperbola 给定的双曲线
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Hyperbola hyperbola, double precision) {
        return hyperbola != null && add(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), precision);
    }

    /**
     * 向集合中添加一条双曲线,若在最小精度范围内存在重复的双曲线,则不予添加
     *
     * @param k 给定的双曲线的系数k
     * @param x 给定的双曲线原点的横坐标
     * @param y 给定的双曲线原点的纵坐标
     * @return 若添加成功则返回true
     */
    public boolean add(double k, double x, double y) {
        return add(k, x, y, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加一条双曲线,若在给定的精度范围内存在重复的双曲线,则不予添加
     *
     * @param k         给定的双曲线的系数k
     * @param x         给定的双曲线原点的横坐标
     * @param y         给定的双曲线原点的纵坐标
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(double k, double x, double y, double precision) {
        return !contains(k, x, y, precision) && set.add(new Hyperbola(k, x, y));
    }

    /**
     * 根据给定的系数k,原点横坐标与纵坐标来查找双曲线
     *
     * @param k 给定的系数k
     * @param x 给定的原点横坐标
     * @param y 给定的原点纵坐标
     * @return 若存在各个参数相同的双曲线, 则返回该双曲线
     */
    public Hyperbola findHyperbola(final double k, final double x, final double y) {
        return (Hyperbola) CollectionUtils.find(set, new Predicate() {
            public boolean evaluate(Object o) {
                Hyperbola hyperbola = (Hyperbola) o;
                return HyperbolaUtil.coincide(hyperbola.getK(), hyperbola.getX(), hyperbola.getY(), k, x, y);
            }
        });
    }
}
