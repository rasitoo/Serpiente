import org.junit.jupiter.api.Test;
import servicio.Movimiento;
import ui.canva.Serpiente;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimientoTest {
    @Test
    public void testMovimiento() {
        Serpiente serpiente = new Serpiente(10, 10, null);
        Movimiento movimiento = new Movimiento(serpiente);
        movimiento.setVelocidad(100);
        assertTrue(movimiento.getVelocidad() == 100);

        Thread thread = new Thread(movimiento);
        thread.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(serpiente.getX()>0){
            assertTrue(serpiente.getX() > 0);
        }else{
            assertTrue(serpiente.getX() <= 0);
        }

        if(serpiente.getY()>0){
            assertTrue(serpiente.getY() > 0);
        }else{
            assertTrue(serpiente.getY() <= 0);
        }


        movimiento.parar();
    }
}
