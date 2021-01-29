package service;

public class ResponseWrapper<R, E> {

  private final R response;
  private final E error;

  public ResponseWrapper(R response, E error) {
    this.response = response;
    this.error = error;
  }

  public R getResponse() {
    return response;
  }

  public E getError() {
    return error;
  }

  public boolean hasResponse() {
    return getResponse() != null;
  }

  public boolean hasError() {
    return getError() != null;
  }
}