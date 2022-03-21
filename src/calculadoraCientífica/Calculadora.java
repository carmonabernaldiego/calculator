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

	private Pila<Character> operatorStack;
	private Pila<Double> valueStack;

	private double resultado = 0;

	// Constructor.
	public Calculadora() {
		nuevoNumero = true;
		puntoDecimal = false;
		error = false;

		operatorStack = new Pila<Character>();
		valueStack = new Pila<Double>();

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

	private boolean isOperator(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	private int getPrecedence(char ch) {
		if (ch == '+' || ch == '-') {
			return 1;
		}
		if (ch == '*' || ch == '/') {
			return 2;
		}
		return 0;
	}

	private void processOperator(char t) {
		double a, b;
		if (valueStack.empty()) {
			System.out.println("Error de expresión.");
			error = true;
			return;
		} else {
			b = valueStack.peek();
			valueStack.pop();
		}
		if (valueStack.empty()) {
			System.out.println("Error de expresión.");
			error = true;
			return;
		} else {
			a = valueStack.peek();
			valueStack.pop();
		}
		double r;
		if (t == '+') {
			Suma suma = new Suma(a, b);
			r = suma.getRes();
			suma.imprimirResultado();
		} else if (t == '-') {
			Resta resta = new Resta(a, b);
			r = resta.getRes();
			resta.imprimirResultado();
		} else if (t == '*') {
			Multiplicacion multiplicacion = new Multiplicacion(a, b);
			r = multiplicacion.getRes();
			multiplicacion.imprimirResultado();
		} else if(t == '/') {
			Division division = new Division(a, b);
			r = division.getRes();
			division.imprimirResultado();
		} else {
			System.out.println("Error del operador.");
			error = true;
			return;
		}
		valueStack.push(r);
	}

	public void processInput(String input) {
		// The tokens that make up the input
		String[] tokens = input.split(" ");

		// Main loop - process all input tokens
		for (int n = 0; n < tokens.length; n++) {
			String nextToken = tokens[n];
			char ch = nextToken.charAt(0);
			if (ch >= '0' && ch <= '9') {
				double value = Double.parseDouble(nextToken);
				valueStack.push(value);
			} else if (isOperator(ch)) {
				if (operatorStack.empty() || getPrecedence(ch) > getPrecedence(operatorStack.peek())) {
					operatorStack.push(ch);
				} else {
					while (!operatorStack.empty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
						char toProcess = operatorStack.peek();
						operatorStack.pop();
						processOperator(toProcess);
					}
					operatorStack.push(ch);
				}
			} else if (ch == '(') {
				operatorStack.push(ch);
			} else if (ch == ')') {
				while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
					char toProcess = operatorStack.peek();
					operatorStack.pop();
					processOperator(toProcess);
				}
				if (!operatorStack.empty() && operatorStack.peek() == '(') {
					operatorStack.pop();
				} else {
					System.out.println("Error: paréntesis desequilibrado.");
					error = true;
				}
			}
		}
		// Empty out the operator stack at the end of the input
		while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
			char toProcess = operatorStack.peek();
			operatorStack.pop();
			processOperator(toProcess);
		}
		// Print the result if no error has been seen.
		if (error == false) {
			double result = valueStack.peek();
			valueStack.pop();
			if (!operatorStack.empty() || !valueStack.empty()) {
				System.out.println("Error de expresión.");
			} else {
				resultado = result;
			}
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
			displayOperators.setText("");
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
				display.setText("0.");
				nuevoNumero = false;
			}
		}

		//  Botón Suma.
		if (e.getSource() == bs) {
			if(resultado != 0) {
				displayOperators.setText(display.getText() + " + ");
			} else {
				if (!nuevoNumero) {
					displayOperators.setText(displayOperators.getText() + display.getText() + " + ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayOperators.getText().charAt(displayOperators.getText().length() - 2);

					if (caracter == '-' || caracter == '*' || caracter == '/') {
						displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " + ");
					}
				}
			}
		}

		//  Botón Resta.
		if (e.getSource() == br) {
			if(resultado != 0) {
				displayOperators.setText(display.getText() + " - ");
			} else {
				if (!nuevoNumero) {
					displayOperators.setText(displayOperators.getText() + display.getText() + " - ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayOperators.getText().charAt(displayOperators.getText().length() - 2);

					if (caracter == '+' || caracter == '*' || caracter == '/') {
						displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " - ");
					}
				}
			}
		}

		//  Botón Multiplicación.
		if (e.getSource() == bm) {
			if(resultado != 0) {
				displayOperators.setText(display.getText() + " * ");
			} else {
				if (!nuevoNumero) {
					displayOperators.setText(displayOperators.getText() + display.getText() + " * ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayOperators.getText().charAt(displayOperators.getText().length() - 2);

					if (caracter == '/' || caracter == '+' || caracter == '-') {
						displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " * ");
					}
				}
			}
		}

		//  Botón División.
		if (e.getSource() == bd) {
			if(resultado != 0) {
				displayOperators.setText(display.getText() + " / ");
			} else {
				if (!nuevoNumero) {
					displayOperators.setText(displayOperators.getText() + display.getText() + " / ");
					nuevoNumero = true;
					puntoDecimal = false;
				} else {
					char caracter = displayOperators.getText().charAt(displayOperators.getText().length() - 2);

					if (caracter == '*' || caracter == '+' || caracter == '-') {
						displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " / ");
					}
				}
			}
		}

		//  Botón Resultado.
		if (e.getSource() == bi) {
			resultado();
		}
	}

	//  Métodos.
	private void resultado(){
		if(!display.getText().equals("0")) {
			if (!nuevoNumero) {
				nuevoNumero = true;

				displayOperators.setText(displayOperators.getText() + display.getText());

				String calculo = displayOperators.getText();

				processInput(calculo);
			} else {
				char caracter = displayOperators.getText().charAt(displayOperators.getText().length() - 2);

				if(caracter == '/') {
					displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " / ");
					nuevoNumero = false;
					resultado();
				} else if (caracter == '*') {
					displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " * ");
					nuevoNumero = false;
					resultado();
				} else if (caracter == '+') {
					displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " + ");
					nuevoNumero = false;
					resultado();
				} else if (caracter == '-') {
					displayOperators.setText(displayOperators.getText().substring(0, displayOperators.getText().length() - 3) + " - ");
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

	private void limpiar(){
		nuevoNumero = true;
		puntoDecimal = false;
		error = false;
	}
}