package ui;
// Un objeto que sea Dibujable es un objeto al que
// le puedo pedir que se pinte en un lienzo que yo le doy.
// El objeto, desde este m�todo, lanzar� al Lienzo las
// llamadas que sean necesarias para pintarse. Como el objeto
// sabe en qu� posici�n est� y sabe cu�l es su aspecto,
// el objeto sabr� qu� p�xeles tendr� que marcar o qu� textos
// deber� escribir.

public interface Dibujable {
	public void setLienzo(Lienzo lienzo);
	public void dibujar();
}