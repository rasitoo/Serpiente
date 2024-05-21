package servicio;

import ui.canva.Serpiente;

import javax.swing.*;

/**
 * Clase que realiza comprobaciones y calculos para la clase serpiente
 *
 * @author Rodrigo
 * @author Patri
 */
public class ServicioSerpiente {
    /**
     * Objeto serpiente sobre el que se hacen calculos
     */
    private Serpiente serpiente;

    /**
     * Constructor para la clase ServicioSerpiente
     *
     * @param serpiente el objeto Serpiente sobre el que se operara
     */
    public ServicioSerpiente(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    /**
     * Metodo que calcula la proxima posicion de la serpiente segun su direccion actual
     *
     * @param ultimo la ultima posicion de la serpiente
     * @return la proxima posicion de la serpiente despues de considerar su direccion
     */
    public int[] direccion(int[] ultimo) {
        int x = 0;
        int y = 0;
        switch (serpiente.getDir()) {
            case "der":
                x = ultimo[0] + 1;
                y = ultimo[1];
                break;
            case "izq":
                x = ultimo[0] - 1;
                y = ultimo[1];
                break;
            case "arr":
                x = ultimo[0];
                y = ultimo[1] - 1;
                break;
            case "aba":
                x = ultimo[0];
                y = ultimo[1] + 1;
                break;
        }
        return comprobarBordeSerpiente(x, y);
    }

    /**
     * Metodo que comprueba si la proxima posicion calculada de la serpiente esta dentro de los límites del tablero y si no aparece en el lado contrario
     *
     * @param x la coordenada x de la proxima posicion
     * @param y la coordenada y de la proxima posicion
     * @return la proxima posicion de la serpiente despues de considerar los limites
     */
    public int[] comprobarBordeSerpiente(int x, int y) {
        if (x >= serpiente.getCan())
            x = 0;
        else if (x < 0)
            x = serpiente.getCan() - 1;
        else if (y >= serpiente.getCan())
            y = 0;
        else if (y < 0)
            y = serpiente.getCan() - 1;
        int[] nuevo = {x, y};
        return nuevo;
    }

    /**
     * Metodo que comprueba si las coordenadas dadas estan dentro de los limites del obstaculo.
     * Si las coordenadas estan fuera de los limites, se ajustan.
     *
     * @param x la coordenada x a verificar
     * @param y la coordenada y a verificar
     * @return las coordenadas ajustadas
     */
    public int[] comprobarBordeObstaculo(int x, int y) {
        if (x >= serpiente.getCan())
            x--;
        else if (x < 0)
            x++;
        else if (y >= serpiente.getCan())
            y--;
        else if (y < 0)
            y++;
        int[] nuevo = {x, y};
        return nuevo;
    }

    /**
     * Metodo que maneja la accion cuando la serpiente choca con si misma o un obstaculo.
     *
     * @param nuevo la nueva posicion de la cabeza de la serpiente
     */
    public void accionChoque(int[] nuevo) {
        if (serpiente.isChoque()) {
            serpiente.getMov().parar();
            JOptionPane.showMessageDialog(serpiente, "Has perdido!!");
        } else {
            if (nuevo[0] == serpiente.getComida()[0] && nuevo[1] == serpiente.getComida()[1]) {
                serpiente.getSerpiente().add(nuevo);
                serpiente.generarComida();
                serpiente.setPuntuacion(serpiente.getPuntuacion() + 5);
                serpiente.getVista().actualizarPuntuacion(serpiente.getPuntuacion());
            } else {
                serpiente.getSerpiente().add(nuevo);
                serpiente.getSerpiente().remove(0);
            }
        }
    }

    /**
     * Metodo que comprueba si la nueva posicion de la cabeza de la serpiente choca con si misma o un obstaculo.
     *
     * @param nuevo la nueva posicion de la cabeza de la serpiente
     */
    public void comprobarChoque(int[] nuevo) {
        for (int[] coordenada : serpiente.getSerpiente()) {
            if (coordenada[0] == nuevo[0] && coordenada[1] == nuevo[1]) {
                serpiente.setChoque(true);
            }
            if (!serpiente.getObstaculo().isEmpty())
                for (int i = 0; i < serpiente.getNumCuadrados(); i++) {
                    if (serpiente.getObstaculo().get(i)[0] == nuevo[0] && serpiente.getObstaculo().get(i)[1] == nuevo[1]) {
                        serpiente.setChoque(true);
                    }
                }
        }
    }

    /**
     * Comprueba si las coordenadas dadas estan ocupadas por la serpiente.
     *
     * @param x la coordenada x a verificar
     * @param y la coordenada y a verificar
     * @return true si las coordenadas estan ocupadas, false en caso contrario
     */
    public boolean ocupadoGeneracionComida(int x, int y) {
        boolean ocupado = false;
        for (int[] coordenada : serpiente.getSerpiente()) {
            if (coordenada[0] == x && coordenada[1] == y) {
                ocupado = true;
                break;
            }
        }
        return ocupado;
    }

    /**
     * Comprueba si las coordenadas dadas estan ocupadas por la serpiente en relacion a un obstaculo futuro.
     *
     * @param x la coordenada x a verificar
     * @param y la coordenada y a verificar
     * @return true si las coordenadas estan ocupadas, false en caso contrario
     */
    public boolean ocupadoGeneracionObstaculo(int x, int y) {
        boolean ocupado = false;
        for (int i = 0; i < serpiente.getNumFuturosCuadrados(); i++) {
            for (int[] coordenada : serpiente.getSerpiente()) {
                if (coordenada[0] == serpiente.getFuturoobstaculo().get(i)[0] && coordenada[1] == serpiente.getFuturoobstaculo().get(i)[1]) {
                    ocupado = true;
                    break;
                }
            }
        }
        return ocupado;
    }
}

