import gui.MainFrame;

import java.awt.EventQueue;
import java.io.IOException;

public class StartApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            try {
                new MainFrame2();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
