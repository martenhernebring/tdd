package se.hernebring.primevalidator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeValidatorTest {

  @Disabled
  @Test void allIntegersPrimesReturnTrue() throws IOException {
    Path file = Paths.get("src/main/resources/primes.txt");
    boolean allNumbersArePrimes;
    try(var stream = Files.lines(file)) {
      allNumbersArePrimes = stream.mapToInt(Integer::parseInt).allMatch(Main::PrimeTime);
    }
    assertTrue(allNumbersArePrimes);
  }

}
