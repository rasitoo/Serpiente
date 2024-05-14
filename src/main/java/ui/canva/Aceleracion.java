package ui.canva;

import lombok.Data;

/**
 * @author Rodrigo
 * @author Patricia
 */
@Data
public class Aceleracion implements Runnable {

    /**
     * Movimiento de la serpiente.
     */
    Movimiento movimiento;

    /**
     * Constructor de la clase Aceleracion.
     *
     * @param movimiento La clase Movimiento que controla el movimiento de la serpiente.
     */
    public Aceleracion(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Método que se ejecuta cuando se inicia el hilo en el que va a acelerar durante 5s cada 20s
     * y después volverá a su velocidad original
     */
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            movimiento.setVELOCIDAD((int) (movimiento.getVELOCIDAD() * 0.5));
            try {
                Thread.sleep(5000);
                movimiento.setVELOCIDAD(movimiento.getVELOCIDAD() * 2);
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
