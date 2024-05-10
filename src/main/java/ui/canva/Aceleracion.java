package ui.canva;

/**
 * @author Rodrigo
 * @date 10 mayo, 2024
 */
public class Aceleracion implements Runnable {
    Movimiento movimiento;

    Aceleracion(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    @Override
    public void run() {
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
