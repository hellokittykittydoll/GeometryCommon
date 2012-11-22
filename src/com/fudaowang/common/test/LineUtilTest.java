package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Segment;
import com.fudaowang.common.util.LineUtil;
import com.fudaowang.common.util.Util;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * LineUtil的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/15/12
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineUtilTest extends TestCase {
    private double precision = 0.0001;

    private Line l1 = new Line(1, -1, 1);
    private Line l_1 = new Line(1, -1, -1);
    private Line _l1 = new Line(-1, -1, 1);
    private Line _l_ = new Line(-1, -1, -1);

    private Point p00 = new Point(0, 0);
    private Point p01 = new Point(0, 1);
    private Point p0_1 = new Point(0, -1);
    private Point p10 = new Point(1, 0);
    private Point p11 = new Point(1, 1);
    private Point p_10 = new Point(-1, 0);
    private Segment segment = new Segment(p10, new Point(-10, 0));

    /**
     * 测试求直线,线段等的交点
     *
     * @throws Exception
     */
    @Test
    public void testIntersect() throws Exception {
        Point p = LineUtil.intersect(l1, _l1);
        assertTrue(Util.coincide(p, p01));
        p = LineUtil.intersect(l1, l_1);
        assertNull(p);
        p = LineUtil.intersect(segment, l1, true);
        assertTrue(Util.coincide(p, p_10));
    }

    /**
     * 测试点是否在线段范围内
     *
     * @throws Exception
     */
    @Test
    public void testInSegment() throws Exception {
        Segment s = new Segment(-1, -1, 1, 1);
        assertTrue(LineUtil.inSegment(p00, s));
        assertTrue(LineUtil.inSegment(p10, s));
        assertFalse(LineUtil.inSegment(p11, s));
        s = new Segment(-1, 0, 1, 0);
        assertTrue(LineUtil.inSegment(p00, s));
        assertTrue(LineUtil.inSegment(p01, s));
        assertFalse(LineUtil.inSegment(p10, s));
        assertFalse(LineUtil.inSegment(new Point(10, 0), s));
    }

    /**
     * 测试点到直线,线段的距离
     *
     * @throws Exception
     */
    @Test
    public void testDistance() throws Exception {
        double d = LineUtil.distance(p00, l1);
        assertTrue(Util.equal(d, Math.sqrt(2) / 2.0, precision));
        d = LineUtil.distance(p11, segment);
        assertEquals(d, 1.0);
    }

    /**
     * 测试判断点是否在线上
     *
     * @throws Exception
     */
    @Test
    public void testOnLine() throws Exception {
        assertTrue(LineUtil.onLine(p01, l1, precision));
        assertTrue(LineUtil.onLine(p_10, l1, precision));
        assertFalse(LineUtil.onLine(p00, l1, precision));
    }

    /**
     * 测试做垂线
     *
     * @throws Exception
     */
    @Test
    public void testVerticalLine() throws Exception {
        Line line = LineUtil.verticalLine(p10, l1);
        assertEquals(line.getA(), _l1.getA());
        assertEquals(line.getB(), _l1.getB());
        assertEquals(line.getC(), _l1.getC());
        line = LineUtil.verticalLine(p01, l1);
        assertEquals(line.getA(), _l1.getA());
        assertEquals(line.getB(), _l1.getB());
        assertEquals(line.getC(), _l1.getC());
        line = LineUtil.verticalLine(p00, segment);
        assertTrue(LineUtil.onLine(p01, line, precision));
        assertTrue(LineUtil.onLine(p0_1, line, precision));
    }

    /**
     * 测试求垂足
     *
     * @throws Exception
     */
    @Test
    public void testVerticalPoint() throws Exception {
        Point p = LineUtil.verticalPoint(p10, l1);
        assertTrue(Util.coincide(p, p01));
        p = LineUtil.verticalPoint(p10, l1);
        assertTrue(Util.coincide(p, p01));
        p = LineUtil.verticalPoint(p00, segment);
        assertTrue(Util.coincide(p, p00));
        p = LineUtil.verticalPoint(p0_1, segment);
        assertTrue(Util.coincide(p, p00));
    }

    /**
     * 测试求平行线
     *
     * @throws Exception
     */
    @Test
    public void testParallelLine() throws Exception {
        Line line = LineUtil.parallelLine(p10, l1);
        assertEquals(line.getA(), l_1.getA());
        assertEquals(line.getB(), l_1.getB());
        assertEquals(line.getC(), l_1.getC());
        line = LineUtil.parallelLine(p01, segment);
        double d = LineUtil.distance(p00, line);
        assertEquals(d, 1.0);
    }

    /**
     * 测试由两点求直线
     *
     * @throws Exception
     */
    @Test
    public void testGetLine() throws Exception {
        Line line = LineUtil.getLine(p01, p_10);
        double d = LineUtil.distance(p10, line);
        assertTrue(Util.equal(d, Math.sqrt(2), precision));
        d = LineUtil.distance(p0_1, line);
        assertTrue(Util.equal(d, Math.sqrt(2), precision));
    }

    /**
     * 测试直线,线段的平移
     *
     * @throws Exception
     */
    @Test
    public void testTranslation() throws Exception {
        Line line = LineUtil.translation(l1, 1, -1);
        assertTrue(LineUtil.onLine(p10, line, precision));
        assertTrue(LineUtil.onLine(p0_1, line, precision));
    }

    /**
     * 测试求直线,线段的倾角
     *
     * @throws Exception
     */
    @Test
    public void testGetAngle() throws Exception {
        double a = LineUtil.getAngle(p_10, p01);
        assertEquals(a, Math.PI / 4);
        a = LineUtil.getAngle(p01, p_10);
        assertEquals(a, -Math.PI * 3 / 4);
        a = LineUtil.getAngle(p01, p0_1);
        assertEquals(a, -Math.PI / 2);
        a = LineUtil.getAngle(p00, p_10);
        assertEquals(a, Math.PI);
    }

    /**
     * 测试判断两线段是否相交
     *
     * @throws Exception
     */
    @Test
    public void testLinesIntersect() throws Exception {
        Segment s1 = new Segment(0, 0, 0, 1);
        Segment s2 = new Segment(2, 2, 1, 0);
        assertFalse(LineUtil.linesIntersect(s1, s2));
        s1 = new Segment(0, -1, 0, 1);
        s2 = new Segment(-1, 0, 1, 0);
        assertTrue(LineUtil.linesIntersect(s1, s2));
    }
}
