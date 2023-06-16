import graphics.SnakeView;
import logic.UserInput;

import javax.swing.*;

public class S29239Projekt04 extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                S29239Projekt04::new
        );
    }

    public S29239Projekt04() {
        SnakeView view = new SnakeView();

        this.getContentPane().add(view);

        this.setSize(335, 538);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
