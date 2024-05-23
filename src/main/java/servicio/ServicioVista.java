package servicio;

import ui.canva.Vista;

import java.awt.event.KeyEvent;

/**
 * Clase servicio encargada de comprobaciones y calculos para la clase vista
 *
 * @author Rodrigo
 * @author Patri
 */
public class ServicioVista {
    /**
     * La vista que se utilizara para mostrar la serpiente y el tablero.
     */
    private Vista vista;

    /**
     * Constructor de la clase ServicioVista
     * @param vista
     */
    public ServicioVista(Vista vista) {
        this.vista = vista;
    }


    /**
     * Metodo que se llama cuando se presiona una tecla.
     * Actualiza el movimiento de la serpiente en funcion de la tecla presionada.
     *
     * @param event el evento de teclado.
     */
    public void tecla(KeyEvent event) {

        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!vista.getSerpiente().getDir().equals("izq") && !vista.getSerpiente().getDir().equals("der"))
                    vista.getSerpiente().setCambiodir("izq");
                break;
            case KeyEvent.VK_RIGHT:
                if (!vista.getSerpiente().getDir().equals("izq") && !vista.getSerpiente().getDir().equals("der"))
                    vista.getSerpiente().setCambiodir("der");
                break;
            case KeyEvent.VK_UP:
                if (!vista.getSerpiente().getDir().equals("arr") && !vista.getSerpiente().getDir().equals("aba"))
                    vista.getSerpiente().setCambiodir("arr");
                break;
            case KeyEvent.VK_DOWN:
                if (!vista.getSerpiente().getDir().equals("arr") && !vista.getSerpiente().getDir().equals("aba"))
                    vista.getSerpiente().setCambiodir("aba");
                break;
        }
    }
}
