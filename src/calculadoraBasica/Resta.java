package calculadoraBasica;

public class Resta extends Operacion{
	private double resta;

	public Resta(double num1, double num2) {
		super(num1, num2, '-');
		this.resta = num1 - num2;
		this.setRes(this.resta);
	}
}