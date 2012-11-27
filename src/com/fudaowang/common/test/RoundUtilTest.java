package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Round;
import com.fudaowang.common.util.LineUtil;
import com.fudaowang.common.util.RoundUtil;
import com.fudaowang.common.util.Util;
import org.junit.Test;

import java.util.List;

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
    private double precision = 0.0001;

    /**
     * 测试计算圆与直线的交点
     *
     * @throws Exception
     */
    @Test
    public void testIntersect() throws Exception {
        Round round = new Round(new Point(1, 1), Math.sqrt(2));
        Line l1 = new Line(1, -1, 0);
        Line l2 = new Line(0, 1, 0);
        Point[] points = RoundUtil.intersect(l1, round);

        assertEquals(2, points.length);

        assertTrue(LineUtil.onLine(points[0], l1, precision));
        assertTrue(LineUtil.onLine(points[1], l1, precision));
        assertTrue(Util.equal(Math.sqrt(2) * 2.0, Util.distance(points[0], points[1]), precision));
    }

    @Test
    public void testDistance() throws Exception {

    }

    @Test
    public void testOnRound() throws Exception {

    }

    @Test
    public void testTranslation() throws Exception {

    }

    @Test
    public void testInRound() throws Exception {

    }

    @Test
    public void testTangentPoint() throws Exception {

    }
}
