package nsu.panova.Main.Tetris.About;

import nsu.panova.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class AboutButton extends JButton {

    public AboutButton(JButton button) {
        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());
        setText(button.getText());
        setFocusPainted(false);
        setBounds(240, 360, 200, BUTTON_SIZE);

        ActionListener actionListener = new AboutActionListener();
        addActionListener(actionListener);
    }


    public static class AboutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.about();
        }
    }

}
