package menu.screens;

import javax.swing.*;

import menu.MenuManager;

import java.awt.*;

public class MainScreen extends JPanel {
	private static final long serialVersionUID = 5515969088439768641L;

	public MainScreen(MenuManager manager) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridy = 0;

		JButton btnExercicios = createStyledButton("Exercícios");
		JButton btnTop = createStyledButton("Top");
		JButton btnConta = createStyledButton("Conta");

		add(btnExercicios, gbc);
		gbc.gridx = 1;
		add(btnTop, gbc);
		gbc.gridx = 2;
		add(btnConta, gbc);
	}
	
	private JButton createStyledButton(String text) {
		JButton btn = new JButton(text);
		btn.setPreferredSize(new Dimension(125, 100));
		btn.setFocusPainted(false);
		btn.setBackground(new Color(58, 123, 213));
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial", Font.BOLD, 16));
		btn.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(30, 90, 180), 2),
			BorderFactory.createEmptyBorder(10, 10, 10, 10)
		));
		return btn;
	}
}
