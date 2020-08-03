package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.BuiltInFunctionImplementation;
import Model.Sinh;

class sinhTest {

	// Testing sin in degrees so we have to multiply angle by pi/180
	@Test
	void sinhPIDeg() {
		assertEquals(Math.sinh(Math.PI * Math.PI / 180), Sinh.sinh(BuiltInFunctionImplementation.getPi(), true),
				0.00001, "Fail at x = " + Math.PI + ".");
	}

	@Test
	void sinhPosIntDeg() {
		for (int i = 1; i < 100; i++) {
			assertEquals(Math.sinh(i * Math.PI / 180), Sinh.sinh(i, true), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void sinhNegIntDeg() {
		for (int i = 1; i < 100; i++) {
			assertEquals(Math.sinh(-i * Math.PI / 180), Sinh.sinh(-i, true), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void sinhPosDecimalsDeg() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.sinh(i * Math.PI / 180), Sinh.sinh(i, true), 0.00001, "Fail at x = " + i + ".");
			i++;
		}
	}

	@Test
	void sinhNegDecimalsDeg() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.sinh(i * Math.PI / 180), Sinh.sinh(i, true), 0.00001, "Fail at x = " + i + ".");
			i--;
		}
	}
	
	@Test
	void sinhPIRad() {
		assertEquals(Math.sinh(Math.PI), Sinh.sinh(BuiltInFunctionImplementation.getPi(), false),
				0.00001, "Fail at x = " + Math.PI + ".");
	}

	//Past 25, the numbers become too large to accurately compare
	@Test
	void sinhPosIntRad() {
		for (int i = 1; i < 25; i++) {
			assertEquals(Math.sinh(i), Sinh.sinh(i, false), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void sinhNegIntRad() {
		for (int i = 1; i < 25; i++) {
			assertEquals(Math.sinh(-i), Sinh.sinh(-i, false), 0.00001, "Fail at x = " + i + ".");
		}
	}

	@Test
	void sinhPosDecimalsRad() {
		double i = 0.1;
		while (i < 25.0) {
			assertEquals(Math.sinh(i), Sinh.sinh(i, false), 0.00001, "Fail at x = " + i + ".");
			i++;
		}
	}

	@Test
	void sinhNegDecimalsRad() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.sinh(i), Sinh.sinh(i, false), 0.00001, "Fail at x = " + i + ".");
			i--;
		}
	}

}
