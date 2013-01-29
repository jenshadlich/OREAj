package de.jeha.oreaj.regex.rx;

public interface RX extends Iterable<RX> {
    public String show();

	/*
    <T> T visit(Visitor<T> v);

	interface Visitor<T> {
		T leaf(RX key);

		T node(T down);

		T branch(T left, T right);
	}
	*/
}
