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
            case 0 -> component.setBackground(new Color(153, 255, 51));
            case 1 -> component.setBackground(new Color(62, 115, 168));
            case 2 -> component.setBackground(new Color(86, 171, 224));
            case 3 -> component.setBackground(Color.RED);
        }

        if(hasFocus){
            ((JComponent) component).setBorder(BorderFactory.createEmptyBorder());
        }

        return component;
    }

    @Override
    protected void setValue(Object value){
        setText("");
    }
}
