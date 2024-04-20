package ui;
// Un objeto que sea "Tickable" es un objeto que est� interesado
// en saber c�mo van pasando unidades de tiempo.
// Por CADA UNA de estas unidades de tiempo, yo tengo la
// obligaci�n de llamar a su m�todo tick().
// El objeto, a su vez, actualizar� su estado interno en el m�todo
// (ubicaci�n//movimiento, puntuaciones, etc.).

public interface Tickable {
	public void tick();
}