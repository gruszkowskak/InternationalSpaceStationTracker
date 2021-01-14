import gui.MainFrame;
import gui.MainFrame2;
import gui.MainFrame;

import java.awt.EventQueue;
import java.io.IOException;

public class StartApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new MainFrame();
//                new MainFrame2();
                //new PassTime_Frame();
                //new Astronauts_Frame();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
