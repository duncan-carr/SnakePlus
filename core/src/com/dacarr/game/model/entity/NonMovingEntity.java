package com.dacarr.game.model.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dacarr.game.model.snake.Snake;
import com.dacarr.game.model.util.Position;

import java.util.function.BiConsumer;
/**
 * A {@link NonMovingEntity} is an implementation of {@link Entity}
 * which has an immutable position.
 */
public class NonMovingEntity extends Entity {
    private final Position position;
    private final Color color;
    private final int width, height;
    private final BiConsumer<NonMovingEntity, Snake> snakeTouchEvent;

    public NonMovingEntity(Position position, Color color, int width, int height, BiConsumer<NonMovingEntity, Snake> snakeTouchEvent) {
        this.position = position;
        this.color = color;
        this.width = width;
        this.height = height;
        this.snakeTouchEvent = snakeTouchEvent;
    }

    @Override
    public Position getPosition() {
        return position;
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

    public void render(ShapeRenderer renderer, Snake snake) {
        super.render(renderer);

        if (snake.getPosition().equals(getPosition())) {
            snakeTouchEvent.accept(this, snake);
        }
    }

}
