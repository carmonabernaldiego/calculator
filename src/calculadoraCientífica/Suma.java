package calculadoraCientífica;

public class Suma extends Operacion {
	private double suma;

	public Suma(double num1 ,double num2) {
		super(num1, num2 ,'+');
		this.suma = num1 + num2;
		this.setRes(this.suma);
	}
}