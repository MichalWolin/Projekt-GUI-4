package graphics;

import javax.swing.*;
import java.awt.*;
import logic.*;

public class SnakeView extends JTable{
    public SnakeView() {
        super(new SnakeModel());
        this.setRowHeight(20);
        for (int i = 0; i < 16; i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(20);
            this.getColumnModel().getColumn(i).setMinWidth(20);
            this.getColumnModel().getColumn(i).setMaxWidth(20);
        }

        this.setEnabled(false);
        this.setAutoResizeMode(AUTO_RESIZE_OFF);
        this.setGridColor(Color.BLACK);

        this.setDefaultRenderer(Object.class, new SnakeCellRenderer());
    }
}


