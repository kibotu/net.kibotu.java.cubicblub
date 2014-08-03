package game.model;

import game.controller.BulletEvent;
import game.controller.BulletListener;
import game.controller.BulletState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import config.Config;

public class Bullet {

	protected BulletState _state = BulletState.RUNNING;
	private List<BulletListener> _listeners = new ArrayList<BulletListener>();

	protected Point position;
	protected int zindex;
	protected int speed;
	protected int width;
	protected int height;
	protected int distance;
	protected static final String OUT_OF_BOUNDS = "OUT_OF_BOUNDS";

	public Bullet(Point position) {
		this.position = new Point(position);
		width = 3;
		height = 3;
		distance = Config.DEFAULT_BULLET_DISTANCE;
		speed = Config.DEFAULT_AMUNITION_BULLET_SPEED;
	}

	public void move() {
		if (position.x + speed < Config.WINDOW_WIDTH - 3 && distance > 0) {
			position.x += speed;
			distance--;
		} else {
			_state = BulletState.REACHED_LIMIT;
			dispatchEvent();
		}
	}

	public void draw(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(Color.black);
		g.fill3DRect(position.x, position.y, width, height, true);
		g.setColor(oldColor);
	}

	public synchronized void addBulletStateListener(BulletListener listener) {
		_listeners.add(listener);
	}

	public synchronized void removeBulletStateListener(BulletListener listener) {
		_listeners.remove(listener);
	}

	private synchronized void dispatchEvent() {
		BulletEvent event = new BulletEvent(this, _state);
		for (int i = 0; i < _listeners.size(); ++i) {
			_listeners.get(i).bulletStateReceived(event);
		}
	}
}
