package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Parabola;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.util.LineUtil;
import com.fudaowang.common.util.NumberUtil;
import com.fudaowang.common.util.ParabolaUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * 抛物线的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/22/12
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParabolaUtilTest {
    /**
     * 测试生成抛物线
     *
     * @throws Exception
     */
    @Test
    public void testGetParabola() throws Exception {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(-1, 1);

        Parabola parabola = ParabolaUtil.getParabola(p0, p1, p2);
        assertEquals(1.0, parabola.getA());
        assertEquals(0.0, parabola.getB());
        assertEquals(0.0, parabola.getC());

        parabola = ParabolaUtil.getParabola(0, 0, 0, 1, 2, 2);
        assertNull(parabola);
        parabola = ParabolaUtil.getParabola(0, 0, 0, 1, 0, -1);
        assertNull(parabola);
    }

    /**
     * 测试计算抛物线的顶点
     *
     * @throws Exception
     */
    @Test
    public void testVertexPoint() throws Exception {
        Point p = ParabolaUtil.vertexPoint(new Parabola(1, 0, 0));
        assertEquals(0.0, p.getX());
        assertEquals(0.0, p.getY());
        p = ParabolaUtil.vertexPoint(new Parabola(-1, 2, -2));
        assertEquals(1.0, p.getX());
        assertEquals(-1.0, p.getY());
    }

    /**
     * 测试生成抛物线的Y值
     *
     * @throws Exception
     */
    @Test
    public void testGetY() throws Exception {
        Parabola parabola = new Parabola(-1, 2, -2);
        double d = ParabolaUtil.getY(parabola, 2);
        assertEquals(-2.0, d);
    }

    /**
     * 测试计算抛物线与直线的交点
     *
     * @throws Exception
     */
    @Test
    public void testIntersect() throws Exception {
        Line line = new Line(1, 1, 0);
        Parabola parabola = new Parabola(-1, 2, -2);
        Point[] points = ParabolaUtil.intersect(line, parabola);
        assertEquals(2, points.length);

        assertTrue(LineUtil.onLine(points[0], line, NumberUtil.MIN_VALUE));
        assertTrue(LineUtil.onLine(points[1], line, NumberUtil.MIN_VALUE));
    }

    /**
     * 测试对抛物线进行平移
     *
     * @throws Exception
     */
    @Test
    public void testTranslation() throws Exception {
        Parabola parabola = new Parabola(-1, 2, -2);
        parabola = ParabolaUtil.translation(parabola, -1, 1);
        assertEquals(-1.0, parabola.getA());
        assertEquals(0.0, parabola.getB());
        assertEquals(0.0, parabola.getC());
    }
}
