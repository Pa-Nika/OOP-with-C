package nsu.panova.Main.View;

import nsu.panova.Main.Controller.InitKeyBoard;
import nsu.panova.Main.Model.FormSupport;
import nsu.panova.Main.Model.ModelArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static nsu.panova.Main.Config.*;

public class GameForm extends JFrame {
    private JPanel game_area_holder;
    private JLabel Score;
    private JLabel Level;
    private JButton Back;
    private final Score score_area;
    private final Level level_game;
    private final Back back_button;
    private final FormSupport form_support;

    public GameForm() {
        GameArea game_area = new GameArea(game_area_holder);
        ModelArea model_game_area = game_area.getModelArea();
        model_game_area.setGameArea(game_area);

        score_area = new Score(Score);
        level_game = new Level(Level);
        form_support = new FormSupport(model_game_area, game_area, score_area, level_game);
        back_button = new Back(Back, model_game_area, form_support);

        setSize(SIZE_WIDTH_WINDOW, SIZE_HEIGHT_WINDOW);
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(game_area.getBackground());

        add(game_area);
        add(score_area);
        add(level_game);
        add(back_button);

        setLayout(null);

        this.addKeyListener(new InitKeyBoard(model_game_area, form_support));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // Если мышь весит на JFrame
                requestFocusInWindow();
            }

            @Override
            public void mouseExited(MouseEvent e) { // И если выйдет с формы на любой другой компонент
                requestFocusInWindow();
            }
        });
    }


    public void startGame() {
        form_support.startGame();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameForm form = new GameForm();
                form.setVisible(true);
            }
        });
    }
}
