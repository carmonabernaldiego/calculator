package calculadoraBasica;

import java.awt.event.*;

public class Calculadora extends Interfaz implements ActionListener {
	double numero = 0, resultado = 0;
	char operacion = 'n';
	StringBuffer s = new StringBuffer();

	//Para indicar que he terminado de escribir dígitos un número y que voy a añadir el siguiente
	boolean nuevoNumero = true;
	//Para indicar si ya he utilizado el punto decimal en ese número (solo puede haber uno)
	boolean puntoDecimal = false;
	//Para almacenas los resultados parciales y totales de las operaciones realizadas
	double operando1 = 0;
	double operando2 = 0;
	double resultado = 0;
	//Para almacenar el string de la operación realizada (+, -, *, /)
	String operacion = "";

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
		Double digUno, digDos;
		if (e.getSource() == b1) {
			s.append('1');
		} else if (e.getSource() == b2) {
			s.append('2');
		} else if (e.getSource() == b3) {
			s.append('3');
		} else if (e.getSource() == b4) {
			s.append('4');
		} else if (e.getSource() == b5) {
			s.append('5');
		} else if (e.getSource() == b6) {
			s.append('6');
		} else if (e.getSource() == b7) {
			s.append('7');
		} else if (e.getSource() == b8) {
			s.append('8');
		} else if (e.getSource() == b9) {
			s.append('9');
		} else if (e.getSource() == b0) {
			s.append('0');
		} else if (e.getSource() == bp) {
			s.append('.');
		} else if (e.getSource() == br) { //resta
			if (display.getText() != "") {
				digUno = Double.parseDouble(display.getText());
				numero = digUno.doubleValue();
			}
			s = null;
			s = new StringBuffer();
			operacion = 'm';
		} else if (e.getSource() == bs) { //suma
			if (display.getText() != "") {
				digUno = Double.parseDouble(display.getText());
				numero = digUno.doubleValue();
			}
			s = null;
			s = new StringBuffer();
			operacion = 's';
		} else if (e.getSource() == bi) { //Realiza cálculo
			if (display.getText() != "") {
				digDos = Double.parseDouble(display.getText());
				if (operacion == 'r') {
					resultado = (numero - digDos.doubleValue());
				} else if (operacion == 'd') {
					resultado = (numero / digDos.doubleValue());
				} else if (operacion == 'm') {
					resultado = (numero * (digDos.doubleValue()));
				} else if (operacion == 's') {
					resultado = (numero + digDos.doubleValue());
				}
			}
			s = null;
			s = new StringBuffer();
			s.append(resultado);
		}

		//Mostrar Resultado.
		display.setText(s.toString());

		//Resetear valores.
		if (e.getSource() == bc) {
			numero = 0;
			resultado = 0;
			s = null;
			s = new StringBuffer();
			display.setText(s.toString());
		}
	}
}