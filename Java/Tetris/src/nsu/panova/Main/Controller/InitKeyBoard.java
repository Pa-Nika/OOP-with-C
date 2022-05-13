package nsu.panova.Main.Controller;

import nsu.panova.Main.Model.FormSupport;
import nsu.panova.Main.Model.ModelArea;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InitKeyBoard implements KeyListener {
    private final DefaultController controller;

    public InitKeyBoard(ModelArea area,FormSupport support) {
        controller = new DefaultController(area, support);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            controller.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            controller.moveDown();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            controller.roundUp();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
