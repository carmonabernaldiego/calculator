package calculadoraCientífica;

public class Operacion {
	private char operacion;
	private double num1;
	private double num2;
	private double resultado;

	public Operacion(double num1, double num2, char operacion) {
		this.num1 = num1;
		this.num2 = num2;
		this.operacion = operacion;
	}

	public void setRes(double res) {
		this.resultado = res;
	}

	public double getRes() {
		return resultado;
	}

	public void imprimirResultado(){
		System.out.println(this.num1 + " " + this.operacion + " " + this.num2 + " = " + this.resultado);
	}
}