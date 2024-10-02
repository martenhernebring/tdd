package se.hernebring.coindeterminer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static int CoinDeterminer(int[] sortedDescCoins, int output) {
    int coins = 0;
    if(sortedDescCoins == null //|| sortedDescCoins.isEmpty()
    ) {
      return coins;
    }
    Map<Integer, List<Integer>> combinationsOfCoins = new LinkedHashMap<>();
    for (int coinValue : sortedDescCoins) {
      if (output % coinValue == 0) {
        //combinationsOfCoins.put(coinValue, output / coinValue);
        break;
      } else {
        /*while(output > coinValue) {
          combinationsOfCoins.put(coinValue,
              combinationsOfCoins.getOrDefault(coinValue, 0) + 1);
          output -= coinValue;
        }*/
      }
    }
    //for(Map.Entry<Integer, Integer> e : combinationsOfCoins.entrySet()) {
    //  coins += e.getValue();
    //}
    return coins;
  }
}
