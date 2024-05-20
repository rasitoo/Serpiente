import org.junit.jupiter.api.Test;
import ui.menu.MenuPrincipal;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
    @Test
    public void testMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal("menu");

        // Check if the window is visible
        assertTrue(menuPrincipal.getContentPane().isVisible());

        // Check if the window has the correct size
        assertEquals(500, menuPrincipal.getVentana().getWidth());
        assertEquals(320, menuPrincipal.getVentana().getHeight());

        // Check if the window has the correct title
        assertEquals("menu", menuPrincipal.getTitle());

        // Check if the window has the correct background image
        assertTrue(menuPrincipal.getVentana().getContentPane().getComponentCount() > 0);
        assertTrue(menuPrincipal.getVentana().getContentPane().getComponent(0) instanceof JLabel);

        // Check if the window has the correct button
        assertTrue(menuPrincipal.getVentana().getContentPane().getComponentCount() > 1);
        assertTrue(menuPrincipal.getVentana().getContentPane().getComponent(1) instanceof JButton);
        assertTrue(((JButton) menuPrincipal.getVentana().getContentPane().getComponent(1)).getText().equals("Jugar"));
    }
}
