package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.BuiltInFunctionImplementation;
import Model.Cos;

class cosTest {

	//Testing cos in degrees so we have to multiply angle by pi/180
	@Test
	void cosPI() {
		assertEquals(Math.cos(Math.PI * Math.PI/180), Cos.cos(BuiltInFunctionImplementation.getPi()), 0.00001,
				"Fail at x = " + Math.PI + ".");
	}
	
	@Test
	void cosPosInt() {
		for(int i = 1; i < 100; i++) {
			assertEquals(Math.cos(i * Math.PI/180), Cos.cos(i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void cosNegInt() {
		for(int i = 1; i < 100; i++) {
			assertEquals(Math.cos(-i * Math.PI/180), Cos.cos(-i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void cosPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.cos(i * Math.PI/180), Cos.cos(i), 0.00001,
					"Fail at x = " + i + ".");
			i++;
		}
	}
	
	@Test
	void cosNegDecimals() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.cos(i * Math.PI/180), Cos.cos(i), 0.00001,
					"Fail at x = " + i + ".");
			i--;
		}
	}

}
