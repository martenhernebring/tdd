package se.hernebring.primegenerator;

    import java.util.*;

    public class Prime {

      private static final Map<Integer, Integer> next = new LinkedHashMap<>();

      public static boolean[] generatePrimes(int maxValue) {
        boolean[] primes = new boolean[maxValue];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        int findMax = (int) Math.sqrt(maxValue);
        for(int i = 2; i <= findMax; i++) {
          if(primes[i]) {
            int j = i * i;
            while(j < maxValue) {
              primes[j] = false;
              j = j + i;
            }
            next.put(i, j);
          }
        }
        return primes;
      }

      public static boolean[] generatePrimes(boolean[] oldPrimes, int time) {
        boolean[] newPrimes = new boolean[oldPrimes.length];
        Arrays.fill(newPrimes, true);
        int maxValue = time * oldPrimes.length;
        System.out.println(maxValue);
        int findMax = (int) Math.sqrt(maxValue);
        for(int i = 2; i <= findMax; i++) {
          if(oldPrimes[i]) {
            int j = next.getOrDefault(i, i * i);
            while(j < maxValue) {
              newPrimes[j - (time - 1) * oldPrimes.length] = false;
              j = j + i;
            }
            next.put(i, j);
          }
        }
        return newPrimes;
      }

      public static int[] generatePrimes(int from, int to) {
        int[] primes = new int[(to - from) + 1];
        int i = from;
        for (; i < to; i++) {
          if (isPrime(i)) {
            primes[i - from] = i;
          }
        }
        return primes;
      }

      public static boolean isPrime(int n) {
        if (n == 0 || n == 1) {
          return false;
        }
        if (n == 2) {
          return true;
        }
        for (int i = 2; i * i <= n; i++) {
          if (n % i == 0) {
            return false;
          }
        }
        return true;

      }
    }
