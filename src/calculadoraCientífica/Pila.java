package fullcalculator;

public class Pila<T> {
	private ElementoPila<T> top;

	public Pila() {
		top = null;
	}

	public boolean empty() {
		return top == null;
	}

	public T peek() {
		return top.value;
	}

	public void push(T x) {
		ElementoPila<T> newItem = new ElementoPila<T>(x);
		newItem.next = top;
		top = newItem;
	}

	public void pop() {
		top = top.next;
	}
}