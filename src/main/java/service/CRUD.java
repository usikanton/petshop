package service;

import java.util.List;

public interface CRUD<Request, Response, Default> {

  ResponseWrapper<Response, Default> create(Request request);

  ResponseWrapper<Response, Default> readOne(long id);

  ResponseWrapper<List<Response>, Default> readAll();

  ResponseWrapper<Response, Default> update(Request request);

  ResponseWrapper<Default, Default> delete(long id);

}
