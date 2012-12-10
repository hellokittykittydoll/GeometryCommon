package com.fudaowang.common.test;

import com.fudaowang.common.graph.Line;
import com.fudaowang.common.graph.Point;
import com.fudaowang.common.graph.Polygon;
import com.fudaowang.common.util.PolygonUtil;
import org.junit.Test;

import static junit.framework.TestCase.*;
/**
 * 多边形的测试类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/7/12
 * Time: 9:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class PolygonUtilTest {
    private Polygon source = new Polygon(new Point(0, 1), new Point(-2, 1), new Point(-3, -1), new Point(1, -1));
    @Test
    public void testRotate() throws Exception {
        Polygon polygon = PolygonUtil.rotate(source, new Point(0, 0), -Math.PI / 2.0);
        Polygon target = new Polygon(new Point(1, 2), new Point(1, 0), new Point(-1, 3), new Point(-1, -1));
        assertTrue(PolygonUtil.coincide(polygon, target));
    }

    @Test
    public void testGetAxialSymmetry() throws Exception {
        Polygon p1 = PolygonUtil.getAxialSymmetry(source, new Line(1, -1, 0));
        Polygon p2 = PolygonUtil.rotate(source, new Point(-1, -1), -Math.PI / 2.0);
        assertTrue(PolygonUtil.coincide(p1, p2));
    }
}
