package se.hernebring.primevalidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws IOException {
    Instant start = java.time.Instant.now();
    Main.printPrimes();
    Instant end = java.time.Instant.now();
    Duration between = java.time.Duration.between(start, end);
    System.out.println( between ); // PT2H21M8.2257144S
    System.out.format("%dD, %02d:%02d:%02d.%04d \n", between.toDays(),
        between.toHours(), between.toMinutes(), between.getSeconds(), between.toMillis()); // 0D, 02:141:8468.8468225
  }

  private static void printPrimes() throws IOException {
    Path file = Paths.get("src/main/resources/primes.txt");
    int timeMax = 214, batchSize = 10_000_000;
    List<String> lines;

    boolean[] primes = Prime.generatePrimes(batchSize);
    lines = new ArrayList<>(1_000_000);
    for(int i = 0; i < primes.length; i++) {
      if(primes[i]) {
        lines.add(String.valueOf(i));
      }
    }
    Files.write(file, lines, StandardCharsets.UTF_8);
    int time = 2;
    for(; time <= timeMax; time++) {
      boolean[] newPrimes = Prime.generatePrimes(primes, time);
      lines = new ArrayList<>(1_000_000);
      for(int i = (time - 1) * primes.length; i < time * primes.length; i++) {
        if(newPrimes[i - (time - 1) * primes.length]) {
          lines.add(String.valueOf(i));
        }
      }
      Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    int[] lastPrimes = Prime.generatePrimes(timeMax * batchSize, Integer.MAX_VALUE);
    lines = Arrays.stream(lastPrimes).filter(v -> v > 0)
        .mapToObj(Integer::toString).collect(Collectors.toList());
    lines.add(String.valueOf(Integer.MAX_VALUE));
    Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
  }

}
