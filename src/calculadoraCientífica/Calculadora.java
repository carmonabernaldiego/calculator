package calculadoraCientífica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculadora extends Interfaz implements ActionListener {
	//  Declarar variables.
	private boolean nuevoNumero;
	private boolean puntoDecimal;
	private boolean error;

	private Pila<String> PilaOperadores;
	private Pila<Double> PilaValores;

	private double resultado = 0;

	//  Constructor.
	public Calculadora() {
		nuevoNumero = true;
		puntoDecimal = false;
		error = false;

		PilaOperadores = new Pila<String>();
		PilaValores = new Pila<Double>();

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

	private void limpiar(){
		nuevoNumero = true;
		puntoDecimal = false;
		error = false;
	}

	private boolean esNumero(String cadena) {
		boolean numero;

		try {
			Double.parseDouble(cadena);
			numero = true;
		} catch (NumberFormatException excepcion) {
			numero = false;
		}

		return numero;
	}

	private boolean esOperador(String ch) {
		return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
	}

	private int getOrden(String operador) {
		if (operador.equals("+") || operador.equals("-")) {
			return 1;
		}
		if (operador.equals("*") || operador.equals("/")) {
			return 2;
		}
		return 0;
	}

	private void procesarOperacion(String calculo) {
		double a, b;

		if (PilaValores.empty()) {
			System.out.println("Error de expresión.");
			error = true;
			return;
		} else {
			b = PilaValores.peek();
			PilaValores.pop();
		}

		if (PilaValores.empty()) {
			System.out.println("Error de expresión.");
			error = true;
			return;
		} else {
			a = PilaValores.peek();
			PilaValores.pop();
		}

		double result;

		switch (calculo) {
			case "+" -> {
				Suma suma = new Suma(a, b);
				result = suma.getRes();
				suma.imprimirResultado();
			}
			case "-" -> {
				Resta resta = new Resta(a, b);
				result = resta.getRes();
				resta.imprimirResultado();
			}
			case "*" -> {
				Multiplicacion multiplicacion = new Multiplicacion(a, b);
				result = multiplicacion.getRes();
				multiplicacion.imprimirResultado();
			}
			case "/" -> {
				Division division = new Division(a, b);
				result = division.getRes();
				division.imprimirResultado();
			}
			default -> {
				System.out.println("Error del operador.");
				error = true;
				return;
			}
		}
		PilaValores.push(result);
	}

	public void procesarEntradaCalculo(String entrada) {
		//  Dividir cadena.
		String[] elementos = entrada.split(" ");

		//  Bucle principal - Procesar todos los elementos del array[].
		for (int n = 0; n < elementos.length; n++) {
			if (esNumero(elementos[n])) {
				double valor = Double.parseDouble(elementos[n]);
				PilaValores.push(valor);
			} else {
				if (PilaOperadores.empty() || getOrden(elementos[n]) > getOrden(PilaOperadores.peek())) {
					PilaOperadores.push(elementos[n]);
				} else {
					while (!PilaOperadores.empty() && getOrden(elementos[n]) <= getOrden(PilaOperadores.peek())) {
						String operador = PilaOperadores.peek();
						PilaOperadores.pop();
						procesarOperacion(operador);
					}
					PilaOperadores.push(elementos[n]);
				}
			}

			if (elementos[n].equals("(")) {
				PilaOperadores.push(elementos[n]);
			} else if (elementos[n].equals(")")) {
				while (!PilaOperadores.empty() && esOperador(PilaOperadores.peek())) {
					String operador = PilaOperadores.peek();
					PilaOperadores.pop();
					procesarOperacion(operador);
				}
				if (!PilaOperadores.empty() && PilaOperadores.peek().equals("(")) {
					PilaOperadores.pop();
				} else {
					System.out.println("Error: paréntesis desequilibrado.");
					error = true;
				}
			}
		}

		//  Vaciar pila de operadores.
		while (!PilaOperadores.empty() && esOperador(PilaOperadores.peek())) {
			String operador = PilaOperadores.peek();
			PilaOperadores.pop();
			procesarOperacion(operador);
		}

		//  Imprimir el resultado si no se ha visto ningún error.
		if (error == false) {
			double result = PilaValores.peek();
			PilaValores.pop();
			if (!PilaOperadores.empty() || !PilaValores.empty()) {
				System.out.println("Error de expresión.");
			} else {
				resultado = result;
			}
		}
	}

	//  Obtener resultado.
	private void resultado(){
		if(!display.getText().equals("0")) {
			if (!nuevoNumero) {
				nuevoNumero = true;

				displayCalculo.setText(displayCalculo.getText() + display.getText());

				String calculo = displayCalculo.getText();

				procesarEntradaCalculo(calculo);
			} else {
				char caracter = displayCalculo.getText().charAt(displayCalculo.getText().length() - 2);

				if(caracter == '/') {
					displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " / ");
					nuevoNumero = false;
					resultado();
				} else if (caracter == '*') {
					displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " * ");
					nuevoNumero = false;
					resultado();
				} else if (caracter == '+') {
					displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " + ");
					nuevoNumero = false;
					resultado();
				} else if (caracter == '-') {
					displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " - ");
					nuevoNumero = false;
					resultado();
				}
			}
			//Formateo y muestro en el display
			Locale localeActual = Locale.ENGLISH;
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols(localeActual);
			simbolos.setDecimalSeparator('.');
			DecimalFormat formatoResultado = new DecimalFormat("#.######", simbolos);
			display.setText(String.valueOf(formatoResultado.format(resultado)));
		}
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
			displayCalculo.setText("");
			limpiar();
			resultado = 0;
		}

		//  Botón . - Punto decimal.
		if (e.getSource() == bp) {
			if(!display.getText().contains(".")) {
				if (!puntoDecimal) {
					if (nuevoNumero) {
						display.setText("0.");
						nuevoNumero = false;
					} else {
						display.setText(display.getText() + ".");
						puntoDecimal = true;
						nuevoNumero = false;
					}
				}
			} else {
				if (nuevoNumero) {
					display.setText("0.");
					nuevoNumero = false;
				}
			}
		}

		//  Botón Suma.
		if (e.getSource() == bs) {
			if(resultado != 0) {
				displayCalculo.setText(display.getText() + " + ");
				resultado = 0;
			} else {
				if (!nuevoNumero) {
					displayCalculo.setText(displayCalculo.getText() + display.getText() + " + ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayCalculo.getText().charAt(displayCalculo.getText().length() - 2);

					if (caracter == '-' || caracter == '*' || caracter == '/') {
						displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " + ");
					}
				}
			}
		}

		//  Botón Resta.
		if (e.getSource() == br) {
			if(resultado != 0) {
				displayCalculo.setText(display.getText() + " - ");
				resultado = 0;
			} else {
				if (!nuevoNumero) {
					displayCalculo.setText(displayCalculo.getText() + display.getText() + " - ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayCalculo.getText().charAt(displayCalculo.getText().length() - 2);

					if (caracter == '+' || caracter == '*' || caracter == '/') {
						displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " - ");
					}
				}
			}
		}

		//  Botón Multiplicación.
		if (e.getSource() == bm) {
			if(resultado != 0) {
				displayCalculo.setText(display.getText() + " * ");
				resultado = 0;
			} else {
				if (!nuevoNumero) {
					displayCalculo.setText(displayCalculo.getText() + display.getText() + " * ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayCalculo.getText().charAt(displayCalculo.getText().length() - 2);

					if (caracter == '/' || caracter == '+' || caracter == '-') {
						displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " * ");
					}
				}
			}
		}

		//  Botón División.
		if (e.getSource() == bd) {
			if(resultado != 0) {
				displayCalculo.setText(display.getText() + " / ");
				resultado = 0;
			} else {
				if (!nuevoNumero) {
					displayCalculo.setText(displayCalculo.getText() + display.getText() + " / ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayCalculo.getText().charAt(displayCalculo.getText().length() - 2);

					if (caracter == '*' || caracter == '+' || caracter == '-') {
						displayCalculo.setText(displayCalculo.getText().substring(0, displayCalculo.getText().length() - 3) + " / ");
					}
				}
			}
		}

		//  Botón Resultado.
		if (e.getSource() == bi) {
			resultado();
		}
	}
}