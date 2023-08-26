package com.dacarr.game.model.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dacarr.game.model.util.Position;

public abstract class Entity {

    public abstract Position getPosition();
    public abstract Color getColor();
    public abstract int getWidth();
    public abstract int getHeight();

    public void render(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(getColor());
        renderer.rect(getPosition().x(), getPosition().y(), getWidth(), getHeight());
        renderer.end();
    }

}
