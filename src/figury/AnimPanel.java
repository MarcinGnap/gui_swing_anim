package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	private int delay = 70;

	private Timer timer;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	void addFig() {
		Figura fig = null;
		Random rand = new Random();
		int n = rand.nextInt(100);
		switch(n % 3){
			case 0: fig = new Kwadrat(buffer, delay, getWidth(), getHeight());
				break;

			case 1: fig = new Elipsa(buffer, delay, getWidth(), getHeight());
				break;

			case 2: fig = new Trojkat(buffer, delay, getWidth(), getHeight());
				break;
		}
		timer.addActionListener(fig);
		new Thread(fig).start();
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}

	public void addAll(int choise){
		Figura fig = null;
		switch(choise){
			case 0: fig = new Elipsa(buffer, delay, getWidth(), getHeight());
				break;

			case 1: fig = new Kwadrat(buffer, delay, getWidth(), getHeight());
				break;

			case 2: fig = new Trojkat(buffer, delay, getWidth(), getHeight());
				break;
		}
		timer.addActionListener(fig);
		new Thread(fig).start();
	}
}
