package se.hernebring.stringreduction;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

  public static int StringReduction(String str) {
    if(str == null) {
      return 0;
    }
    Deque<Character> stack = new ArrayDeque<>();
    for(int i = 0; i < str.length(); i++) {
      char current = str.charAt(i);
      while(!stack.isEmpty() && stack.peekLast() != current) {
        current = merge(current, stack.pop());
      }
      stack.push(current);
    }
    return stack.size();
  }

  private static char merge(char current, char previous) {
    return (char) ('a' + 'b' + 'c' - current - previous);
  }
}