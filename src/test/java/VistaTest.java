import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.canva.Vista;

class VistaTest {

    private Vista vista;

    @BeforeEach
    void setUp() {
        vista = new Vista("Test", 500, "dificil");
    }

    @Test
    void testActualizarPuntuacionConZero() {
        vista.actualizarPuntuacion(0);
        assertEquals("Puntuacion: 0", vista.getPuntuacion().getText());
    }

    @Test
    void testActualizarPuntuacionPositiva() {
        vista.actualizarPuntuacion(10);
        assertEquals("Puntuacion: 10", vista.getPuntuacion().getText());
    }

    @Test
    void testActualizarPuntuacionNegativa() {
        vista.actualizarPuntuacion(-5);
        assertEquals("Puntuacion: -5", vista.getPuntuacion().getText());
    }

    @Test
    void testActualizarPuntuacionPositivaConZero() {
        vista.actualizarPuntuacion(0);
        assertEquals("Puntuacion: 0", vista.getPuntuacion().getText());
        vista.actualizarPuntuacion(10);
        assertEquals("Puntuacion: 10", vista.getPuntuacion().getText());
    }

    @Test
    void testActualizarPuntuacionConZeroYNegativa() {
        vista.actualizarPuntuacion(0);
        assertEquals("Puntuacion: 0", vista.getPuntuacion().getText());
        vista.actualizarPuntuacion(-5);
        assertEquals("Puntuacion: -5", vista.getPuntuacion().getText());
    }
}