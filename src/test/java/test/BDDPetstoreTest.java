package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.Order;
import model.Pet;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Properties;
import utils.datagen.OrderGenerator;
import utils.datagen.PetGenerator;

public class BDDPetstoreTest {

  @Test(groups = "pet")
  public void addPetToStoreTest() {
    Pet pet = PetGenerator.generatePet();
    Pet createdPet =
        given()
          .body(pet)
        .when()
          .post("/pet")
        .then()
          .assertThat().statusCode(200)
          .assertThat().extract().body().as(Pet.class);

        given()
          .pathParam("petId", createdPet.getId())
        .when()
          .get("/pet/{petId}")
        .then()
          .assertThat().extract().body().as(Pet.class).equals(pet);
  }

  @Test(groups = "pet")
  public void deletePetFromStoreTest() {
    Pet pet = PetGenerator.generatePet();
    Pet createdPet =
        given()
          .body(pet)
        .when()
          .post("/pet")
        .then()
          .extract().body().as(Pet.class);

        given()
          .pathParam("petId", createdPet.getId())
        .when()
          .delete("/pet/{petId}")
        .then()
          .assertThat().statusCode(HttpStatus.SC_OK)
          .assertThat().body("code", Matchers.equalTo(HttpStatus.SC_OK))
          .assertThat().body("message", Matchers.equalTo(createdPet.getId().toString()));

        given()
          .pathParam("petId", createdPet.getId())
        .when()
          .get("/pet/{petId}")
        .then()
          .assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
          .assertThat().body("code", Matchers.equalTo(1))
          .assertThat().body("type", Matchers.equalTo("error"))
          .assertThat().body("message", Matchers.equalTo("Pet not found"));
  }

  @Test(groups = "order")
  public void makeOrderInStoreTest() {
    Order order = OrderGenerator.generateOrder();
    Order createdOrder =
        given()
          .body(order)
        .when()
          .post("/store/order")
        .then()
          .assertThat().statusCode(200)
          .assertThat().extract().body().as(Order.class);

        given()
          .pathParam("orderId", createdOrder.getId())
        .when()
          .get("/store/order/{orderId}")
        .then()
          .assertThat().extract().body().as(Order.class).equals(order);
  }

  private static RequestSpecification given() {
    return RestAssured.given()
        .contentType(ContentType.JSON)
        .baseUri(Properties.get("base.uri"))
        .basePath("/v2");
  }
}