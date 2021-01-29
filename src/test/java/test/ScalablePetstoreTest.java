package test;

import model.Order;
import model.Order.OrderStatus;
import model.ServiceMessage;
import model.Pet;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.OrderService;
import service.PetService;
import service.ResponseWrapper;
import utils.datagen.OrderGenerator;
import utils.datagen.PetGenerator;

public class ScalablePetstoreTest {

  @Test(groups = "pet")
  public void addPetToTheStoreTest() {
    //generate new pet with random data
    Pet pet = PetGenerator.generatePet();
    //send POST request and verify that no errors in response, response data corresponds to data from request
    ResponseWrapper<Pet, ServiceMessage> petResponse = PetService.getInstance().create(pet);
    Assert.assertNull(petResponse.getError());
    Assert.assertEquals(petResponse.getResponse(), pet);
    //check pet was created
    ResponseWrapper<Pet, ServiceMessage> petResponseCheckAddition = PetService.getInstance().readOne(petResponse.getResponse().getId());
    Assert.assertEquals(petResponseCheckAddition.getResponse(), pet);
  }

  @Test(groups = "pet")
  public void removePetFromStoreTest() {
    //generate new pet with random data
    Pet pet = PetGenerator.generatePet();
    //send POST request and get petID from response for further usage
    ResponseWrapper<Pet, ServiceMessage> petResponse = PetService.getInstance().create(pet);
    long petId = petResponse.getResponse().getId();
    //send DELETE request using previously saved petID for deleting particular record by ID
    ResponseWrapper<ServiceMessage, ServiceMessage> petDeleteResponse = PetService.getInstance().delete(petId);
    Assert.assertNull(petDeleteResponse.getError());
    Assert.assertTrue(petDeleteResponse.getResponse().getCode() == HttpStatus.SC_OK);
    Assert.assertEquals(petDeleteResponse.getResponse().getMessage(), String.valueOf(petId));
    //verify that record has been deleted successfully by sending GET request using previously saved ID
    ResponseWrapper<Pet, ServiceMessage> petReadResponse = PetService.getInstance().readOne(petId);
    Assert.assertEquals(petReadResponse.getError().getCode().intValue(), 1);//code for deleted
    Assert.assertEquals(petReadResponse.getError().getType(), "error");
    Assert.assertEquals(petReadResponse.getError().getMessage(), "Pet not found");
  }

  @Test(groups = "order")
  public void makeAnOrderTest() {
    //generate new order with random data
    Order order = OrderGenerator.generateOrder();
    //send POST request and verify that order is saved and data corresponds to data from request
    ResponseWrapper<Order, ServiceMessage> orderResponse = OrderService.getInstance().create(order);
    Assert.assertEquals(orderResponse.getResponse(), order);
    Assert.assertEquals(orderResponse.getResponse().getStatus(), OrderStatus.PLACED);
  }
}