package com.kalachev.task4;

public class DivisionsContainer {
	private int dividend;
	private int divisor;
	private int negativeIntHandler;
	public int getDividend() {
		return dividend;
	}
	public void setDividend(int dividend) {
		this.dividend = dividend;
	}
	public int getDivisor() {
		return divisor;
	}
	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}
	public int getNegativeIntHandler() {
		return negativeIntHandler;
	}
	public void setNegativeIntHandler(int negativeIntHandler) {
		this.negativeIntHandler = negativeIntHandler;
	}
	
	public DivisionsContainer(int dividend, int divisor) {
		super();
		this.dividend = dividend;
		this.divisor = divisor;
		this.negativeIntHandler = 0;
	}
}
