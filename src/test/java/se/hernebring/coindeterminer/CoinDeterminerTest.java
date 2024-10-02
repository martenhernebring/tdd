package se.hernebring.coindeterminer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.hernebring.coindeterminer.Main.CoinDeterminer;

public class CoinDeterminerTest {

  private int[] sortedCoins;

  @BeforeEach void createSet() {
    sortedCoins = new int[3];
  }

  @Test void nullReturnsZero() {
    assertEquals(0, CoinDeterminer(null, 0));
  }

  @Test void noCoinsReturnZero() {
    assertEquals(0, CoinDeterminer(sortedCoins, -1));
  }

  @Test void oneCoinEqualsOutputReturnsOne() {
    sortedCoins[0] = 1;
    assertEquals(1, CoinDeterminer(sortedCoins, 1));
  }

  @Test void oneCoinEqualsHalfOutputReturnsTwoTimesCoin() {
    sortedCoins[0] = 1;
    assertEquals(2, CoinDeterminer(sortedCoins, 2));
  }

  @Test void twoCoinsThenMinIsBiggestCoin() {
    sortedCoins[0] = 1;
    sortedCoins[1] = 2;
    assertEquals(Arrays.stream(sortedCoins).sorted().toArray(), sortedCoins);
    assertEquals(1, CoinDeterminer(sortedCoins, 2));
  }
/*
  @Test void twoCoinsReturnsTwoWhenBiggestAndSmallestCoin() {
    sortedDescList.add(2);
    sortedCoins[0] = 1;
    sortedDescList.sort(Collections.reverseOrder());
    assertEquals(2, CoinDeterminer(sortedDescList, 3));
  }

  @Test void threeCoinsReturnsThreeWhenOneEach() {
    sortedDescList.add(4);
    sortedDescList.add(2);
    sortedCoins[0] = 1;
    assertEquals(sortedDescList.stream().sorted(
        Collections.reverseOrder()).toList(), sortedDescList);
    assertEquals(3, CoinDeterminer(sortedDescList, 7));
  }

  @Test void threeCoinsReturnsLowestTwoWhenOneIsTooBig() {
    sortedDescList.add(4);
    sortedDescList.add(2);
    sortedCoins[0] = 1;
    sortedDescList.sort(Collections.reverseOrder());
    assertEquals(2, CoinDeterminer(sortedDescList, 3));
  }

  @Test void threeCoinsReturnsFour() {
    sortedDescList.add(10);
    sortedDescList.add(2);
    sortedCoins[0] = 1;
    assertEquals(sortedDescList.stream().sorted(
        Collections.reverseOrder()).toList(), sortedDescList);
    assertEquals(4, CoinDeterminer(sortedDescList, 15));
  }

  @Test void twoCoinsWhereFirstShouldBeExcludedReturnsThree() {
    sortedDescList.add(5);
    sortedDescList.add(2);
    assertEquals(sortedDescList.stream().sorted(
        Collections.reverseOrder()).toList(), sortedDescList);
    assertEquals(3, CoinDeterminer(sortedDescList, 6));
  }
*/
}
