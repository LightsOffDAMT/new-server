package com.company.server.dto;

public class MoveEvent {
    private double x;
    private double y;
    private MoveType moveType;

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public enum MoveType{
        POINT, DIFF, SPEED
    }
}
