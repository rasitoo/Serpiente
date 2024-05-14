import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.canva.Serpiente;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Data
public class SerpienteTest {

    private Serpiente serpiente;
    private List<int[]> obstaculos;
    private List<int[]> futuroObstaculos;

    @BeforeEach
    void setUp() {
        serpiente = new Serpiente(10, 10, null);
        obstaculos = new ArrayList<>();
        futuroObstaculos = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(10, serpiente.getTammax());
        assertEquals(10, serpiente.getCan());
        assertNull(serpiente.getVista());
        assertEquals(Color.decode("#159853"), serpiente.getColorserpiente());
        assertEquals(Color.decode("#83443"), serpiente.getColorfuturoobstaculo());
        assertEquals(0, serpiente.getPuntuacion());
        assertFalse(serpiente.isChoque());
        assertNull(serpiente.getMovim());
        assertNull(serpiente.getObstaculo1());
        assertNull(serpiente.getAceleracion());
    }

    @Test
    void testPaint() {
        // Create a mock Graphics object
        Graphics mockGraphics = mock(Graphics.class);

        // Call the paint method with the mock object
        serpiente.paint(mockGraphics);

        // Verify that the paint method was called with the correct arguments
        verify(mockGraphics, times(1)).setColor(Color.lightGray);
        verify(mockGraphics, times(1)).fillPolygon(any(Polygon.class));
        verify(mockGraphics, times(1)).setColor(serpiente.getColorserpiente());
        verify(mockGraphics, times(1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        verify(mockGraphics, times(1)).setColor(serpiente.getColorserpiente().darker());
        verify(mockGraphics, times(1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    void testAvanzar() {
        // Set up the initial state of the serpiente
        serpiente.setDir("der");
        serpiente.setCambiodir("izq");
        serpiente.setSerpiente(new ArrayList<>(List.of(new int[]{0, 0}, new int[]{0, 1})));
        serpiente.setComida(new int[]{0, 0});
        serpiente.setObstaculo(new ArrayList<>(List.of(new int[]{0, 0})));
        serpiente.setFuturoobstaculo(new ArrayList<>(List.of(new int[]{0, 0})));

        // Call the avanzar method
        serpiente.avanzar();

        // Verify that the serpiente's state has changed as expected
        assertEquals(1, serpiente.getSerpiente().size());
        assertEquals(new int[]{0, 1}, serpiente.getSerpiente().get(0));
        assertEquals(new int[]{0, 0}, serpiente.getComida());
        assertEquals(new int[]{0, 0}, serpiente.getObstaculo().get(0));
        assertEquals(new int[]{0, 0}, serpiente.getFuturoobstaculo().get(0));
    }

    @Test
    void testGenerarComida() {
        // Call the generarComida method
        serpiente.generarComida();

        // Verify that the comida has been generated in a random position
        assertTrue(serpiente.getComida()[0] >= 0 && serpiente.getComida()[0] < 10);
        assertTrue(serpiente.getComida()[1] >= 0 && serpiente.getComida()[1] < 10);
    }

    @Test
    void testGenerarObstaculo() {
        // Call the generarObstaculo method
        serpiente.generarObstaculo();

        // Verify that the obstaculos have been generated in a random position
        assertTrue(serpiente.getObstaculo().size() >= 0 && serpiente.getObstaculo().size() <= 3);
        for (int[] obstaculo : serpiente.getObstaculo()) {
            assertTrue(obstaculo[0] >= 0 && obstaculo[0] < 10);
            assertTrue(obstaculo[1] >= 0 && obstaculo[1] < 10);
        }

        // Verify that the future obstacles have been generated in a random position
        assertTrue(serpiente.getFuturoobstaculo().size() >= 0 && serpiente.getFuturoobstaculo().size() <= 3);
        for (int[] futuroObstaculo : serpiente.getFuturoobstaculo()) {
            assertTrue(futuroObstaculo[0] >= 0 && futuroObstaculo[0] < 10);
            assertTrue(futuroObstaculo[1] >= 0 && futuroObstaculo[1] < 10);
        }
    }
}
