package ui.canva;

import servicio.ServicioVista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la ventana principal de la aplicacion de la serpiente.
 * Esta clase extiende de JFrame y contiene los componentes necesarios para la interfaz grafica.
 *
 * @author Rodrigo
 * @author Patricia
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
     * El lugar donde se va a mostrar la puntuacion
     */
    JTextField puntuacion;

    /**
     * Objeto servicio que hara comprobaciones y calculos
     */
    ServicioVista servicio = new ServicioVista(this);

    /**
     * Constructor de la clase Vista.
     * Inicializa los componentes de la ventana y los parámetros necesarios para
     * la ventana de la aplicacion.
     *
     * @param tituloVentana el titulo de la ventana.
     * @param ancho         el ancho de la ventana.
     * @param dificultad    la dificultad de la partida.
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
                BorderFactory.createEmptyBorder(alto - 20, 10, 0, 0)
        ));


        serpiente.add(puntuacion);

        serpiente.setBounds(10, 10, ancho, alto);
        serpiente.setOpaque(false);//El panel de la serpiente esta encima del fondo, por lo tanto debe ser transparente

        fondo.setBounds(10, 10, ancho, alto);
        initComponents();


    }

    public Serpiente getSerpiente() {
        return serpiente;
    }

    /**
     * Metodo que actualiza la puntuacion en la interfaz grafica.
     *
     * @param puntuacion la puntuacion actual.
     */
    public void actualizarPuntuacion(int puntuacion) {
        this.puntuacion.setText("Puntuacion: " + puntuacion);
    }

    /**
     * Este metodo es llamado desde dentro del constructor para inicializar la ventana.
     * Inicialmente generado por NetBeans, pero modificado para que añada margenes a la ventana, anadir todos los JPanel en el orden necesario y con el tamano necesario,
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                servicio.tecla(evt);
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


        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(ancho, alto, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup())
                        .addGap(ancho, ancho, Short.MAX_VALUE)
                        .addComponent(serpiente)
                        .addComponent(fondo)

        );

        pack();
    }

    /**
     * Metodo principal de la aplicacion.
     *
     * @param args los argumentos de la linea de comandos.
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