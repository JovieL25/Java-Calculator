package project.testCases;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Math;
import org.junit.jupiter.api.Test;

import project.BuiltInFunctionImplementation;
import project.CalculatorFunctions;

class LnXTest {

	@Test
	void lnE() {
		assertEquals(Math.log(Math.E), CalculatorFunctions.ln(BuiltInFunctionImplementation.getE()), 0.0000001,
				"Fail at x = " + Math.E + ".");
	}

	@Test
	void lnPI() {
		assertEquals(Math.log(Math.PI), CalculatorFunctions.ln(BuiltInFunctionImplementation.getPi()), 0.0000001,
				"Fail at x = " + Math.PI + ".");
	}
	
	@Test
	void lnPosInt() {
		for(int i = 1; i < 100; i++) {
			assertEquals(Math.log(i), CalculatorFunctions.ln(i), 0.0000001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void lnPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.log(i), CalculatorFunctions.ln(i), 0.0000001,
					"Fail at x = " + i + ".");
			i++;
		}
	}
}
