package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

  @JsonInclude(Include.NON_NULL)
  private Long id;
  private Long petId;
  private Integer quantity;
  private String shipDate;
  private OrderStatus status;
  private Boolean complete;

  public static class Builder {

    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private OrderStatus status;
    private Boolean complete;

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withPetId(Long petId) {
      this.petId = petId;
      return this;
    }

    public Builder withQuantity(Integer quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder withShipDate(String shipDate) {
      this.shipDate = shipDate;
      return this;
    }

    public Builder withStatus(OrderStatus status) {
      this.status = status;
      return this;
    }

    public Builder withComplete(Boolean complete) {
      this.complete = complete;
      return this;
    }

    public Order build() {
      Order order = new Order();
      order.id = id;
      order.petId = petId;
      order.quantity = quantity;
      order.shipDate = shipDate;
      order.status = status;
      order.complete = complete;
      return order;
    }
  }

  public enum OrderStatus {

    @JsonProperty("placed")
    PLACED,

    @JsonProperty("approved")
    APPROVED,

    @JsonProperty("delivered")
    DELIVERED;

    @JsonValue
    public String getOrderStatus() {
      return this.name().toLowerCase();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPetId() {
    return petId;
  }

  public void setPetId(Long petId) {
    this.petId = petId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getShipDate() {
    return shipDate;
  }

  public void setShipDate(String shipDate) {
    this.shipDate = shipDate;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Boolean getComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(petId, order.petId) && Objects
        .equals(quantity, order.quantity) && status == order.status && Objects
        .equals(complete, order.complete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(petId, quantity, status, complete);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", petId=" + petId +
        ", quantity=" + quantity +
        ", shipDate='" + shipDate + '\'' +
        ", status=" + status +
        ", complete=" + complete +
        '}';
  }
}