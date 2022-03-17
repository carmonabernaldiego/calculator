package calculadoraBasica;

public class Multiplicacion extends Operacion{
	double multi;

	public Multiplicacion(double n1, double n2) {
		super(n1, n2, '*');
		this.multi = n1 * n2;
		this.setRes(this.multi);
	}
}