package com.fudaowang.geometry.common.tuple;

import com.fudaowang.geometry.common.util.NumberUtil;

/**
 * 表示成对的两个double数值
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/16/13
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleTuple {
    protected final double number1;
    protected final double number2;

    /**
     * 获得第一个数值
     *
     * @return 第一个数值
     */
    public double getNumber1() {
        return number1;
    }

    /**
     * 获得第二个数值
     *
     * @return 第二个数值
     */
    public double getNumber2() {
        return number2;
    }

    /**
     * 利用两个double值来构造一对数值
     *
     * @param number1 第一个double值
     * @param number2 第二个double值
     */
    public DoubleTuple(double number1, double number2) {
        if (Double.isNaN(number1) || Double.isNaN(number2)) {
            throw new IllegalArgumentException("number必须是一个有效数值");
        }

        if (number1 > number2) {
            this.number1 = number2;
            this.number2 = number1;
        } else {
            this.number1 = number1;
            this.number2 = number2;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleTuple)) return false;

        DoubleTuple that = (DoubleTuple) o;

        return (NumberUtil.equal(number1, that.number1) && NumberUtil.equal(number2, that.number2));
    }

    public int hashCode() {
        int result;
        long temp;
        temp = number1 != +0.0d ? Double.doubleToLongBits(number1) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = number2 != +0.0d ? Double.doubleToLongBits(number2) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
