package com.fudaowang.common.test;

import static junit.framework.TestCase.*;

import com.fudaowang.common.util.NumberUtil;
import org.junit.Test;

/**
 * 测试数值计算
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/29/12
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class NumberUtilTest {
    /**
     * 测试两个数值在最小精度范围内是否相等
     *
     * @throws Exception
     */
    @Test
    public void testEqual() throws Exception {
        assertEquals(3.14159265358979323846, Math.PI, NumberUtil.MIN_VALUE);
        assertTrue(NumberUtil.equal(3.14159265358979323846, Math.PI));
        assertFalse(NumberUtil.equal(3.1415926, Math.PI));
    }

    /**
     * 测试数值在最小精度范围内是否为0
     *
     * @throws Exception
     */
    @Test
    public void testIsZero() throws Exception {
        assertTrue(NumberUtil.isZero(3.14159265358979323846 - Math.PI));
        assertFalse(NumberUtil.isZero(3.1415926 - Math.PI));
    }

    /**
     * 测试数值在最小精度范围内小于0
     *
     * @throws Exception
     */
    @Test
    public void testIsLessThanZero() throws Exception {
        assertTrue(NumberUtil.isLessThanZero(3.1415926 - Math.PI));
        assertFalse(NumberUtil.isLessThanZero(3.14159265358979323846 - Math.PI));
    }

    /**
     * 测试在最小精度范围内,一个数小于另一个数
     *
     * @throws Exception
     */
    @Test
    public void testIsLessThan() throws Exception {
        assertTrue(NumberUtil.isLessThan(3.1415926, Math.PI));
        assertFalse(NumberUtil.isLessThan(3.14159265358979323846, Math.PI));
    }

    /**
     * 测试在最小精度范围内大于0
     *
     * @throws Exception
     */
    @Test
    public void testIsMoreThanZero() throws Exception {
        assertTrue(NumberUtil.isMoreThan(Math.PI, 3.1415926));
        assertFalse(NumberUtil.isMoreThan(Math.PI, 3.14159265358979323846));
    }

    /**
     * 测试数值在最小精度范围内,一个数大于另一个数
     *
     * @throws Exception
     */
    @Test
    public void testIsMoreThan() throws Exception {
        assertTrue(NumberUtil.isMoreThanZero(Math.PI - 3.1415926));
        assertFalse(NumberUtil.isMoreThanZero(Math.PI - 3.14159265358979323846));
    }

    /**
     * 测试数值在给定的数值范围内
     *
     * @throws Exception
     */
    @Test
    public void testValueInRange() throws Exception {
        assertFalse(NumberUtil.valueInRange(3.1415926535, Math.PI, 3.14159265358979323846));
        assertTrue(NumberUtil.valueInRange(3.1415926, Math.PI, 3.1415926535));
    }
}
