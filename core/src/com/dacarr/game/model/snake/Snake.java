package com.dacarr.game.model.snake;

import com.badlogic.gdx.graphics.Color;
import com.dacarr.game.model.entity.MovingEntity;
import com.dacarr.game.model.util.Position;

import java.util.ArrayList;

public class Snake extends MovingEntity {

    private final ArrayList<Position> tail;
    private int length;

    public Snake() {
        super(new Position(256, 256), Color.GREEN, 16, 16);

        this.tail = new ArrayList<>();
        this.length = 3;
    }

    public ArrayList<Position> getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
