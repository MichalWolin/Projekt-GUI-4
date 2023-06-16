package graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class SnakeCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {

        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int cellValue = (int) value;
        switch (cellValue) {
            case 0 -> component.setBackground(Color.GREEN);
            case 1 -> component.setBackground(Color.BLUE);
            case 2 -> component.setBackground(Color.BLACK);
            case 3 -> component.setBackground(Color.RED);
            default -> component.setBackground(table.getBackground());
        }

        return component;
    }

    @Override
    protected void setValue(Object value){
        setText("");
    }
}
