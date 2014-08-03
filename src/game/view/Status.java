package game.view;

import game.model.GameModel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import config.Config;

public class Status extends JTextPane {

    private static final long serialVersionUID = -9104834156275251522L;
    protected JLabel label;

    public Status(GameModel model) {
	super();
	setText("Leben " + Config.DEFAULT_LIFES + " | Score: "
		+ Config.DEFAULT_SCORE);
	setPreferredSize(new Dimension(500, 30));
	setContentType("text/html");
	setFocusable(false);
    }

    public void setStatus(String text) {
	setText(text);
    }
}