import org.junit.jupiter.api.Test;
import ui.canva.Aceleracion;
import ui.canva.Movimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AceleracionTest {

    @Test
    void testAceleracion() {
        // Create a mock Movimiento object
        Movimiento mockMovimiento = mock(Movimiento.class);

        // Create an instance of Aceleracion with the mock Movimiento object
        Aceleracion aceleracion = new Aceleracion(mockMovimiento);

        // Start the Aceleracion thread
        Thread aceleracionThread = new Thread(aceleracion);
        aceleracionThread.start();

        // Wait for the thread to complete its initial 5-second sleep
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the velocity has been halved after the initial 5-second sleep
        float expectedVelocity = mockMovimiento.getVELOCIDAD() / 2;
        float actualVelocity = mockMovimiento.getVELOCIDAD();
        assertEquals(expectedVelocity, actualVelocity, "Velocity should be halved after 5 seconds");

        // Wait for the thread to complete its next 5-second sleep and velocity doubling
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the velocity has been doubled after the next 5-second sleep
        expectedVelocity = mockMovimiento.getVELOCIDAD() * 2;
        actualVelocity = mockMovimiento.getVELOCIDAD();
        assertEquals(expectedVelocity, actualVelocity, "Velocity should be doubled after 5 seconds");

        // Wait for the thread to complete its random sleep duration
        try {
            Thread.sleep((long) (Math.random() * 20000 + 20000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the velocity has been updated again after the random sleep duration
        expectedVelocity = mockMovimiento.getVELOCIDAD();
        actualVelocity = mockMovimiento.getVELOCIDAD();
        assertEquals(expectedVelocity, actualVelocity, "Velocity should be updated after the random sleep duration");
    }
}