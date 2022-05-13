package nsu.panova.Main.Tetris.Leaders;

import javax.swing.*;
import java.util.Vector;

import static nsu.panova.Main.Config.BUTTON_SIZE;

public class TopPlayers extends JList {

    public TopPlayers(JList top) {
        Vector<Integer> arr = new Vector<Integer>();
        for (int i = 0; i < 10; i++) {
            arr.add(i + 1);
        }
        setListData(arr);

        setLayoutOrientation(JList.VERTICAL);
        setVisible(true);
        setBounds(BUTTON_SIZE, 160, BUTTON_SIZE, 385);
        setFont(top.getFont());
        setForeground(top.getForeground());
        setBackground(top.getBackground());
        setVisibleRowCount(top.getVisibleRowCount());
    }

}
