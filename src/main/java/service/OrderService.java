package service;

import model.Order;

public final class OrderService extends BasePetStoreService<Order> {

  private static OrderService orderService;

  private OrderService() {
  }

  public static OrderService getInstance() {
    if (orderService == null) {
      orderService = new OrderService();
    }
    return orderService;
  }

  /**
   * Method for specifying URI for order-only related endpoints
   */
  @Override
  protected String uri() {
    return "/store/order";
  }

  /**
   * Method for mapping response on Order model
   */
  @Override
  protected Class<Order> getResponseClass() {
    return Order.class;
  }
}