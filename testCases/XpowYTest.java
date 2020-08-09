package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Ten_to_x;

class XpowYTest {

	@Test
	// less cases, will overflow if number is too high
	void XpowYPosInt() {
		for (int i = 0; i < 10; i++) {
			try {
				assertEquals(Math.pow(10, i), Ten_to_x.ten_to_x(i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void XpowYNegInt() {
		for (int i = 1; i < 10; i++) {
			try {
				assertEquals(Math.pow(10, -i), Ten_to_x.ten_to_x(-i), 0.00001, "Fail at x = " + -i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// issue with accuracy when doing positive decimals (probably due to using EXP)
	@Test
	void XpowYPosDecimals() {
		double i = 0.1;
		while (i < 10.0) {
			try {
				assertEquals(Math.pow(10, i), Ten_to_x.ten_to_x(i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	@Test
	void XpowYNegDecimals() {
		double i = -0.1;
		while (i < -10.0) {
			try {
				assertEquals(Math.pow(10, -i), Ten_to_x.ten_to_x(-i), 0.00001, "Fail at x = " + -i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i--;
		}
	}

}
