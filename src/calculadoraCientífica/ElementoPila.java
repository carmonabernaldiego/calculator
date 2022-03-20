package fullcalculator;

public class ElementoPila<T> {
	public T value;
	public ElementoPila<T> next;

	public ElementoPila(T x) {
		this.value = x;
		this.next = null;
	}
}