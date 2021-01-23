package gui;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    public AboutPanel(){
        super();
        setLayout(new BorderLayout());
        add(new JLabel("<html>Authors: <br/><br/>" +
                "    Klaudia Gruszkowska<br/>" +
                "    Bartosz Jamroży<br/>" +
                "    Agata Kaczmarek<br/><br/><br/>" +
                "Ten projekt został stworzony w ramach projektu na studiach z przedmiotu Zaawansowane Programowanie Obiektowe i Funkcyjne." +
                " Aplikacja przedstawia aktualne położenie Międzynarodowej Stacji Kosmicznej na mapie świata. " +
                "Dane dotyczące położenia pobierany za pomocą publicznie dostępnego API ze strony http://open-notify.org/Open-Notify-API/ISS-Location-Now/." +
                "Dodatkowo aplikacja posiada zakładkę Astronauts, któa wyświetla astronautów aktualnie przebywających w kosmosie, wraz z ich zdjęciami, które również są pobierane za pomocą API." +
                "Ostatnią zakładką jest Pass Time , która wyświetla datę następnego przelotu ISS nad wybraną lokalizacją.</html>"),BorderLayout.NORTH);


    }
}
