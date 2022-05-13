package nsu.panova.Main.Tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class ExitButton extends JButton {

    public ExitButton(JButton button) {
        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());

        setText(button.getText());
        setFocusPainted(false);
        setBounds(240, 440, 200, BUTTON_SIZE);

        ActionListener actionListener = new ExitActionListener();
        addActionListener(actionListener);
    }

    public static class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
