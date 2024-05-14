package ui.canva;


/**
 * @author Rodrigo
 * @author Patricia
 */
public class Obstaculo implements Runnable {

    /**
     * La clase serpiente donde se generarán los obstáculos.
     */
    private Serpiente serpiente;

    /**
     * Indica si se generan obstáculos.
     */
    private boolean generar = true;

    /**
     * Constructor para la clase Obstaculo.
     *
     * @param serpiente panel donde está de la serpiente en el que generará los obstáculos
     */
    public Obstaculo(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    public boolean isGenerar() {
        return generar;
    }

    public void setGenerar(boolean generar) {
        this.generar = generar;
    }

    /**
     * Sobrescribe el método run para generar los obstáculos todo el rato cada 5 segundos.
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
