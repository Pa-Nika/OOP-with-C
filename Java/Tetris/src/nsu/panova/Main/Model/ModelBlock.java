package nsu.panova.Main.Model;

import nsu.panova.Main.View.GameArea;

import java.awt.*;
import java.util.Objects;

import static nsu.panova.Main.Config.*;

public class ModelBlock {
    private final Block block;
    private final ModelArea model_game_area;
    private final GameArea game_area;
    private final Color[][] background;

    public ModelBlock (ModelArea area) {
        model_game_area = area;
        game_area = model_game_area.getGameArea();
        this.block = model_game_area.getBlock();
        background = model_game_area.getBackground();
    }

//    private boolean checkDown() {
//        if (block.getYOffset() + block.getHeight() == ROWS_COUNT) {
//            return false;
//        }
//
//        for (int i = 0; i < block.getHeight(); i++) {
//            for (int j = 0; j < block.getWidth(); j++) {
//                if (block.getBlock()[i * block.getWidth() + j] == 1 &&
//                        background[block.getYOffset() + block.getHeight() ][block.getXOffset() + j] != null) {
//
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

    private boolean checkLeft() {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getBlock()[i * block.getWidth() + j] == 1 && block.getXOffset() + j == 0) {
                    return false;
                }
                if (block.getYOffset() + i > 0) {
                    if (block.getBlock()[i * block.getWidth() + j] == 1 &&
                            background[block.getYOffset() + i ][block.getXOffset() + j - 1] != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkRight() {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getBlock()[i * block.getWidth() + j] == 1 && block.getXOffset() + j == COLUMN_COUNT - 1) {
                    return false;
                }

                if (block.getYOffset() + i > 0) {
                    if (block.getBlock()[i * block.getWidth() + j] == 1 &&
                            background[block.getYOffset() + i ][block.getXOffset() + j + 1] != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkUp() {
        int[][] type_of_round = block.getType();

        for (int i = 0; i < block.getWidth(); i++) {
            for (int j = 0; j < block.getHeight(); j++) {

                if (type_of_round[(block.getCurRound() + 1) % COUNT_ROTATION_FIGURES][i * block.getHeight() + j] == 1) {

                    if (block.getXOffset() + j + 1 > COLUMN_COUNT)
                        return false;

                    if (block.getXOffset() + j < 0)
                        return false;

                    if (i + block.getYOffset() >= ROWS_COUNT)
                        return false;

                    if (block.getYOffset() > 0 && background[i + block.getYOffset()][j + block.getXOffset()] != null)
                        return false;

                    for (int k = 0; k < j + 1; k++) {
                        for (int t = 0; t < j + 1; t++) {
                            if (block.getYOffset() > 0 && block.getXOffset() >= 0 && k + block.getYOffset() < ROWS_COUNT
                                    && background[k + block.getYOffset()][t + block.getXOffset()] != null) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public void moveBlockRight() {
        if (checkRight() ) {
            block.moveRight();
            game_area.redraw();
        }

    }

    public void moveBlockLeft() {
        if (checkLeft() ) {
            block.moveLeft();
            game_area.redraw();
        }
    }

    public void moveBlockDown(FormSupport form_support) {
        int flag = model_game_area.moveDown();
        if (flag > 1) {
            int all_score = form_support.getAllScore();
            int score = flag + all_score;
            form_support.fillScore(score);

            int all_level = form_support.getAllLevel();
            if (score / SCORES_FOR_LEVEL + 1 >= all_level) {
                form_support.fillLevel(score / SCORES_FOR_LEVEL + 1);
            }
        }
        game_area.redraw();
    }

    public void rotateBlock() {
        if (model_game_area.isGameOver() || !checkUp())
            return;

        int[] new_block = block.roundUpBlock();

        if (!Objects.equals(block.getBlockName(), "O") && block.getCurRound() % COUNT_BORDER_VALUES == SECOND_POSITION_OF_BORDER_VALUES) {
            model_game_area.setHeight(3);
            model_game_area.setWidth(4);
        } else {
            model_game_area.setHeight(4);
            model_game_area.setWidth(3);
        }

        model_game_area.setBlockArr(new_block);
        game_area.redraw();
    }
}
