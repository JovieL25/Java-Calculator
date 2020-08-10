package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.BuiltInFunctionImplementation;
import Model.Tan;

class tanTest {

	// Testing tan in degrees so we have to multiply angle by pi/180
	@Test
	void tanPI() {
		assertEquals(Math.tan(Math.PI * Math.PI / 180), Tan.tan(BuiltInFunctionImplementation.getPi()), 0.00001,
				"Fail at x = " + Math.PI + ".");
	}

	@Test
	void tanPosInt() {
		for (int i = 1; i < 50; i++) {
			assertEquals(Math.tan(i * Math.PI / 180), Tan.tan(i), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void tanNegInt() {
		for (int i = 1; i < 50; i++) {
			assertEquals(Math.tan(-i * Math.PI / 180), Tan.tan(-i), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void tanPosDecimals() {
		double i = 0.1;
		while (i < 50.0) {
			assertEquals(Math.tan(i * Math.PI / 180), Tan.tan(i), 0.00001, "Fail at x = " + i + ".");
			i++;
		}
	}

	@Test
	void tanNegDecimals() {
		double i = -0.1;
		while (i < -50.0) {
			assertEquals(Math.tan(i), Tan.tan(i), 0.00001, "Fail at x = " + i + ".");
			i--;
		}
	}

}
