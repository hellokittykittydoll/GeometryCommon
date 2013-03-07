package com.fudaowang.geometry.common.tuple;

/**
 * 表示成对的两个int值
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/16/13
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntegerTuple {
    protected final int number1;
    protected final int number2;

    /**
     * 获得第一个数值
     *
     * @return 第一个数值
     */
    public int getNumber1() {
        return number1;
    }

    /**
     * 获得第二个数值
     *
     * @return 第二个数值
     */
    public int getNumber2() {
        return number2;
    }

    /**
     * 利用两个int值构造一对数值
     *
     * @param number1 第一个int值
     * @param number2 第二个int值
     */
    public IntegerTuple(int number1, int number2) {
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
        if (!(o instanceof IntegerTuple)) return false;

        IntegerTuple that = (IntegerTuple) o;

        if (number1 != that.number1) return false;
        if (number2 != that.number2) return false;

        return true;
    }

    public int hashCode() {
        int result = number1;
        result = 31 * result + number2;
        return result;
    }
}
