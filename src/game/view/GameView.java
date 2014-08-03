package game.view;
import game.model.GameModel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GameView extends JPanel {

    private static final long serialVersionUID = -2208539628642301065L;

    public GameView(GameModel model) {
	super();
	setLayout(new BorderLayout());
	Status status = new Status(model);
	add(status, BorderLayout.NORTH);
	add(new GameCanvas(model, status), BorderLayout.CENTER);
    }
}
