package se.hernebring.primevalidator;

import java.math.BigInteger;

public class Main {

  public static final BigInteger ONE = BigInteger.valueOf(1), TWO = BigInteger.valueOf(2);
  public static final int[] INTEGERS_SPRP_BASE = {7, 61};

  public static boolean PrimeTime(int num) {
    if(num < 4) {
      return num >= 2;
    }
    if(num == INTEGERS_SPRP_BASE[0] || num == INTEGERS_SPRP_BASE[1]) {
      return true;
    }
    if((num & 1) == 0) {
      return false;
    }
    BigInteger bi = new BigInteger(num + "");
    return passesMillerRabinStrongProbablePrimeBaseForIntegers(bi);
  }

  private static boolean passesMillerRabinStrongProbablePrimeBaseForIntegers(BigInteger biUnderTest) {
    // Find a and m such that m is odd and this == 1 + 2**a * m
    BigInteger thisMinusOne = biUnderTest.subtract(ONE);
    BigInteger m = thisMinusOne;
    int a = m.getLowestSetBit();
    m = m.shiftRight(a);

    // Do the tests
    for (int sprpBase : INTEGERS_SPRP_BASE) {
      int j = 0;
      BigInteger z = new BigInteger(sprpBase + "").modPow(m, biUnderTest);
      while (!((j == 0 && z.equals(ONE)) || z.equals(thisMinusOne))) {
        if (j > 0 && z.equals(ONE) || ++j == a)
          return false;
        z = z.modPow(TWO, biUnderTest);
      }
    }
    return true;
  }

}
