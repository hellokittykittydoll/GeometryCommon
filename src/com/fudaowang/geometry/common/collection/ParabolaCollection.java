package com.fudaowang.geometry.common.collection;

import com.fudaowang.geometry.common.graph.Parabola;
import com.fudaowang.geometry.common.util.NumberUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 抛物线的集合类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/2/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParabolaCollection {
    private final Set set;

    /**
     * 构造方法
     */
    public ParabolaCollection() {
        set = new HashSet();
    }

    /**
     * 在最小精度范围内判断是否存在重复的抛物线
     *
     * @param parabola 给定的抛物线
     * @return 若存在与给定抛物线重复的抛物线, 则返回true
     */
    public boolean contains(Parabola parabola) {
        return contains(parabola, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断是否存在重复的抛物线
     *
     * @param parabola  给定的抛物线
     * @param precision 给定的精度
     * @return 若存在与给定抛物线重复的抛物线, 则返回true
     */
    public boolean contains(Parabola parabola, double precision) {
        if (parabola == null) {
            throw new NullPointerException("抛物线为null");
        }
        return contains(parabola.getA(), parabola.getB(), parabola.getC(), precision);
    }

    /**
     * 在最小精度范围内判断是否存在重复的抛物线
     *
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     * @return 若存在与给定抛物线重复的抛物线, 则返回true
     */
    public boolean contains(double a, double b, double c) {
        return contains(a, b, c, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定的精度范围内判断是否存在重复的抛物线
     *
     * @param a         抛物线的系数a
     * @param b         抛物线的系数b
     * @param c         抛物线的系数c
     * @param precision 给定的精度
     * @return 若存在与给定抛物线重复的抛物线, 则返回true
     */
    public boolean contains(final double a, final double b, final double c, final double precision) {
        if (NumberUtil.isZero(a)) {
            throw new IllegalArgumentException("抛物线的系数a不能为0");
        }

        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Parabola parabola = (Parabola) o;
                return NumberUtil.equal(parabola.getA(), a, precision)
                        && NumberUtil.equal(parabola.getB(), b, precision)
                        && NumberUtil.equal(parabola.getC(), c, precision);
            }
        });
    }

    /**
     * 向集合中添加一条抛物线,若在最小精度范围内有重复,则不予添加
     *
     * @param parabola 给定的抛物线
     * @return 若添加成功则返回true
     */
    public boolean add(Parabola parabola) {
        return add(parabola, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加一条抛物线,若在给定精度范围内有重复,则不予添加
     *
     * @param parabola  给定的抛物线
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Parabola parabola, double precision) {
        return parabola != null && add(parabola.getA(), parabola.getB(), parabola.getC(), precision);

    }

    /**
     * 向集合中添加一条抛物线,若在最小精度范围内有重复,则不予添加
     *
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     * @return 若添加成功则返回true
     */
    public boolean add(double a, double b, double c) {
        return add(a, b, c, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加一条抛物线,若在给定精度范围内有重复,则不予添加
     *
     * @param a         抛物线的系数a
     * @param b         抛物线的系数b
     * @param c         抛物线的系数c
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(double a, double b, double c, double precision) {
        return !contains(a, b, c, precision) && set.add(new Parabola(a, b, c));
    }

    /**
     * 获得抛物线的个数
     *
     * @return 集合的size
     */
    public int size() {
        return set.size();
    }
}
