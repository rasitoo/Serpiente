package ui.canva;


import servicio.Aceleracion;
import servicio.Movimiento;
import servicio.Obstaculo;
import servicio.ServicioSerpiente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase serpiente, se encarga de pintar las coordenadas de la serpiente, comida y obstaculos
 *
 * @author Rodrigo
 * @author Patricia
 */

public class Serpiente extends JPanel {
    /**
     * La vista que se utilizara para mostrar la serpiente y el tablero.
     */
    private final Vista vista;

    /**
     * Color de la serpiente.
     */
    private Color colorserpiente = new Color(15, 158, 13);

    /**
     * Color de donde va a aparecer el futuro obstaculo
     */
    private Color colorfuturoobstaculo = new Color(83, 44, 3, 120);

    /**
     * Imagen de la comida
     */
    private ImageIcon imgComida = new ImageIcon(".\\src\\main\\java\\imagenes\\manzana.png");

    /**
     * Imagen de los obstaculos
     */
    private ImageIcon imgObst = new ImageIcon(".\\src\\main\\java\\imagenes\\barril-de-vino.png");


    /**
     * Tamano maximo
     * Tamano del cuadrado
     * La cantidad de cuadrados en el tablero.
     * Resto de la division
     */
    private int tammax, tam, can, res; //tamaño maximo, tamaño cuadradito y cantidad de cuadraditos, tabien tenemos el resto

    /**
     * Lista donde se guardaran las coordenadas donde van a aparecer el cuerpo de la serpiente
     */
    private List<int[]> serpiente = new ArrayList<>(); //Un arraylist de longitud variable que contiene arrays de int con las coordenadas de la serpiente

    /**
     * Array donde se guardaran las coordenadas donde va a aparecer la comida
     */
    private int[] comida = new int[2]; //Habrá solo una comida, por lo tanto, con un array normal nos vale

    /**
     * Lista donde se guardaran las coordenadas donde van a aparecer los objetos
     */
    private List<int[]> obstaculo = new ArrayList<>();

    /**
     * Lista donde se guardaran las coordenadas donde van a aparecer los futuros objetos
     */
    private List<int[]> futuroobstaculo = new ArrayList<>();
    /**
     * Numero de obstaculos que se van a generar.
     */
    private int numCuadrados;

    /**
     * El numero de futuros obstaculos que se generaran.
     */
    private int numFuturosCuadrados;
    /**
     * La direccion en la que se movera la serpiente.
     */
    private String dir = "der";

    /**
     * La direccion a la que cambiara el movimiento la serpiente.
     */
    private String cambiodir = "der";

    /**
     * La puntuacion actual.
     */
    private int puntuacion = 0;

    /**
     * El choque con un obstaculo.
     */
    private boolean choque = false;

    /**
     * Hilo donde se generara el movimiento de la serpiente.
     */
    private Thread movim;

    /**
     * El movimiento de la serpiente.
     */
    private Movimiento mov;

    /**
     * Hilo donde se generaran los obstaculos de la serpiente.
     */
    private Thread obst;

    /**
     * Generar los obstaculos de la serpiente.
     */
    private Obstaculo obstaculo1;

    /**
     * Hilo donde se generara la aceleracion de la serpiente.
     */
    private Thread acele;

    /**
     * La aceleracion de la serpiente.
     */
    private Aceleracion aceleracion;
    /**
     * Objeto que hara comprobaciones y calculos
     */
    private ServicioSerpiente servicio = new ServicioSerpiente(this);

    public Color getColorfuturoobstaculo() {
        return colorfuturoobstaculo;
    }


    public Color getColorserpiente() {
        return colorserpiente;
    }

    public Thread getMovim() {
        return movim;
    }

