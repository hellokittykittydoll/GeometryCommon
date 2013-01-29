package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.util.NumberUtil;

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

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

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

    /**
     * 判断坐标系的横坐标间距与纵坐标间距是否均匀
     *
     * @return 若横坐标间距和纵坐标间距一致, 则返回true
     */
    public boolean isSymmetrical() {
        return NumberUtil.equal(spaceX, spaceY);
    }

    /**
     * 获得横坐标的最小值
     *
     * @return 横坐标的最小值
     */
    public double getMinX() {
        return minX;
    }

    /**
     * 设定横坐标的最小值
     *
     * @param minX 横坐标的最小值
     */
    public void setMinX(double minX) {
        this.minX = minX;
    }

    /**
     * 获得横坐标的最大值
     *
     * @return 横坐标的最大值
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * 设定横坐标的最大值
     *
     * @param maxX 横坐标的最大值
     */
    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    /**
     * 获得纵坐标的最小值
     *
     * @return 纵坐标的最小值
     */
    public double getMinY() {
        return minY;
    }

    /**
     * 设定纵坐标的最小值
     *
     * @param minY 纵坐标的最小值
     */
    public void setMinY(double minY) {
        this.minY = minY;
    }

    /**
     * 获得纵坐标的最大值
     *
     * @return 纵坐标的最大值
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * 设定纵坐标的最大值
     *
     * @param maxY 纵坐标的最大值
     */
    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
}
