package ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MenuPrincipal {


    public static void main(String[] args) {

        JFrame ventana= new JFrame ("Menú");
        ventana.setPreferredSize(new Dimension(900,600));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Estilo del cursor*/
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon icon = new ImageIcon("cursor3.jpeg");
        Cursor cursor = tk.createCustomCursor(icon.getImage(), new Point(0, 0), "Cursor personalizado");
        ventana.setCursor(cursor);

        ImageIcon imagenFondo = new ImageIcon("/imagenes/fondo2.jpeg");
        JLabel fondo = new JLabel(imagenFondo);
        fondo.setSize(ventana.getContentPane().getSize());
        fondo.setOpaque(true);
        ventana.getContentPane().add(fondo, BorderLayout.CENTER);

        int maxGap = 20;
        GridLayout experimentLayout = new GridLayout(1,1);

        JPanel parteCentral = new JPanel();
        parteCentral.setLayout(new FlowLayout(FlowLayout.CENTER));
        parteCentral.setPreferredSize(new Dimension(150,50));

        JButton boton = new JButton("Jugar");
        Dimension buttonSize = boton.getPreferredSize();
        parteCentral.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 1.5) + maxGap,
                (int) (buttonSize.getHeight() * 2.5) + maxGap * 2));
        ventana.getContentPane().add(parteCentral, BorderLayout.CENTER);
        parteCentral.setBorder(BorderFactory.createEmptyBorder(240, 20, 20, 20));//para poner bordes a todos los laterales
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame ventanaDificultades= new JFrame ("Niveles");
                ventana.setVisible(false);
                ventanaDificultades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaDificultades.setPreferredSize(new Dimension(900,600));

                JPanel parteCentral2 = new JPanel();
                parteCentral2.setLayout(new BoxLayout(parteCentral2,BoxLayout.Y_AXIS)); //para que esten alineados en vertical
                ventanaDificultades.getContentPane().add(parteCentral2, BorderLayout.CENTER);
                parteCentral2.setBorder(BorderFactory.createEmptyBorder(230, 390, 20, 20));

                JButton boton1=new JButton("Facil");
                JButton boton2=new JButton("Dificil");
                boton1.setFont(new Font("Arial", Font.BOLD, 26));
                boton2.setFont(new Font("Arial", Font.BOLD, 26));
                parteCentral2.add(boton1);
                parteCentral2.add(Box.createRigidArea(new Dimension(0, 20))); // Añade un espacio en horizontal
                parteCentral2.add(boton2);

                ventanaDificultades.getContentPane().add(parteCentral2);
                ventanaDificultades.setResizable(false);
                ventanaDificultades.pack();
                ventanaDificultades.setVisible(true);
            }
        });
        boton.setBackground(new Color(128, 70, 8));
        boton.setFont(new Font("Arial", Font.BOLD, 26)); // Set font style and size
        parteCentral.add(boton);

        ventana.setResizable(false); //para no poder modificar el tamaño de la pantalla
        ventana.pack();
        ventana.setVisible(true);
        System.out.println("Se muestra la ventana ");
    }
}
