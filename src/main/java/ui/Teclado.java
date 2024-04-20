package ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// NOTA: los c�digos de las teclas est�n en forma de constantes en:
//       KeyEvent.VK_ZZZ

public class Teclado implements KeyListener, Tickable {
	private static final int NUM_TECLAS = 256;
	
	private boolean[] estadoTeclasBasico; // Estado actual del teclado.

	private enum EstadoTeclaCon1Vez {
		LIBRE,           // No abajo.
		PRESIONADA_1VEZ, // Abajo por primera vez.
		PRESIONADA_MAS   // Abajo, pero no por primera vez.
	}

	private EstadoTeclaCon1Vez[] estadoTeclasCon1Vez; // Estado del teclado ya analizado.

	public Teclado() {
		estadoTeclasBasico = new boolean[NUM_TECLAS];
		
		estadoTeclasCon1Vez = new EstadoTeclaCon1Vez[NUM_TECLAS];
		// Se inicializa el estado 
		for (int i = 0; i < NUM_TECLAS; ++i) {
			estadoTeclasCon1Vez[i] = EstadoTeclaCon1Vez.LIBRE;
		}
	}
	
	public boolean pulsada(int codigoTecla) {
		return estadoTeclasBasico[codigoTecla];
	}

	public synchronized void procesarEstadosCon1Vez() {
		for (int i = 0; i < NUM_TECLAS; ++i) {
			if (estadoTeclasBasico[i]) { // La tecla est� pulsada.
				if (estadoTeclasCon1Vez[i] == EstadoTeclaCon1Vez.LIBRE) {
					// La tecla est� abajo ahora pero no lo estaba antes.
					estadoTeclasCon1Vez[i] = EstadoTeclaCon1Vez.PRESIONADA_1VEZ;
				} else {
					// La tecla est� abajo ahora y YA lo estaba antes.
					estadoTeclasCon1Vez[i] = EstadoTeclaCon1Vez.PRESIONADA_MAS;
				}
			} else { // La tecla no est� pulsada.
				estadoTeclasCon1Vez[i] = EstadoTeclaCon1Vez.LIBRE;
			}
		}
	}

	public boolean pulsada1Vez(int codigoTecla) {
		return estadoTeclasCon1Vez[codigoTecla] == EstadoTeclaCon1Vez.PRESIONADA_1VEZ;
	}

	// M�todo de la interfaz KeyListener. NO necesitamos llamarlo nosotros,
	// es un m�todo para que "el sistema" nos informe de teclas pulsadas.
	public synchronized void keyPressed(KeyEvent evento) {
		int codigo = evento.getKeyCode();
		
		// Solo se actualiza la tecla si el c�digo est� entre las
		// teclas "normales" (ya que puede haber otras multimedia, etc.).
		if (codigo >= 0 && codigo < NUM_TECLAS) {
			estadoTeclasBasico[codigo] = true;
		}
	}

	// Metodo de la interfaz KeyListener. NO necesitamos llamarlo nosotros,
	// es un metodo para que "el sistema" nos informe de teclas pulsadas.
	public synchronized void keyReleased(KeyEvent evento) {
		int codigo = evento.getKeyCode();

		// Solo se actualiza la tecla si el c�digo est� entre las
		// teclas "normales" (ya que puede haber otras multimedia, etc.).
		if (codigo >= 0 && codigo < NUM_TECLAS) {
			estadoTeclasBasico[codigo] = false;
		}
	}

	// M�todo de la interfaz KeyListener. NO necesitamos llamarlo nosotros,
	// es un m�todo para que "el sistema" nos informe de teclas pulsadas.
	public void keyTyped(KeyEvent e) {
		// No necesitamos este m�todo pero como la interfaz
		// KeyListener nos obliga a implementarlo, lo ponemos vac�o.
	}

	public void tick() {
		procesarEstadosCon1Vez();
	}
}