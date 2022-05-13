package nsu.panova.Main.Tetris;

import nsu.panova.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class NewGameButton extends JButton {

    NewGameButton(JButton button) {
        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());
        setText(button.getText());
        setFocusPainted(false);
        setBounds(240, 200, 200, BUTTON_SIZE);

        ActionListener actionListener = new NewGameActionListener();
        addActionListener(actionListener);
    }

    public static class NewGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.start();
        }
    }
}
