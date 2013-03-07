package com.fudaowang.geometry.common.tuple;

import com.fudaowang.geometry.common.util.NumberUtil;

import java.util.Arrays;

/**
 * 表示三个一组的double值
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleTriple {
    protected final double number1;
    protected final double number2;
    protected final double number3;

    /**
     * 利用三个double值来构造DoubleTriple
     *
     * @param number1 第一个double值
     * @param number2 第二个double值
     * @param number3 第三个double值
     */
    public DoubleTriple(double number1, double number2, double number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    /**
     * 利用三个double值来构造DoubleTriple
     *
     * @param number1 第一个double值
     * @param number2 第二个double值
     * @param number3 第三个double值
     * @param order   若要求排序,则double值按照次序从小到大分配
     */
    public DoubleTriple(double number1, double number2, double number3, boolean order) {
        if (Double.isNaN(number1) || Double.isNaN(number2) || Double.isNaN(number3)) {
            throw new IllegalArgumentException("number必须是一个有效数值");
        }

        if (order) {
            double[] array = new double[]{number1, number2, number3};
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
     * 获得第一个double值
     *
     * @return 第一个double值
     */
    public double getNumber1() {
        return number1;
    }

    /**
     * 获得第二个double值
     *
     * @return 第二个double值
     */
    public double getNumber2() {
        return number2;
    }

    /**
     * 获得第三个double值
     *
     * @return 第三个double值
     */
    public double getNumber3() {
        return number3;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleTriple)) return false;

        DoubleTriple that = (DoubleTriple) o;

        return NumberUtil.equal(number1, that.number1) && NumberUtil.equal(number2, that.number2) && NumberUtil.equal(number3, that.number3);
    }

    public int hashCode() {
        int result;
        long temp;
        temp = number1 != +0.0d ? Double.doubleToLongBits(number1) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = number2 != +0.0d ? Double.doubleToLongBits(number2) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = number3 != +0.0d ? Double.doubleToLongBits(number3) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * 忽略次序比较两个DoubleTriple是否相等
     *
     * @param doubleTriple 需要比较的DoubleTriple
     * @return 若相等则返回true
     */
    public boolean equalsIgnoreOrder(DoubleTriple doubleTriple) {
        if (doubleTriple == null) {
            throw new NullPointerException("需要比较的DoubleTriple为null");
        }

        return contains(doubleTriple.number1) && contains(doubleTriple.number2) && contains(doubleTriple.number3);
    }

    /**
     * 判断是否包含给定的double值
     *
     * @param number 给定的double值
     * @return 若在最小精度范围内存在相等的double值, 则返回true
     */
    public boolean contains(double number) {
        return NumberUtil.equal(number1, number) || NumberUtil.equal(number2, number) || NumberUtil.equal(number3, number);
    }
}
