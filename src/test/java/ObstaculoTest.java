import org.junit.jupiter.api.Test;
import ui.canva.Obstaculo;
import ui.canva.Serpiente;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ObstaculoTest {

    @Test
    void testObstaculo() {
        // Create a mock Serpiente object
        Serpiente mockSerpiente = mock(Serpiente.class);

        // Create an instance of Obstaculo with the mock Serpiente object
        Obstaculo obstaculo = new Obstaculo(mockSerpiente);

        // Start the Obstaculo thread
        Thread obstaculoThread = new Thread(obstaculo);
        obstaculoThread.start();

        // Wait for the thread to complete its initial 5-second sleep
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that an obstacle has been generated after the initial 5-second sleep
        assertTrue(!mockSerpiente.getObstaculo().isEmpty(), "An obstacle should have been generated after 5 seconds");

        // Stop the Obstaculo thread
        obstaculo.setGenerar(false);

        // Wait for the thread to complete its next 5-second sleep
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that no more obstacles are generated after stopping the thread
        assertFalse(!mockSerpiente.getObstaculo().isEmpty(), "No more obstacles should be generated after stopping the thread");
    }
}
