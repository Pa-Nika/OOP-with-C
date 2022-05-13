package nsu.panova.Main.Tetris.Leaders;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import static nsu.panova.Main.Config.*;

public class LeaderBoard extends JFrame {
    private JTable LeaderBoard;
    private JButton Back;
    private JPanel Panel;
    private JScrollPane ScrollPane;
    private JList Top;
    private final Table leader_table;

    private DefaultTableModel table_model;

    public LeaderBoard() {
        setSize(SIZE_WIDTH_WINDOW + 1, SIZE_HEIGHT_WINDOW);
        setTitle("Leaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(LeaderBoard.getBackground());

        leader_table = new Table(LeaderBoard);
        BackButtonLeaders back_button = new BackButtonLeaders(Back);
        TopPlayers list = new TopPlayers(Top);
        initModel();
        initSorter();

        add(list);
        add(leader_table);
        add(back_button);
        setLayout(null);
    }

    public void initModel() {
        table_model = (DefaultTableModel) leader_table.getModel();
        table_model.addColumn("Name");
        table_model.addColumn("Score");
        table_model.addColumn("Level");
        table_model.insertRow(0,  new Object[] { " Name", "Level" ,"Score" });

        try {
            Vector<String> data_vector = new Vector<String>();
            data_vector.add(" Name");
            data_vector.add(" Score");
            data_vector.add(" level");

            File file = new File("leaderboard.txt");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            table_model.setDataVector((Vector<? extends Vector<String>>) ois.readObject(), data_vector);
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void initSorter() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(LEVEL_COLUMN, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(SCORE_COLUMN, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        leader_table.setRowSorter(sorter);
    }

    public void newName(String name, int score) {
        if (table_model.getRowCount() == TOP_COUNT &&
                (int)table_model.getValueAt(table_model.getRowCount() - 1, SCORE_COLUMN) >= score) {
            return;
        }

        for (int i = 1; i < table_model.getRowCount(); i++) {
            if (table_model.getValueAt(i, NAME_COLUMN).equals(" " + name)) {
                if ((int)table_model.getValueAt(i, SCORE_COLUMN) >= score) {
                    return;
                }
                else {
                    table_model.removeRow(i);
                }
            }
        }
        table_model.addRow(new Object[] {" " + name, score / SCORES_FOR_LEVEL + 1, score});

        try {
            File file = new File("leaderboard.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(table_model.getDataVector());
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LeaderBoard leader_board = new LeaderBoard();
                leader_board.setVisible(true);
            }
        });
    }
}
