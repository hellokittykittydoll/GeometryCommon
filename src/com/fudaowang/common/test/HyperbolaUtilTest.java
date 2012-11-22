package com.fudaowang.common.test;

import com.fudaowang.common.graph.Hyperbola;
import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.util.HyperbolaUtil;
import com.fudaowang.common.util.LineUtil;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * 双曲线测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/22/12
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class HyperbolaUtilTest {
    private double precision = 0.0001;

    /**
     * 测试双曲线与直线相交
     * @throws Exception
     */
    @Test
    public void testIntersect() throws Exception {
        Hyperbola hyperbola = new Hyperbola(1);
        Line l1 = new Line(1, -1, 0);
        Line l2 = new Line(1, 1, 0);
        Line l3 = new Line(1, 0, 0);
        Line l4 = new Line(0, 1, 0);

        Point[] points = HyperbolaUtil.intersect(hyperbola, l1);
        assertEquals(2, points.length);
        assertTrue(LineUtil.onLine(points[0], l1, precision));
        assertTrue(LineUtil.onLine(points[1], l1, precision));

        points = HyperbolaUtil.intersect(hyperbola, l2);
        assertEquals(0, points.length);
        points = HyperbolaUtil.intersect(hyperbola, l3);
        assertEquals(0, points.length);
        points = HyperbolaUtil.intersect(hyperbola, l4);
        assertEquals(0, points.length);
    }
}
