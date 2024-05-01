package ui.canva;

import java.awt.*;

//NOTA: los c�digos de los colores est�n en forma de constantes en Color.ZZZ
//      o se pueden crear en el momento mediante "new Color(int, int, int)".

public interface Lienzo {
	public int getTamX();
	public int getTamY();
	public void redimensionar(int tamX, int tamY);
	
	public void limpiar();
	public void marcarPixel(int x, int y, Color color);
	public void escribirTexto(int x, int y, String texto, Color color);
	
	public void volcar();
}