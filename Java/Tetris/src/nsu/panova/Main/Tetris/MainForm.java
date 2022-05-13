package nsu.panova.Main.Tetris;

import nsu.panova.Main.Tetris.About.AboutButton;
import nsu.panova.Main.Tetris.Leaders.LeaderboardButton;

import javax.swing.*;
import java.awt.*;

import static nsu.panova.Main.Config.*;

public class MainForm extends JFrame{
    private JButton NewGame;
    private JButton Leaderboard;
    private JButton About;
    private JButton Exit;
    private JPanel MainMenu;

    public MainForm() {
        setSize(SIZE_HEIGHT, 780);
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(MainMenu.getBackground());

        NewGameButton new_game = new NewGameButton(NewGame);
        LeaderboardButton leader_button = new LeaderboardButton(Leaderboard);
        AboutButton about_button = new AboutButton(About);
        ExitButton exit_button = new ExitButton(Exit);

        add(new_game);
        add(leader_button);
        add(about_button);
        add(exit_button);

        setLayout(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainForm main_form = new MainForm();
                main_form.setVisible(true);
            }
        });
    }
}
