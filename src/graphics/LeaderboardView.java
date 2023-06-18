package graphics;

import logic.LeaderboardModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class LeaderboardView extends JTable {
    public LeaderboardView(LeaderboardModel leaderboardModel) {
        super(leaderboardModel);
        this.setRowHeight(50);

        this.setAutoResizeMode(AUTO_RESIZE_OFF);
        this.setGridColor(Color.WHITE);
        this.setEnabled(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setBackground(Color.BLACK);
        centerRenderer.setForeground(Color.WHITE);
        this.setDefaultRenderer(Object.class, centerRenderer);

        Font font = new Font("Comic Sans MS", Font.PLAIN, 20);
        this.setFont(font);
    }
}
