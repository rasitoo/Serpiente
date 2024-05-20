import org.junit.jupiter.api.Test;
import ui.canva.Fondo;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FondoTest {
    @Test
    public void testFondo() {
        Fondo fondo = new Fondo(200, 10);
        JFrame frame = new JFrame("Test Fondo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setContentPane(fondo);
        frame.setVisible(true);

        // Check if the background color is set correctly
        assertTrue(fondo.getColorFondo().equals(Color.gray));

        // Check if the size and number of squares are set correctly
        assertEquals(200, fondo.getTamMax());
        assertEquals(10, fondo.getCan());
        assertEquals(20, fondo.getTam());
        assertEquals(0, fondo.getRes());

    }
}
