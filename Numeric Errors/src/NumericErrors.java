/*
 * @author Cole Johnstone
 * NetID: 9077492057
 * */
public class NumericErrors {
	/*
	 * A.
	 */
	public static int add(int x, int y) {

		if (x > 0 && y > 0) {// if both integers are positive we have to make sure that
								// the difference between the max integer value and x/y is less than
								// that of the other integer so as to not have overflow
			if (Integer.MAX_VALUE - x < y || Integer.MAX_VALUE - y < x)
				throw new ArithmeticException("int overflow");
		}

		if (x < 0 && y < 0) {// similar thing here just with minimum value
			if (Integer.MIN_VALUE - x > y || Integer.MIN_VALUE - y > x)
				throw new ArithmeticException("int overflow");
		}
		int sum = x + y;
		return sum;
	}

	/*
	 * B.
	 */
	public static int subtract(int x, int y) {
		if (x > 0 && y > 0) {// subtraction is similar to add for the first two if statements
			if (Integer.MAX_VALUE - x < y || Integer.MAX_VALUE - y < x)
				throw new ArithmeticException("int overflow");
		}

		else if (x < 0 && y < 0) {
			if (Integer.MIN_VALUE - x > y || Integer.MIN_VALUE - y > x)
				throw new ArithmeticException("int overflow");
		} else if (x > 0 && y < 0) {// this is where subtraction differs from addition
									// when x > 0 and y < 0, the negative value of y
									// must be smaller than the space between x and the max value
			if (Integer.MAX_VALUE - x < y * -1)
				throw new ArithmeticException("int overflow");
		} else if (x < 0 && y > 0) {// similar concept just with opposite signs
			if (Integer.MIN_VALUE - x > y * -1)
				throw new ArithmeticException("int overflow");
		}
		int dif = x - y;
		return dif;
	}

	/*
	 * C. This is harder than doing the sum or difference because there are special
	 * cases like the top if statement where the min value could be flipped to be
	 * greater than the max value resulting in overflow. This is an edge case not
	 * checked by the other if statement tests. Basically there are more edge cases
	 * we have to be looking out for.
	 * 
	 */
	public static int multiply(int x, int y) {
		if ((x == Integer.MIN_VALUE && y == -1) || (y == Integer.MIN_VALUE && x == -1)) {// since the absolute value of
																							// the min value is greater
																							// than the max value, we
																							// need to check that it
																							// isn't being flipped
			throw new ArithmeticException("int overflow");
		}
		if ((x > 0 && y > 0) || (x < 0 && y < 0)) {
			if (Integer.MAX_VALUE / Math.abs(x) < Math.abs(y) || Integer.MAX_VALUE / Math.abs(y) < Math.abs(x))
				throw new ArithmeticException("int overflow");
		} else if (x > 0 && y < 0) {
			if (Integer.MIN_VALUE / x > y)
				throw new ArithmeticException("int overflow");
		} else if (x < 0 && y > 0) {
			if (Integer.MIN_VALUE / y > x)
				throw new ArithmeticException("int overflow");
		}
		int prod = x * y;
		return prod;
	}

	/*
	 * D. Overflow detection is necessary since dividing the MIN_VALUE by -1 results
	 * in overflow
	 */
	public static int divide(int x, int y) {
		if (y == 0)
			throw new ArithmeticException("y is 0. Result is undefined");
		if (x == Integer.MIN_VALUE && y == -1) {
			throw new ArithmeticException("int overflow");
		}
		int res = x / y;
		return res;
	}

	public static void main(String[] args) {
		if (testADD())
			System.out.println("Add tests: passed");
		else
			System.out.println("Add tests: failed");
		if (testSub())
			System.out.println("Subtraction tests: passed");
		else
			System.out.println("Subtraction tests: failed");
		if (testMult())
			System.out.println("Multiply tests: passed");
		else
			System.out.println("Multiply tests: failed");
		if (testDiv())
			System.out.println("Division tests: passed");
		else
			System.out.println("Division tests: failed");

	}

