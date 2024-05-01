package ui.menu;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class MenuPrincipal {


    public static void main(String[] args) {

        JFrame ventana= new JFrame ("Menú");
        ventana.setPreferredSize(new Dimension(900,600));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int maxGap = 20;
        GridLayout experimentLayout = new GridLayout(1,1);

        JPanel parteCentral = new JPanel();
        parteCentral.setLayout(experimentLayout);

        //Set up components preferred size
        JButton b = new JButton("Jugar");
        Dimension buttonSize = b.getPreferredSize();
        parteCentral.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 1.5) + maxGap,
                (int) (buttonSize.getHeight() * 2.5) + maxGap * 2));
        ventana.getContentPane().add(parteCentral, BorderLayout.CENTER);
        parteCentral.add(b);

        ventana.pack();
        ventana.setVisible(true);
        System.out.println("Se muestra la ventana ");
    }
}
