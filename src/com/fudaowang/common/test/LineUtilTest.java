package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Segment;
import com.fudaowang.common.util.LineUtil;
import com.fudaowang.common.util.NumberUtil;
import com.fudaowang.common.util.PointUtil;

import static junit.framework.TestCase.*;

import com.fudaowang.common.util.SegmentUtil;
import org.junit.Test;

/**
 * LineUtil的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/15/12
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineUtilTest {
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
        assertTrue(PointUtil.coincide(p, p01));
        p = LineUtil.intersect(l1, l_1);
        assertNull(p);
        p = SegmentUtil.intersect(segment, l1, true);
        assertTrue(PointUtil.coincide(p, p_10));
    }

    /**
     * 测试点是否在线段范围内
     *
     * @throws Exception
     */
    @Test
    public void testInSegment() throws Exception {
        Segment s = new Segment(-1, -1, 1, 1);
        assertTrue(SegmentUtil.inSegment(p00, s));
        assertTrue(SegmentUtil.inSegment(p10, s));
        assertFalse(SegmentUtil.inSegment(p11, s));
        s = new Segment(-1, 0, 1, 0);
        assertTrue(SegmentUtil.inSegment(p00, s));
        assertTrue(SegmentUtil.inSegment(p01, s));
        assertFalse(SegmentUtil.inSegment(p10, s));
        assertFalse(SegmentUtil.inSegment(new Point(10, 0), s));
    }

    /**
     * 测试点到直线,线段的距离
     *
     * @throws Exception
     */
    @Test
    public void testDistance() throws Exception {
        double d = LineUtil.distance(p00, l1);
        assertTrue(NumberUtil.equal(d, Math.sqrt(2) / 2.0));
        d = SegmentUtil.distance(p11, segment);
        assertEquals(d, 1.0);
    }

    /**
     * 测试判断点是否在线上
     *
     * @throws Exception
     */
    @Test
    public void testOnLine() throws Exception {
        assertTrue(LineUtil.onLine(p01, l1, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p_10, l1, NumberUtil.MIN_VALUE));
        assertFalse(LineUtil.onLine(p00, l1, NumberUtil.MIN_VALUE));
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
        line = SegmentUtil.verticalLine(p00, segment);
        assertTrue(LineUtil.onLine(p01, line, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p0_1, line, NumberUtil.MIN_VALUE));
    }

    /**
     * 测试求垂足
     *
     * @throws Exception
     */
    @Test
    public void testVerticalPoint() throws Exception {
        Point p = LineUtil.verticalPoint(p10, l1);
        assertTrue(PointUtil.coincide(p, p01));
        p = LineUtil.verticalPoint(p10, l1);
        assertTrue(PointUtil.coincide(p, p01));
        p = SegmentUtil.verticalPoint(p00, segment);
        assertTrue(PointUtil.coincide(p, p00));
        p = SegmentUtil.verticalPoint(p0_1, segment);
        assertTrue(PointUtil.coincide(p, p00));
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
        line = SegmentUtil.parallelLine(p01, segment);
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
        assertTrue(NumberUtil.equal(d, Math.sqrt(2)));
        d = LineUtil.distance(p0_1, line);
        assertTrue(NumberUtil.equal(d, Math.sqrt(2)));

        line = LineUtil.getLine(p10, Math.PI / 4);
        assertTrue(LineUtil.onLine(p10, line, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p0_1, line, NumberUtil.MIN_VALUE));

        line = LineUtil.getLine(p10, Math.PI / 2);
        assertTrue(LineUtil.onLine(p10, line, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p11, line, NumberUtil.MIN_VALUE));

        line = LineUtil.getLine(p00, Math.PI);
        assertTrue(LineUtil.onLine(p10, line, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p_10, line, NumberUtil.MIN_VALUE));
    }

    /**
     * 测试直线,线段的平移
     *
     * @throws Exception
     */
    @Test
    public void testTranslation() throws Exception {
        Line line = LineUtil.translation(l1, 1, -1);
        assertTrue(LineUtil.onLine(p10, line, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p0_1, line, NumberUtil.MIN_VALUE));
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

        a = LineUtil.getAngle(l1);
        assertEquals(a, Math.PI / 4);
        a = LineUtil.getAngle(l_1);
        assertEquals(a, Math.PI / 4);
        a = LineUtil.getAngle(_l1);
        assertEquals(a, -Math.PI / 4);
        a = LineUtil.getAngle(_l_);
        assertEquals(a, -Math.PI / 4);

        Line l = new Line(1, 0, 0);
        a = LineUtil.getAngle(l);
        assertEquals(a, -Math.PI / 2);
        l = new Line(0, 1, 0);
        a = LineUtil.getAngle(l);
        assertEquals(a, -0.0);
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
        assertFalse(SegmentUtil.linesIntersect(s1, s2));
        s1 = new Segment(0, -1, 0, 1);
        s2 = new Segment(-1, 0, 1, 0);
        assertTrue(SegmentUtil.linesIntersect(s1, s2));
    }

    /**
     * 测试线段的旋转
     *
     * @throws Exception
     */
    @Test
    public void testRotateSegment() throws Exception {
        Segment s1 = new Segment(p_10, p01);
        Segment s2 = SegmentUtil.rotate(s1, p00, Math.PI / 2);
        assertTrue(PointUtil.coincide(s2.getP1(), p0_1));
        assertTrue(PointUtil.coincide(s2.getP2(), p_10));

        s2 = SegmentUtil.rotate(s1, p00, Math.PI);
        assertTrue(PointUtil.coincide(s2.getP1(), p10));
        assertTrue(PointUtil.coincide(s2.getP2(), p0_1));

        s2 = SegmentUtil.rotate(s1, p01, Math.PI / 2);
        assertTrue(PointUtil.coincide(s2.getP1(), p10));
        assertTrue(PointUtil.coincide(s2.getP2(), p01));

        s2 = SegmentUtil.rotate(s1, new Point(100, 99), Math.PI * 2);
        assertTrue(PointUtil.coincide(s2.getP1(), s1.getP1()));
        assertTrue(PointUtil.coincide(s2.getP2(), s1.getP2()));
    }

    /**
     * 测试直线的旋转
     *
     * @throws Exception
     */
    @Test
    public void testRotateLine() throws Exception {
        Line line = new Line(1, -1, 1); //y=x+1

        Line l = LineUtil.rotate(line, p00, Math.PI / 2); //y=-x-1
        assertTrue(LineUtil.onLine(p_10, l, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p0_1, l, NumberUtil.MIN_VALUE));

        l = LineUtil.rotate(line, p00, Math.PI); //y=x-1
        assertTrue(LineUtil.onLine(p10, l, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p0_1, l, NumberUtil.MIN_VALUE));

        l = LineUtil.rotate(line, p01, Math.PI / 2); //y=-x+1
        assertTrue(LineUtil.onLine(p01, l, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(p10, l, NumberUtil.MIN_VALUE));

        l = LineUtil.rotate(line, p10, Math.PI / 4);
        double d = -Math.sqrt(2) + 1;
        assertTrue(LineUtil.onLine(new Point(d, 100), l, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(new Point(d, -0.5), l, NumberUtil.MIN_VALUE));
    }

    /**
     * 测试获取x值
     *
     * @throws Exception
     */
    @Test
    public void testGetX() throws Exception {
        Line line = new Line(3, -7, 6);
        double x = LineUtil.getX(line, 9.0 / 7.0);
        assertEquals(x, 1, NumberUtil.MIN_VALUE);

        line = new Line(0, 1, 1);
        x = LineUtil.getX(line, 5);
        assertEquals(x, Double.NaN);

        line = new Line(1, 0, 1);
        x = LineUtil.getX(line, 5);
        assertEquals(x, Double.NaN);
    }

    /**
     * 测试获取y值
     *
     * @throws Exception
     */
    @Test
    public void testGetY() throws Exception {
        Line line = new Line(3, -7, 6);
        double x = LineUtil.getX(line, 9.0 / 7.0);
        assertEquals(x, 1, NumberUtil.MIN_VALUE);

        line = new Line(0, 1, 1);
        x = LineUtil.getX(line, 5);
        assertEquals(x, Double.NaN);

        line = new Line(1, 0, 1);
        x = LineUtil.getX(line, 5);
        assertEquals(x, Double.NaN);
    }

    /**
     * 测试计算对称线段
     *
     * @throws Exception
     */
    @Test
    public void testGetAxialSymmetry() throws Exception {
        Segment s1 = new Segment(-2, 1, -1, 1);
        Line line = new Line(1, -1, 0);
        Segment s2 = SegmentUtil.getAxialSymmetry(s1, line);
        assertTrue(PointUtil.coincide(s2.getP1(), new Point(1, -2)));
        assertTrue(PointUtil.coincide(s2.getP2(), new Point(1, -1)));

        Segment s3 = new Segment(-1, -1, 1, 1);
        s2 = SegmentUtil.getAxialSymmetry(s1, s3);
        assertTrue(PointUtil.coincide(s2.getP1(), new Point(1, -2)));
        assertTrue(PointUtil.coincide(s2.getP2(), new Point(1, -1)));
    }
}
