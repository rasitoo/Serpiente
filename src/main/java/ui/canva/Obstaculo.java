package ui.canva;


/**
 * Clase que se ejecuta en un hilo paralelo y se encarga de añadir los obstaculos cada 5 segundos en el panel serpiente
 *
 * @author Rodrigo
 * @author Patricia
 */
public class Obstaculo implements Runnable {

    /**
     * La clase serpiente donde se generaran los obstaculos.
     */
    private Serpiente serpiente;

    /**
     * Indica si se generan obstaculos.
     */
    private boolean generar = true;

    /**
     * Constructor para la clase Obstaculo.
     *
     * @param serpiente panel donde esta de la serpiente en el que generara los obstaculos
     */
    public Obstaculo(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    public void setGenerar(boolean generar) {
        this.generar = generar;
    }

    /**
     * Sobrescribe el metodo run para generar los obstaculos cada 5 segundos.
     */
    @Override
    public void run() {
        while (generar) {
            serpiente.generarObstaculo();
            serpiente.repaint();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
