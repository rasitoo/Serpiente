package ui.canva;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PrincipalDemoCuadrado {
    private static final int DELAY = 500; // Milisegundos.

    public static void main(String[] args) {
        VentanaMultimedia ventana = new VentanaMultimedia("Serpiente", 20, 20, 20, Color.cyan);

        int x = 10, y = 10; //posicion inicial del cuadrado
        int dir = 0; //si la direccion es 0 va hacia la izquierda, 1 arriba, 2 derecha, 3 abajo.

        boolean modoTecladoContinuo = false; // true: modo continuo / false: modo "1-vez". Lo ponemosde 1 vez ya que sirve para cambiar la direccion de la serpiente

        Teclado teclado = ventana.getTeclado();

        while (true) { // Al cerrar la ventana se acaba la aplicaci�n.
            teclado.tick();

            ventana.limpiar();

            switch (dir) {
                case 0:
                    x--;
                    break;
                case 1:
                    y--;
                    break;
                case 2:
                    x++;
                    break;
                case 3:
                    y++;
                    break;
            }

            if (modoTecladoContinuo) {
                if (teclado.pulsada(KeyEvent.VK_LEFT) && x > 0) x--;
                if (teclado.pulsada(KeyEvent.VK_RIGHT) && x < ventana.getAncho() - 1) x++;
                if (teclado.pulsada(KeyEvent.VK_UP) && y > 0) y--;
                if (teclado.pulsada(KeyEvent.VK_DOWN) && y < ventana.getAlto() - 1) y++;
            } else {
                if (teclado.pulsada1Vez(KeyEvent.VK_LEFT) && x > 0) dir = 0;
                if (teclado.pulsada1Vez(KeyEvent.VK_RIGHT) && x < ventana.getAncho() - 1) dir = 2;
                if (teclado.pulsada1Vez(KeyEvent.VK_UP) && y > 0) dir = 1;
                if (teclado.pulsada1Vez(KeyEvent.VK_DOWN) && y < ventana.getAlto() - 1) dir = 3;
            }

            ventana.marcarPixel(x, y, Color.GRAY);


            ventana.volcar();

            Utilidades.espera(DELAY);
        }
    }
}
