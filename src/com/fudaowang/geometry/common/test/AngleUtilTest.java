package com.fudaowang.geometry.common.test;

import com.fudaowang.geometry.common.graph.Angle;
import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.util.AngleUtil;
import com.fudaowang.geometry.common.util.NumberUtil;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * AngelUtil的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/22/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class AngleUtilTest {
    /**
     * 测试计算角度
     *
     * @throws Exception
     */
    @Test
    public void testGetAngleRadians() throws Exception {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, -1);
        Point p2 = new Point(0, 1);
        double d = AngleUtil.getAngleRadians(p0, p1, p2);
        assertEquals(d, Math.PI);
        p2 = new Point(1, 0);
        d = AngleUtil.getAngleRadians(p0, p1, p2);
        assertTrue(NumberUtil.equal(d, Math.PI / 2));
        p2 = new Point(1, -1);
        d = AngleUtil.getAngleRadians(p0, p1, p2);
        assertTrue(NumberUtil.equal(d, Math.PI / 4));
        p2 = new Point(0, -1);
        d = AngleUtil.getAngleRadians(p0, p1, p2);
        assertEquals(d, 0.0);
    }

    /**
     * 测试计算角度
     *
     * @throws Exception
     */
    @Test
    public void testGetAngleDegrees() throws Exception {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, -1);
        Point p2 = new Point(0, 1);
        double d = AngleUtil.getAngleDegrees(p0, p1, p2);
        assertEquals(d, 180.0);
        p2 = new Point(1, 0);
        d = AngleUtil.getAngleDegrees(p0, p1, p2);
        assertTrue(NumberUtil.equal(d, 90.0));
        p2 = new Point(1, -1);
        d = AngleUtil.getAngleDegrees(p0, p1, p2);
        assertTrue(NumberUtil.equal(d, 45.0));
        p2 = new Point(0, -1);
        d = AngleUtil.getAngleDegrees(p0, p1, p2);
        assertEquals(d, 0.0);
    }

    /**
     * 测试判断点是否在角内
     *
     * @throws Exception
     */
    @Test
    public void testPointInAngle() throws Exception {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, -1);
        Point p2 = new Point(1, 0);
        Angle angle = new Angle(p0, p1, p2);

        Point p = new Point(1, -1);
        assertTrue(AngleUtil.pointInAngle(p, angle));
        p = new Point(0, 1);
        assertFalse(AngleUtil.pointInAngle(p, angle));
    }
}
