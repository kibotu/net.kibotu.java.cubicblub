package game;

import game.controller.GameController;

import java.awt.Container;

import javax.swing.JApplet;

import config.Config;

public class GameBoostrap extends JApplet {

    private static final long serialVersionUID = -7214376977358008722L;

    public void init() {
	GameController controller = new GameController();
	Container container = getContentPane();
	container.add(controller.getView());
	setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT + 50);
	setVisible(true);
	controller.start();
    }
}