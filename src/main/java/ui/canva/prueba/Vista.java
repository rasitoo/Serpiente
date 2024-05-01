package ui.canva.prueba;

import javax.swing.*;

/**
 * @author Rodrigo
 * @date 01 mayo, 2024
 */
public class Vista extends JFrame {
    int ancho,alto;
    public Vista(String tituloVentana, int ancho, int alto) {
        super(tituloVentana);

        this.ancho = ancho;
        this.alto = alto;

        this.setLocationRelativeTo(null); //ventana en el centro

        Serpiente serpiente = new Serpiente(800,30);
        add(serpiente);
        serpiente.setBounds(10,10,ancho,alto);
        serpiente.setOpaque(false);//El panel de la serpiente esta encima del fondo, por lo tanto debe ser transparente

        Fondo fondo = new Fondo(800,30); //El fondo sera de 800 pixeles dividido en 30 cuadrados
        add(fondo);
        fondo.setBounds(10,10,ancho,alto);
    }

    public static void main(String[] args) {
        Vista vista = new Vista("Serpiente",800,30);
        vista.setVisible(true);
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setResizable(false);
    }
}
