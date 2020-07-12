package api.services;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static utils.PropertiesReader.getPropertyByName;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonService {
  protected RequestSpecification specification;

  protected CommonService() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RequestSpecBuilder builder = new RequestSpecBuilder()
        .setContentType(JSON)
        .setBaseUri(getPropertyByName("todois_domain"))
        .addHeader("Authorization", format("Bearer %s", getPropertyByName("token")))
        .addFilter(new AllureRestAssured());
    specification = builder.build();
  }

  protected Response getNoParams(String uri, String body) {
    return given(specification).body(body).get(uri);
  }

  protected Response postNoParams(String uri, String body) {
    return given(specification).body(body).post(uri);
  }

  protected Response deleteNoParams(String uri, String body) {
    return given(specification).body(body).delete(uri);
  }

  protected Response putNoParams(String uri, String body) {
    return given(specification).body(body).put(uri);
  }
}
