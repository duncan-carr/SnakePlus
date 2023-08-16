package com.dacarr.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dacarr.game.util.Direction;
import com.dacarr.game.util.Player;
import com.dacarr.game.util.Position;

public class SnakeGame extends ApplicationAdapter {

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Player player;
	private Rectangle target;

	private boolean gameStarted;

	private float timeBetweenFrames;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 512, 512);

		shapeRenderer = new ShapeRenderer();

		player = new Player(16, 16);
		target = new Rectangle(0, 0, 16, 16);

		Position pos = generateRandomPosition();
		target.setPosition(pos.x(), pos.y());

		timeBetweenFrames = 0;
		gameStarted = false;
	}

	@Override
	public void render() {
		if (!gameStarted) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				player.setDirection(Direction.LEFT);
				gameStarted = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				player.setDirection(Direction.RIGHT);
				gameStarted = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				player.setDirection(Direction.UP);
				gameStarted = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				player.setDirection(Direction.DOWN);
				gameStarted = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
				gameStarted = true;
			}
		}

		if (!player.hasChangedDirection()) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				if (player.getDirection() == Direction.UP || player.getDirection() == Direction.DOWN) {
					player.setDirection(Direction.LEFT);
				}
			}

			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				if (player.getDirection() == Direction.UP || player.getDirection() == Direction.DOWN) {
					player.setDirection(Direction.RIGHT);
				}
			}

			if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				if (player.getDirection() == Direction.RIGHT || player.getDirection() == Direction.LEFT) {
					player.setDirection(Direction.UP);
				}
			}

			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
				if (player.getDirection() == Direction.RIGHT || player.getDirection() == Direction.LEFT) {
					player.setDirection(Direction.DOWN);
				}
			}
		}

		if (player.getHeadPosition().equals(new Position((int) target.x, (int) target.y))) {
			Position pos = generateRandomPosition();
			target.setPosition(pos.x(), pos.y());
			player.setLength(player.getLength() + 1);
		}

		if (player.getTail().contains(player.getHeadPosition())) {
			gameStarted = false;
			player.setHeadPosition(new Position(256, 256));
			player.getTail().clear();
			player.setLength(3);
			Position pos = generateRandomPosition();
			target.setPosition(pos.x(), pos.y());
		}

		while (player.getTail().size() > player.getLength()) {
			player.getTail().poll();
		}

		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(player.getHeadPosition().x(), player.getHeadPosition().y(), player.getWidth(), player.getHeight());
		for (Position pos : player.getTail()) {
			shapeRenderer.rect(pos.x(), pos.y(), 16, 16);
		}
		shapeRenderer.setColor(Color.YELLOW);
		shapeRenderer.rect(target.x, target.y, target.width, target.height);
		shapeRenderer.end();

		if (!gameStarted) return;

		timeBetweenFrames += Gdx.graphics.getDeltaTime();
		if (!(timeBetweenFrames >= 1f/8f)) return;
		timeBetweenFrames = 0;

		Position newPosition;
		switch (player.getDirection()) {
			case LEFT:
				newPosition = player.getHeadPosition().moveLeft();
				if (!(newPosition.x() < 0)) {
					player.setHeadPosition(newPosition);
				} else {
					player.setHeadPosition(new Position(496, newPosition.y()));
				}
				break;
			case RIGHT:
				newPosition = player.getHeadPosition().moveRight();
				if (!(newPosition.x() > 496)) {
					player.setHeadPosition(newPosition);
				} else {
					player.setHeadPosition(new Position(0, newPosition.y()));
				}
				break;
			case UP:
				newPosition = player.getHeadPosition().moveUp();
				if (!(newPosition.y() > 496)) {
					player.setHeadPosition(newPosition);
				} else {
					player.setHeadPosition(new Position(newPosition.x(), 0));
				}
				break;
			case DOWN:
				newPosition = player.getHeadPosition().moveDown();
				if (!(newPosition.y() < 0)) {
					player.setHeadPosition(newPosition);
				} else {
					player.setHeadPosition(new Position(newPosition.x(), 496));
				}
				break;
		}

		player.setChangedDirection(false);
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	public Position generateRandomPosition() {
		Position newPosition = null;
		Position oldPosition = new Position((int) target.x, (int) target.y);
		while (newPosition == null || newPosition.equals(oldPosition) || player.getTail().contains(newPosition) || player.getHeadPosition().equals(newPosition)) {
			int upper = 31;
			int lower = 0;

			int x = (int) (Math.random() * (upper - lower)) + lower;
			int y = (int) (Math.random() * (upper - lower)) + lower;

			newPosition = new Position(x * 16, y * 16);
		}

		return newPosition;
	}
}