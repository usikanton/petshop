package utils.datagen;

import static utils.datagen.RandomDataGenerator.generateRandomInt;
import static utils.datagen.RandomDataGenerator.generateRandomLong;
import static utils.datagen.RandomDataGenerator.generateRandomString;

import java.util.ArrayList;
import java.util.List;
import model.Pet;
import model.Pet.PetStatus;
import model.Pet.Tag;

public final class PetGenerator {

  private PetGenerator() {
  }

  public static Pet generatePet() {
    Pet.Category category = new Pet.Category();
    category.setId(generateRandomLong());
    category.setName(generateRandomString());
    return new Pet.Builder()
        .withCategory(category)
        .withName(generateRandomString())
        .withPhotoUrl(generateListOfPhotoUrl())
        .withTags(generateListOfTags())
        .withStatus(PetStatus.AVAILABLE)
        .build();
  }

  private static List<String> generateListOfPhotoUrl() {
    List<String> photoUrls = new ArrayList<>();
    for (int i = 0; i < generateRandomInt(10); i++) {
      photoUrls.add(generateRandomString());
    }
    return photoUrls;
  }

  private static List<Pet.Tag> generateListOfTags() {
    List<Tag> tagList = new ArrayList<>();
    for (int j = 0; j < generateRandomInt(10); j++) {
      Tag tag = new Tag();
      tag.setId(generateRandomLong());
      tag.setName(generateRandomString());
      tagList.add(tag);
    }
    return tagList;
  }
}