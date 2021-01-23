package gui;

import url.ISSPassTimes;
import url.ISSPassTimesURL;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassTime_Frame extends JFrame {

    public PassTime_Frame() {
        super("Next pass time");
        Image icon = new javax.swing.ImageIcon("src/main/resources/nasa_logo.png").getImage();
        setIconImage(icon);
//        JFrame frame = new JFrame("Flow Layout");
//        JButton button,button1, button2, button3,button4;
//        button = new JButton("button 1");
//        button1 = new JButton("button 2");
//        button2 = new JButton("button 3");
//        button3 = new JButton("button 4");
//        button4 = new JButton("button 5");
//        frame.add(button);
//        frame.add(button1);
//        frame.add(button2);
//        frame.add(button3);
//        frame.add(button4);
//        frame.setLayout(new FlowLayout());
//        frame.setSize(300,300);
//        frame.setVisible(true);

        ISSPassTimesURL isspasstimesURL= new ISSPassTimesURL();
        ISSPassTimes issPassTimes = null; //Warsaw
        try {
            issPassTimes = isspasstimesURL.RequestISSPassTimes(52.216f, 21);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //if not null, bo inaczej się
        //take the first pass
        String time= String.valueOf(issPassTimes.getResponse().get(0));
        //convert date to human readable format
        Long time_sub= Long.parseLong(time.substring(32,42));
        Date date = new java.util.Date(time_sub*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1"));
        String formattedDate = sdf.format(date);
        add(new JLabel("Next pass date is : "+formattedDate));
        // create an empty combo box with items of type String
        JComboBox<String> comboLanguage = new JComboBox<String>();

// add items to the combo box
        comboLanguage.addItem("English");
        comboLanguage.addItem("French");
        comboLanguage.addItem("Spanish");
        comboLanguage.addItem("Japanese");
        comboLanguage.addItem("Suahili");
        comboLanguage.addItem("Chinese");
        comboLanguage.setEditable(true);
        AutoCompletion.enable(comboLanguage);
        //add(comboLanguage);
        JPanel wrapper = new JPanel();
        wrapper.add( comboLanguage);
        add( wrapper );
        FileReader fr = null;
        try {
            fr = new FileReader("src/main/resources/City_poland.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        char [][] tablica = new char[2321][3];
        int i=0;
        while (true) {
            try {
                if (!((i=fr.read()) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s=Character.toString((char) i);
//            tablica [i][1]=s.substring(0,23);
//            tablica [i][2]=s.substring(24,30);
//            tablica [i][3]=s.substring(39,45);
            tablica[i][1]=(char) i;
            System.out.print(tablica);
        }
        //System.out.println(tablica);

//        add(new JButton ("pierwszy"));
//        add(new JButton("drugi"));
//        add(new JButton("trzeci"));
//        add(new JButton("czwarty"));
//        setLayout(new GridLayout(2,3));
        //setLayout(new FlowLayout());
        //setSize(300,300);
//        setVisible(true);
//        JButton cancelButton, setButton;
//        cancelButton = new JButton("cancel");
//        setButton = new JButton("set");
//        JPanel listPane = new JPanel();
//        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
//        JLabel label = new JLabel("some text");
//        listPane.add(label);
//        listPane.add(Box.createRigidArea(new Dimension(0,5)));
//        //listPane.add();
//        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//        JPanel buttonPane = new JPanel();
//        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
//        buttonPane.add(Box.createHorizontalGlue());
//        buttonPane.add(cancelButton);
//        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
//        buttonPane.add(setButton);
//        Container contentPane = getContentPane();
//        contentPane.add(listPane, BorderLayout.CENTER);
//        contentPane.add(buttonPane, BorderLayout.PAGE_END);
        // create a label to display text
//        JButton b, b1, b2, b3;
//        JLabel l;
//        l = new JLabel("panel label");
//
//        // create a new buttons
//        b = new JButton("button1");
//        b1 = new JButton("button2");
//        b2 = new JButton("button3");
//        b3 = new JButton("button4");
//        JPanel p = new JPanel();
//        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
//        p.add(b);
//        p.add(b1);
//        p.add(b2);
//        p.add(b3);
//        p.add(l);
//        add(p);

//        String[] bookTitles = new String[] {"Effective Java", "Head First Java",
//                "Thinking in Java", "Java for Dummies"};
//
//        JComboBox<String> bookList = new JComboBox<>(bookTitles);
//
//// add to the parent container (e.g. a JFrame):
//        p.add(bookList);
//
//// get the selected item:
//        String selectedBook = (String) bookList.getSelectedItem();
//        System.out.println("You seleted the book: " + selectedBook);

        //JFrame frame = new JFrame("Flow Layout");
//        JButton button,button1, button2, button3,button4;
//        button = new JButton("button 1");
//        button1 = new JButton("button 2");
//        button2 = new JButton("button 3");
//        button3 = new JButton("button 4");
//        button4 = new JButton("button 5");
//        add(button,BorderLayout.SOUTH);
//        frame.add(button);
//        frame.add(button1);
//        frame.add(button2);
//        frame.add(button3);
//        frame.add(button4);
//        frame.setLayout(new GridLayout(2,3));
//        frame.setSize(300,300);
//        frame.setVisible(true);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu isstrackerMenu = new JMenu("ISS Tracker");
        JMenuItem isstrackerItem = new JMenuItem("Go to...");
        isstrackerItem.addActionListener((event)-> {
            try {
                new MainFrame("Map.jpg");
                setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });
        isstrackerMenu.add(isstrackerItem);
        menuBar.add(isstrackerMenu);
        JMenu astrounautsMenu = new JMenu("Astrounauts");
        JMenuItem astrounatsItem = new JMenuItem("Go to...");
        astrounatsItem.addActionListener((event)-> {
            new Astronauts_Frame();
            setVisible(true);
            this.setVisible(false);
        });
        astrounautsMenu.add(astrounatsItem);
        menuBar.add(astrounautsMenu);
        setJMenuBar(menuBar);
        setSize(1414,660);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}