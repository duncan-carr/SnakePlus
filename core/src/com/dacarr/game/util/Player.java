package com.dacarr.game.util;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Queue;

public class Player {

    private final int width, height;
    private Position headPosition;
    private final Queue<Position> tail;
    private Direction direction;
    private boolean changedDirection;
    private int length;

    public Player(int width, int height) {
        this.width = width;
        this.height = height;
        this.headPosition = new Position(256, 256);
        this.direction = Direction.RIGHT;
        this.changedDirection = false;
        this.length = 3;
        this.tail = new LinkedList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Position getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(Position position) {
        tail.add(this.headPosition);
        this.headPosition = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.changedDirection = true;
    }

    public boolean hasChangedDirection() {
        return changedDirection;
    }

    public void setChangedDirection(boolean changedDirection) {
        this.changedDirection = changedDirection;
    }

    public Queue<Position> getTail() {
        return tail;
    }

    public boolean isCovering(Position position) {
        return (headPosition.equals(position) || tail.contains(position));
    }

    public void move(Vector2D vector) {
        Position newPosition;
        tail.add(this.headPosition);
        switch (vector.getDirection()) {
            case LEFT:
                newPosition = new Position(headPosition.x() - vector.getDistance(), headPosition.y());
                if (!(newPosition.x() < 0)) {
                    headPosition = newPosition;
                } else {
                    headPosition = new Position(496, newPosition.y());
                }
                break;
            case RIGHT:
                newPosition = new Position(headPosition.x() + vector.getDistance(), headPosition.y());
                if (!(newPosition.x() > 496)) {
                    headPosition = newPosition;
                } else {
                    headPosition = new Position(0, newPosition.y());
                }
                break;
            case UP:
                newPosition = new Position(headPosition.x(), headPosition.y() + vector.getDistance());
                if (!(newPosition.y() > 496)) {
                    headPosition = newPosition;
                } else {
                    headPosition = new Position(newPosition.x(), 0);
                }
                break;
            case DOWN:
                newPosition = new Position(headPosition.x(), headPosition.y() - vector.getDistance());
                if (!(newPosition.y() < 0)) {
                    headPosition = newPosition;
                } else {
                    headPosition = new Position(newPosition.x(), 496);
                }
                break;
        }
    }

}
