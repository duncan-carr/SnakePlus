package com.dacarr.game.model.entity;

import com.badlogic.gdx.graphics.Color;
import com.dacarr.game.model.util.Position;

/**
 * A {@link MovingEntity} is an implementation of {@link Entity}
 * which has a mutable position.
 */
public class MovingEntity extends Entity {

    private Position position;
    private final Color color;
    private final int width, height;

    public MovingEntity(Position position, Color color, int width, int height) {
        this.position = position;
        this.color = color;
        this.width = width;
        this.height = height;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the new position
     * @return the old position
     */
    public Position setPosition(Position position) {
        Position oldPosition = this.position;
        this.position = position;
        return oldPosition;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

}
