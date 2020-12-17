
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("ISS tracker");

        JPanel worldMapPanel = new WorldMapPanel();
        add(worldMapPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}