package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.util.Util;
import junit.framework.TestCase;

/**
 * Util的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/14/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UtilTest extends TestCase {
    private double precision;
    private Point p00;
    private Point p11;
    private Point p1010;
    private Point p_1_1;
    private Point p_10_10;
    private Point p1_1;
    private Point p_11;
    private Point p_1010;
    private Point p10_10;


    public void setUp() throws Exception {
        precision = 0.0001;
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
    public void testDistance() throws Exception {
        double d1 = Util.distance(p11, p_1_1);
        double d2 = Util.distance(1, 1, -1, -1);
        double d3 = Util.distance(1, -1, -1, 1);
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
    public void testGetMidPoint() throws Exception {
        Point p1 = Util.getMidPoint(p11, p_1_1);
        Point p2 = Util.getMidPoint(p1010, p_10_10);
        Point p3 = Util.getMidPoint(1, 1, -1, -1);
        Point p4 = Util.getMidPoint(10, -10, -10, 10);

        assertTrue(Util.equal(p1, p2, precision));
        assertTrue(Util.equal(p1, p3, precision));
        assertTrue(Util.equal(p1, p4, precision));
        assertTrue(Util.equal(p1, p00, precision));
    }

    /**
     * 测试求中心对称点
     *
     * @throws Exception
     */
    public void testGetCenterSymmetricalPoint() throws Exception {
        Point p1 = Util.getCenterSymmetricalPoint(p11, p00);
        assertTrue(Util.equal(p1, p_1_1, precision));

        Point p2 = Util.getCenterSymmetricalPoint(p1010, p00);
        assertTrue(Util.equal(p2, p_10_10, precision));
    }

    /**
     * 测试求轴对称点
     *
     * @throws Exception
     */
    public void testGetAxialSymmetricalPoint() throws Exception {
        Line l1 = new Line(1, -1, 0);
        Line l2 = new Line(1, 0, 0);
        Line l3 = new Line(0, 1, 0);

        Point p1 = Util.getAxialSymmetricalPoint(p1_1, l1);
        assertTrue(Util.equal(p1, p_11, precision));

        Point p2 = Util.getAxialSymmetricalPoint(p11, l2);
        assertTrue(Util.equal(p2, p_11, precision));

        Point p3 = Util.getAxialSymmetricalPoint(p11, l3);
        assertTrue(Util.equal(p3, p1_1, precision));
    }

    /**
     * 测试精度范围内的数值相等
     *
     * @throws Exception
     */
    public void testEqual() throws Exception {
        assertTrue(Util.equal(1.0001, 1.0002, precision));
        assertFalse(Util.equal(1.0001, 1.0003, precision));
    }

    /**
     * 测试旋转
     *
     * @throws Exception
     */
    public void testRotate() throws Exception {
        Point p1 = Util.rotate(p11, p00, Math.PI / 2.0);
        assertTrue(Util.equal(p1, p_11, precision));

        Point p2 = Util.rotate(p11, p00, Math.PI / -2.0);
        assertTrue(Util.equal(p2, p1_1, precision));

        Point p3 = Util.rotate(p11, p00, Math.PI);
        assertTrue(Util.equal(p3, p_1_1, precision));
    }

    /**
     * 测试旋转缩放
     *
     * @throws Exception
     */
    public void testRotateAndStretch() throws Exception {
        Point p1 = Util.rotateAndStretch(p11, p00, Math.PI / 2.0, 10);
        assertTrue(Util.equal(p1, p_1010, precision));

        Point p2 = Util.rotateAndStretch(p11, p00, Math.PI / -2.0, 10);
        assertTrue(Util.equal(p2, p10_10, precision));

        Point p3 = Util.rotateAndStretch(p11, p00, Math.PI, 10);
        assertTrue(Util.equal(p3, p_10_10, precision));
    }

    /**
     * 测试缩放
     *
     * @throws Exception
     */
    public void testStretch() throws Exception {
        Point p1 = Util.stretch(p11, p00, 10);
        assertTrue(Util.equal(p1, p1010, precision));

        Point p2 = Util.stretch(p_10_10, p00, 0.1);
        assertTrue(Util.equal(p2, p_1_1, precision));

        Point p3 = Util.stretch(p11, p00, -10);
        assertTrue(Util.equal(p3, p_10_10, precision));
    }

    /**
     * 测试重合判定
     *
     * @throws Exception
     */
    public void testValidateCoincide() throws Exception {
        assertTrue(Util.coincide(p11, p11));
        assertFalse(Util.coincide(p11, p1010));
    }

    /**
     * 测试数值范围判定
     *
     * @throws Exception
     */
    public void testValueInRange() throws Exception {
        assertTrue(Util.valueInRange(0, 2, 1));
        assertTrue(Util.valueInRange(-1, 1, 0));
        assertTrue(Util.valueInRange(-2, 0, -1));
        assertFalse(Util.valueInRange(0,2,2));
    }

    /**
     * 测试平移
     *
     * @throws Exception
     */
    public void testTranslation() throws Exception {

    }
}
