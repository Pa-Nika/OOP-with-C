package nsu.panova.Main.Tetris.About;

import nsu.panova.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class BackButtonAbout extends JButton {

    public BackButtonAbout(JButton button) {
        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());

        setText(button.getText());
        setFocusPainted(false);
        setBounds(240, 360, 200, BUTTON_SIZE);

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
