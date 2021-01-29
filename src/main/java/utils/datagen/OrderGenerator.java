package utils.datagen;

import static utils.datagen.RandomDataGenerator.generateDateString;
import static utils.datagen.RandomDataGenerator.generateRandomBoolean;
import static utils.datagen.RandomDataGenerator.generateRandomInt;
import static utils.datagen.RandomDataGenerator.generateRandomLong;

import model.Order;
import model.Order.OrderStatus;

public final class OrderGenerator {

  private OrderGenerator() {
  }

  public static Order generateOrder() {
    Order order = new Order.Builder()
        .withPetId(generateRandomLong())
        .withQuantity(generateRandomInt())
        .withShipDate(generateDateString())
        .withStatus(OrderStatus.PLACED)
        .withComplete(generateRandomBoolean())
        .build();
    return order;
  }
}