package com.dacarr.game.model.object;

import com.badlogic.gdx.graphics.Color;
import com.dacarr.game.model.entity.NonMovingEntity;
import com.dacarr.game.model.util.Position;

public class Target extends NonMovingEntity {

    public Target(Position position) {
        super(position, Color.YELLOW, 16, 16, (entity, snake) -> {
            snake.setLength(snake.getLength() + 1);
        });
    }

}
