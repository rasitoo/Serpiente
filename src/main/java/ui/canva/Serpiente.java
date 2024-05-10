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
    private final Vista vista;
    Color colorserpiente = new Color(15,158,13);
    Color colorcomida = new Color(207,16,16);
    Color colorobstaculo = new Color(83, 44, 3);
    Color colorfuturoobstaculo = new Color(83, 44, 3,120);
    ImageIcon imgComida =new ImageIcon(".\\src\\main\\java\\imagenes\\manzana.png");
    ImageIcon imgObst =new ImageIcon(".\\src\\main\\java\\imagenes\\barril-de-vino.png");


    int tammax, tam, can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos residuo, que es el resto de la division
    List<int[]> serpiente = new ArrayList<>(); //Un arraylist de longitud variable que contiene arrays de int con las coordenadas de la serpiente
    int[] comida = new int[2]; //Habrá solo una comida, por lo tanto, con un array normal nos vale
    List<int[]> obstaculo = new ArrayList<>();
    List<int[]> futuroobstaculo = new ArrayList<>();

    int numCuadrados;
    int numFuturosCuadrados;

    String dir = "der";
    String cambiodir = "der";
    int puntuacion = 0;

    boolean choque = false;

    Thread movim;
    Movimiento mov;

    Thread obst;
    Obstaculo obstaculo1;

    Thread acele;
    Aceleracion aceleracion;

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

    public Serpiente(int tammax, int can, Vista vista) {
        this.vista = vista;
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
        movim = new Thread(mov);
        movim.start();

        obstaculo1 = new Obstaculo(this);
        obst = new Thread(obstaculo1);
        obst.start();

        aceleracion = new Aceleracion(mov);
        acele = new Thread(aceleracion);
        acele.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //Cuando pinta lo hace iniciando de nuevo, haciendo que se mueva, es decir, lo que estaba antes lo borra
        for (int i = 0; i < serpiente.size(); i++) {
            if(i==serpiente.size()-1) {
                // Create a semi-arc polygon
                Polygon semiArc = new Polygon();

                // Define the arc's radius
                int radius = tam/2;
                int centerX = res / 2 +serpiente.get(i)[0] * tam ;
                int centerY = (int) (res / 2 + serpiente.get(i)[1] * tam);
                int startAngle = 0;
                int endAngle = 0;

                // Define the arc's center
                switch (dir){
                    case "der":
                        centerX+=0;
                        centerY+=tam/2;
                        startAngle = 270;
                        endAngle = 450;
                        break;
                    case "izq":
                        centerX+=tam;
                        centerY+=tam/2;
                        startAngle = 90;
                        endAngle = 270;
                        break;
                    case "arr":
                        centerX+=tam/2;
                        centerY+=tam;
                        startAngle = 0;
                        endAngle = 180;
                        break;
                    case "aba":
                        centerX+=tam/2;
                        centerY+= 0;
                        startAngle = 180;
                        endAngle = 360;
                        break;
                }
                // Add points for the semi-arc
                for (int angle = startAngle; angle <= endAngle; angle++) {
                    int x = centerX + (int) (radius * Math.cos(Math.toRadians(angle)));
                    int y = centerY - (int) (radius * Math.sin(Math.toRadians(angle)));
                    semiArc.addPoint(x, y);
                }
                g.setColor(Color.lightGray);

                g.fillPolygon(semiArc);
            }
            else if (i %2 == 0){
                g.setColor(colorserpiente); //pinta el fondo
                g.fillRect(res / 2 + serpiente.get(i)[0] * tam, res / 2 + serpiente.get(i)[1] * tam, tam, tam); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes

            }
            else {
                g.setColor(colorserpiente.darker()); //pinta el fondo
                g.fillRect(res / 2 + serpiente.get(i)[0] * tam, res / 2 + serpiente.get(i)[1] * tam, tam, tam); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes

            }

        }
        g.drawImage(imgComida.getImage(),res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1, null);

        g.setColor(colorfuturoobstaculo);
        for (int i = 0; i < numFuturosCuadrados; i++) {
            g.fillRect(res / 2 + futuroobstaculo.get(i)[0] * tam, res / 2 + futuroobstaculo.get(i)[1] * tam, tam - 1, tam - 1); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes
        }
        for (int i = 0; i < numCuadrados; i++) {
            g.drawImage(imgObst.getImage(),res / 2 + obstaculo.get(i)[0] * tam, res / 2 + obstaculo.get(i)[1] * tam, tam - 1, tam - 1, null); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes
        }
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
            }

            if (!obstaculo.isEmpty())
                for (int i = 0; i < numCuadrados; i++) {
                    if (obstaculo.get(i)[0] == nuevo[0] && obstaculo.get(i)[1] == nuevo[1]) {
                        choque = true;
                    }
                }
        }
        if (choque) {
            mov.parar();
            JOptionPane.showMessageDialog(this, "Has perdido!!");
        } else {
            if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                serpiente.add(nuevo);
                generarComida();
                puntuacion += 5;
                vista.actualizarPuntuacion(puntuacion);
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
        numCuadrados = numFuturosCuadrados;
        numFuturosCuadrados = (int) (Math.random() * 3 + 1);
        int x = (int) (Math.random() * can);
        int y = (int) (Math.random() * can);
        obstaculo = futuroobstaculo;
        futuroobstaculo = new ArrayList<>();
        futuroobstaculo.add(new int[]{x, y});
        for (int i = 0; i < numFuturosCuadrados; i++){
            x+=(int)(Math.random() *2);
            y+=(int)(Math.random() *2);

            if (x >= can)
                x--;
            else if (x < 0)
                x++;
            else if (y >= can)
                y--;
            else if (y < 0)
                y++;
            futuroobstaculo.add(new int[]{x , y});
        }
        for (int i = 0; i < numFuturosCuadrados; i++) {
            for (int[] coordenada : serpiente) {
                if (coordenada[0] == futuroobstaculo.get(i)[0] && coordenada[1] == futuroobstaculo.get(i)[1]) {
                    ocupado = true;
                    break;
                }
            }
        }
        if (ocupado) {
            generarObstaculo();
        }
    }

}
