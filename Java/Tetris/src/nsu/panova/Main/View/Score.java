package nsu.panova.Main.View;

import javax.swing.*;

import static nsu.panova.Main.Config.START_POSITION_OF_GAME_EL;

public class Score extends JLabel {
    public Score(JLabel text) {
        text.setVisible(false);
        setText(text.getText());
        setFont(text.getFont());
        setForeground(text.getForeground());
        setBounds(START_POSITION_OF_GAME_EL, 50, 500, 300);
    }
}
