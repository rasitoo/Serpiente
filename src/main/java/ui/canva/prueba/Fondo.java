package ui.canva.prueba;

import javax.swing.*;
import java.awt.*;

/**
 * @author Rodrigo
 * @date 01 mayo, 2024
 */
public class Fondo extends JPanel {
    Color colorfondo = Color.gray;
    int tammax, tam,can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos residuo, que es el resto de la division

    public Fondo(int tammax, int can){
        this.tammax = tammax;
        this.can = can;
        tam = tammax/can;
        res = tammax%can;
    }
    @Override
    public void paint(Graphics g){
        super.paint(g); //Cuando pinta lo hace iniciando de nuevo, haciendo que se mueva, es decir, lo que estaba antes lo borra
        g.setColor(colorfondo); //pinta el fondo
        for (int x = 0; x < can; x++){
            for (int y = 0; y<can; y++){
                g.fillRect(res/2+x*tam,res/2+y*tam,tam-1, tam-1); //pintará x lineas de y cuadrados en la posicion dada del bucle mas el residuo para que no haya espacio sin asignar, y el tamaño sera el correspondiente -1 para que se vea una linea, se puede quitar el -1 para que no haya linea
            }
        }
    }
}
