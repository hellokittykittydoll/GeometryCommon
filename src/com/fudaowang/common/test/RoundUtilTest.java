package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Round;
import com.fudaowang.common.graph.RoundEnum;
import com.fudaowang.common.util.NumberUtil;
import com.fudaowang.common.util.PointUtil;
import com.fudaowang.common.util.RoundUtil;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * 圆的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/23/12
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoundUtilTest {

    /**
     * 测试计算圆与直线的交点
     *
     * @throws Exception
     */
    @Test
    public void testIntersect() throws Exception {
        Round round = new Round(new Point(1, 1), Math.sqrt(2));
        Line l1 = new Line(1, -1, 0);
        Line l2 = new Line(1, 1, 0);

        Point[] points = RoundUtil.intersect(l1, round);
        assertEquals(2, points.length);
        assertEquals(points[0].getX(), 2.0, NumberUtil.MIN_VALUE);
        assertEquals(points[0].getY(), 2.0, NumberUtil.MIN_VALUE);
        assertEquals(points[1].getX(), 0.0, NumberUtil.MIN_VALUE);
        assertEquals(points[1].getY(), 0.0, NumberUtil.MIN_VALUE);

        points = RoundUtil.intersect(l2, round);
        assertEquals(1, points.length);
        assertEquals(points[0].getX(), 0.0, NumberUtil.MIN_VALUE);
        assertEquals(points[0].getY(), 0.0, NumberUtil.MIN_VALUE);
    }

    /**
     * 测试判断点是否在圆上
     *
     * @throws Exception
     */
    @Test
    public void testOnRound() throws Exception {
        Round round = new Round(new Point(1, 1), Math.sqrt(2));
        Point p = new Point(0, 0);
        assertTrue(RoundUtil.onRound(p, round, NumberUtil.MIN_VALUE));
        p = new Point(1, 0);
        assertFalse(RoundUtil.onRound(p, round, NumberUtil.MIN_VALUE));
        p = new Point(-1, -1);
        assertFalse(RoundUtil.onRound(p, round, NumberUtil.MIN_VALUE));
    }

    /**
     * 测试计算圆平移
     *
     * @throws Exception
     */
    @Test
    public void testTranslation() throws Exception {
        Round round = new Round(new Point(1, 1), Math.sqrt(2));
        round = RoundUtil.translation(round, -1, -1);
        Point p = new Point(0, 0);
        assertTrue(PointUtil.coincide(p, round.getCenter()));
        assertEquals(Math.sqrt(2), round.getRadius());
    }

    /**
     * 测试判断点是否在圆内
     *
     * @throws Exception
     */
    @Test
    public void testInRound() throws Exception {
        Round round = new Round(new Point(1, 1), Math.sqrt(2));
        Point p = new Point(0, 0);
        assertFalse(RoundUtil.inRound(p, round));
        p = new Point(1, 0);
        assertTrue(RoundUtil.inRound(p, round));
        p = new Point(-1, -1);
        assertFalse(RoundUtil.inRound(p, round));
    }

    /**
     * 测试计算点到圆的切线
     *
     * @throws Exception
     */
    @Test
    public void testTangentPoint() throws Exception {
        Round round = new Round(new Point(1, 1), Math.sqrt(2));
        Point p = new Point(0, 0);
        Point[] points = RoundUtil.tangentPoint(p, round);
        assertEquals(points.length, 1);
        assertTrue(PointUtil.coincide(p, points[0]));
        p = new Point(1, 0);
        points = RoundUtil.tangentPoint(p, round);
        assertEquals(points.length, 0);
        p = new Point(1 - Math.sqrt(2), 1 - Math.sqrt(2));
        points = RoundUtil.tangentPoint(p, round);
        assertEquals(points.length, 2);
        assertEquals(p.getX(), 1 - Math.sqrt(2), NumberUtil.MIN_VALUE);
        assertEquals(p.getY(), 1 - Math.sqrt(2), NumberUtil.MIN_VALUE);
    }

    /**
     * 测试计算两圆的位置关系
     *
     * @throws Exception
     */
    @Test
    public void testGetRelationship() throws Exception {
        Round r1 = new Round(0, 0, 10);
        Round r2 = new Round(5, 0, 4);

        RoundEnum relation = RoundUtil.getRelationship(r1, r2);
        assertEquals(relation, RoundEnum.CONTAIN);

        relation = RoundUtil.getRelationship(r1, r2, 1);
        assertEquals(relation, RoundEnum.CONTAIN);

        r2 = new Round(5, 0, 5);
        relation = RoundUtil.getRelationship(r1, r2);
        assertEquals(relation, RoundEnum.INTERNAL);

        r2 = new Round(5, 0, 5);
        relation = RoundUtil.getRelationship(r1, r2, 1);
        assertEquals(relation, RoundEnum.INTERNAL);

        r2 = new Round(15, 0, 5);
        relation = RoundUtil.getRelationship(r1, r2, 1);
        assertEquals(relation, RoundEnum.EXTERNAL);

        r2 = new Round(15, 0, 4);
        relation = RoundUtil.getRelationship(r1, r2, 1);
        assertEquals(relation, RoundEnum.SEPARATE);
    }
}
