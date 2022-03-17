package calculadoraBasica;

import java.awt.event.*;

public class Calculadora extends Interfaz implements ActionListener {
	//  Declarar variables.
	boolean nuevoNumero = true;
	boolean puntoDecimal = false;

	double operando1 = 0;
	double operando2 = 0;
	double resultado = 0;

	String operacion = "";

	// Constructor.
	public Calculadora() {
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b0.addActionListener(this);
		bs.addActionListener(this);
		br.addActionListener(this);
		bd.addActionListener(this);
		bm.addActionListener(this);
		bp.addActionListener(this);
		bi.addActionListener(this);
		bc.addActionListener(this);
	}

	//  Manejo de los eventos para cada botón.
	public void actionPerformed(ActionEvent e) {
		//  Botones numéricos.
		if (nuevoNumero) {
			if (e.getSource() == b1) {
				display.setText("1");
				nuevoNumero = false;
			} else if (e.getSource() == b2) {
				display.setText("2");
				nuevoNumero = false;
			} else if (e.getSource() == b3) {
				display.setText("3");
				nuevoNumero = false;
			} else if (e.getSource() == b4) {
				display.setText("4");
				nuevoNumero = false;
			} else if (e.getSource() == b5) {
				display.setText("5");
				nuevoNumero = false;
			} else if (e.getSource() == b6) {
				display.setText("6");
				nuevoNumero = false;
			} else if (e.getSource() == b7) {
				display.setText("7");
				nuevoNumero = false;
			} else if (e.getSource() == b8) {
				display.setText("8");
				nuevoNumero = false;
			} else if (e.getSource() == b9) {
				display.setText("9");
				nuevoNumero = false;
			}
		} else {
			if (e.getSource() == b1) {
				display.setText(display.getText() + "1");
			} else if (e.getSource() == b2) {
				display.setText(display.getText() + "2");
			} else if (e.getSource() == b3) {
				display.setText(display.getText() + "3");
			} else if (e.getSource() == b4) {
				display.setText(display.getText() + "4");
			} else if (e.getSource() == b5) {
				display.setText(display.getText() + "5");
			} else if (e.getSource() == b6) {
				display.setText(display.getText() + "6");
			} else if (e.getSource() == b7) {
				display.setText(display.getText() + "7");
			} else if (e.getSource() == b8) {
				display.setText(display.getText() + "8");
			} else if (e.getSource() == b9) {
				display.setText(display.getText() + "9");
			} else if (e.getSource() == b0) {
				display.setText(display.getText() + "0");
			}
		}

		//  Botón C - Resetear valores.
		if (e.getSource() == bc) {
			display.setText("0");
			limpiar();
		}

		//  Botón . - Punto decimal.
		if (e.getSource() == bp) {
			if (!puntoDecimal){
				display.setText(display.getText() + ".");
				puntoDecimal = true;
				nuevoNumero = false;
			}
		}

		//  Botón Suma.
		if (e.getSource() == bs) {
			if (operacion.equals("")) {
				//Asocio la operación del botón a la variable
				operacion = "+";
				//Asigno a operando2 el valor del display (como double)
				operando2 = Double.parseDouble(display.getText());
				//Reseteo para poder introducir otro número y otro decimal
				nuevoNumero = true;
				puntoDecimal = false;
				//Si tenía alguna pendiente, calculo el resultado de la anterior y luego me guardo la actual
			} else {
				operando2 = resultado(); //Se almacena en operando2 para poder encadenar operaciones posteriores
				operacion = "+";
			}
			//SOUT para comprobar que estoy guardando los valores adecuados
			System.out.println(operando2 + " " + operacion + " " + operando1);
		}

		//  Botón Resta.
		if (e.getSource() == br) {
			if (operacion.equals("")) {
				//Asocio la operación del botón a la variable
				operacion = "-";
				//Asigno a operando2 el valor del display (como double)
				operando2 = Double.parseDouble(display.getText());
				//Reseteo para poder introducir otro número y otro decimal
				nuevoNumero = true;
				puntoDecimal = false;
				//Si tenía alguna pendiente, calculo el resultado de la anterior y luego me guardo la actual
			} else {
				operando2 = resultado(); //Se almacena en operando2 para poder encadenar operaciones posteriores
				operacion = "-";
			}
			//SOUT para comprobar que estoy guardando los valores adecuados
			System.out.println(operando2 + " " + operacion + " " + operando1);
		}

		//  Botón Multiplicación.
		if (e.getSource() == bm) {
			if (operacion.equals("")) {
				//Asocio la operación del botón a la variable
				operacion = "*";
				//Asigno a operando2 el valor del display (como double)
				operando2 = Double.parseDouble(display.getText());
				//Reseteo para poder introducir otro número y otro decimal
				nuevoNumero = true;
				puntoDecimal = false;
				//Si tenía alguna pendiente, calculo el resultado de la anterior y luego me guardo la actual
			} else {
				operando2 = resultado(); //Se almacena en operando2 para poder encadenar operaciones posteriores
				operacion = "*";
			}
			//SOUT para comprobar que estoy guardando los valores adecuados
			System.out.println(operando2 + " " + operacion + " " + operando1);
		}

		//  Botón División.
		if (e.getSource() == bd) {
			if (operacion.equals("")) {
				//Asocio la operación del botón a la variable
				operacion = "/";
				//Asigno a operando2 el valor del display (como double)
				operando2 = Double.parseDouble(display.getText());
				//Reseteo para poder introducir otro número y otro decimal
				nuevoNumero = true;
				puntoDecimal = false;
				//Si tenía alguna pendiente, calculo el resultado de la anterior y luego me guardo la actual
			} else {
				operando2 = resultado(); //Se almacena en operando2 para poder encadenar operaciones posteriores
				operacion = "/";
			}
			//SOUT para comprobar que estoy guardando los valores adecuados
			System.out.println(operando2 + " " + operacion + " " + operando1);
		}

		//  Botón Resultado.
		if (e.getSource() == bi) {
			//Al pulsar el botón de resultado, directamente lo calculo y reseteo la calculadora,
			//sin necesidad de almacenar el resultado para futuras operaciones
			resultado();
		}
	}

	//  Métodos.
	private double resultado(){
		operando1 = Double.parseDouble(display.getText());

		switch (operacion) {
			case "+" :  resultado = operando2 + operando1;
				break;
			case "-" :  resultado = operando2 - operando1;
				break;
			case "*" :  resultado = operando2 * operando1;
				break;
			case "/" :  resultado = operando2 / operando1;
				break;
		}

		display.setText(String.valueOf(resultado));

		limpiar();

		return resultado;
	}

	private void limpiar(){
		operacion = "";
		operando1 = 0;
		operando2 = 0;
		nuevoNumero = true;
		puntoDecimal = false;
	}
}