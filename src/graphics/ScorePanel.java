package graphics;

import events.ScoreEvent;
import events.ScoreListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScorePanel extends JPanel implements ScoreListener {
    private int score;
    public ScorePanel() {
        this.setBackground(new Color(153, 255, 51));
        this.setPreferredSize(new Dimension(100, 30));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.score = 0;
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        g.drawString("Score: " + this.score, 10, 25);
    }

    @Override
    public void scoreChange(ScoreEvent e) {
        this.score = e.getScore();
        this.repaint();
    }
}
