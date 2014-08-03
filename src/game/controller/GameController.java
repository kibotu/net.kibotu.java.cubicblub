package game.controller;

import game.model.Bullet;
import game.model.GameModel;
import game.model.Player;
import game.view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

public class GameController implements Runnable, KeyListener, BulletListener {

	protected final GameView view;
	protected final GameModel model;
	protected boolean running;
	protected Set<String> pressedKeys;

	public GameController() {
		model = new GameModel();
		view = new GameView(model);
		view.addKeyListener(this);
		view.setFocusable(true);
		pressedKeys = new TreeSet<String>();
	}

	public JPanel getView() {
		return view;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pressedKeys.add("right");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pressedKeys.add("left");
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			pressedKeys.add("up");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pressedKeys.add("down");
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			pressedKeys.add("space");
			// Player player = model.getPlayer();
			/*
			 * player.addAmunition(-1); Bullet bullet = new
			 * Bullet(player.getPosition());
			 * bullet.addBulletStateListener(this); model.addBullet(bullet);
			 */
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pressedKeys.remove("right");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pressedKeys.remove("left");
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			pressedKeys.remove("up");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pressedKeys.remove("down");
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			pressedKeys.remove("space");
		}
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	@Override
	public void bulletStateReceived(BulletEvent event) {
		Bullet bullet = (Bullet) event.getSource();
		if (event.getEventName() == BulletState.REACHED_LIMIT) {
			bullet.removeBulletStateListener(this);
			model.removeBullet(bullet);
		}
	}

	protected void movePlayer() {
		Player player = model.getPlayer();
		int speed = player.getSpeed();
		if (pressedKeys.contains("right")) {
			player.moveRel(speed, 0);
		}
		if (pressedKeys.contains("left")) {
			player.moveRel(-speed, 0);
		}
		if (pressedKeys.contains("up")) {
			player.moveRel(0, -speed);
		}
		if (pressedKeys.contains("down")) {
			player.moveRel(0, speed);
		}
		if (pressedKeys.contains("space")) {
			player.addAmunition(-1);
			Bullet bullet = new Bullet(player.getPosition());
			bullet.addBulletStateListener(this);
			model.addBullet(bullet);
		}
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		while (true) {
			/** sleep **/
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/** player moves **/
			movePlayer();

			/** moving bullets **/
			List<Bullet> bullets = model.getBullets();
			for (int i = 0; i < bullets.size(); ++i) {
				bullets.get(i).move();
			}

			/** repaint **/
			view.repaint();
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}
}
