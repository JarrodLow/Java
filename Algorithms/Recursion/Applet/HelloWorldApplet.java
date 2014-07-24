import java.awt.*;
import javax.swing.*;

public class HelloWorldApplet extends JApplet {

	private String text;
	private int initialTextX, initialTextY;

	@Override
	public void init() {
		text = "Hello World";
		initialTextX = 50;
		initialTextY = 50;

		setSize(300, 300);
	}

	@Override
	public void paint(Graphics page) {
		page.setColor(Color.yellow);
		page.fillRect(5, 5, 100, 100);

		page.setColor(Color.pink);
		page.drawString(text, initialTextX, initialTextY);
	}
}
