package com.dacarr.game.model.util;

import com.dacarr.game.model.entity.Entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static Position random(ArrayList<Entity> entities) {
        int upper = 31;
        int lower = 0;

        int x = (int) (Math.random() * (upper - lower)) + lower;
        int y = (int) (Math.random() * (upper - lower)) + lower;

        Position pos = new Position(x * 16, y * 16);
        if (entities.stream().anyMatch(e -> e.getPosition().equals(pos))) {
            random(entities);
        }

        return pos;
    }

}
