import graphics.ScorePanel;
import graphics.SnakeView;
import logic.SnakeModel;

import javax.swing.*;
import java.awt.*;

public class S29239Projekt04 extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                S29239Projekt04::new
        );
    }

    public S29239Projekt04() {
        this.setLayout(new BorderLayout());

        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);
        ScorePanel scorePanel = new ScorePanel();

        view.addDirectionListener(model);
        model.addScoreListener(scorePanel);

        this.getContentPane().add(scorePanel, BorderLayout.NORTH);
        this.getContentPane().add(view, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