    public Movimiento getMov() {
        return mov;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Aceleracion getAceleracion() {
        return aceleracion;
    }


    public Obstaculo getObstaculo1() {
        return obstaculo1;
    }


    public boolean isChoque() {
        return choque;
    }

    public void setChoque(boolean choque) {
        this.choque = choque;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getNumFuturosCuadrados() {
        return numFuturosCuadrados;
    }

    public int getNumCuadrados() {
        return numCuadrados;
    }

    public int getCan() {
        return can;
    }


    public int getTammax() {
        return tammax;
    }


    public List<int[]> getSerpiente() {
        return serpiente;
    }

    public void setSerpiente(List<int[]> serpiente) {
        this.serpiente = serpiente;
    }

    public int[] getComida() {
        return comida;
    }

    public void setComida(int[] comida) {
        this.comida = comida;
    }

    public List<int[]> getObstaculo() {
        return obstaculo;
    }

    public void setObstaculo(List<int[]> obstaculo) {
        this.obstaculo = obstaculo;
    }

    public List<int[]> getFuturoobstaculo() {
        return futuroobstaculo;
    }

    public void setFuturoobstaculo(List<int[]> futuroobstaculo) {
        this.futuroobstaculo = futuroobstaculo;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }


    public void setCambiodir(String cambiodir) {
        this.cambiodir = cambiodir;
    }

    public Vista getVista() {
        return vista;
    }

    /**
     * Constructor para la clase Serpiente.
     *
     * @param tammax el tamano maximo.
     * @param can    el numero de cuadrados.
     * @param vista  la vista que se utilizara para mostrar la serpiente y el tablero.
     */


    public Serpiente(int tammax, int can, Vista vista) {
        this.vista = vista;
        this.tammax = tammax;
        this.can = can;
        tam = tammax / can;
        res = tammax % can;
        int[] a = {can / 2, can / 2};
        int[] b = {can / 2, can / 2 - 1};
        serpiente.add(a);
        serpiente.add(b);
        generarComida();

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
     * Sobrescribe el metodo paint para dibujar la serpiente, la comida y los obstaculos
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //Cuando pinta lo hace iniciando de nuevo, haciendo que se mueva, es decir, lo que estaba antes lo borra
        for (int i = 0; i < serpiente.size(); i++) {
            if (i == serpiente.size() - 1) {
                Polygon cabeza = crearCabeza(i);
                g.setColor(Color.lightGray);
                g.fillPolygon(cabeza);
            } else if (i % 2 == 0) {
                g.setColor(colorserpiente); //pinta el fondo
                g.fillRect(res / 2 + serpiente.get(i)[0] * tam, res / 2 + serpiente.get(i)[1] * tam, tam, tam); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes

            } else {
                g.setColor(colorserpiente.darker()); //pinta el fondo
                g.fillRect(res / 2 + serpiente.get(i)[0] * tam, res / 2 + serpiente.get(i)[1] * tam, tam, tam); //en el list serpiente tenemos las coordenadas, entonces este bucle pinta las coordenadas correspondientes

            }

        }
        g.drawImage(imgComida.getImage(), res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1, null);

        g.setColor(colorfuturoobstaculo);
        for (int i = 0; i < numFuturosCuadrados; i++) {
            g.fillRect(res / 2 + futuroobstaculo.get(i)[0] * tam, res / 2 + futuroobstaculo.get(i)[1] * tam, tam - 1, tam - 1);
        }
        for (int i = 0; i < numCuadrados; i++) {
            g.drawImage(imgObst.getImage(), res / 2 + obstaculo.get(i)[0] * tam, res / 2 + obstaculo.get(i)[1] * tam, tam - 1, tam - 1, null);
        }
    }

    /**
     * Crea un arco semicircular para la cabeza de la serpiente.
     *
     * @param i Indice de la parte del cuerpo de la serpiente
     * @return Un objeto Polygon que representa el arco semicircular de la cabeza
     */
    private Polygon crearCabeza(int i) {
        Polygon semiArc = new Polygon();

        int radio = tam / 2;
        int centroX = res / 2 + serpiente.get(i)[0] * tam;
        int centroY = (int) (res / 2 + serpiente.get(i)[1] * tam);
        int anguloInicial = 0;
        int anguloFinal = 0;

        // Determina los angulos inicial y final del arco semicircular según la dirección actual de la serpiente
        switch (dir) {
            case "der":
                centroX += 0;
                centroY += tam / 2;
                anguloInicial = 270;
                anguloFinal = 450;
                break;
            case "izq":
                centroX += tam;
                centroY += tam / 2;
                anguloInicial = 90;
                anguloFinal = 270;
                break;
            case "arr":
                centroX += tam / 2;
                centroY += tam;
                anguloInicial = 0;
                anguloFinal = 180;
                break;
            case "aba":
                centroX += tam / 2;
                centroY += 0;
                anguloInicial = 180;
                anguloFinal = 360;
                break;
        }
        // Crea un arco semicircular añadiendo puntos al objeto Polygon según el centro, radio y ángulos calculados
        for (int angulo = anguloInicial; angulo <= anguloFinal; angulo++) {
            int x = centroX + (int) (radio * Math.cos(Math.toRadians(angulo)));
            int y = centroY - (int) (radio * Math.sin(Math.toRadians(angulo)));
            semiArc.addPoint(x, y);
        }
        return semiArc;
    }


    /**
     * Metodo para que la serpiente avance, cambie de direccion cada vez que se pulsa una flecha,
     * cuando el cuerpo de la serpiente se encuentre con un obstaculo se choque y
     * se acabe la partida, que los obstaculos y la comida no aparezcan encima de la serpiente
     */
    public void avanzar() {
        this.dir = this.cambiodir; //Antes de avanzar pone la nueva direccion
        int[] ultimo = serpiente.get((serpiente.size() - 1));
        int[] nuevo = servicio.direccion(ultimo);
        servicio.comprobarChoque(nuevo);
        servicio.accionChoque(nuevo);
    }

    /**
     * Método para que se genere la comida
     */
    public void generarComida() {
        int x = (int) (Math.random() * can);
        int y = (int) (Math.random() * can);
        if (servicio.ocupadoGeneracionComida(x, y)) {
            generarComida();
        } else {
            comida = new int[]{x, y};
        }
    }

    /**
     * Metodo para que se generen diferentes numeros de obstaculos y en diferentes posiciones
     * ademas de mostrar donde van a aparecer los siguientes obstaculos
     *
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
        for (int i = 0; i < numFuturosCuadrados; i++) {
            x += (int) (Math.random() * 2);
            y += (int) (Math.random() * 2);
            futuroobstaculo.add(servicio.comprobarBordeObstaculo(x, y));
        }
        if (servicio.ocupadoGeneracionObstaculo(x,y)) {
            generarObstaculo();
        }
    }

}
