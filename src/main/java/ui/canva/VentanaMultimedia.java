package ui.canva;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//NOTA: los c�digos de los colores est�n en forma de constantes en Color.ZZZ
//o se pueden crear en el momento mediante "new Color(int, int, int)".

public class VentanaMultimedia extends JFrame implements Lienzo {
	private static final long serialVersionUID = 1L;

	private static final int PIXELES_EXTRA_ALTO_VENTANA = 28;
	private static final int PIXELES_EXTRA_ANCHO_VENTANA = 6;

	private int ancho, alto;
	private int tamPixel;
	private Color colorFondo;
	
	private GraphicsConfiguration configuracionGraficaSistema;
	
	// El lienzo Java sobre el que dibujaremos.
	private Canvas canvas;
	
	// Imagen en b�ffer. Aqu� se ir� pintando y, cuando toque volcar,
	// sustituiremos la imagen visible por esta.
	private BufferedImage imagenEnBuffer;
	private Graphics2D imagenG2D; // Este es el Graphics2D propio de la imagen.

	// El Teclado que engancharemos a la ventana y que pondremos
	// disponible para terceros, para que puedan consultar las teclas.
	private Teclado teclado = new Teclado();

	public VentanaMultimedia(String tituloVentana, int ancho, int alto, int tamPixel, Color colorFondo) {
		super(tituloVentana);
		
		this.ancho = ancho;
		this.alto = alto;
		this.tamPixel = tamPixel;
		this.colorFondo = colorFondo;
		
		// Se obtiene la configuraci�n gr�fica del sistema y se guarda,
		// ya que ser� necesaria para algunas cuestiones.
		GraphicsEnvironment entornoGrafico = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice adaptadorGrafico = entornoGrafico.getDefaultScreenDevice();
		configuracionGraficaSistema = adaptadorGrafico.getDefaultConfiguration();

		// A continuaci�n vienen aspectos de "ventanas Java".
		
		// Se impide redimensionar la ventana.
		setResizable(false);

		// Se le dice que al cerrar la ventana se termine la aplicaci�n.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Se crea un nuevo componente (java.awt.Canvas) de tipo lienzo (Canvas). 
		canvas = new Canvas();		
		add(canvas); // Y se a�ade a este JFrame.

		ajustarTamannoMarco();
		
		// Se pide ignorar el repintado. Necesario al usar doble b�ffer.
		setIgnoreRepaint(true);
		canvas.setIgnoreRepaint(true);

		// Se ordena al JFrame que procese todo lo que le hemos dicho.
		pack();
		
		// Se indica que la ventana deber� ser visible a partir de este momento.
		setVisible(true);
		
		// Se crea una estrategia de doble b�ffer, para poder actualizar la
		// pantalla sin parpadeos (solo se modifica la imagen visible cuando
		// se tiene listo el "siguiente fotograma").
		canvas.createBufferStrategy(2);

		// Se "engancha" nuestro teclado al JFrame y al Canvas, para que ambos
		// informen de las teclas pulsadas que les lleguen.
		// Es necesario engancharlo a AMBOS. Al JFrame, para cuando est� activo �l
		// (=tenga el "foco") y al Canvas para cuando s� est� activo el Canvas.
		this.addKeyListener(teclado);
		canvas.addKeyListener(teclado);
	}

	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public Teclado getTeclado() {
		return teclado;
	}

	private void ajustarTamannoMarco() {
		// Se calcula e indica la posici�n X, Y as� como el tama�o X, Y del JFrame.
		// La posici�n se calcula para que la ventana quede centrada en la pantalla.
		// Se suman algunos p�xeles adicionales para tener en cuenta que el JFrame
		// tiene un t�tulo y unos bordes que tambi�n ocupan algunos p�xeles.  
		int posXMarco = (configuracionGraficaSistema.getBounds().width - (ancho*tamPixel + PIXELES_EXTRA_ANCHO_VENTANA)) / 2;
		int posYMarco = (configuracionGraficaSistema.getBounds().height - (alto*tamPixel + PIXELES_EXTRA_ALTO_VENTANA)) / 2;
		setBounds(posXMarco, posYMarco, ancho*tamPixel + PIXELES_EXTRA_ANCHO_VENTANA, alto*tamPixel + PIXELES_EXTRA_ALTO_VENTANA);

		// Se le establece el tama�o al lienzo.
		canvas.setSize(ancho*tamPixel, alto*tamPixel);
	}

	public void limpiar() {
		imagenEnBuffer = configuracionGraficaSistema.createCompatibleImage(ancho*tamPixel, alto*tamPixel);		
		imagenG2D = imagenEnBuffer.createGraphics();
		imagenG2D.setColor(colorFondo);
		imagenG2D.fillRect(0, 0, ancho*tamPixel, alto*tamPixel);
	}
	
	// Ejecutar este m�todo requiere haber llamado previamente a limpiar().
	public void marcarPixel(int x, int y, Color color) {
		// Se pinta el pixel sobre el Graphics2D de la imagen que tenemos en el buffer.
		imagenG2D.setColor(color);
		imagenG2D.fillRect(x*tamPixel, y*tamPixel, tamPixel, tamPixel);
	}
	
	public void escribirTexto(int x, int y, String texto, Color color) {
		// Actualmente, la altura del texto puede sobrepasar la altura
		// de un pixel y, en este momento, no s� c�mo se ajusta.
		// Habr� que hacer un imagenG2D.getFont(), modificar el font
		// y finalmente un setFont(), pero no s� c�mo modificar el font.
		
		imagenG2D.setColor(color);

		// Se realiza un ajuste ya que la posici�n indicada corresponde al
		// punto inferior izquierdo del texto (no al superior izquierdo).
		imagenG2D.drawString(texto, x*tamPixel, (y+1)*tamPixel);
	}

	// Ejecutar este m�todo requiere haber llamado previamente a limpiar(),
	// y, si se quiere representar algo, tambi�n a marcarPixel(), claro.
	public void volcar() {
		BufferStrategy buffer = canvas.getBufferStrategy();
		Graphics graphics = buffer.getDrawGraphics();
		
		graphics.drawImage(imagenEnBuffer, 0, 0, null);
		
		// Este if es por cosas de Windows.		
		if (!buffer.contentsLost()) buffer.show();
	}

	public int getTamX() {
		return ancho;
	}
	public int getTamY() {
		return alto;
	}

	public void redimensionar(int tamX, int tamY) {
		ancho = tamX;
		alto = tamY;	
		ajustarTamannoMarco();
	}
}