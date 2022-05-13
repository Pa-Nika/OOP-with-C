package nsu.panova.Main.Model;

import nsu.panova.Main.Main;
import nsu.panova.Main.View.GameArea;

import static nsu.panova.Main.Config.*;

public class GameThread extends Thread{
    private final ModelArea model_game_area;
    private final GameArea game_area;
    private final FormSupport form_support;
    private int score = 0;
    private int level = 1;

    public GameThread(ModelArea model_game_area, GameArea game_area, FormSupport support) {
        this.model_game_area = model_game_area;
        this.game_area = game_area;
        form_support = support;

        form_support.fillScore(score);
        form_support.fillLevel(level);
    }

    @Override
    public void run() {
        int  flag = 0;

        while(true) {
            if (flag != 0) {
                int sleep_sec = MAX_SLOW_SPEED - form_support.getAllLevel() * SPEED_INCREASE;

                try {
                    Thread.sleep(sleep_sec);
                } catch (InterruptedException e) {
                    return;
                }

                flag = model_game_area.moveDown();
                if (flag > 1) {
                    score = form_support.getAllScore();
                    score += flag;
                    form_support.fillScore(score);

                    level = form_support.getAllLevel();
                    if (score / SCORES_FOR_LEVEL + 1 >= level) {
                        level = score / SCORES_FOR_LEVEL + 1;
                        form_support.fillLevel(level);
                    }
                }
                game_area.redraw();
                if (model_game_area.isGameOver()) {
                    Main.gameOver(form_support.getAllScore());
                    break;
                }
            } else {
                model_game_area.newBlock();
                flag = 1;
            }
        }
    }
}
