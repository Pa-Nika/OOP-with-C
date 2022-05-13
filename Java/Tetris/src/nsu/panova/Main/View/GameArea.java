package nsu.panova.Main.View;

import nsu.panova.Main.Model.ModelArea;
import nsu.panova.Main.Model.Block;

import javax.swing.*;
import java.awt.*;

import static nsu.panova.Main.Config.*;

public class GameArea extends JPanel implements View{
    private final int size_of_cell;
    private final ModelArea model_game_area;
    private Block block_;
    private int height;
    private int wight;

    public GameArea(JPanel holder) {
        holder.setVisible(false);
        size_of_cell = SIZE_HEIGHT / ROWS_COUNT;
        setBounds(size_of_cell, size_of_cell, SIZE_WIDTH_GAME + 1, SIZE_HEIGHT + 1);
        setBackground(holder.getBackground());

        model_game_area = new ModelArea();
        block_ = model_game_area.newBlock();
        height = model_game_area.getHeight();
        wight = model_game_area.getWidth();
    }

    @Override
    public void drawBlock(Graphics g) {
        block_ = model_game_area.getBlock();
        int[] block = block_.getBlock();
        height = model_game_area.getHeight();
        wight = model_game_area.getWidth();

        for (int i = 0; i < wight; i++) {
            for (int j = 0; j < height; j++) {
                if (block[j * wight + i] == 1) {
                    g.setColor(block_.getColor());
                    g.fillRect((i + block_.getXOffset()) * size_of_cell,
                            (j + block_.getYOffset()) * size_of_cell,
                            size_of_cell, size_of_cell);

                    g.setColor(Color.lightGray);
                    g.drawRect((i + block_.getXOffset()) * size_of_cell,
                            (j + block_.getYOffset()) * size_of_cell,
                            size_of_cell, size_of_cell);
                }
            }
        }
    }

    @Override
    public void drawBackground(Graphics g) {
        Color[][] background = model_game_area.getBackground();
        Color color;
        for (int i = 0; i < COLUMN_COUNT; i++) {
            for (int j = 0; j < ROWS_COUNT; j++) {
                color = background[j][i];

                if (color != null) {
                    g.setColor(color);
                    g.fillRect(i * size_of_cell, j * size_of_cell, size_of_cell, size_of_cell);

                    g.setColor(Color.lightGray);
                    g.drawRect(i * size_of_cell, j * size_of_cell, size_of_cell, size_of_cell);
                }
            }
        }
    }

    @Override
    public void redraw() {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < COLUMN_COUNT; i++) {
            for (int j = 0; j < ROWS_COUNT; j++) {
                g.setColor(Color.lightGray);
                g.drawRect(i * size_of_cell, j * size_of_cell, size_of_cell, size_of_cell);
            }
        }

        drawBackground(g);
        drawBlock(g);
    }

    public ModelArea getModelArea() { return model_game_area; }
}
