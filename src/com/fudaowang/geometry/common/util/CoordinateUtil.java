package com.fudaowang.geometry.common.util;

import com.fudaowang.geometry.common.graph.Coordinate;
import com.fudaowang.geometry.common.graph.CoordinateName;

/**
 * 对坐标系相关操作的类
 * double absoluteX = originX + x * spaceX;
 * double absoluteY = originY - y * spaceY;
 * <p/>
 * double relativeX = (x - originX) / spaceX;
 * double relativeY = (originY - y) / spaceY;
 * <p/>
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/18/13
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class CoordinateUtil {
    /**
     * 将绝对坐标值转换为相对坐标值
     *
     * @param value          需要转换的坐标值
     * @param coordinate     相对坐标系
     * @param coordinateName 坐标系的名称
     * @return 转换后的相对坐标值
     */
    public static double toAbsoluteCoordinate(double value, Coordinate coordinate, CoordinateName coordinateName) {
        if (coordinate == null) {
            return Double.NaN;
        }
        return coordinateName == CoordinateName.X ?
                toAbsoluteCoordinateX(value, coordinate.getOriginX(), coordinate.getSpaceX()) :
                toAbsoluteCoordinateY(value, coordinate.getOriginY(), coordinate.getSpaceY());
    }

    /**
     * 将相对横坐标转换为绝对横坐标
     *
     * @param value  需要转换的横坐标值
     * @param origin 相对坐标原点的横坐标值
     * @param space  相对坐标的横坐标间距
     * @return 绝对横坐标值
     */
    public static double toAbsoluteCoordinateX(double value, double origin, double space) {
        return origin + value * space;
    }

    /**
     * 将相对纵坐标转换为绝对纵坐标
     *
     * @param value  需要转换的纵坐标值
     * @param origin 相对坐标原点的纵坐标值
     * @param space  相对坐标的纵坐标间距
     * @return 绝对纵坐标值
     */
    public static double toAbsoluteCoordinateY(double value, double origin, double space) {
        return origin - value * space;
    }

    /**
     * 将相对坐标值转换为绝对坐标值
     *
     * @param value          需要转换的坐标值
     * @param coordinate     相对坐标系
     * @param coordinateName 坐标系的名称
     * @return 转换后的绝对坐标值
     */
    public static double toRelativeCoordinate(double value, Coordinate coordinate, CoordinateName coordinateName) {
        if (coordinate == null) {
            return Double.NaN;
        }
        return coordinateName == CoordinateName.X ?
                toRelativeCoordinateX(value, coordinate.getOriginX(), coordinate.getSpaceX()) :
                toRelativeCoordinateY(value, coordinate.getOriginY(), coordinate.getSpaceY());
    }

    /**
     * 将绝对横坐标转换为相对横坐标
     *
     * @param value  需要转换的横坐标值
     * @param origin 相对坐标原点的横坐标值
     * @param space  相对坐标的横坐标间距
     * @return 相对横坐标值
     */
    public static double toRelativeCoordinateX(double value, double origin, double space) {
        return (value - origin) / space;
    }

    /**
     * 将绝对纵坐标转换为相对纵坐标
     *
     * @param value  需要转换的纵坐标值
     * @param origin 相对坐标原点的纵坐标值
     * @param space  相对坐标的纵坐标间距
     * @return 相对纵坐标值
     */
    public static double toRelativeCoordinateY(double value, double origin, double space) {
        return (origin - value) / space;
    }
}
