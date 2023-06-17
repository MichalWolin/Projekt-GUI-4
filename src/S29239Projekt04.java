import graphics.SnakeView;
import logic.SnakeModel;

import javax.swing.*;

public class S29239Projekt04 extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                S29239Projekt04::new
        );
    }

    public S29239Projekt04() {
        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);

        view.addDirectionListener(model);

        this.getContentPane().add(view);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
