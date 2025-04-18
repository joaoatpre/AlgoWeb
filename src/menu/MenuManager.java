package menu;

import javax.swing.*;

import menu.screens.*;

import java.util.HashMap;

public class MenuManager extends Menu {
	private final HashMap<String, JPanel> screens;

	public MenuManager() {
		super();
		screens = new HashMap<>();
	}

	public void setup() {
		screens.put("loading", new LoadingScreen(this));
		screens.put("login", new LoginScreen(this));
		screens.put("register", new RegisterScreen(this));
		screens.put("main", new MainScreen(this));

		renderScreen("loading");
	}

	public void renderScreen(String screenName) {
		JPanel panel = screens.get(screenName);
		if (panel != null) {
			mainFrame.setContentPane(panel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
	}
}
