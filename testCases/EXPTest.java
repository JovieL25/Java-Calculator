package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Exp;

class EXPTest {

	@Test
	// less cases, will overflow if number is too high
	// issue with static variable existing outside the scope of the function
	void XtoNPosInt() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 1; i < 10; i++) {
			try {
				assertEquals(Math.pow(Math.E, i), Exp.EXP2(i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void XtoNNegInt() {
		for (int i = 1; i < 10; i++) {
			try {
				assertEquals(Math.pow(Math.E, -i), Exp.EXP2(-i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void XtoNPosDecimals() {
		double i = 0.1;
		while (i < 10.0) {
			try {
				assertEquals(Math.pow(Math.E, i), Exp.EXP2(i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	@Test
	void XtoNNegDecimals() {
		double i = -0.1;
		while (i < -10.0) {
			try {
				assertEquals(Math.pow(Math.E, -i), Exp.EXP2(-i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i--;
		}
	}

}
