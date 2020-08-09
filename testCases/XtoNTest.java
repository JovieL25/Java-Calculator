package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.XtoN;

class XtoNTest {

	@Test
	// less cases, will overflow if number is too high
	void XtoNPosInt() {
		for (int i = 0; i < 10; i++) {
			try {
				assertEquals(Math.pow(i, i), XtoN.Xton(i, i), 0.00001, "Fail at x = " + i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void XtoNNegInt() {
		for (int i = 1; i < 10; i++) {
			try {
				assertEquals(Math.pow(i, -i), XtoN.Xton(i, -i), 0.00001, "Fail at x = " + -i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// issue with accuracy when doing positive decimals
	@Test
	void XtoNPosDecimals() {
		double i = 0.1;
		while (i < 10.0) {
			try {
				assertEquals(Math.pow(i, i), XtoN.Xton(i, i), 0.00001, "Fail at x = " + i + ".");
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
				assertEquals(Math.pow(i, -i), XtoN.Xton(i, -i), 0.00001, "Fail at x = " + -i + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i--;
		}
	}
}
