package se.hernebring.primevalidator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main {

  public static final BigInteger ONE = BigInteger.valueOf(1), TWO = BigInteger.valueOf(2);
  public static final List<Integer> INTEGERS_SPRP_BASE = List.of(2, 3, 5, 7);

  public static boolean PrimeTime(int num) {
    int lastBaseIndex = INTEGERS_SPRP_BASE.size() - 1;
    if(num == INTEGERS_SPRP_BASE.get(lastBaseIndex)) {
      return true;
    } else if (num < INTEGERS_SPRP_BASE.get(lastBaseIndex)) {
      return Collections.binarySearch(INTEGERS_SPRP_BASE, num) >= 0;
    }
    if ((num & 1) == 0) {
      return false;
    }
    return passesMillerRabinStrongProbablePrimeBaseForIntegers(new BigInteger(num + ""));
  }

  private static boolean passesMillerRabinStrongProbablePrimeBaseForIntegers(BigInteger biUnderTest) {
    // Find a and m such that m is odd and this == 1 + 2**a * m
    BigInteger biUTMinusOne = biUnderTest.subtract(ONE);
    BigInteger m = biUTMinusOne;
    int a = m.getLowestSetBit();
    m = m.shiftRight(a);

    // Do the tests
    for (int sprpBase : INTEGERS_SPRP_BASE) {
      BigInteger z = new BigInteger(sprpBase + "").modPow(m, biUnderTest);
      if (!z.equals(ONE)) {
        int j = 0;
        while (!z.equals(biUTMinusOne)) {
          if (z.equals(ONE) || ++j == a)
            return false;
          z = z.modPow(TWO, biUnderTest);
        }
      }
    }
    return true;
  }
}
