package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controller.Shunting_yard_algorithm;

class shuntingYardTest {
	@Test
	void isOperator() {
		assertTrue(Shunting_yard_algorithm.isoperator("+-"));
		assertTrue(Shunting_yard_algorithm.isoperator("*/"));
		assertTrue(Shunting_yard_algorithm.isoperator("^"));
	}

	@Test
	void isFunction() {
		assertTrue(Shunting_yard_algorithm.isfunction("ln"));
	}

	@Test
	void isNumber() {
		assertTrue(Shunting_yard_algorithm.isnumber("32"));
		assertTrue(Shunting_yard_algorithm.isnumber("452"));
		assertTrue(Shunting_yard_algorithm.isnumber("321212"));
	}

	@Test
	void shunting_yard_algorithm_parseTest() {
		try {
			assertEquals(12, Shunting_yard_algorithm.shunting_yard_algorithm_parse("10+2"), 0.00001);
			assertEquals(7, Shunting_yard_algorithm.shunting_yard_algorithm_parse("12-5"), 0.00001);
			assertEquals(Math.sin(12), Shunting_yard_algorithm.shunting_yard_algorithm_parse("sin(12)"), 0.00001);
			assertEquals(Math.log(5), Shunting_yard_algorithm.shunting_yard_algorithm_parse("ln(5)"), 0.00001);
			assertEquals(Math.tan(15), Shunting_yard_algorithm.shunting_yard_algorithm_parse("tan(15)"), 0.00001);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
