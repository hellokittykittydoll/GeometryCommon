package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Segment;
import com.fudaowang.common.graph.Triangle;
import com.fudaowang.common.util.PointUtil;
import com.fudaowang.common.util.TriangleUtil;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * 三角形的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/6/12
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class TriangleUtilTest {
    private Triangle triangle = new Triangle(0, 1, -1, -1, 1, -1);

    /**
     * 测试计算三角形的面积
     *
     * @throws Exception
     */
    @Test
    public void testGetArea() throws Exception {
        double a = TriangleUtil.getArea(triangle);
        assertEquals(a, 2.0);
    }

    /**
     * 测试判断点是否在三角形内
     *
     * @throws Exception
     */
    @Test
    public void testInTriangle() throws Exception {
        Point p = new Point(0, 0);
        assertTrue(TriangleUtil.inTriangle(p, triangle));
        p = new Point(1, 0);
        assertFalse(TriangleUtil.inTriangle(p, triangle));
        p = new Point(0.5, -0.5);
        assertTrue(TriangleUtil.inTriangle(p, triangle));
        p = new Point(1, -1);
        assertFalse(TriangleUtil.inTriangle(p, triangle));
        p = new Point(5, 5);
        assertFalse(TriangleUtil.inTriangle(p, triangle));
    }

    /**
     * 测试旋转三角形
     *
     * @throws Exception
     */
    @Test
    public void testRotate() throws Exception {
        Point center = new Point(0, 0);
        Triangle tri = TriangleUtil.rotate(triangle, center, Math.PI / 2);
        assertTrue(PointUtil.coincide(tri.getP1(), new Point(-1, 0)));
        assertTrue(PointUtil.coincide(tri.getP2(), new Point(1, -1)));
        assertTrue(PointUtil.coincide(tri.getP3(), new Point(1, 1)));
    }

    /**
     * 测试获取轴对称三角形
     *
     * @throws Exception
     */
    @Test
    public void testGetAxialSymmetry() throws Exception {
        Line line = new Line(1, -1, 0);
        Triangle tri = TriangleUtil.getAxialSymmetry(triangle, line);
        assertTrue(PointUtil.coincide(tri.getP1(), new Point(1, 0)));
        assertTrue(PointUtil.coincide(tri.getP2(), new Point(-1, -1)));
        assertTrue(PointUtil.coincide(tri.getP3(), new Point(-1, 1)));

        Segment segment = new Segment(-1, -1, 1, 1);
        tri = TriangleUtil.getAxialSymmetry(triangle, segment);
        assertTrue(PointUtil.coincide(tri.getP1(), new Point(1, 0)));
        assertTrue(PointUtil.coincide(tri.getP2(), new Point(-1, -1)));
        assertTrue(PointUtil.coincide(tri.getP3(), new Point(-1, 1)));
    }
}