	/*
	 * tests the add function for both edge cases and legitimate inputs
	 */
	public static boolean testADD() {
		try {
			add(Integer.MAX_VALUE - 1, 1);
		} catch (Exception e2) {
			return false;
		}

		try {
			add(Integer.MAX_VALUE - 1, 2);
			return false;
		} catch (ArithmeticException e) {
			try {
				add(1, Integer.MAX_VALUE - 1);
			} catch (ArithmeticException e1) {
				return false;
			}
			try {
				add(2, Integer.MAX_VALUE - 1);
			} catch (ArithmeticException e1) {
				try {
					add(-1, Integer.MIN_VALUE + 1);
				} catch (ArithmeticException e2) {
					return false;
				}
				try {
					add(-2, Integer.MIN_VALUE);
				} catch (ArithmeticException e2) {
					try {
						add(Integer.MIN_VALUE + 1, -1);
					} catch (ArithmeticException e3) {
						return false;
					}
					try {
						add(Integer.MIN_VALUE + 1, -2);
					} catch (ArithmeticException e3) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * tests the subtract function for both edge cases and legitimate inputs
	 */
	public static boolean testSub() {
		try {
			subtract(Integer.MAX_VALUE - 1, 1);
		} catch (Exception e2) {
			return false;
		}

		try {
			subtract(Integer.MAX_VALUE - 1, 2);
			return false;
		} catch (ArithmeticException e) {
			try {
				subtract(1, Integer.MAX_VALUE - 1);
			} catch (ArithmeticException e1) {
				return false;
			}
			try {
				subtract(2, Integer.MAX_VALUE - 1);
			} catch (ArithmeticException e1) {
				try {
					subtract(-1, Integer.MIN_VALUE + 1);
				} catch (ArithmeticException e2) {
					return false;
				}
				try {
					subtract(-2, Integer.MIN_VALUE);
				} catch (ArithmeticException e2) {
					try {
						subtract(Integer.MIN_VALUE + 1, -1);
					} catch (ArithmeticException e3) {
						return false;
					}
					try {
						subtract(Integer.MIN_VALUE + 1, -2);
					} catch (ArithmeticException e3) {
						try {
							subtract(Integer.MIN_VALUE + 1, 1);
						} catch (ArithmeticException e4) {
							return false;
						}
						try {
							subtract(Integer.MIN_VALUE + 1, 2);
						} catch (ArithmeticException e4) {
							try {
								subtract(Integer.MAX_VALUE - 1, 1);
							} catch (ArithmeticException e5) {
								return false;
							}
							try {
								subtract(Integer.MAX_VALUE - 1, 2);
							} catch (ArithmeticException e5) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * tests the multiply function for both edge cases and legitimate inputs
	 */
	public static boolean testMult() {
		try {
			multiply(Integer.MAX_VALUE, 1);
			multiply(1, Integer.MAX_VALUE);
			multiply(Integer.MAX_VALUE, -1);
			multiply(-1, Integer.MAX_VALUE);
			multiply(Integer.MIN_VALUE, 1);
			multiply(1, Integer.MIN_VALUE);
			multiply(-1, Integer.MIN_VALUE + 1);
			multiply(Integer.MIN_VALUE + 1, -1);
		} catch (Exception e) {
			return false;
		}
		try {
			multiply(Integer.MIN_VALUE, -1);
		} catch (ArithmeticException e) {
			try {
				multiply(8, 268435455);
				multiply(268435455, 8);
			} catch (ArithmeticException e1) {
				return false;
			}
			try {
				multiply(8, 268435456);
			} catch (ArithmeticException e1) {
				try {
					multiply(268435456, 8);
				} catch (ArithmeticException e2) {
					try {
						multiply(-8, 268435456);
						multiply(268435456, -8);
						return true;
					} catch (ArithmeticException e3) {
						return false;
					}
				}
			}
		}

		return false;
	}

	/*
	 * tests the division function for both edge cases and legitimate inputs
	 */
	public static boolean testDiv() {
		try {
			divide(Integer.MAX_VALUE, Integer.MAX_VALUE);
			divide(Integer.MAX_VALUE, Integer.MIN_VALUE);
			divide(Integer.MIN_VALUE, Integer.MIN_VALUE);
			divide(Integer.MIN_VALUE, Integer.MAX_VALUE);
			divide(0, 1);
		} catch (Exception e) {
			return false;
		}
		try {
			divide(Integer.MAX_VALUE, 0);
		} catch (ArithmeticException e) {
			try {
				divide(Integer.MIN_VALUE, -1);
			} catch (ArithmeticException e1) {
				return true;
			}
		}

		return false;
	}
}
