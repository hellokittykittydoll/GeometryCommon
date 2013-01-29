package com.fudaowang.geometry.common.collection;

import com.fudaowang.geometry.common.graph.Line;
import com.fudaowang.geometry.common.util.LineUtil;
import com.fudaowang.geometry.common.util.NumberUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 表示直线集合的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/2/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineCollection {
    public final Set set;

    /**
     * 构造方法
     */
    public LineCollection() {
        set = new HashSet();
    }

    /**
     * 在最小精度范围内判断是否存在重合的直线
     *
     * @param line 给定的直线
     * @return 若存在重复的直线则返回true
     */
    public boolean contains(Line line) {
        return contains(line, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定精度范围内判断是否存在重合的直线
     *
     * @param line      给定的直线
     * @param precision 给定的精度
     * @return 若存在重复的直线则返回true
     */
    public boolean contains(Line line, double precision) {
        if (line == null) {
            throw new NullPointerException("直线为null");
        }
        return contains(line.getA(), line.getB(), line.getC(), precision);
    }

    /**
     * 在最小精度范围内判断是否存在重合的直线
     *
     * @param a 直线的系数a
     * @param b 直线的系数a
     * @param c 直线的系数a
     * @return 若存在重合的直线则返回true
     */
    public boolean contains(double a, double b, double c) {
        return contains(a, b, c, NumberUtil.MIN_VALUE);
    }

    /**
     * 在给定精度范围内判断是否存在重合的直线
     *
     * @param a         直线的系数a
     * @param b         直线的系数a
     * @param c         直线的系数a
     * @param precision 给定的精度
     * @return 若存在重合的直线则返回true
     */
    public boolean contains(final double a, final double b, final double c, final double precision) {
        if (!LineUtil.isLogical(a, b)) {
            throw new IllegalArgumentException("直线的系数a与系数b不能同时为0");
        }
        return CollectionUtils.exists(set, new Predicate() {
            public boolean evaluate(Object o) {
                Line line = (Line) o;
                return LineUtil.coincide(line.getA(), line.getB(), line.getC(), a, b, c, precision);
            }
        });
    }

    /**
     * 向集合中添加直线,若在最小精度范围内已经存在重合的直线,则不予添加
     *
     * @param line 给定的直线
     * @return 若添加成功则返回true
     */
    public boolean add(Line line) {
        return add(line, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加直线,若在给定精度范围内已经存在重合的直线,则不予添加
     *
     * @param line      给定的直线
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(Line line, double precision) {
        return line != null && add(line.getA(), line.getB(), line.getC(), precision);
    }

    /**
     * 向集合中添加直线,若在最小精度范围内已经存在重合的直线,则不予添加
     *
     * @param a 直线的系数a
     * @param b 直线的系数a
     * @param c 直线的系数a
     * @return 若添加成功则返回true
     */
    public boolean add(double a, double b, double c) {
        return add(a, b, c, NumberUtil.MIN_VALUE);
    }

    /**
     * 向集合中添加直线,若在给定精度范围内已经存在重合的直线,则不予添加
     *
     * @param a         直线的系数a
     * @param b         直线的系数a
     * @param c         直线的系数a
     * @param precision 给定的精度
     * @return 若添加成功则返回true
     */
    public boolean add(double a, double b, double c, double precision) {
        return !contains(a, b, c, precision) && set.add(new Line(a, b, c));
    }

    /**
     * 获得直线的个数
     *
     * @return 集合的size
     */
    public int size() {
        return set.size();
    }

    /**
     * 获得所有的直线
     *
     * @return 直线的数组
     */
    public Line[] getLines() {
        return (Line[]) set.toArray(new Line[set.size()]);
    }

    /**
     * 在集合中根据给定的系数查找直线
     *
     * @param a 给定的系数a
     * @param b 给定的系数b
     * @param c 给定的系数c
     * @return 若存在与三个系数相同的直线, 则返回该直线
     */
    public Line findLine(final double a, final double b, final double c) {
        return (Line) CollectionUtils.find(set, new Predicate() {
            public boolean evaluate(Object o) {
                Line line = (Line) o;
                return LineUtil.coincide(line.getA(), line.getB(), line.getC(), a, b, c);
            }
        });
    }
}
