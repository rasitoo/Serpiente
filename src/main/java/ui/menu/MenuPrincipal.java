package ui.menu;

import ui.canva.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Rodrigo
 * @author Patricia
 * @date 01 mayo, 2024
 */

/**
 * Clase que representa el menú principal de la aplicación.
 * Esta clase extiende de JFrame y contiene los componentes necesarios para la interfaz gráfica.
 */
public class MenuPrincipal extends javax.swing.JFrame{

    /**
     * Método estático abre la ventana del menú principal
     */

    public static void menu() {
        JFrame ventana = crearVentana("Menú", 500, 320);;
        ventana.setPreferredSize(new Dimension(500,320));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ponerImagenFondo(ventana, ".\\src\\main\\java\\imagenes\\fondo3.png");
        ponerCursor(ventana);
        JButton boton = crearBoton("Jugar", 60, Color.BLACK, new Font("Calibri", Font.BOLD, 28));
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuDificultades(ventana);
            }
        });
        ventana.getContentPane().add(boton, BorderLayout.SOUTH);
        ventana.pack();
        ventana.setVisible(true);
    }

    /**
     * Método estático que muestra el menú de selección de niveles.
     *
     * @param ventana la ventana principal de la aplicación.
     */
    public static void menuDificultades(JFrame ventana) {
        ventana.setVisible(false);
        JFrame ventanaDificultades = crearVentana("Niveles", 500, 320);
        ponerImagenFondo(ventanaDificultades, ".\\src\\main\\java\\imagenes\\fondo2.jpeg");
        ponerCursor(ventanaDificultades);
        JButton boton = crearBoton("  Fácil  ", 50, Color.BLACK, new Font("Calibri", Font.BOLD, 28));
        JButton boton2 = crearBoton(" Difícil ", 50, Color.BLACK, new Font("Calibri", Font.BOLD, 28));
        DificultadActionListeners(ventanaDificultades, boton, boton2);
        ventanaDificultades.getContentPane().add(boton, BorderLayout.NORTH);
        ventanaDificultades.getContentPane().add(boton2, BorderLayout.SOUTH);
        ventanaDificultades.pack();
        ventanaDificultades.setVisible(true);
    }

    /**
     * Método estático que crea una ventana con un título y un tamaño específico.
     *
     * @param titulo el título de la ventana.
     * @param ancho el ancho de la ventana.
     * @param alto el alto de la ventana.
     * @return la ventana creada.
     */
    private static JFrame crearVentana(String titulo, int ancho, int alto) {
        JFrame ventana = new JFrame(titulo);
        ventana.setPreferredSize(new Dimension(ancho, alto));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return ventana;
    }

    /**
     * Método estático que pone una imagen de fondo en una ventana.
     *
     * @param ventana la ventana en la que se pondrá la imagen de fondo.
     * @param imagePath la ruta de la imagen de fondo.
     */
    private static void ponerImagenFondo(JFrame ventana, String imagePath) {
        ImageIcon imagenFondo = new ImageIcon(imagePath);
        JLabel fondo = new JLabel(imagenFondo);
        fondo.setSize(ventana.getContentPane().getSize());
        fondo.setOpaque(true);
        ventana.getContentPane().add(fondo, BorderLayout.CENTER);
    }

    /**
     * Método estático que establece el cursor personalizado en una ventana.
     *
     * @param ventana la ventana en la que se establecerá el cursor personalizado.
     */
    private static void ponerCursor(JFrame ventana) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon icon = new ImageIcon(".\\src\\main\\java\\imagenes\\cursor1.png");
        Cursor cursor = tk.createCustomCursor(icon.getImage(), new Point(0, 0), "Cursor personalizado");
        ventana.setCursor(cursor);
    }

    /**
     * Método estático que crea un botón con un texto específico, un tamaño, un color de texto, y una fuente.
     *
     * @param texto el texto que se mostrará en el botón.
     * @param altura la altura del botón.
     * @param colorLetra el color del texto en el botón.
     * @param estilo la fuente que se utilizará para mostrar el texto en el botón.
     * @return el botón creado.
     */
    private static JButton crearBoton(String texto, int altura, Color colorLetra, Font estilo) {
        JButton button = new JButton(texto);
        button.setPreferredSize(new Dimension(0, altura));
        button.setForeground(colorLetra);
        button.setFont(estilo);
        button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        return button;
    }


    /**
     * Método estático que establece los escuchadores de eventos para los botones de selección de nivel.
     *
     * @param ventanaDificultades la ventana en la que se establecerán los escuchadores de eventos.
     * @param boton el botón de selección de nivel.
     * @param boton2 el segundo botón de selección de nivel.
     */
    private static void DificultadActionListeners(JFrame ventanaDificultades, JButton boton, JButton boton2) {
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaDificultades.setVisible(false);
                new Vista("Juego de la serpiente (facil)", 500, "facil").setVisible(true);
            }
        });
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaDificultades.setVisible(false);
                new Vista("Juego de la serpiente (dificil)", 500, "dificil").setVisible(true);
            }
        });
    }

    /**
     * Método principal de la aplicación que al ejecutarlo se abrirá el menú
     *
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        menu();
        System.out.println("Se muestra la ventana ");
    }
}
