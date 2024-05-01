package ui.canva;
public class Utilidades {
	public static void espera(int milisegundos) {
		// Se espera unos milisegundos.
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			// No hacemos nada/anulamos la posible excepci�n.			
		}
	}
}
