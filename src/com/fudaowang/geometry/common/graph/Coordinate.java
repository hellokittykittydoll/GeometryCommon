package com.fudaowang.geometry.common.graph;

/**
 * 表示一个相对坐标的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/4/13
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class Coordinate {
    private final double originX;
    private final double originY;
    private final double spaceX;
    private final double spaceY;

    /**
     * 构造方法
     *
     * @param originX 原点的横坐标
     * @param originY 原点的纵坐标
     * @param spaceX  横坐标单位长度值
     * @param spaceY  纵坐标单位长度值
     */
    public Coordinate(double originX, double originY, double spaceX, double spaceY) {
        this.originX = originX;
        this.originY = originY;
        this.spaceX = spaceX;
        this.spaceY = spaceY;
    }

    /**
     * 获得原点的横坐标
     *
     * @return 原点的横坐标
     */
    public double getOriginX() {
        return originX;
    }

    /**
     * 获得原点的纵坐标
     *
     * @return 原点的纵坐标
     */
    public double getOriginY() {
        return originY;
    }

    /**
     * 获得横坐标单位长度值
     *
     * @return 横坐标单位长度值
     */
    public double getSpaceX() {
        return spaceX;
    }

    /**
     * 获得纵坐标单位长度值
     *
     * @return 纵坐标单位长度值
     */
    public double getSpaceY() {
        return spaceY;
    }
}
