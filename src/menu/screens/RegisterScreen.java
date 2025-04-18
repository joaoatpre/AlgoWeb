package menu.screens;

import javax.swing.*;
import menu.MenuManager;
import java.awt.*;
import connection.Authenticate;

public class RegisterScreen extends JPanel {
	private static final long serialVersionUID = -3891710441290297343L;

	public RegisterScreen(MenuManager manager) {
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
		JLabel confirmPasswordLabel = new JLabel("Confirmar Senha:");
		confirmPasswordLabel.setFont(confirmPasswordLabel.getFont().deriveFont(15f));
		JPasswordField confirmPasswordField = new JPasswordField(15);

		add(usernameLabel, gbc);
		gbc.gridx = 1;
		add(usernameField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		add(passwordLabel, gbc);
		gbc.gridx = 1;
		add(passwordField, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		add(confirmPasswordLabel, gbc);
		gbc.gridx = 1;
		add(confirmPasswordField, gbc);

		JButton submitButton = new JButton("Registrar");
		JButton backButton = new JButton("Voltar");

		submitButton.setFocusPainted(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setOpaque(true);
		submitButton.setBackground(new Color(58, 123, 237));
		submitButton.setForeground(Color.WHITE);

		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setOpaque(true);
		backButton.setBackground(new Color(58, 123, 237));
		backButton.setForeground(Color.WHITE);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		buttonPanel.add(submitButton);
		buttonPanel.add(backButton);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		add(buttonPanel, gbc);

		submitButton.addActionListener(_ -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			String confirmPassword = new String(confirmPasswordField.getPassword());
			
			if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Favor, Preencher todos os campos.", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(null, "As senhas não são iguais.", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (Authenticate.register(username, password)) {
		        JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
		        manager.renderScreen("login");
		    } else {
		        JOptionPane.showMessageDialog(null, "Erro ao registrar. Usuário já existe.", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
		    }
		});

		backButton.addActionListener(_ -> {
			manager.renderScreen("login");
		});
	}
}
