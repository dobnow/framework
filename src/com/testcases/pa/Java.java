package com.testcases.pa;

public class Java {

	int variable = 5;

	public static void main(String args[]) {
		Java object = new Java();

		object.MethodA(20);
		object.MethodB();
	}

	void MethodA(int variable) {
		variable = 10;
		System.out.println("Value of Instance variable :" + this.variable);
		System.out.println("Value of Local variable :" + variable);
		System.out.println("Value of variable + this.variable :" + (variable + this.variable));
		System.out.println("Value of variable + this.variable :" + variable + this.variable);
	}

	void MethodB() {
		int variable = 40;
		System.out.println("Value of Instance variable :" + this.variable);
		System.out.println("Value of Local variable :" + variable);
		System.out.println("Value of variable + this.variable :" + (variable + this.variable));
		System.out.println("Value of variable + this.variable :" + variable + this.variable);
	}

}
