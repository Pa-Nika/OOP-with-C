package nsu.panova.Main.Model;

import nsu.panova.Main.View.GameArea;
import nsu.panova.Main.View.Level;
import nsu.panova.Main.View.Score;

public class FormSupport {
    private final ModelArea model_game_area;
    private final GameArea game_area;
    private final Score score_area;
    private final Level level_game;
    private GameThread game_thread;

    private int all_score = 0;
    private int all_level = 1;

    public FormSupport(ModelArea m_area, GameArea g_area, Score score, Level level) {
        model_game_area = m_area;
        game_area = g_area;
        score_area = score;
        level_game = level;
    }

    public void startGame() {
        model_game_area.startInitBackground();
        game_thread = new GameThread(model_game_area, game_area, this);
        game_thread.start();
    }


    public void fillScore(int score) {
        all_score = score;
        score_area.setText("Score: " + score);
    }

    public void fillLevel(int level) {
        all_level = level;
        level_game.setText("Level: " + level);
    }

    public int getAllScore() {
        return all_score;
    }
    public int getAllLevel() {
        return all_level;
    }
    public GameThread getGameThread() {
        return game_thread;
    }
}
