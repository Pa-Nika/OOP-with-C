package nsu.panova.Main.View;

import nsu.panova.Main.Controller.DefaultController;
import nsu.panova.Main.Main;
import nsu.panova.Main.Model.FormSupport;
import nsu.panova.Main.Model.ModelArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.panova.Main.Config.BUTTON_SIZE;
import static nsu.panova.Main.Config.START_POSITION_OF_GAME_EL;

public class Back extends JButton {
    private final ModelArea model_game_area;
    private final FormSupport form_support;

    public Back(JButton button, ModelArea area, FormSupport support) {
        model_game_area = area;
        form_support = support;

        button.setVisible(false);
        setForeground(button.getForeground());
        setFont(button.getFont());
        setBackground(button.getBackground());
        setText(button.getText());
        setFocusPainted(false);
        setBounds(START_POSITION_OF_GAME_EL, 300, 135, BUTTON_SIZE);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultController controller = new DefaultController(model_game_area, form_support);
                controller.setThread(form_support.getGameThread());
                controller.stopGame();
                Main.back();
            }
        });
    }
}
