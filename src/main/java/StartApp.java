import gui.*;
import java.awt.EventQueue;


public class StartApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new StartFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
