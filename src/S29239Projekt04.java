import events.CollisionEvent;
import events.CollisionListener;
import graphics.LeaderboardView;
import graphics.ScorePanel;
import graphics.SnakeView;
import logic.LeaderboardModel;
import logic.SnakeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class S29239Projekt04 implements CollisionListener {
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
        model.addCollisionListener(this);

        currentFrame.getContentPane().add(scorePanel, BorderLayout.NORTH);
        currentFrame.getContentPane().add(view, BorderLayout.CENTER);

        currentFrame.pack();
        currentFrame.setLocationRelativeTo(null);
        currentFrame.setVisible(true);
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void leaderboard(int score) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        currentFrame = new JFrame();
        currentFrame.setLayout(new BorderLayout());
//        currentFrame.setPreferredSize(new Dimension(500, 900));

        JPanel gameOverPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
                String text = "Game Over :(";
                FontMetrics fontMetrics = g.getFontMetrics();
                int x = (getWidth() - fontMetrics.stringWidth(text)) / 2;
                int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                g.drawString(text, x, y);
            }
        };
        gameOverPanel.setPreferredSize(new Dimension(500, 100));
        gameOverPanel.setLayout(new BorderLayout());
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        currentFrame.getContentPane().add(gameOverPanel, BorderLayout.NORTH);

        LeaderboardModel model = new LeaderboardModel(score);
        LeaderboardView view = new LeaderboardView(model);

        currentFrame.getContentPane().add(view, BorderLayout.CENTER);

        JPanel playAgainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
                String text = "Play Again";
                FontMetrics fontMetrics = g.getFontMetrics();
                int x = (getWidth() - fontMetrics.stringWidth(text)) / 2;
                int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                g.drawString(text, x, y);
            }
        };
        playAgainPanel.setPreferredSize(new Dimension(500, 100));
        playAgainPanel.setLayout(new BorderLayout());
        playAgainPanel.setBackground(Color.BLACK);
        playAgainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        playAgainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newGame();
            }
        });

        currentFrame.getContentPane().add(playAgainPanel, BorderLayout.SOUTH);

        currentFrame.pack();
        currentFrame.setLocationRelativeTo(null);
        currentFrame.setVisible(true);
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void collisionOccurred(CollisionEvent e) {
        leaderboard(e.getScore());
    }
}
