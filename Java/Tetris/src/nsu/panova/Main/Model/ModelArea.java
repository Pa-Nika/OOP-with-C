package nsu.panova.Main.Model;

import nsu.panova.Main.View.GameArea;

import java.awt.*;

import static nsu.panova.Main.Config.*;

public class ModelArea {
    private static final int COUNT_FIGURES = 7;
    private int[] block;
    private Block block_;
    private int height;
    private int width;
    private GameArea game_area;

    private Color[][] background;

    public ModelArea() {
        newBlock();
    }

    public void startInitBackground() {
        background = new Color[ROWS_COUNT][COLUMN_COUNT];
    }

    public Block newBlock() {
        double color_num = Math.random() * COUNT_FIGURES;
        block_ = new Block((int)color_num);
        //block_ = new Block(4);
        block = block_.getBlock();
        height = 4;
        width = 3;
        return block_;
    }

    public int moveDown() {
        int count_of_null_block_el = 0;
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (block[i * height + j] == 0)
                    count_of_null_block_el++;
            }
        }
        if (count_of_null_block_el == COUNT_EL_IN_BLOCK_ARR) {
            return 0;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (block_.getYOffset() >= 0 && block[i * width + j] == 1
                        && block_.getYOffset() + i + 1 == ROWS_COUNT) {
                    fillBackground();
                    return cleanLine();
                }
                if (block[i * width + j] == 1 && block_.getYOffset() + i >= 0 &&
                        background[block_.getYOffset() + i + 1][block_.getXOffset() + j] != null) {
                    fillBackground();
                    return cleanLine();
                }
            }
        }

        block_.moveDown();
        return 1;
    }

    public int cleanLine() {
        boolean line_flag;
        int count_clean_lines = 0;
        int score = 0;

        for (int i = 0; i < ROWS_COUNT; i++) {
            line_flag = true;
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (background[i][j] == null) {
                    line_flag = false;
                    break;
                }
            }

            if (line_flag) {
                count_clean_lines++;
                for (int x = i; x > 0; x--) {
                    System.arraycopy(background[x - 1], 0, background[x], 0, COLUMN_COUNT);
                }
                for(int x = 0; x < width; x ++) {
                    for (int y = 0; y < height; y++) {
                        block[x * height + y] = 0;
                    }
                }
            }
        }

        if (count_clean_lines != 0) {
            score += SCORE_FOR_LINE * count_clean_lines;
            score += SCORE_FOR_LINE * (count_clean_lines - 1);
        }

        return score;
    }

    private void fillBackground() {
        Color color_back = block_.getColor();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (block[j * width + i] == 1 && j + block_.getYOffset() >= 0) {
                    background[j + block_.getYOffset()][i + block_.getXOffset()] = color_back;
                }
            }
        }
    }

    public boolean isGameOver() {
        boolean flag = false;
        for (int i = 0; i < COLUMN_COUNT; i++) {
            if (background[0][i] != null) {
                flag = true;
                break;
            }
        }
        return flag;
    }



    public Block getBlock() {
        return block_;
    }
    public void setBlockArr(int[] new_block) {
        block = new_block;
    }
    public Color[][] getBackground() {
        return background;
    }
    public GameArea getGameArea() {
        return game_area;
    }
    public int getHeight() { return height; }
    public int getWidth() {return width; }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setGameArea(GameArea area){ this.game_area = area;}
}
