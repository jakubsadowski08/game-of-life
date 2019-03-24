
import javax.swing.*;
import java.awt.*;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyFrame frame = new MyFrame();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });

        while(true) {
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
        }

    }
    }