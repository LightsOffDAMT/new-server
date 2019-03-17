package com.company.game.abilities;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public interface Movable {
    /**
     *
     * @param x - destination x
     * @param y - destination y
     */
    void moveToPoint(double x, double y);

    /**
     *
     * @param dx - difference in x
     * @param dy - difference in y
     */
    void moveByDiff(double dx, double dy);

    /**
     * @param alpha - is polar angle
     * @param speed - is value of speed vector
     *
     **/
    void moveBySpeed(double speed, double alpha);
}