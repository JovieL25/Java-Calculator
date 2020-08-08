package testCases;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Math;

import org.junit.jupiter.api.Test;

import Model.BuiltInFunctionImplementation;
import Model.Ln;

class LnTest {

	@Test
	void lnE() {
		try {
			assertEquals(Math.log(Math.E), Ln.ln2(BuiltInFunctionImplementation.getE()), 0.00001,
					"Fail at x = " + Math.E + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void lnPosInt() {
		for (int i = 1; i < 100; i++) {
			try {
				assertEquals(Math.log(i), Ln.ln2(i), 0.00001,
						"Fail at x = " + Math.E + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void lnPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			try {
				assertEquals(Math.log(i), Ln.ln2(i), 0.00001,
						"Fail at x = " + Math.E + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
