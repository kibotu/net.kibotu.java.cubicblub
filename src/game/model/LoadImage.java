package game.model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoadImage extends Component {

	private static final long serialVersionUID = 1994514037196757683L;
	Image img;

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public LoadImage(String filepath) {
		assert(filepath != null);
		//img = ImageIO.read(new File(filepath));
		img = (new ImageIcon(this.getClass().getClassLoader().getResource(filepath))).getImage();
	}

	public Image getImage() {
		return img;
	}
}