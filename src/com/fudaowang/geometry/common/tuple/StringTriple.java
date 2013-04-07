package com.fudaowang.geometry.common.tuple;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 表示三个一组的字符串组
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringTriple {
    protected final String string1;
    protected final String string2;
    protected final String string3;

    /**
     * 利用三个字符串来构造StringTriple
     *
     * @param string1 第一个字符串
     * @param string2 第二个字符串
     * @param string3 第三个字符串
     */
    public StringTriple(String string1, String string2, String string3) {
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
    }

    /**
     * 利用三个字符串来构造StringTriple
     *
     * @param string1 第一个字符串
     * @param string2 第二个字符串
     * @param string3 第三个字符串
     * @param order   若要求排序,则三个字符串按照先后顺序排列
     */
    public StringTriple(String string1, String string2, String string3, boolean order) {
        if (string1 == null || string2 == null || string3 == null) {
            throw new NullPointerException("字符串为null");
        }
        if (StringUtils.isEmpty(string1) || StringUtils.isEmpty(string2) || StringUtils.isEmpty(string3)) {
            throw new IllegalArgumentException("string内容为空");
        }

        if (order) {
            String[] array = new String[]{string1, string2, string3};
            Arrays.sort(array);
            this.string1 = array[0];
            this.string2 = array[1];
            this.string3 = array[2];
        } else {
            this.string1 = string1;
            this.string2 = string2;
            this.string3 = string3;
        }
    }

    /**
     * 获得第一个字符串
     *
     * @return 第一个字符串
     */
    public String getString1() {
        return string1;
    }

    /**
     * 获得第二个字符串
     *
     * @return 第二个字符串
     */
    public String getString2() {
        return string2;
    }

    /**
     * 获得第三个字符串
     *
     * @return 第三个字符串
     */
    public String getString3() {
        return string3;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringTriple)) return false;

        StringTriple that = (StringTriple) o;

        if (string1 != null ? !string1.equals(that.string1) : that.string1 != null) return false;
        if (string2 != null ? !string2.equals(that.string2) : that.string2 != null) return false;
        if (string3 != null ? !string3.equals(that.string3) : that.string3 != null) return false;

        return true;
    }

    public int hashCode() {
        int result = string1 != null ? string1.hashCode() : 0;
        result = 31 * result + (string2 != null ? string2.hashCode() : 0);
        result = 31 * result + (string3 != null ? string3.hashCode() : 0);
        return result;
    }

    /**
     * 忽略次序比较两个StringTriple是否相等
     *
     * @param stringTriple 需要比较的StringTriple
     * @return 若相等则返回true
     */
    public boolean equalsIgnoreOrder(StringTriple stringTriple) {
        if (stringTriple == null) {
            throw new NullPointerException("需要比较的StringTriple为null");
        }
        return contains(stringTriple.string1) && contains(stringTriple.string2) && contains(stringTriple.string3);
    }

    /**
     * 判断是否包含给定的字符串
     *
     * @param string 需要判断的字符串
     * @return 若存在与之相等的字符串, 则返回true
     */
    public boolean contains(String string) {
        if (string == null) {
            throw new NullPointerException("需要判断的字符串为null");
        }
        return string.equals(string1) || string.equals(string2) || string.equals(string3);
    }
}
