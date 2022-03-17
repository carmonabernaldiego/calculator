package calculadoraBasica;

public class Division extends Operacion{
	double div = 0;

	public Division(double n1, double n2) {
		super(n1, n2, '/');
		if(n2==0) System.out.println("No se puede dividir entre cero");
		else this.div = n1 / n2;
		this.setRes(this.div);
	}
}