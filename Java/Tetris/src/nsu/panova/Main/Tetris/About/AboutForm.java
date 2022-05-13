package nsu.panova.Main.Tetris.About;

import javax.swing.*;

import static nsu.panova.Main.Config.*;

public class AboutForm extends JFrame {
    private JPanel AboutPanel;
    private JLabel Text;
    private JButton Back;

    public AboutForm() {
        setSize(SIZE_WIDTH_WINDOW, SIZE_HEIGHT_WINDOW);
        setTitle("About");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(AboutPanel.getBackground());

        AboutText about_text = new AboutText(Text);
        BackButtonAbout back_button = new BackButtonAbout(Back);

        add(about_text);
        add(back_button);

        setLayout(null);
    }

}
