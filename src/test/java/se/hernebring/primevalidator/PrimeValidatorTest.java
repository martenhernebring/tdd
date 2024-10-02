package se.hernebring.primevalidator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

    public class PrimeValidatorTest {

      @Test void allIntegersPrimesReturnTrue() throws IOException {
        Path file = Paths.get("src/main/resources/primes.txt");
        boolean allNumbersArePrimes;
        try(var stream = Files.lines(file)) {
          allNumbersArePrimes = stream.mapToInt(Integer::parseInt)
              .allMatch(Main::PrimeTime);
        }
        assertTrue(allNumbersArePrimes);
      }

      @Disabled
      //Warning: Took 56 minutes on my computer
      @Test void allIntegersNonPrimesReturnFalse() throws IOException {
        Path file = Paths.get("src/main/resources/primes.txt");
        int[] primes;
        try(var stream = Files.lines(file)) {
          primes = stream.mapToInt(Integer::parseInt).toArray();
        }
        for(int i = 0, primeI = 0; primeI < primes.length; i++, primeI++) {
          while(i < primes[primeI]) {
            if(Main.PrimeTime(i)) {
              System.out.println(i);
              fail();
            }
            i++;
          }
        }
      }

      @Test void allIntegersFromZeroToHundred() {
        for(int i = 0; i <= 100; i++) {
          System.out.println(Main.PrimeTime(i));
        }
      }

    }
