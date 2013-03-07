package com.fudaowang.geometry.common.tuple;

import org.apache.commons.lang.StringUtils;

/**
 * 表示成对的两个字符串
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/16/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringTuple {
    protected final String string1;
    protected final String string2;

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
     * 利用两个字符串来构造成对的字符串
     *
     * @param string1 第一个字符串
     * @param string2 第二个字符串
     */
    public StringTuple(String string1, String string2) {
        if (string1 == null || string2 == null) {
            throw new NullPointerException("string为null");
        }
        if (StringUtils.isEmpty(string1) || StringUtils.isEmpty(string2)) {
            throw new IllegalArgumentException("string内容为空");
        }

        if (string1.compareTo(string2) < 0) {
            this.string1 = string1;
            this.string2 = string2;
        } else {
            this.string1 = string2;
            this.string2 = string1;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringTuple)) return false;

        StringTuple that = (StringTuple) o;

        if (string1 != null ? !string1.equals(that.string1) : that.string1 != null) return false;
        if (string2 != null ? !string2.equals(that.string2) : that.string2 != null) return false;

        return true;
    }

    public int hashCode() {
        int result = string1 != null ? string1.hashCode() : 0;
        result = 31 * result + (string2 != null ? string2.hashCode() : 0);
        return result;
    }
}
