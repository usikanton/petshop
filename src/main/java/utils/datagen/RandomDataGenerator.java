package utils.datagen;

import java.time.LocalDateTime;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;

public final class RandomDataGenerator {

  private RandomDataGenerator() {
  }

  //method generates random string with 10 chars length
  public static String generateRandomString() {
    return RandomStringUtils.randomAlphanumeric(10);
  }

  //method generates random long value
  public static long generateRandomLong() {
    return new Random().nextLong();
  }

  public static int generateRandomInt() {
    return new Random().nextInt();
  }

  public static int generateRandomInt(int bound) {
    return new Random().nextInt(bound);
  }

  public static Boolean generateRandomBoolean() {
    return new Random().nextBoolean();
  }

  public static String generateDateString() {
    return String.valueOf(LocalDateTime.now());
  }
}
