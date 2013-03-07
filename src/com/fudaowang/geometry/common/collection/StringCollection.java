package com.fudaowang.geometry.common.collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于表示字符串的集合
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringCollection {
    private final boolean allowRepeat;
    private final List list;

    /**
     * 默认构造方法
     *
     * @param allowRepeat 是否允许字符串重复
     */
    public StringCollection(boolean allowRepeat) {
        this.allowRepeat = allowRepeat;
        list = new ArrayList();
    }

    /**
     * 默认不允许字符串为空的构造方法
     */
    public StringCollection() {
        this.allowRepeat = false;
        list = new ArrayList();
    }

    /**
     * 以初始容量构造集合
     *
     * @param count       指定的初始容量
     * @param allowRepeat 是否允许字符串重复
     */
    public StringCollection(int count, boolean allowRepeat) {
        if (count > 0) {
            list = new ArrayList(count);
            this.allowRepeat = allowRepeat;
        }

        throw new IllegalArgumentException("初始容量必须大于0");
    }

    /**
     * 以初始容量构造不允许重复的字符串集合
     *
     * @param count 指定的初始容量
     */
    public StringCollection(int count) {
        if (count > 0) {
            list = new ArrayList(count);
            this.allowRepeat = false;
        }

        throw new IllegalArgumentException("初始容量必须大于0");
    }

    /**
     * 判断给定的字符串在集合中是否存在
     *
     * @param str 给定的字符串
     * @return 若存在则返回true
     */
    public boolean contains(final String str) {
        if (str == null) {
            throw new NullPointerException("字符串为null");
        }

        return CollectionUtils.exists(list, new Predicate() {
            public boolean evaluate(Object object) {
                String s = (String) object;
                return s.equals(str);
            }
        });
    }

    /**
     * 向集合中添加字符串
     *
     * @param str 需要添加的字符串
     * @return 若添加成功则返回true
     */
    public boolean add(String str) {
        if (str == null) {
            return false;
        }

        if (allowRepeat) {
            return list.add(str);
        }

        return contains(str) ? false : list.add(str);
    }

    /**
     * 返回字符串数组
     *
     * @return 集合对应的字符串数组
     */
    public String[] toArray() {
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 集合的容量
     *
     * @return 集合的容量
     */
    public int size() {
        return list.size();
    }
}
