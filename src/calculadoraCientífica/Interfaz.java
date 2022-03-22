package calculadoraCientífica;

import javax.swing.*;
import java.awt.*;

public class Interfaz extends JFrame {
	JTextField display, displayCalculo;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
	JButton bs, br, bd, bm, bp, bi, bc;
	JPanel superior, medio, inferior;

	public Interfaz() {
		//  Interfaz Gráfica para el Usuario.
		inicializarComponentes();
		dibujarPantalla();
	}

	private void inicializarComponentes() {
		//  Declarar componentes.
		//  Display.
		display = new JTextField("0");
		display.setFont(new Font("Dialog", Font.BOLD, 30));
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);

		//  Display Operators.
		displayCalculo = new JTextField("");
		displayCalculo.setFont(new Font("Dialog", Font.BOLD, 18));
		displayCalculo.setHorizontalAlignment(JTextField.RIGHT);
		displayCalculo.setEditable(true);

		// Botones numéricos.
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		b0 = new JButton("0");

		//  Botones Operaciones.
		bs = new JButton("+");
		br = new JButton("-");
		bd = new JButton("/");
		bm = new JButton("*");
		bc = new JButton("C");
		bp = new JButton(".");
		bi = new JButton("Resultado");

		// Paneles.
		superior = new JPanel();
		medio = new JPanel();
		inferior = new JPanel();
	}

	private void dibujarPantalla() {
		//  Colocar componentes.
		setLayout(new BorderLayout(0,10));
		superior.setLayout(new GridLayout(2 ,1));
		superior.add(displayCalculo);
		superior.add(display);

		medio.setLayout(new GridLayout(4 ,4));
		medio.add(b7);
		medio.add(b8);
		medio.add(b9);
		medio.add(bd);
		medio.add(b4);
		medio.add(b5);
		medio.add(b6);
		medio.add(bm);
		medio.add(b1);
		medio.add(b2);
		medio.add(b3);
		medio.add(br);
		medio.add(bc);
		medio.add(b0);
		medio.add(bp);
		medio.add(bs);

		inferior.setLayout(new BorderLayout());
		inferior.add("Center", bi);

		//  Maquetar.
		add("North" ,superior);
		add("Center" ,medio);
		add("South" ,inferior);

		//  Configuración de Ventana.
		setTitle("Calculadora");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50 ,50 ,336 ,450);
		setResizable(false);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}