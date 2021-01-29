package service;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import model.ServiceMessage;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.Properties;


/**
 * Class describes base service behavior on CRUD model. It allows to scale your framework, reduce
 * amount of code, cause this base service can be used for any endpoint by inheriting from it.
 */
public abstract class BasePetStoreService<R> implements CRUD<R, R, ServiceMessage> {

  static {
    RestAssured.defaultParser = Parser.JSON;
  }

  private static final Logger LOGGER = LogManager.getLogger(BasePetStoreService.class);

  protected abstract String uri();

  protected abstract Class<R> getResponseClass();

  /**
   * Method for POST request which returns response wrapper parametrized with generic classes
   *
   * @return ResponseWrapper
   */
  @Override
  public ResponseWrapper<R, ServiceMessage> create(R request) {
    Response response = client().urlEncodingEnabled(false).body(request).post(uri());
    return processResponse(response, HttpStatus.SC_OK);
  }

  /**
   * Method for GET request which returns response wrapper parametrized with generic classes. Takes
   * an ID as an input value. Gives information about one particular record
   *
   * @return ResponseWrapper
   */
  @Override
  public ResponseWrapper<R, ServiceMessage> readOne(long id) {
    Response response = client().urlEncodingEnabled(true)
        .get(uri().concat(String.format("/%d", id)));
    return processResponse(response, HttpStatus.SC_OK);
  }

  /**
   * Method for GET request which returns response wrapper of list of responses. Gives information
   * about all records
   *
   * @return ResponseWrapper<List < Response>>
   */
  @Override
  public ResponseWrapper<List<R>, ServiceMessage> readAll() {
    //TODO add implementation
    throw new NotImplementedException();
  }

  @Override
  public ResponseWrapper<R, ServiceMessage> update(R request) {
    //TODO add implementation
    throw new NotImplementedException();
  }

  /**
   * Methdo for DELETE request which returns response wrapper parametrized with ServiceMessage
   * classes. Responses from this request can be mapped only on ServiceMessage in both ways -
   * positive and negative responses
   *
   * @return ResponseWrapper<ServiceMessage, ServiceMessage>
   */
  @Override
  public ResponseWrapper<ServiceMessage, ServiceMessage> delete(long id) {
    Response response = client().urlEncodingEnabled(true)
        .delete(uri().concat(String.format("/%d", id)));
    return processResponse(response, HttpStatus.SC_OK, ServiceMessage.class);
  }

  /**
   * Method for processing responses for DELETE requests only due to the specific of responses' body
   * structure - it's the same for both positive and negative responses.
   *
   * @return ResponseWrapper<Response, ServiceMessage>
   */
  private ResponseWrapper<R, ServiceMessage> processResponse(Response response, int httpStatus) {
    return processResponse(response, httpStatus, getResponseClass());
  }

  /**
   * Method for processing responses for GET,POST,PUT requests. Returns ResponseWrapper parametrized
   * with Response, Http status code and generic model class. Services-inheritors can override
   * method and specify theri own model classes to map responses on particular models
   *
   * @return ResponseWrapper<Response, ServiceMessage>
   */
  private <T> ResponseWrapper<T, ServiceMessage> processResponse(Response response, int httpStatus,
      Class<T> responseClazz) {
    T resp = null;
    ServiceMessage serviceMessage = null;
    LOGGER.debug(response.prettyPrint());
    if (response.statusCode() == httpStatus) {
      resp = response.as(responseClazz);
    } else {
      if (response.getBody() != null) {
        serviceMessage = response.as(ServiceMessage.class);
      } else {
        serviceMessage = new ServiceMessage();
        serviceMessage.setCode(response.statusCode());
        serviceMessage.setType(response.getContentType());
        serviceMessage.setMessage("GENERIC ERROR");
      }
    }
    return new ResponseWrapper(resp, serviceMessage);
  }

  /**
   * Configuration of RestAssured client which processes requests with specified base uri and base
   * path. Returns request specification
   *
   * @return RequestSpecification
   */
  private static RequestSpecification client() {
    return given()
        .contentType(ContentType.JSON)
        .baseUri(Properties.get("base.uri"))
        .basePath("/v2");
  }
}