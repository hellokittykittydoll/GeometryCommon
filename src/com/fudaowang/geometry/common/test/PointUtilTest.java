package com.fudaowang.geometry.common.test;

import com.fudaowang.geometry.common.graph.Line;
import com.fudaowang.geometry.common.graph.Point;
import com.fudaowang.geometry.common.graph.Segment;
import com.fudaowang.geometry.common.util.NumberUtil;
import com.fudaowang.geometry.common.util.PointUtil;

import static junit.framework.TestCase.*;

import com.fudaowang.geometry.common.util.SegmentUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Util的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/14/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointUtilTest {
    private Point p00;
    private Point p11;
    private Point p1010;
    private Point p_1_1;
    private Point p_10_10;
    private Point p1_1;
    private Point p_11;
    private Point p_1010;
    private Point p10_10;

    @Before
    public void setUp() throws Exception {
        p00 = new Point(0, 0);
        p11 = new Point(1, 1);
        p1010 = new Point(10, 10);
        p_1_1 = new Point(-1, -1);
        p_10_10 = new Point(-10, -10);
        p1_1 = new Point(1, -1);
        p_11 = new Point(-1, 1);
        p_1010 = new Point(-10, 10);
        p10_10 = new Point(10, -10);
    }

    @After
    public void tearDown() throws Exception {
        p00 = null;
        p11 = null;
        p1010 = null;
        p_1_1 = null;
        p_10_10 = null;
        p1_1 = null;
        p_11 = null;
        p_1010 = null;
        p10_10 = null;
    }

    /**
     * 测试计算两点之间的距离
     *
     * @throws Exception
     */
    @Test
    public void testDistance() throws Exception {
        double d1 = PointUtil.distance(p11, p_1_1);
        double d2 = PointUtil.distance(1, 1, -1, -1);
        double d3 = PointUtil.distance(1, -1, -1, 1);
        double d4 = Math.sqrt(8);

        assertEquals(d1, d2);
        assertEquals(d2, d3);
        assertEquals(d3, d4);
    }

    /**
     * 测试求两点之间的中点
     *
     * @throws Exception
     */
    @Test
    public void testGetMidPoint() throws Exception {
        Point p1 = SegmentUtil.getMidpoint(p11, p_1_1);
        Point p2 = SegmentUtil.getMidpoint(p1010, p_10_10);
        Point p3 = SegmentUtil.getMidpoint(1, 1, -1, -1);
        Point p4 = SegmentUtil.getMidpoint(10, -10, -10, 10);

        assertTrue(PointUtil.coincide(p1, p2));
        assertTrue(PointUtil.coincide(p1, p3));
        assertTrue(PointUtil.coincide(p1, p4));
        assertTrue(PointUtil.coincide(p1, p00));
    }

    /**
     * 测试求中心对称点
     *
     * @throws Exception
     */
    @Test
    public void testGetCenterSymmetricalPoint() throws Exception {
        Point p1 = PointUtil.getCentralSymmetry(p11, p00);
        assertTrue(PointUtil.coincide(p1, p_1_1));

        Point p2 = PointUtil.getCentralSymmetry(p1010, p00);
        assertTrue(PointUtil.coincide(p2, p_10_10));
    }

    /**
     * 测试求轴对称点
     *
     * @throws Exception
     */
    @Test
    public void testGetAxialSymmetricalPoint() throws Exception {
        Line l1 = new Line(1, -1, 0);
        Line l2 = new Line(1, 0, 0);
        Line l3 = new Line(0, 1, 0);

        Point p1 = PointUtil.getAxialSymmetry(p1_1, l1);
        assertTrue(PointUtil.coincide(p1, p_11));

        Point p2 = PointUtil.getAxialSymmetry(p11, l2);
        assertTrue(PointUtil.coincide(p2, p_11));

        Point p3 = PointUtil.getAxialSymmetry(p11, l3);
        assertTrue(PointUtil.coincide(p3, p1_1));

        Segment s1 = new Segment(p_1_1, p11);
        Segment s2 = new Segment(0, 1, 0, -1);
        Segment s3 = new Segment(-1, 0, 1, 0);

        p1 = PointUtil.getAxialSymmetry(p1_1, s1);
        assertTrue(PointUtil.coincide(p1, p_11));

        p2 = PointUtil.getAxialSymmetry(p11, s2);
        assertTrue(PointUtil.coincide(p2, p_11));

        p3 = PointUtil.getAxialSymmetry(p11, s3);
        assertTrue(PointUtil.coincide(p3, p1_1));
    }

    /**
     * 测试精度范围内的数值相等
     *
     * @throws Exception
     */
    @Test
    public void testCoincide() throws Exception {
        Point p1 = new Point(1.414, 3.14159265358979323846);
        Point p2 = new Point(Math.sqrt(2), Math.PI);
        assertFalse(PointUtil.coincide(p1, p2));
        assertTrue(PointUtil.coincide(p1010, new Point(10, 10)));
    }

    /**
     * 测试旋转
     *
     * @throws Exception
     */
    @Test
    public void testRotate() throws Exception {
        Point p1 = PointUtil.rotate(p11, p00, Math.PI / 2.0);
        assertTrue(PointUtil.coincide(p1, p_11));

        Point p2 = PointUtil.rotate(p11, p00, Math.PI / -2.0);
        assertTrue(PointUtil.coincide(p2, p1_1));

        Point p3 = PointUtil.rotate(p11, p00, Math.PI);
        assertTrue(PointUtil.coincide(p3, p_1_1));
    }

    /**
     * 测试旋转缩放
     *
     * @throws Exception
     */
    @Test
    public void testRotateAndStretch() throws Exception {
        Point p1 = PointUtil.rotateAndStretchTo(p11, p00, Math.PI / 2.0, 10);
        assertTrue(PointUtil.coincide(p1, p_1010));

        Point p2 = PointUtil.rotateAndStretch(p11, p00, Math.PI / -2.0, 10);
        assertTrue(PointUtil.coincide(p2, p10_10));

        Point p3 = PointUtil.rotateAndStretch(p11, p00, Math.PI, 10);
        assertTrue(PointUtil.coincide(p3, p_10_10));
    }

    /**
     * 测试缩放
     *
     * @throws Exception
     */
    @Test
    public void testStretch() throws Exception {
        Point p1 = PointUtil.stretch(p11, p00, 10);
        assertTrue(PointUtil.coincide(p1, p1010));

        Point p2 = PointUtil.stretch(p_10_10, p00, 0.1);
        assertTrue(PointUtil.coincide(p2, p_1_1));

        Point p3 = PointUtil.stretch(p11, p00, -10);
        assertTrue(PointUtil.coincide(p3, p_10_10));
    }

    /**
     * 测试重合判定
     *
     * @throws Exception
     */
    @Test
    public void testValidateCoincide() throws Exception {
        assertTrue(PointUtil.coincide(p11, p11));
        assertFalse(PointUtil.coincide(p11, p1010));
    }

    /**
     * 测试数值范围判定
     *
     * @throws Exception
     */
    @Test
    public void testValueInRange() throws Exception {
        assertTrue(NumberUtil.valueInRange(0, 2, 1));
        assertTrue(NumberUtil.valueInRange(-1, 1, 0));
        assertTrue(NumberUtil.valueInRange(-2, 0, -1));
        assertFalse(NumberUtil.valueInRange(0, 2, 2));
    }

    /**
     * 测试平移
     *
     * @throws Exception
     */
    @Test
    public void testTranslation() throws Exception {
        Point p1 = PointUtil.translation(p11, 9, -11);
        assertTrue(PointUtil.coincide(p1, p10_10));
    }
}
