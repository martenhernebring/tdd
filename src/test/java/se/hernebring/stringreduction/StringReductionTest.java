package se.hernebring.stringreduction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.hernebring.stringreduction.Main.StringReduction;

public class StringReductionTest {

  @Test
  void mainHasMethod() {
    StringReduction(null);
  }

  @Test
  void emptyStringReturnsZero() {
    int result = StringReduction("");
    assertEquals(0, result);
  }

  @Test
  void aReturns1() {
    int result = StringReduction("a");
    assertEquals(1, result);
  }

  @Test
  void aaReturns2() {
    int result = StringReduction("aa");
    assertEquals(2, result);
  }

  @Test
  void abReturns1() {
    int result = StringReduction("ab");
    assertEquals(1, result);
  }

  @Test
  void reducedRepeatedly() {
    int result = StringReduction("abb");
    assertEquals(1, result);
  }

  @Test
  void reducedUntilItCannotBeFurtherReduced() {
    int result = StringReduction("baaa");
    assertEquals(1, result);
  }

  @Test
  void reducedRepeatedlyUntilItCannotBeFurtherReduced() {
    int result = StringReduction("bcab");
    assertEquals(1, result);
  }

}
