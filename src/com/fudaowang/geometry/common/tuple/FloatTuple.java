package com.fudaowang.geometry.common.tuple;

/**
 * 表示成对的两个float值
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/16/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class FloatTuple {
    private final float number1;
    private final float number2;

    /**
     * 获得第一个数值
     *
     * @return 第一个数值
     */
    public float getNumber1() {
        return number1;
    }

    /**
     * 获得第二个数值
     *
     * @return 第二个数值
     */
    public float getNumber2() {
        return number2;
    }

    /**
     * 利用两个float值来构造一对数值
     *
     * @param number1 第一个float值
     * @param number2 第二个float值
     */
    public FloatTuple(float number1, float number2) {
        if (number1 > number2) {
            this.number1 = number2;
            this.number2 = number1;
        } else {
            this.number1 = number1;
            this.number2 = number2;
        }
    }
}
