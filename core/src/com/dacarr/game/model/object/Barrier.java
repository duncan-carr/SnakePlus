package com.dacarr.game.model.object;

import com.badlogic.gdx.graphics.Color;
import com.dacarr.game.model.entity.NonMovingEntity;
import com.dacarr.game.model.util.Position;

public class Barrier extends NonMovingEntity {

    public Barrier(Position position) {
        super(position, Color.GRAY, 16, 16, (entity, snake) -> {
            // todo: loses the game
        });
    }

}
