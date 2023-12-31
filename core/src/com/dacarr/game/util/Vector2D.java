package com.dacarr.game.util;

public class Vector2D {

    private final int distance;
    private final Direction direction;

    public Vector2D(int distance, Direction direction) {
        this.distance = distance;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDistance() {
        return distance;
    }

}
