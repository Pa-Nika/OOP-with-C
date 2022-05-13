package nsu.panova.Main.View;

import javax.swing.*;

import static nsu.panova.Main.Config.START_POSITION_OF_GAME_EL;

public class Level extends JLabel {
    public Level(JLabel level) {
        level.setVisible(false);
        setText(level.getText());
        setFont(level.getFont());
        setForeground(level.getForeground());
        setBounds(START_POSITION_OF_GAME_EL, 100, 500, 300);
    }
}
