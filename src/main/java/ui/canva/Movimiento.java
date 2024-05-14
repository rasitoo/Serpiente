package ui.canva;

import java.util.Timer;
import java.util.TimerTask;

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
    private int VELOCIDAD = 250;

    /**
     * Indica si el movimiento está activo.
     */
    private boolean movimiento = true;

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
                Thread.sleep(VELOCIDAD);
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

    /**
     * Establece la velocidad de la serpiente en milisegundos.
     *
     * @param VELOCIDAD la velocidad en milisegundos
     */
    public void setVELOCIDAD(int VELOCIDAD) {
        this.VELOCIDAD = VELOCIDAD;
    }

    /**
     * Obtiene la velocidad de la serpiente en milisegundos.
     *
     * @return la velocidad en milisegundos
     */
    public int getVELOCIDAD() {
        return VELOCIDAD;
    }
}
