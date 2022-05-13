package nsu.panova.Main.Model;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static nsu.panova.Main.Config.*;

public class Block {
    private Color color;
    private int[] block;
    private int x_offset, y_offset;
    private int curr_round = 0;
    private int[][] type_of_round;

    private int height;
    private int width;

    private final Properties properties;
    private String block_name;

    public Block(int color_num) {
        block = new int[COUNT_EL_IN_BLOCK_ARR];
        properties = new Properties();
        x_offset = (COLUMN_COUNT / 2) - (COUNT_WIDTH_BLOCK_IN_SHAPE / 2);
        y_offset = START_Y_OFFSET;

        switch (color_num) {
            case (0) -> {
                color = Color.blue;
                block_name = "O";
            }
            case (1) -> {
                color = Color.red;
                block_name = "l";
            }
            case (2) -> {
                color = Color.green;
                block_name = "S";
            }
            case (3) -> {
                color = Color.magenta;
                block_name = "Z";
            }
            case (4) -> {
                color = Color.yellow;
                block_name = "L";
            }
            case (5) -> {
                color = Color.orange;
                block_name = "J";
            }
            case (6) -> {
                color = Color.cyan;
                block_name = "T";
            }
        }

        loadBlock();

        height = 4;
        width = 3;
        initTypeArr();
    }

    private void initTypeArr() {
        type_of_round = new int[COUNT_ROTATION_FIGURES][COUNT_EL_IN_BLOCK_ARR];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                type_of_round[0][x * width + y] = block[x * width + y];
                if (Objects.equals(block_name, "O")) {
                    type_of_round[1] = type_of_round[2] = type_of_round[3] = type_of_round[0];
                }
                if (Objects.equals(block_name, "l") || Objects.equals(block_name, "Z") ||
                        Objects.equals(block_name, "S")) {
                    type_of_round[2] = type_of_round[0];
                }
            }
        }

        if (Objects.equals(block_name, "O"))
            return;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                type_of_round[1][x * height + (height - 1 - y)] =
                        type_of_round[0][y * width + x];
                if (Objects.equals(block_name, "l") || Objects.equals(block_name, "Z") ||
                        Objects.equals(block_name, "S")) {
                    type_of_round[3] = type_of_round[1];
                }
            }
        }

        if (Objects.equals(block_name, "l") || Objects.equals(block_name, "Z") ||
                Objects.equals(block_name, "S"))
            return;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                type_of_round[2][y * width + x] =
                        type_of_round[0][(height - y - 1) * width + (width - x - 1)];
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                type_of_round[3][x * height + y] =
                        type_of_round[0][y * width + (width - x - 1)];
            }
        }
    }

    private void loadBlock() {
        try {
            InputStream inputStream = Block.class.getResourceAsStream("block.properties");
            properties.load(inputStream);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String conf = properties.getProperty(block_name);
        String[] numbers = conf.split("\\s");

        int cur = 0;
        for (String number : numbers) {
            if (Objects.equals(number, "|"))
                continue;
            if (Objects.equals(number, "0")) {
                block[cur] = 0;
                cur++;
            }
            if (Objects.equals(number, "1")) {
                block[cur] = 1;
                cur++;
            }
        }
    }


    public void moveDown() {
        y_offset++;
    }
    public void moveLeft() {
        x_offset--;
    }
    public void moveRight() {
        x_offset++;
    }
    public int[] roundUpBlock() {
        curr_round++;
        block = type_of_round[curr_round % COUNT_ROTATION_FIGURES];

        if (!Objects.equals(block_name, "O")) {
            if (curr_round % 2 == 1) {
                height = 3;
                width = 4;
            } else {
                height = 4;
                width = 3;
            }
        }

        return block;
    }

    public int[] getBlock() {
        return block;
    }
    public Color getColor() {
        return color;
    }
    public int getXOffset() {
        return x_offset;
    }
    public int getYOffset() {
        return y_offset;
    }
    public String getBlockName() {
        return block_name;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getCurRound() {
        return curr_round;
    }
    public int[][] getType() {
        return type_of_round;
    }
}
