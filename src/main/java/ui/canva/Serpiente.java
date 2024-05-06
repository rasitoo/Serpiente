package ui.canva;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo
 * @date 01 mayo, 2024
 */

public class Serpiente extends JPanel {
    Color colorserpiente = Color.blue;
    Color colorcomida = Color.red;
    int tammax, tam, can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos residuo, que es el resto de la division
    List<int[]> serpiente = new ArrayList<>(); //Un arraylist de longitud variable que contiene arrays de int con las coordenadas de la serpiente
    int[] comida = new int[2]; //Habrá solo una comida por lo tanto con un array normal nos vale
    String dir = "der";
    String cambiodir = "der" ;

    boolean choque = false;
    Thread hilo;
    Movimiento mov;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCambiodir() {
        return cambiodir;
    }

    public void setCambiodir(String cambiodir) {
        this.cambiodir = cambiodir;
    }

    public Serpiente(int tammax, int can) {
        this.tammax = tammax;
        this.can = can;
        tam = tammax / can;
        res = tammax % can;
        int[] a = {can / 2, can / 2}; //Primera coordenada en el centro del tablero, sera la cabeza
        int[] b = {can / 2, can / 2 - 1}; //Segunda coordenada al lado para que no parezca un huevo
        serpiente.add(a);
        serpiente.add(b);
        generarComida();

        mov = new Movimiento(this);
        hilo = new Thread(mov);
        hilo.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //Cuando pinta lo hace iniciando de nuevo, haciendo que se mueva, es decir, lo que estaba antes lo borra
        g.setColor(colorserpiente); //pinta el fondo
        for (int i = 0; i < serpiente.size(); i++) {
            g.fillRect(res / 2 + serpiente.get(i)[0] * tam, res / 2 + serpiente.get(i)[1] * tam, tam, tam); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes
        }

        g.setColor(colorcomida);
        g.fillRect(res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes

    }

    public void avanzar() {
        this.dir = this.cambiodir; //Antes de avanzar pone la nueva direccion
        int[] ultimo = serpiente.get((serpiente.size() - 1));
        int x = 0;
        int y = 0;
        switch (dir) {
            case "der":
                x = ultimo[0] + 1;
                y = ultimo[1];
                break;
            case "izq":
                x = ultimo[0] - 1;
                y = ultimo[1];
                break;
            case "arr":
                x = ultimo[0];
                y = ultimo[1] - 1;
                break;
            case "aba":
                x = ultimo[0];
                y = ultimo[1] + 1;
                break;
        }
        int[] nuevo = {x, y}; //Si queremos que al tocar el borde aparezca por el borde contrario poner Math.floorMod
        for (int[] coordenada : serpiente) {
            if (coordenada[0] == nuevo[0] && coordenada[1] == nuevo[1]) {
                choque = true;
                mov.parar();
            }
        }
        if (choque) {
            JOptionPane.showMessageDialog(this, "Has perdido!!");
        } else {
            if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                serpiente.add(nuevo);
                generarComida();
            } else {
                serpiente.add(nuevo);
                serpiente.remove(0);
            }
        }
    }

    public void generarComida() {
        boolean ocupado = false;
        int x = (int) (Math.random() * can);
        int y = (int) (Math.random() * can);
        for (int[] coordenada : serpiente) {
            if (coordenada[0] == x && coordenada[1] == y) {
                generarComida();
            }
        }
        comida = new int[]{x, y};
    }

}
