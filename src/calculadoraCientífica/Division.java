package calculadoraCientífica;

public class Division extends Operacion {
	double division = 0;

	public Division(double num1, double num2) {
		super(num1, num2, '/');
		this.division = num1 / num2;
		this.setRes(this.division);
	}
}