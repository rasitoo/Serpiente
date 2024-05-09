package ui.canva;

public class Obstaculo implements Runnable {
    Serpiente serpiente;
    boolean generar = true;

    Obstaculo(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

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
