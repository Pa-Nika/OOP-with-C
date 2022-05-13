package nsu.panova.Main.Tetris.Leaders;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class Table extends JTable {

    public Table(JTable table) {
        table.setVisible(false);
        setBounds(80, 120, 550, 11 * table.getRowHeight() + 1);
        setBackground(table.getBackground());
        setForeground(table.getForeground());
        setGridColor(table.getGridColor());
        setRowHeight(table.getRowHeight());
        setFont(table.getFont());
        setBorder(new LineBorder(Color.lightGray));
        setShowGrid(true);
        setAutoCreateRowSorter(true);

    }
}
