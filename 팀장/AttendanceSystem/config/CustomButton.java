package config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(new Font("Inter", Font.PLAIN, 18));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
    }
    public void customizeButton(JButton button) {
        button.setBackground(new Color(255, 255, 255));
        button.setFont(new Font("Inter", Font.PLAIN, 18));
        button.setForeground(Color.black);
        button.setBorderPainted(false);
    }
    
    public static JButton createButton(String text, Color background, Color foreground, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setFont(new Font("Inter", Font.PLAIN, 18));
        button.setForeground(foreground);
        button.setBorderPainted(false);
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        return button;
    }
}
