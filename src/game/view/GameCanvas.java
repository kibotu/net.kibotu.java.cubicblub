package game.view;

import game.model.Bullet;
import game.model.GameModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameCanvas extends JPanel {

    private static final long serialVersionUID = 1608604435320427826L;
    protected final Status status;
    protected final GameModel model;

    public GameCanvas(GameModel model, Status status) {
	super();
	setBackground(Color.WHITE);
	this.model = model;
	this.status = status;
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g.create();
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	status.setText("<html><body><center>Leben: <b>"
		+ model.getPlayer().getLifes() + "</b> Punkte: <b>"
		+ model.getPlayer().getScore() + "</b></center></body></html>");
	model.getPlayer().draw(g2);
	for(Bullet bullet: model.getBullets()) {
	    bullet.draw(g2);
	}
	g2.dispose();
    }
}