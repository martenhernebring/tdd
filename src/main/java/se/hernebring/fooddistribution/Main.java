package se.hernebring.fooddistribution;

import java.util.List;
import java.util.ArrayList;

public class Main {

  private static int sandwichesItem = 0;
  private static int firstItem = 1;
  private static int lastItem;
  private static int firstTurningPoint, lastTurningPoint;
  private static int totalDiff;

  private static int findMinAndRemoveItemsWithHighExpectedValue(int[] arr) {
    int min = Math.min(arr[firstItem], arr[lastItem]);
    for(int i = firstItem + 1; i < lastItem; i++) {
      while(arr[sandwichesItem] > 0 && arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
        arr[sandwichesItem]--;
        arr[i]--;
      }
      min = Math.min(min, arr[i]);
    }
    return min;
  }

  private static void removeItemsWithMiddleExpectedValue(int[] arr) {
    for(int i = firstItem + 1; i < lastItem - 1; i++) {
      while(arr[sandwichesItem] > 1 && arr[i] > arr[i - 1] && arr[i] == arr[i + 1]
          && arr[i + 1] > arr[i + 2]) {
        arr[sandwichesItem] -= 2;
        arr[i]--;
        arr[i + 1]--;
      }
    }
    while(arr[sandwichesItem] > 0 && arr[firstItem] > arr[firstItem + 1]) {
      arr[sandwichesItem]--;
      arr[firstItem]--;
    }
    while(arr[sandwichesItem] > 0 && arr[lastItem] > arr[lastItem - 1]) {
      arr[sandwichesItem]--;
      arr[lastItem]--;
    }
  }

  private static int findMaxAndTotalDiffAndSubtractMin(int[] arr, int min) {
    int max = 0;
    totalDiff = 0;
    for(int i = firstItem; i <= lastItem; i++) {
      arr[i] -= min;
      max = Math.max(max, arr[i]);
      totalDiff += arr[i];
    }
    return max;
  }

  private static void findFirstAndLastTurningPointAndRemoveItemsHigherThanThemInTheMiddle(int[] arr) {
    List<Integer> turningPoint = new ArrayList<>();
    if(arr[firstItem] < arr[firstItem + 1]) {
      turningPoint.add(firstItem);
    }
    for(int i = firstItem + 1; i < lastItem; i++) {
      if(arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
        turningPoint.add(i);
      }
    }
    if(arr[lastItem] < arr[lastItem - 1]) {
      turningPoint.add(lastItem);
    }
    if(turningPoint.size() > 0) {
      firstTurningPoint = turningPoint.get(0);
      lastTurningPoint = turningPoint.get(turningPoint.size() - 1);
      for(int i = firstTurningPoint + 1; i < lastTurningPoint; i++) {
        while(arr[sandwichesItem] > 0 && arr[i] > arr[firstTurningPoint] && arr[i] > arr[lastTurningPoint]) {
          arr[sandwichesItem]--;
          arr[i]--;
        }
      }
    } else {
      firstTurningPoint = arr.length - 1;
      lastTurningPoint = 0;
    }
  }

  private static int removeMaxItemsBeforeTheFirstAndAfterTheLastTurningPoint(int[] arr, int max) {
    if(arr[firstItem] > arr[firstItem + 1]) {
      arr[sandwichesItem]--;
      arr[firstItem]--;
    }
    if(arr[sandwichesItem] > 0 && arr[lastItem] > arr[lastItem - 1]) {
      arr[sandwichesItem]--;
      arr[lastItem]--;
    }
    if(arr[sandwichesItem] > 1 && arr[firstItem] == max && arr[firstItem + 1] == max && arr[firstItem + 2] != max) {
      arr[sandwichesItem] -= 2;
      arr[firstItem]--;
      arr[firstItem + 1]--;
    }
    if(arr[sandwichesItem] > 1 && arr[lastItem] == max && arr[lastItem - 1] == max && arr[lastItem - 2] != max) {
      arr[sandwichesItem] -= 2;
      arr[lastItem]--;
      arr[lastItem - 1]--;
    }
    for(int i = firstItem + 1; i < firstTurningPoint && arr[sandwichesItem] > 0; i++) {
      if(arr[i] == max) {
        arr[sandwichesItem]--;
        arr[i]--;
      }
    }
    for(int i = lastTurningPoint + 1; i < lastItem && arr[sandwichesItem] > 0; i++) {
      if(arr[i] == max) {
        arr[sandwichesItem]--;
        arr[i]--;
      }
    }
    max--;
    int maxEdge = Math.max(arr[firstItem], arr[lastItem]);
    max = Math.max(max, maxEdge);
    return max;
  }

  private static int getTotalDiff(int[] arr) {
    totalDiff = 0;
    for(int i = 1; i < arr.length - 1; i++) {
      totalDiff += Math.abs(arr[i + 1] - arr[i]);
    }
    return totalDiff;
  }

  public static int FoodDistribution(int[] arr) {
    lastItem = arr.length - 1;
    int min = findMinAndRemoveItemsWithHighExpectedValue(arr);
    removeItemsWithMiddleExpectedValue(arr);
    int max = findMaxAndTotalDiffAndSubtractMin(arr, min);
    if(max <= 0 || totalDiff <= arr[sandwichesItem]) {
      return 0;
    }
    if(arr[sandwichesItem] > 0) {
      findFirstAndLastTurningPointAndRemoveItemsHigherThanThemInTheMiddle(arr);
    }
    while(arr[sandwichesItem] > 0) {
      max = removeMaxItemsBeforeTheFirstAndAfterTheLastTurningPoint(arr, max);
    }
    return getTotalDiff(arr);
  }

  public static void main(String[] args) {
    int[] arr = new int[] {3, 1, 0, 1, 1, 1, 0, 1};
    System.out.println(FoodDistribution(arr));
  }
}
