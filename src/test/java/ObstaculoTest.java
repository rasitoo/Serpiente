import org.junit.Test;
import servicio.Obstaculo;
import ui.canva.Serpiente;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ObstaculoTest {

    @Test
    public void testObstaculo() {
        Serpiente mockSerpiente = mock(Serpiente.class);

        Obstaculo obstaculo = new Obstaculo(mockSerpiente);

        Thread obstaculoThread = new Thread(obstaculo);
        obstaculoThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        obstaculo.setGenerar(false);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(!mockSerpiente.getObstaculo().isEmpty(), "No deberían generarse obstaculos despues de parar el hilo");
    }
}
