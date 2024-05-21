package ui.canva;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de crear la cuadrícula de fondo sobre la que se mueve la serpiente
 *
 * @author Rodrigo
 * @author Patricia
 */
public class Fondo extends JPanel {

    /**
     * El color de fondo.
     */
    private Color colorFondo = Color.gray;

    /**
     * El tamano maximo.
     */
    private int tamMax;

    /**
     * El tamano de los cuadrados.
     */
    private int tam;

    /**
     * El numero de cuadrados.
     */
    private int can;

    /**
     * El resto de la division del tamano maximo por el numero de cuadrados.
     */
    private int res;

    public Color getColorFondo() {
        return colorFondo;
    }


    public int getTamMax() {
        return tamMax;
    }


    public int getTam() {
        return tam;
    }


    public int getCan() {
        return can;
    }


    public int getRes() {
        return res;
    }


    /**
     * Constructor para la clase Fondo.
     *
     * @param tamMax el tamaño máximo
     * @param can    el número de cuadrados
     */
    public Fondo(int tamMax, int can) {
        this.tamMax = tamMax;
        this.can = can;
        this.tam = tamMax / can;
        this.res = tamMax % can;
    }

    /**
     * Sobrescribe el método paint para dibujar el fondo.
     *
     * @param g el objeto Graphics para dibujar
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //Cuando pinta lo hace iniciando de nuevo, haciendo que se mueva, es decir, lo que estaba antes lo borra
        g.setColor(colorFondo); //pinta el fondo
        for (int x = 0; x < can; x++) {
            for (int y = 0; y < can; y++) {
                g.fillRect((res / 2) + x * tam, (res / 2) + y * tam, tam - 1, tam - 1); //pintará x líneas de y cuadrados en la posición dada del bucle más el residuo para que no haya espacio sin asignar, y el tamaño será el correspondiente -1 para que se vea una línea, se puede quitar el -1 para que no haya línea
            }
        }
    }
}