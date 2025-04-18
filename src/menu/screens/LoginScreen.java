package menu.screens;

import javax.swing.*;
import menu.MenuManager;
import java.awt.*;
import connection.Authenticate;

public class LoginScreen extends JPanel {
	private static final long serialVersionUID = 902316457262544582L;

	public LoginScreen(MenuManager manager) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;

		JLabel usernameLabel = new JLabel("Usuário:");
		usernameLabel.setFont(usernameLabel.getFont().deriveFont(15f));
		JTextField usernameField = new JTextField(15);
		JLabel passwordLabel = new JLabel("Senha:");
		passwordLabel.setFont(passwordLabel.getFont().deriveFont(15f));
		JPasswordField passwordField = new JPasswordField(15);

		add(usernameLabel, gbc);
		gbc.gridx = 1;
		add(usernameField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		add(passwordLabel, gbc);
		gbc.gridx = 1;
		add(passwordField, gbc);

		JButton submitButton = new JButton("Entrar");
		JButton registerButton = new JButton("Registrar");

		submitButton.setFocusPainted(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setOpaque(true);
		submitButton.setBackground(new Color(58, 123, 237));
		submitButton.setForeground(Color.WHITE);

		registerButton.setFocusPainted(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setOpaque(true);
		registerButton.setBackground(new Color(58, 123, 237));
		registerButton.setForeground(Color.WHITE);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		buttonPanel.add(submitButton);
		buttonPanel.add(registerButton);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		add(buttonPanel, gbc);

		submitButton.addActionListener(_ -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Favor, Preencher todos os campos.", "Falha na autenticação", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (Authenticate.login(username, password)) {
				manager.renderScreen("main");
			} else {
				JOptionPane.showMessageDialog(null, "Credenciais inválidas.", "Falha na autenticação", JOptionPane.ERROR_MESSAGE);
			}
		});

		registerButton.addActionListener(_ -> {
			manager.renderScreen("register");
		});
	}
}
