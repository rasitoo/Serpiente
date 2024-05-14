package ui.canva;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo
 * @author Patricia
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Serpiente extends JPanel {
    /**
     * La vista que se utilizará para mostrar la serpiente y el tablero.
     */
    private final Vista vista;

    /**
     * Color de la serpiente.
     */
    Color colorserpiente = new Color(15,158,13);

    /**
     * Color de donde va a aprecer el futuro obstaculo
     */
    Color colorfuturoobstaculo = new Color(83, 44, 3,120);

    /**
     * Imagen de la comida
     */
    ImageIcon imgComida =new ImageIcon(".\\src\\main\\java\\imagenes\\manzana.png");

    /**
     * Imagen de los obstaculos
     */
    ImageIcon imgObst =new ImageIcon(".\\src\\main\\java\\imagenes\\barril-de-vino.png");


    /**
     * Tamaño máximo
     * Tamaño del cuadrado
     * La cantidad de cuadrados en el tablero.
     * Residuo, que el resto de la division
     */
    int tammax, tam, can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos residuo, que es el resto de la division

    /**
     * Lista donde se guardarán las coordenadas donde van a aparecer el cuerpo de la serpiente
     */
    List<int[]> serpiente = new ArrayList<>(); //Un arraylist de longitud variable que contiene arrays de int con las coordenadas de la serpiente

    /**
     * Lista donde se guardarán las coordenadas donde va a aparecer la comida
     */
    int[] comida = new int[2]; //Habrá solo una comida, por lo tanto, con un array normal nos vale

    /**
     * Lista donde se guardarán las coordenadas donde van a aparecer los objetos
     */
    List<int[]> obstaculo = new ArrayList<>();

    /**
     * Lista donde se guardarán las coordenadas donde van a aparecer los futuros objetos
     */
    List<int[]> futuroobstaculo = new ArrayList<>();

    int numCuadrados;
    int numFuturosCuadrados;

    /**
     * La dirección en la que se moverá la serpiente.
     */
    String dir = "der";

    /**
     * La dirección a la que cambiará el movimiento la serpiente.
     */
    String cambiodir = "der";

    /**
     * La puntuación actual.
     */
    int puntuacion = 0;

    /**
     * El choque con un obstáculo.
     */
    boolean choque = false;

    /**
     * Hilo donde se generará el movimiento de la serpiente.
     */
    Thread movim;

    /**
     * El movimiento de la serpiente.
     */
    Movimiento mov;

    /**
     * Hilo donde se generarán los obstaculos de la serpiente.
     */
    Thread obst;

    /**
     * Generar los obstaculos de la serpiente.
     */
    Obstaculo obstaculo1;

    /**
     * Hilo donde se generará la aceleración de la serpiente.
     */
    Thread acele;

    /**
     * La aceleración de la serpiente.
     */
    Aceleracion aceleracion;

    /**
     * Devuelve la dirección
     *
     * @return la dirección
     */
    public String getDir() {
        return dir;
    }

    /**
     * Cambia la dirección
     *
     * @param dir dirección del movimiento de la serpiente
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * Obtiene el cambio de direccion de la serpiente
     *
     * @return el cambio de direccion
     */
    public String getCambiodir() {
        return cambiodir;
    }

    /**
     * Establece la velocidad de la serpiente en milisegundos.
     *
     * @param cambiodir dirección a la que cambiará el movimiento la serpiente
     */
    public void setCambiodir(String cambiodir) {
        this.cambiodir = cambiodir;
    }

    /**
     * Constructor para la clase Serpiente.
     *
     * @param tammax el tamaño máximo.
     * @param can    el número de cuadrados.
     * @param vista   la vista que se utilizará para mostrar la serpiente y el tablero.
     */

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

    /**
     * Sobrescribe el método paint para dibujar la serpiente, el tablero, la comida y los obstaculos
     */
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

                // Para que cada vez que la serpiente cambie de direccion se gire el
                // arco hacia esa direccion junto con el cuerpo
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
                //Para hacer un semiarco para la cabeza de la serpiente
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

    /**
     * Método para que la serpiente avance, cambie de dirección cada vez que se pulsa una flecha,
     * cuando el cuerpo de la serpiente se encuentre con un obstaculo se choque y
     * se acabe la partida, que los obstaculos y la comida no aparezcan encima de la serpiente
     */
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
            vista.setVisible(false);
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

    /**
     * Método para que se genere la comida
     */
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

    /**
     * Método para que se generen diferentes números de obstaculos y en diferentes posiciones
     * además de mostrar donde van a aparecer los siguientes obstaculos
     */
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
