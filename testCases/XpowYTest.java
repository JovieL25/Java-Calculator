package project.testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.CalculatorFunctions;

class XpowYTest {

	@Test
	//less cases, will overflow if number is too high
	void XpowYPosInt() {
		System.out.println(CalculatorFunctions.EXP(1, 1));
		for (int i = 0; i < 20; i++) {
			assertEquals(Math.pow(i, i), CalculatorFunctions.XtoN(i, i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void XpowYNegInt() {
		for(int i = 1; i < 20; i++) {
			assertEquals(Math.pow(i, -i), CalculatorFunctions.XtoN(i, -i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	//issue with accuracy when doing positive decimals (probably due to using EXP)
	@Test
	void XpowYPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.pow(i, i), CalculatorFunctions.XtoN(i, i), 0.00001,
					"Fail at x = " + i + ".");
			i++;
		}
	}
	
	@Test
	void XpowYNegDecimals() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.pow(i, -i), CalculatorFunctions.XtoN(i, -i), 0.00001,
					"Fail at x = " + i + ".");
			i--;
		}
	}

}
