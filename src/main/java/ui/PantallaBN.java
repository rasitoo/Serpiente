package ui;

import java.awt.*;

public class PantallaBN implements Lienzo {
	private char[][] contenido;
	private int tamX, tamY;
	
	public PantallaBN(int tamX, int tamY) {
		redimensionar(tamX, tamY);
	}

	public int getTamX() {
		return tamX;
	}

	public int getTamY() {
		return tamY;
	}
	
	public void redimensionar(int tamX, int tamY) {
		this.tamX = tamX;
		this.tamY = tamY;
		this.contenido = new char[tamX][tamY];
	}

	public void limpiar() {
		for (int y = 0; y < tamY; y++) {
			for (int x = 0; x < tamX; x++) {
				contenido[x][y] = ' ';
			}
		}
	}

	public void escribirTexto(int x, int y, String texto, Color color) {
		// El color, lo ignoraremos, ya que somos en blanco y negro.
		
		for (int caracter=0; caracter<texto.length(); caracter++) {
			contenido[x+caracter][y] = texto.charAt(caracter);
		}
	}
	
	public void marcarPixel(int x, int y, Color color) {
		assert ((x >= 0) && (x < tamX) && (y >= 0) && (y < tamY));

		// Como somos una pantalla en blanco y negro y adem�s en texto,
		// convertiremos el color primero a un valor de luminosidad
		// y luego, seg�n la luminosidad, a un caracter.
		
		double luminosidad = extraerLuminosidad(color);
		
		if (luminosidad < 0.10) {
			contenido[x][y] = ' ';
		} else if (luminosidad < 0.30) {
			contenido[x][y] = '�';
		} else if (luminosidad < 0.50) {
			contenido[x][y] = ':';
		} else if (luminosidad < 0.70) {
			contenido[x][y] = '+';
		} else if (luminosidad < 0.90) {
			contenido[x][y] = '*';
		} else {
			contenido[x][y] = '#';
		} 
	}
	
	private double extraerLuminosidad(Color color) {
		// Se calcula la luminosidad mediante los porcentajes que se consideran habituales:
		return (0.2989 * color.getRed() + 0.5870 * color.getGreen() + 0.1140 * color.getBlue()) / 255;
	}

	public void volcar() {
		System.out.println("\n\n\n");
		
		System.out.print("+");
		for (int y = 0; y < tamX; y++) {
			System.out.print("--");
		}
		System.out.print("-+\n");

		for (int y = 0; y < tamY; y++) {
			System.out.print("|");
			for (int x = 0; x < tamX; x++) {
				System.out.print(" " + contenido[x][y]);
			}
			System.out.print(" |\n");
		}

		System.out.print("+");
		for (int y = 0; y < tamX; y++) {
			System.out.print("--");
		}
		System.out.print("-+\n");
	}
}