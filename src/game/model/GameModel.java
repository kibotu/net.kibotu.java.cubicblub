package game.model;

import java.util.LinkedList;
import java.util.List;

import config.Config;

public class GameModel{

    protected Player player;
    protected List<Bullet> bullets;

    public GameModel() {
	player = new Player("Goo", 100, Config.WINDOW_HEIGHT / 2, 0);
	bullets = new LinkedList<Bullet>();
    }

    public Player getPlayer() {
	return player;
    }

    public void addBullet(Bullet bullet) {
	bullets.add(bullet);
    }

    public List<Bullet> getBullets() {
	return bullets;
    }

    public void removeBullet(Bullet bullet) {
	bullets.remove(bullet);
    }
}
