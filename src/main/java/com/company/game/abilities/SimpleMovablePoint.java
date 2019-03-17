package com.company.game.abilities;

import java.awt.*;
import java.util.function.Function;


public class SimpleMovablePoint implements Movable {
    private double x = 0;
    private double y = 0;
    private double rotation = 0;

    @Override
    public void moveToPoint(double x, double y) {
        System.out.println("Moved to point {" + x + ", " + y + "}");
        setX(x).setY(y);
    }

    @Override
    public void moveByDiff(double dx, double dy) {
        setX(x + dx).setY(y + dy);
    }

    @Override
    public void moveBySpeed(double speed, double alpha) {
        double dx = Math.cos(alpha)*speed;
        double dy = Math.sin(alpha)*speed;
        moveByDiff(dx, dy);
    }

    public double getX() {
        return x;
    }

    public SimpleMovablePoint setX(final double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public SimpleMovablePoint setY(final double y) {
        this.y = y;
        return this;
    }

    public SimpleMovablePoint setRotation(double rotation) {
        this.rotation = rotation;
        return this;
    }

    public double getRotation() {
        return rotation;
    }
}
