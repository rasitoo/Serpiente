package ui.canva;

import javax.swing.*;

/**
 * @author Rodrigo
 * @date 10 mayo, 2024
 */
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PruebaPolygon extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.GREEN);

        // Draw the semi-arc polygon
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Semi-Arc Polygon Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PruebaPolygon());
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

