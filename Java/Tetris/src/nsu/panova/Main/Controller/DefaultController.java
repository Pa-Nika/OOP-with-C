package nsu.panova.Main.Controller;

import nsu.panova.Main.Model.FormSupport;
import nsu.panova.Main.Model.GameThread;
import nsu.panova.Main.Model.ModelArea;
import nsu.panova.Main.Model.ModelBlock;

public class DefaultController implements Controller{
    private final ModelArea model_game_area;
    private final FormSupport form_support;
    private GameThread game_thread;

    public DefaultController(ModelArea model_area, FormSupport support) {
        model_game_area = model_area;
        form_support = support;
    }

    @Override
    public void moveLeft() {
        new ModelBlock(model_game_area).moveBlockLeft();
    }

    @Override
    public void moveRight() {
        new ModelBlock(model_game_area).moveBlockRight();
    }

    @Override
    public void moveDown() {
        new ModelBlock(model_game_area).moveBlockDown(form_support);
    }

    @Override
    public void roundUp() {
        new ModelBlock(model_game_area).rotateBlock();
    }

    @Override
    public void stopGame() {
        game_thread.interrupt();
    }

    public void setThread(GameThread thread) {
        game_thread = thread;
    }
}
