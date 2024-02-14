package config;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyHeaderRenderer implements TableCellRenderer {
    JLabel label;

    public MyHeaderRenderer(Font font) {
        label = new JLabel();
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setText(value.toString());
        return label;
    }
}
