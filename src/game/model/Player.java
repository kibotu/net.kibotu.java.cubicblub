package game.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import config.Config;

public class Player {

	protected int lifes;
	protected int score;
	protected int ammunition;
	protected int speed;
	protected String name;
	protected Point position;
	protected int zindex;
	protected int width;
	protected int height;
	protected BufferedImage ship;

	public Player(String name, int x, int y, int zindex) {
		ship = getShip(Config.SHIP_IMAGE_PATH);
		lifes = Config.DEFAULT_LIFES;
		score = Config.DEFAULT_SCORE;
		ammunition = Config.DEFAULT_AMUNITION;
		speed = Config.DEFAULT_SPEED;
		width = ship.getWidth();
		height = ship.getHeight();
		this.name = name;
		position = new Point(x, y);
		this.zindex = zindex;		
	}
	
	public BufferedImage getShip(String filepath) {
		return Pictures.toBufferedImage(new ImageIcon(this.getClass().getClassLoader().getResource(filepath)).getImage());
	}

	public int getLifes() {
		return lifes;
	}

	public void moveRel(int dx, int dy) {
		if (position.x + dx > 0
				&& position.x + dx + width < Config.WINDOW_WIDTH) {
			position.x += dx;
		}
		if (position.y + dy > 0
				&& position.y + dy - height < Config.WINDOW_HEIGHT - height) {
			position.y += dy;
		}

		// future collision detections
	}

	public int getSpeed() {
		return speed;
	}

	public int getScore() {
		return score;
	}

	public void draw(Graphics g) {		
		g.drawImage(ship, position.x - width / 2,  position.y - height / 2+2, null);
	}

	public Point getPosition() {
		return position;
	}

	public void addAmunition(int amount) {
		ammunition += amount;
	}
}