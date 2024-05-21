import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.canva.Serpiente;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class SerpienteTest {

    private Serpiente serpiente;
    private List<int[]> obstaculos;
    private List<int[]> futuroObstaculos;

    @BeforeEach
    void inicio() {
        serpiente = new Serpiente(10, 10, null);
        obstaculos = new ArrayList<>();
        futuroObstaculos = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(10, serpiente.getTammax());
        assertEquals(10, serpiente.getCan());
        assertNull(serpiente.getVista());
        assertEquals(new Color(15,158,13), serpiente.getColorserpiente());
        assertEquals(new Color(83, 44, 3,120), serpiente.getColorfuturoobstaculo());
        assertEquals(0, serpiente.getPuntuacion());
        assertFalse(serpiente.isChoque());
        assertNotNull(serpiente.getMovim());
        assertNotNull(serpiente.getObstaculo1());
        assertNotNull(serpiente.getAceleracion());
    }

    @Test
    void testPaint() {
        Graphics mockGraphics = mock(Graphics.class);

        serpiente.paint(mockGraphics);

        verify(mockGraphics, times(1)).setColor(Color.lightGray);
        verify(mockGraphics, times(1)).fillPolygon(any(Polygon.class));
        verify(mockGraphics, times(1)).setColor(serpiente.getColorserpiente());
        verify(mockGraphics, times(serpiente.getNumFuturosCuadrados()+1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        verify(mockGraphics, times(1)).setColor(serpiente.getColorserpiente());
        verify(mockGraphics, times(serpiente.getNumFuturosCuadrados()+1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    void testAvanzar() {
        serpiente.setDir("der");
        serpiente.setCambiodir("izq");
        serpiente.setSerpiente(new ArrayList<>(List.of(new int[]{0, 0}, new int[]{0, 1})));
        serpiente.setComida(new int[]{0, 0});
        serpiente.setObstaculo(new ArrayList<>(List.of(new int[]{0, 0})));
        serpiente.setFuturoobstaculo(new ArrayList<>(List.of(new int[]{0, 0})));

        serpiente.avanzar();

        assertEquals(2, serpiente.getSerpiente().size());
        assertArrayEquals(new int[]{0, 1}, serpiente.getSerpiente().get(0));
        assertArrayEquals(new int[]{0, 0}, serpiente.getComida());
        assertArrayEquals(new int[]{0, 0}, serpiente.getObstaculo().get(0));
        assertArrayEquals(new int[]{0, 0}, serpiente.getObstaculo().get(0));
    }

    @Test
    void testGenerarComida() {
        serpiente.generarComida();

        assertTrue(serpiente.getComida()[0] >= 0 && serpiente.getComida()[0] < 10);
        assertTrue(serpiente.getComida()[1] >= 0 && serpiente.getComida()[1] < 10);
    }

    @Test
    void testGenerarObstaculo() {
        serpiente.generarObstaculo();

        assertTrue(!serpiente.getObstaculo().isEmpty() && serpiente.getObstaculo().size() <= 3);
        for (int[] obstaculo : serpiente.getObstaculo()) {
            assertTrue(obstaculo[0] >= 0 && obstaculo[0] < 10);
            assertTrue(obstaculo[1] >= 0 && obstaculo[1] < 10);
        }

        assertTrue(!serpiente.getFuturoobstaculo().isEmpty() && serpiente.getFuturoobstaculo().size() <= 3);
        for (int[] futuroObstaculo : serpiente.getFuturoobstaculo()) {
            assertTrue(futuroObstaculo[0] >= 0 && futuroObstaculo[0] < 10);
            assertTrue(futuroObstaculo[1] >= 0 && futuroObstaculo[1] < 10);
        }
    }
}
