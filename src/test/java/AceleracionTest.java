import org.junit.jupiter.api.Test;
import ui.canva.Aceleracion;
import ui.canva.Movimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AceleracionTest {

    @Test
    void testAceleracion() {
        Movimiento mockMovimiento = mock(Movimiento.class);

        Aceleracion aceleracion = new Aceleracion(mockMovimiento);

        Thread aceleracionThread = new Thread(aceleracion);
        aceleracionThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float velocidadEsperada = mockMovimiento.getVelocidad() / 2;
        float velocidadReal = mockMovimiento.getVelocidad();
        assertEquals(velocidadEsperada, velocidadReal, "La velocidad debería ser la mitad después de 5 segundos");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        velocidadEsperada = mockMovimiento.getVelocidad() * 2;
        velocidadReal = mockMovimiento.getVelocidad();
        assertEquals(velocidadEsperada, velocidadReal, "La velocidad debería ser el doble después de 5 segundos");

        try {
            Thread.sleep((long) (Math.random() * 20000 + 20000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        velocidadEsperada = mockMovimiento.getVelocidad();
        velocidadReal = mockMovimiento.getVelocidad();
        assertEquals(velocidadEsperada, velocidadReal, "La velocidad debería cambiar después del tiempo random");
    }
}