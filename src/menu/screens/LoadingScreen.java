package menu.screens;

import javax.swing.*;
import menu.MenuManager;
import utils.Config;
import connection.VerifyServer;
import java.awt.*;

public class LoadingScreen extends JPanel {
    private static final long serialVersionUID = 429493011714209552L;
    private Timer t1;
    private Timer t2;
    private Timer t3;
    
    private JLabel title1;
    private JLabel title2;
    
    private Spinner spinner1;
    private Spinner spinner2;

    @SuppressWarnings("unused")
    public LoadingScreen(MenuManager manager) {
        setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel line1 = new JPanel(new GridBagLayout());
        title1 = new JLabel("Estabelecendo conexão...");
        title1.setFont(title1.getFont().deriveFont(16f));
        this.spinner1 = new Spinner();
        line1.add(this.spinner1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
        line1.add(title1, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        content.add(line1);

        JPanel line2 = new JPanel(new GridBagLayout());
        content.add(line2);

        JPanel line3 = new JPanel(new GridBagLayout());
        content.add(line3);

        add(content);

        t1 = new Timer(1000, _ -> {
        	if (!VerifyServer.isOnline()) {
        		t1.stop();
        		line1.remove(this.spinner1);
        		line1.add(new XIcon(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
                line1.revalidate();
                line1.repaint();
                
        		title1.setText("Falha ao conectar.");
        		
        		new Timer(2500, __ -> {
        			System.exit(0);
        		}).start();
        		return;
        	}
        	
            line1.remove(this.spinner1);
            line1.add(new CheckIcon(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            line1.revalidate();
            line1.repaint();

            title1.setText("Conexão estabelecida.");
            title2 = new JLabel("Verificando versão...");
            title2.setFont(title2.getFont().deriveFont(16f));
            this.spinner2 = new Spinner();
            line2.add(this.spinner2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            line2.add(title2, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            content.revalidate();
            content.repaint();

            t1.stop();
            t2.start();
        });

        t2 = new Timer(2500, _ -> {
        	if (!VerifyServer.getVersion().equals(Config.APP_VERSION)) {
        		t2.stop();
        		line2.remove(this.spinner2);
        		line2.add(new XIcon(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
                line2.revalidate();
                line2.repaint();
                
        		title2.setText("Versão desatualizada.");
        		
        		new Timer(2500, __ -> {
        			System.exit(0);
        		}).start();
        		return;
        	}
        	
            line2.remove(this.spinner2);
            line2.add(new CheckIcon(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            line2.revalidate();
            line2.repaint();

            title2.setText("Versão atualizada.");

            t2.stop();
            t3.start();
        });

        t3 = new Timer(2000, ___ -> {
        	t3.stop();
        	
        	manager.renderScreen("login");
        });
        

        t1.setRepeats(false);
        t2.setRepeats(false);
        t3.setRepeats(false);
        
        t1.start();
    }

    private static class Spinner extends JPanel {
        private static final long serialVersionUID = -695842773039126519L;
        private double ballAngle = 0;
        private int ballSpeed = 20;
        private int ballMaxSpeed = 30;
        private int ballMinSpeed = 10;
        private int speedChange;
        private boolean incressingSpeed = true;
        private Timer timer;

        public Spinner() {
            setPreferredSize(new Dimension(30, 30));
            speedChange = 1;
            if (ballSpeed >= ballMaxSpeed - 5 || ballSpeed <= ballMinSpeed + 5)
                speedChange = 3;
            timer = new Timer(ballSpeed, _ -> {
                ballAngle += 0.1;
                ballSpeed = incressingSpeed ? Math.min(ballMaxSpeed, ballSpeed + speedChange)
                        : Math.max(ballMinSpeed, ballSpeed - speedChange);
                if (ballSpeed == ballMaxSpeed || ballSpeed == ballMinSpeed)
                    incressingSpeed = !incressingSpeed;
                timer.setDelay(ballSpeed);
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int cx = getWidth() / 2, cy = getHeight() / 2, radius = 10;
            int x = (int) (cx + Math.cos(ballAngle) * radius);
            int y = (int) (cy + Math.sin(ballAngle) * radius);
            g2.setColor(Color.BLUE);
            g2.fillOval(x - 5, y - 5, 10, 10);
        }
    }

    private static class CheckIcon extends JPanel {
        private static final long serialVersionUID = -6707430937638385893L;

        public CheckIcon() {
            setPreferredSize(new Dimension(30, 30));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.BLACK);
            g2.drawLine(7, 13, 12, 20);
            g2.drawLine(12, 20, 22, 8);

            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.GREEN);
            g2.drawLine(7, 13, 12, 20);
            g2.drawLine(12, 20, 22, 8);
        }
    }
    
    private static class XIcon extends JPanel {
        private static final long serialVersionUID = -6707430937638385893L;

        public XIcon() {
            setPreferredSize(new Dimension(30, 30));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.RED);
            g2.drawLine(7, 7, 22, 22);
            g2.drawLine(22, 7, 7, 22);
        }
    }
}
