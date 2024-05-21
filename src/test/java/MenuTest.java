import org.junit.jupiter.api.Test;
import ui.menu.MenuPrincipal;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
    @Test
    public void testMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal("menu");

        assertTrue(menuPrincipal.getContentPane().isVisible());

        assertEquals(500, menuPrincipal.getVentana().getWidth());
        assertEquals(320, menuPrincipal.getVentana().getHeight());

        assertEquals("menu", menuPrincipal.getTitle());

        assertTrue(menuPrincipal.getVentana().getContentPane().getComponentCount() > 0);
        assertTrue(menuPrincipal.getVentana().getContentPane().getComponent(0) instanceof JLabel);

        assertTrue(menuPrincipal.getVentana().getContentPane().getComponentCount() > 1);
        assertTrue(menuPrincipal.getVentana().getContentPane().getComponent(1) instanceof JButton);
        assertTrue(((JButton) menuPrincipal.getVentana().getContentPane().getComponent(1)).getText().equals("Jugar"));
    }
}
