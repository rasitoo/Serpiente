package ui.menu;

import ui.canva.Serpiente;
import ui.canva.Vista;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MenuPrincipal extends javax.swing.JFrame{



    public static void main(String[] args) {
        int maxGap=20;

        JFrame ventana= new JFrame ("Menú");
        ventana.setPreferredSize(new Dimension(500,320));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imagenFondo = new ImageIcon(".\\src\\main\\java\\imagenes\\fondo3.png");
        JLabel fondo = new JLabel(imagenFondo);
        fondo.setSize(ventana.getContentPane().getSize());
        fondo.setOpaque(true);
        ventana.getContentPane().add(fondo, BorderLayout.CENTER);


        /*Estilo del cursor*/
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon icon = new ImageIcon("cursor1.png");
        Cursor cursor = tk.createCustomCursor(icon.getImage(), new Point(0, 0), "Cursor personalizado");
        ventana.setCursor(cursor);

        JButton boton = new JButton("Jugar");
        boton.setPreferredSize(new Dimension(0,60));
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("Calibri", Font.BOLD, 28));
        boton.setBorder(BorderFactory.createMatteBorder(3, 3, 2, 2, Color.BLACK));

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame ventanaDificultades= new JFrame ("Niveles");
                ventanaDificultades.setCursor(cursor);
                ventana.setVisible(false);
                ventanaDificultades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaDificultades.setPreferredSize(new Dimension(500,320));

                JLabel fondo = new JLabel(new ImageIcon(".\\src\\main\\java\\imagenes\\fondo2.jpeg"));
                fondo.setSize(ventanaDificultades.getContentPane().getSize());
                fondo.setOpaque(true);

                ventanaDificultades.getContentPane().add(fondo, BorderLayout.CENTER);

                JButton boton1=new JButton("  Fácil  ");
                JButton boton2=new JButton(" Difícil ");
                boton1.setForeground(Color.BLACK);
                boton2.setForeground(Color.BLACK);
                boton1.setFont(new Font("Calibri", Font.BOLD, 28));
                boton2.setFont(new Font("Calibri", Font.BOLD, 28));
                boton1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                boton2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

                boton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e2) {
                        ventanaDificultades.setVisible(false);
                        Vista v=new Vista("Juego de la serpiente", 800, 800);
                        v.setVisible(true);
                        v.setResizable(false);
                    }
                });

                ventanaDificultades.getContentPane().add(boton1, BorderLayout.NORTH);
                boton1.setPreferredSize(new Dimension(0,50));
                ventanaDificultades.getContentPane().add(boton2, BorderLayout.SOUTH);
                boton2.setPreferredSize(new Dimension(0,50));
                ventanaDificultades.setResizable(false);
                ventanaDificultades.pack();
                ventanaDificultades.setVisible(true);
            }
        });
        ventana.getContentPane().add(boton, BorderLayout.SOUTH);
        ventana.setResizable(false); //para no poder modificar el tamaño de la pantalla
        ventana.pack();
        ventana.setVisible(true);
        System.out.println("Se muestra la ventana ");

    }
}
