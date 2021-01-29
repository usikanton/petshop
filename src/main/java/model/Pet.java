package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {

  @JsonInclude(Include.NON_NULL)
  private Long id;
  private Category category;
  private String name;
  private List<String> photoUrls;
  private List<Tag> tags;
  private PetStatus status;

  public static class Category {

    private Long id;
    private String name;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Category category = (Category) o;
      return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, name);
    }

    @Override
    public String toString() {
      return "Category{" +
          "id=" + id +
          ", name='" + name + '\'' +
          '}';
    }
  }

  public static class Tag {

    private Long id;
    private String name;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Tag tag = (Tag) o;
      return Objects.equals(id, tag.id) && Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, name);
    }

    @Override
    public String toString() {
      return "Tag{" +
          "id=" + id +
          ", name='" + name + '\'' +
          '}';
    }
  }

  public enum PetStatus {
    @JsonProperty("available")
    AVAILABLE,
    @JsonProperty("pending")
    PENDING,
    @JsonProperty("sold")
    SOLD;

    @JsonValue
    public String getPetStatus() {
      return this.name().toLowerCase();
    }

    public static PetStatus getRandomPetStatus() {
      return Arrays.stream(values()).findAny().get();
    }
  }

  public static class Builder {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private PetStatus status;

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withCategory(Category category) {
      this.category = category;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withPhotoUrl(List<String> photoUrls) {
      this.photoUrls = photoUrls;
      return this;
    }

    public Builder withTags(List<Tag> tags) {
      this.tags = tags;
      return this;
    }

    public Builder withStatus(PetStatus status) {
      this.status = status;
      return this;
    }

    public Pet build() {
      Pet pet = new Pet();
      pet.id = id;
      pet.category = category;
      pet.name = name;
      pet.photoUrls = photoUrls;
      pet.tags = tags;
      pet.status = status;
      return pet;
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getPhotoUrls() {
    return photoUrls;
  }

  public void setPhotoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public PetStatus getStatus() {
    return status;
  }

  public void setStatus(PetStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pet pet = (Pet) o;
    return Objects.equals(category, pet.category) && Objects
        .equals(name, pet.name) && Objects.equals(photoUrls, pet.photoUrls)
        && Objects.equals(tags, pet.tags) && status == pet.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, name, photoUrls, tags, status);
  }

  @Override
  public String toString() {
    return "Pet{" +
        "id=" + id +
        ", category=" + category +
        ", name='" + name + '\'' +
        ", photoUrls=" + photoUrls +
        ", tags=" + tags +
        ", status=" + status +
        '}';
  }
}