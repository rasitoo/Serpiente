package ui.menu;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MenuPrincipal {


    public static void main(String[] args) {

        JFrame ventana= new JFrame ("Menú");
        ventana.setPreferredSize(new Dimension(500,320));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imagenFondo = new ImageIcon("C:\\Users\\paros\\IdeaProjects\\Serpiente\\src\\main\\java\\imagenes\\fondo3.png");
        JLabel fondo = new JLabel(imagenFondo);
        fondo.setSize(ventana.getContentPane().getSize());
        fondo.setOpaque(true);
        ventana.getContentPane().add(fondo, BorderLayout.CENTER);

        JButton boton = new JButton("Jugar");

        boton.setForeground(Color.BLACK); //color de las letras del boton
        boton.setFont(new Font("Calibri", Font.BOLD, 28)); // Tipo de fuente y tamaño
        boton.setBorder(BorderFactory.createMatteBorder(3, 3, 2, 2, Color.BLACK)); //borde del boton
        boton.setPreferredSize(new Dimension(50,50));
        ventana.getContentPane().add(boton, BorderLayout.SOUTH);

        /*Estilo del cursor*/
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon icon = new ImageIcon("cursor1.png");
        Cursor cursor = tk.createCustomCursor(icon.getImage(), new Point(0, 0), "Cursor personalizado");
        ventana.setCursor(cursor);


        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame ventanaDificultades= new JFrame ("Niveles");
                ventanaDificultades.setCursor(cursor);
                ventana.setVisible(false);
                ventanaDificultades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaDificultades.setPreferredSize(new Dimension(500,320));

                JPanel parteCentral2 = new JPanel();
                parteCentral2.setLayout(new BoxLayout(parteCentral2,BoxLayout.Y_AXIS)); //para que esten alineados en vertical
                ventanaDificultades.getContentPane().add(parteCentral2, BorderLayout.CENTER);
                parteCentral2.setBorder(BorderFactory.createEmptyBorder(80, 200, 20, 20));

                JButton boton1=new JButton("  Fácil  ");
                JButton boton2=new JButton(" Difícil ");
                boton1.setForeground(Color.BLACK);
                boton2.setForeground(Color.BLACK);
                boton1.setFont(new Font("Calibri", Font.BOLD, 28));
                boton2.setFont(new Font("Calibri", Font.BOLD, 28));
                boton1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                boton2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                parteCentral2.add(boton1);
                parteCentral2.add(Box.createRigidArea(new Dimension(0, 40))); // Añade un espacio en horizontal
                parteCentral2.add(boton2);

                ventanaDificultades.getContentPane().add(parteCentral2);
                ventanaDificultades.setResizable(false);
                ventanaDificultades.pack();
                ventanaDificultades.setVisible(true);
            }
        });

        ventana.setResizable(false); //para no poder modificar el tamaño de la pantalla
        ventana.pack();
        ventana.setVisible(true);
        System.out.println("Se muestra la ventana ");
    }
}
