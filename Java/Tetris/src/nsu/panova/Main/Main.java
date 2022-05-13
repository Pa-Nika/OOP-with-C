package nsu.panova.Main;

import nsu.panova.Main.Tetris.About.AboutForm;
import nsu.panova.Main.View.GameForm;
import nsu.panova.Main.Tetris.Leaders.LeaderBoard;
import nsu.panova.Main.Tetris.MainForm;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static GameForm game_form;
    private static MainForm main_form;
    private static AboutForm about_form;
    private static LeaderBoard leader_form;

    public static void start() {
        main_form.dispose();
        game_form.setVisible(true);
        game_form.startGame();
    }

    public static void about() {
        about_form.setVisible(true);
        main_form.dispose();
    }

    public static void back() {
        main_form.setVisible(true);
        about_form.dispose();
        game_form.dispose();
        leader_form.dispose();
    }

    public static void leaders() {
        leader_form.setVisible(true);
        main_form.dispose();
    }

    public static void gameOver(int score) {
        String name = JOptionPane.showInputDialog("Game over... What is your name?");
        leader_form.newName(name, score);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                game_form = new GameForm();
                main_form = new MainForm();
                about_form = new AboutForm();
                leader_form = new LeaderBoard();

                main_form.setVisible(true);
            }
        });
    }
}
