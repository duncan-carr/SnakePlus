package com.dacarr.game.util;

import java.util.Objects;

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

    public Position moveLeft() {
        return new Position(this.x - 16, this.y);
    }

    public Position moveRight() {
        return new Position(this.x + 16, this.y);
    }

    public Position moveUp() {
        return new Position(this.x, this.y + 16);
    }

    public Position moveDown() {
        return new Position(this.x, this.y - 16);
    }

}
