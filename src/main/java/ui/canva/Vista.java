package ui.canva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Rodrigo
 * @author Patricia
 * @date 01 mayo, 2024
 */

/**
 * Clase que representa la ventana principal de la aplicación de la serpiente.
 * Esta clase extiende de JFrame y contiene los componentes necesarios para la interfaz gráfica.
 */
public class Vista extends JFrame {
    /**
     * El ancho y alto de la ventana
     */
    int ancho, alto;

    /**
     * La clase serpiente que contiene el panel con la serpiente, comida y obstaculos
     */
    Serpiente serpiente;

    /**
     * El fondo del juego
     */
    Fondo fondo;

    /**
     * El lugar donde se va a mostrar la puntuación
     */
    JTextField puntuacion;

    /**
     * Panel donde va a aperecer la puntuación
     */
    JPanel panelpuntuacion;

    /**
     * Constructor de la clase Vista.
     * Inicializa los componentes de la ventana y los parámetros necesarios para
     * la ventana de la aplicación.
     *
     * @param tituloVentana el título de la ventana.
     * @param ancho el ancho de la ventana.
     * @param dificultad la dificultad de la partida.
     */

    public Vista(String tituloVentana, int ancho, String dificultad) {
        super(tituloVentana);

        this.ancho = ancho;
        this.alto = ancho + 20;
        this.setSize(ancho + 40, alto + 40);

        this.setLocationRelativeTo(null); //ventana en el centro

        switch (dificultad) {
            case "facil":
                serpiente = new Serpiente(ancho, 20, this);
                fondo = new Fondo(ancho, 20);
                break;
            case "dificil":
                serpiente = new Serpiente(ancho, 10, this);
                fondo = new Fondo(ancho, 10);
                break;
        }

        panelpuntuacion = new JPanel();
        panelpuntuacion.setBounds(10, 10, ancho, alto);
        JSeparator separator = new JSeparator();
        separator.setSize(fondo.getSize());
        panelpuntuacion.add(separator);

        puntuacion = new JTextField(20);
        puntuacion.setEditable(false);
        puntuacion.setText("Puntuacion: 0");
        puntuacion.setBackground(Color.white);
        puntuacion.setForeground(Color.black);
        puntuacion.setFont(new Font("Calibri", Font.BOLD, 15));
        puntuacion.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        puntuacion.setFocusable(false);
        puntuacion.setBounds(ancho, alto, puntuacion.getWidth(), puntuacion.getHeight());
        serpiente.setBorder(BorderFactory.createCompoundBorder(
                serpiente.getBorder(),
                BorderFactory.createEmptyBorder(alto-20, 10, 0, 0)
        ));



        serpiente.add(puntuacion);

        serpiente.setBounds(10, 10, ancho, alto);
        serpiente.setOpaque(false);//El panel de la serpiente esta encima del fondo, por lo tanto debe ser transparente

        fondo.setBounds(10, 10, ancho, alto);
        initComponents();


    }

    /**
     * Método que se llama cuando se presiona una tecla.
     * Actualiza el movimiento de la serpiente en función de la tecla presionada.
     *
     * @param event el evento de teclado.
     */
    private void tecla(KeyEvent event) {

        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!serpiente.getDir().equals("izq") && !serpiente.getDir().equals("der"))
                    serpiente.setCambiodir("izq");
                break;
            case KeyEvent.VK_RIGHT:
                if (!serpiente.getDir().equals("izq") && !serpiente.getDir().equals("der"))
                    serpiente.setCambiodir("der");
                break;
            case KeyEvent.VK_UP:
                if (!serpiente.getDir().equals("arr") && !serpiente.getDir().equals("aba"))
                    serpiente.setCambiodir("arr");
                break;
            case KeyEvent.VK_DOWN:
                if (!serpiente.getDir().equals("arr") && !serpiente.getDir().equals("aba"))
                    serpiente.setCambiodir("aba");
                break;
        }
    }

    /**
     * Método que actualiza la puntuación en la interfaz gráfica.
     *
     * @param puntuacion la puntuación actual.
     */
    void actualizarPuntuacion(int puntuacion) {
        this.puntuacion.setText("Puntuacion: " + puntuacion);
    }

    /**
     * Este método es llamado desde dentro del constructor para inicializar la ventana.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tecla(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(ancho, ancho, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup())
                        .addGap(ancho, ancho, Short.MAX_VALUE)
                        .addComponent(serpiente)
                        .addComponent(fondo)
                        .addGroup(layout.createSequentialGroup())


        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(ancho, alto, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup())
                        .addGap(ancho, ancho, Short.MAX_VALUE)
                        .addComponent(serpiente)
                        .addComponent(fondo)
                        .addGroup(layout.createSequentialGroup())
                        .addGap(ancho, ancho, Short.MAX_VALUE)

        );
        getContentPane().add(panelpuntuacion,BorderLayout.SOUTH);

        pack();
    }// </editor-fold>

    /**
     * Método principal de la aplicación.
     *
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Vista vista = new Vista("Serpiente", 500, "dificil");

                vista.setVisible(true);
                vista.setResizable(false);
            }
        });
    }
}