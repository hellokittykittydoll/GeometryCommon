package com.fudaowang.geometry.common.tuple;

import java.util.Arrays;

/**
 * 表示三个一组的int值
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntegerTriple {
    protected final int number1;
    protected final int number2;
    protected final int number3;

    /**
     * 利用三个int值来构造
     *
     * @param number1 第一个int值
     * @param number2 第二个int值
     * @param number3 第三个int值
     */
    public IntegerTriple(int number1, int number2, int number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    /**
     * 利用三个int值来构造
     *
     * @param number1 第一个int值
     * @param number2 第二个int值
     * @param number3 第三个int值
     * @param order   若要求排序,则三个int值按照从小到大的顺序排列
     */
    public IntegerTriple(int number1, int number2, int number3, boolean order) {
        if (order) {
            int[] array = new int[]{number1, number2, number3};
            Arrays.sort(array);
            this.number1 = array[0];
            this.number2 = array[1];
            this.number3 = array[2];
        } else {
            this.number1 = number1;
            this.number2 = number2;
            this.number3 = number3;
        }
    }

    /**
     * 获得第一个int值
     *
     * @return 第一个int值
     */
    public int getNumber1() {
        return number1;
    }

    /**
     * 获得第二个int值
     *
     * @return 第二个int值
     */
    public int getNumber2() {
        return number2;
    }

    /**
     * 获得第三个int值
     *
     * @return 第三个int值
     */
    public int getNumber3() {
        return number3;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerTriple)) return false;

        IntegerTriple that = (IntegerTriple) o;

        if (number1 != that.number1) return false;
        if (number2 != that.number2) return false;
        if (number3 != that.number3) return false;

        return true;
    }

    public int hashCode() {
        int result = number1;
        result = 31 * result + number2;
        result = 31 * result + number3;
        return result;
    }

    /**
     * 忽略次序比较两个IntegerTriple是否相等
     *
     * @param integerTriple 需要比较的IntegerTriple
     * @return 若相等则返回true
     */
    public boolean equalsIgnoreOrder(IntegerTriple integerTriple) {
        if (integerTriple == null) {
            throw new NullPointerException("需要判断的IntegerTriple为null");
        }

        return contains(integerTriple.number1) && contains(integerTriple.number2) && contains(integerTriple.number3);
    }

    /**
     * 判断是否包含给定的int值
     *
     * @param number 给定的int值
     * @return 若在最小精度范围内存在相等的int值, 则返回true
     */
    public boolean contains(int number) {
        return number == number1 || number == number2 || number == number3;
    }
}
