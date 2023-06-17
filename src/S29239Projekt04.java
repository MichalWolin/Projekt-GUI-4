import graphics.ScorePanel;
import graphics.SnakeView;
import logic.SnakeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class S29239Projekt04 {
    private JFrame currentFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(S29239Projekt04::new);
    }

    public S29239Projekt04() {
        mainMenu();
    }

    public void mainMenu() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        currentFrame = new JFrame();
        currentFrame.setLayout(new BorderLayout());
        currentFrame.setPreferredSize(new Dimension(300, 150));

        JPanel buttonPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
                String text = "Start Game";
                FontMetrics fontMetrics = g.getFontMetrics();
                int x = (getWidth() - fontMetrics.stringWidth(text)) / 2;
                int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                g.drawString(text, x, y);
            }
        };
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newGame();
            }
        });


        currentFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        currentFrame.pack();
        currentFrame.setLocationRelativeTo(null);
        currentFrame.setVisible(true);
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void newGame() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        currentFrame = new JFrame();
        currentFrame.setLayout(new BorderLayout());

        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);
        ScorePanel scorePanel = new ScorePanel();

        view.addDirectionListener(model);
        model.addScoreListener(scorePanel);

        currentFrame.getContentPane().add(scorePanel, BorderLayout.NORTH);
        currentFrame.getContentPane().add(view, BorderLayout.CENTER);

        currentFrame.pack();
        currentFrame.setLocationRelativeTo(null);
        currentFrame.setVisible(true);
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
