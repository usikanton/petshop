package service;

import model.Pet;

public final class PetService extends BasePetStoreService<Pet> {

  private static PetService petService;

  private PetService() {
  }

  public static PetService getInstance() {
    if (petService == null) {
      petService = new PetService();
    }
    return petService;
  }

  /**
   * Method for specifying URI for pet-only related endpoints
   */
  @Override
  protected String uri() {
    return "/pet";
  }

  /**
   * Method for mapping response on Pet model
   */
  @Override
  protected Class<Pet> getResponseClass() {
    return Pet.class;
  }
}