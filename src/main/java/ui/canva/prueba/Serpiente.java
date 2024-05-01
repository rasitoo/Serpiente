package ui.canva.prueba;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Rodrigo
 * @date 01 mayo, 2024
 */
public class Serpiente extends JPanel {
    Color colorserpiente = Color.green;
    int tammax, tam, can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos residuo, que es el resto de la division
    ArrayList<int[]> serpiente = new ArrayList<>(); //Un arraylist de longitud variable que contiene arrays de int con las coordenadas de la serpiente
    int[] comida; //Habrá un maximo de comida por lo tanto con un array normal nos vale

    public Serpiente(int tammax, int can) {
        this.tammax = tammax;
        this.can = can;
        tam = tammax / can;
        res = tammax % can;
        int[] a = {can / 2 - 1, can / 2 - 1}; //Primera coordenada en el centro del tablero, sera la cabeza
        int[] b = {can / 2, can / 2 - 1}; //Segunda coordenada al lado para que no parezca un huevo
        serpiente.add(a);
        serpiente.add(b);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //Cuando pinta lo hace iniciando de nuevo, haciendo que se mueva, es decir, lo que estaba antes lo borra
        g.setColor(colorserpiente); //pinta el fondo
        for (int i = 0; i < serpiente.size(); i++) {
            g.fillRect(res / 2 + serpiente.get(i)[0], res / 2 + serpiente.get(i)[1], tam - 1, tam - 1); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes
        }
    }
}
