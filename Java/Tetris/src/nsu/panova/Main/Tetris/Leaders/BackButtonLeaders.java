package nsu.panova.Main.Tetris.Leaders;

import nsu.panova.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class BackButtonLeaders extends JButton {
    public BackButtonLeaders(JButton button) {
        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());
        setText(button.getText());
        setFocusPainted(false);
        setBounds(240, 556, 200, BUTTON_SIZE);
        setLayout(null);

        ActionListener actionListener = new BackActionListener();
        addActionListener(actionListener);
    }


    public static class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.back();
        }
    }

}
