package Render;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class RenderComboBox extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (isSelected) {
            renderer.setBackground(new Color(0, 0, 255)); // Azul
            renderer.setForeground(Color.WHITE);
        } else {
            renderer.setBackground(Color.WHITE);
            renderer.setForeground(Color.BLACK);
        }

        return renderer;
    }
}
