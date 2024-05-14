package ui.canva;


/**
 * @author Rodrigo
 * @author Patricia
 */
public class Movimiento implements Runnable {

    /**
     * La serpiente que se moverá.
     */
    private Serpiente serpiente;

    /**
     * La velocidad de la serpiente en milisegundos.
     * Menos valor es mayor velocidad.
     */
    private int velocidad = 250;

    /**
     * Indica si el movimiento está activo.
     */
    private boolean movimiento = true;

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Constructor para la clase Movimiento.
     *
     * @param serpiente la serpiente que se moverá
     */
    public Movimiento(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    /**
     * Sobrescribe el método run para realizar el movimiento de la serpiente continuamente a una
     * velocidad determinada.
     */
    @Override
    public void run() {
        while (movimiento) {
            serpiente.avanzar();
            serpiente.repaint();
            try {
                Thread.sleep(velocidad);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Detiene el movimiento de la serpiente.
     */
    public void parar() {
        this.movimiento = false;
    }
}
