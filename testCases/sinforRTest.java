package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.BuiltInFunctionImplementation;
import Model.Sin;

class sinforRTest {
	@Test
	void sinPI() {
		try {
			assertEquals(Math.sin(Math.PI), Sin.sinforR(BuiltInFunctionImplementation.getPi()), 0.00001,
					"Fail at x = " + Math.PI + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void sinPosInt() {
		for (int i = 1; i < 100; i++) {
			try {
				assertEquals(Math.sin(i), Sin.sinforR(i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void sinNegInt() {
		for (int i = 1; i < 100; i++) {
			try {
				assertEquals(Math.sin(-i), Sin.sinforR(-i), 0.00001, "Fail at x = " + -i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void sinPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			try {
				assertEquals(Math.sin(i), Sin.sinforR(i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	@Test
	void sinNegDecimals() {
		double i = -0.1;
		while (i < -100.0) {
			try {
				assertEquals(Math.sin(-i), Sin.sinforR(-i), 0.00001, "Fail at x = " + -i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i--;
		}
	}

}
