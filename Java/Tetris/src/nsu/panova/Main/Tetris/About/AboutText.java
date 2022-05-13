package nsu.panova.Main.Tetris.About;

import javax.swing.*;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class AboutText extends JLabel {

    public AboutText(JLabel text) {
        text.setVisible(false);
        setText("Panova Veronica lab3");
        setFont(text.getFont());
        setForeground(text.getForeground());
        setBounds(200, 280, 300, BUTTON_SIZE);
    }
}
