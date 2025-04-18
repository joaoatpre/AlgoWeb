package menu;

import javax.swing.*;
import java.awt.*;
import utils.Config;

public class Menu {
	protected JFrame mainFrame;
	protected int screenWidth;
	protected int screenHeight;

	public Menu() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth() / 2;
		screenHeight = (int) screenSize.getHeight() / 2;

		mainFrame = new JFrame(Config.APP_NAME);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(screenWidth, screenHeight);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		mainFrame.setVisible(true);
	}
}
