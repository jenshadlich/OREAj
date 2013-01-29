package de.jeha.oreaj.regex.rx;

public class Star extends Op1 {

	public Star(RX down) {
		super(down);
	}

	public String show() {
		return "(" + this.getDown().show() + ")*";
	}
}
