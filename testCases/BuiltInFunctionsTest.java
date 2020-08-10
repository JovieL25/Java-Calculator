package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.Math;
import org.junit.jupiter.api.Test;

import Model.BuiltInFunctions;

class BuiltInFunctionsTest {

	@Test
	void getPi() {
		assertEquals(Math.PI, BuiltInFunctions.getPi(), 0.00001, "Fail at x = " + Math.PI + ".");
	}

	@Test
	void getE() {
		assertEquals(Math.E, BuiltInFunctions.getE(), 0.00001, "Fail at x = " + Math.E + ".");
	}

	@Test
	void abs() {
		assertEquals(Math.abs(-1), BuiltInFunctions.abs(-1), 0.00001, "Fail at x = " + -1 + ".");
	}

	@Test
	void absPosNumbers() {
		for (int i = 0; i < 1000; i++) {
			assertEquals(Math.abs(i), BuiltInFunctions.abs(i), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void absNegNumbers() {
		for (int i = 0; i < 1000; i++) {
			assertEquals(Math.abs(-i), BuiltInFunctions.abs(-i), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void factorial() {
		try {
		assertEquals(120, BuiltInFunctions.factorial(5), 0.00001, "Fail at x = " + 5 + ".");}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void posPow() {
		for (int i = 0; i < 25; i++) {
			assertEquals(Math.pow(i, i + 1), BuiltInFunctions.posPow(i, i + 1), 0.00001,
					"Fail at x = " + i + ".");
		}
	}

}
