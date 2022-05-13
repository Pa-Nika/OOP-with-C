package nsu.panova.Main.Tetris.Leaders;

import nsu.panova.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class LeaderboardButton extends JButton {

    public LeaderboardButton(JButton button) {
        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());

        setText(button.getText());
        setFocusPainted(false);
        setBounds(240, 280, 200, BUTTON_SIZE);

        ActionListener actionListener = new LeaderActionListener();
        addActionListener(actionListener);
    }


    public static class LeaderActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.leaders();
        }
    }
}
