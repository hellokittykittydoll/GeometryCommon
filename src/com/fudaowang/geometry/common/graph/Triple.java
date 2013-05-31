package com.fudaowang.geometry.common.graph;

/**
 * 表示三个元素一起的元组
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 4/7/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Triple<T1, T2, T3> {
    private final T1 item1;
    private final T2 item2;
    private final T3 item3;

    /**
     * 利用三个非空元素构造元组
     *
     * @param item1 第一个元素
     * @param item2 第二个元素
     * @param item3 第三个元素
     */
    public Triple(T1 item1, T2 item2, T3 item3) {
        if (item1 == null || item2 == null || item3 == null) {
            throw new NullPointerException("item is null");
        }
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    /**
     * 获得第一个元素
     *
     * @return 第一个元素
     */
    public T1 getItem1() {
        return item1;
    }

    /**
     * 获得第二个元素
     *
     * @return 第二个元素
     */
    public T2 getItem2() {
        return item2;
    }

    /**
     * 获得第三个元素
     *
     * @return 第三个元素
     */
    public T3 getItem3() {
        return item3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triple)) return false;

        Triple triple = (Triple) o;

        if (!item1.equals(triple.item1)) return false;
        if (!item2.equals(triple.item2)) return false;
        if (!item3.equals(triple.item3)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item1.hashCode();
        result = 31 * result + item2.hashCode();
        result = 31 * result + item3.hashCode();
        return result;
    }
}
