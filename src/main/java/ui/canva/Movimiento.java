package ui.canva;

/**
 * @author Rodrigo
 * @date 03 mayo, 2024
 */
public class Movimiento implements Runnable {
    Serpiente serpiente;
    static final int VELOCIDAD = 250; //Menos es mas velocidad
    boolean movimiento = true;

    public Movimiento(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

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
    public void parar(){
        this.movimiento = false;
    }
}
