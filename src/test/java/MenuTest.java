import org.junit.jupiter.api.Test;
import ui.menu.MenuPrincipal;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
    @Test
    public void testMenuPrincipal() {
        JFrame ventana = new JFrame("Test Menu");
        MenuPrincipal.menu();

        // Check if the window is visible
        assertTrue(ventana.isVisible());

        // Check if the window has the correct size
        assertEquals(500, ventana.getWidth());
        assertEquals(320, ventana.getHeight());

        // Check if the window has the correct title
        assertEquals("Menú", ventana.getTitle());

        // Check if the window has the correct background image
        assertTrue(ventana.getContentPane().getComponentCount() > 0);
        assertTrue(ventana.getContentPane().getComponent(0) instanceof JLabel);
        assertTrue(((JLabel) ventana.getContentPane().getComponent(0)).getIcon().getImage().equals(new ImageIcon(".\\src\\main\\java\\imagenes\\fondo3.png").getImage()));

        // Check if the window has the correct cursor
        assertTrue(ventana.getCursor().getIcon().getImage().equals(new ImageIcon(".\\src\\main\\java\\imagenes\\cursor1.png").getImage()));

        // Check if the window has the correct button
        assertTrue(ventana.getContentPane().getComponentCount() > 1);
        assertTrue(ventana.getContentPane().getComponent(1) instanceof JButton);
        assertTrue(((JButton) ventana.getContentPane().getComponent(1)).getText().equals("Jugar"));
    }
}
