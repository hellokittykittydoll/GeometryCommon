package com.fudaowang.common.test;

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
    private Point p00;
    private Point p11;
    private Point p1010;
    private Point p55;
    private Point p110;
    private Point p_1_1;
    private Point p_10_10;
    private Point p_5_5;

    public void setUp() throws Exception {
        p00 = new Point(0, 0);
        p11 = new Point(1, 1);
        p1010 = new Point(10, 10);
        p55 = new Point(5, 5);
        p110 = new Point(1, 10);
        p_1_1 = new Point(-1, -1);
        p_10_10 = new Point(-10, -10);
        p_5_5 = new Point(-5, -5);
    }

    public void tearDown() throws Exception {
        p00 = null;
        p11 = null;
        p1010 = null;
        p55 = null;
        p110 = null;
        p_1_1 = null;
        p_10_10 = null;
        p_5_5 = null;
    }

    public void testDistance() throws Exception {
        double d1 = Util.distance(p11, p_1_1);
        double d2 = Util.distance(1, 1, -1, -1);
        double d3 = Util.distance(1, -1, -1, 1);
        double d4 = Math.sqrt(8);

        assertEquals(d1, d2);
        assertEquals(d2, d3);
        assertEquals(d3, d4);
    }

    public void testGetMidPoint() throws Exception {
        Point p1 = Util.getMidPoint(p11, p_1_1);
        Point p2 = Util.getMidPoint(p1010, p_10_10);
        Point p3 = Util.getMidPoint(1, 1, -1, -1);
        Point p4 = Util.getMidPoint(10, -10, -10, 10);

        assertEquals();
    }

    public void testGetCenterSymmetricalPoint() throws Exception {

    }

    public void testGetAxialSymmetricalPoint() throws Exception {

    }

    public void testEqual() throws Exception {

    }

    public void testRotate() throws Exception {

    }

    public void testRotateAndStretch() throws Exception {

    }

    public void testStretch() throws Exception {

    }

    public void testValidateCoincide() throws Exception {

    }

    public void testValueInRange() throws Exception {

    }

    public void testTranslation() throws Exception {

    }
}
