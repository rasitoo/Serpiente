package servicio;


/**
 * Clase que se ejecuta en un hilo paralelo y se encarga de acelerar la serpiente durante 5 segundos cada 20-40 segundos
 *
 * @author Rodrigo
 * @author Patricia
 */
public class Aceleracion implements Runnable {

    /**
     * Movimiento de la serpiente.
     */
    private Movimiento movimiento;

    /**
     * Constructor de la clase Aceleracion.
     *
     * @param movimiento La clase Movimiento que controla el movimiento de la serpiente.
     */
    public Aceleracion(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Metodo que se ejecuta cuando se inicia el hilo en el que va a acelerar durante 5s cada 20s
     * y despues volvera a su velocidad original
     */
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            movimiento.setVelocidad((int) (movimiento.getVelocidad() * 0.5));
            try {
                Thread.sleep(5000);
                movimiento.setVelocidad(movimiento.getVelocidad() * 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep((long) (Math.random() * 20000 + 20000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
