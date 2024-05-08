package ui.canva;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Rodrigo
 * @date 01 mayo, 2024
 */

public class Serpiente extends JPanel {
    Color colorserpiente = Color.blue;
    Color colorcomida = Color.red;
    Color colorobstaculo = new Color(128,70, 8);
    int tammax, tam, can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos residuo, que es el resto de la division
    List<int[]> serpiente = new ArrayList<>(); //Un arraylist de longitud variable que contiene arrays de int con las coordenadas de la serpiente
    int[] comida = new int[2]; //Habrá solo una comida, por lo tanto, con un array normal nos vale
    int[] obstaculo = new int[2];
    int numCuadrados=(int)(Math.random()*3+1);
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
        generarObstaculo();

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

        g.setColor(colorobstaculo);
        g.fillRect(res / 2 + obstaculo[0] * tam, res / 2 + obstaculo[1] * tam, tam - 1, tam - 1);

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
        if (x >= can)
            x = 0;
        else if (x < 0)
            x = can - 1;
        else if (y >= can)
            y = 0;
        else if (y < 0)
            y = can - 1;
        int[] nuevo = {x, y};
        for (int[] coordenada : serpiente) {
            if (coordenada[0] == nuevo[0] && coordenada[1] == nuevo[1]) {
                choque = true;
                mov.parar();
            }

            if (obstaculo[0] == nuevo[0] && obstaculo[1] == nuevo[1]) {
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
                ocupado = true;
                break;
            }
        }
        if (ocupado) {
            generarComida();
        } else {
            comida = new int[]{x, y};
        }
    }

    public void generarObstaculo() {
        boolean ocupado = false;
        int x = (int) (Math.random() * can);
        int y = (int) (Math.random() * can);
        for (int[] coordenada : serpiente) {
            if (coordenada[0] == x && coordenada[1] == y) {
                ocupado = true;
                break;
            }
        }
        if (ocupado) {
            generarObstaculo();
        } else {
            obstaculo = new int[]{x, y};
            // Add a timer to make the obstacle appear after 15 seconds
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    generarObstaculo();
                    repaint(); // This will trigger the paint method to update the obstacle
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5000); // 5000 is 5 seconds in milliseconds
        }
    }

}
