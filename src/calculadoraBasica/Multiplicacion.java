package calculadoraBasica;

public class Multiplicacion extends Operacion{
	private double multiplicacion;

	public Multiplicacion(double num1, double num2) {
		super(num1, num2, '*');
		this.multiplicacion = num1 * num2;
		this.setRes(this.multiplicacion);
	}
}