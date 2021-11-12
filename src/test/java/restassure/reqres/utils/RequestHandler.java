package restassure.reqres.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import restassure.reqres.test.ProductTest;
import restassure.reqres.test.UserTest;

import java.util.Map;

public class RequestHandler extends ApiHelper {

    public RequestHandler(RequestSpecBuilder specBuilder) {
        requestSpecBuilder = specBuilder;
    }

    public RequestHandler forUsers() {
        requestSpecBuilder.setBasePath(UserTest.BASE_PATH);
        return this;
    }

    public RequestHandler forUser(long id) {
        requestSpecBuilder.setBasePath(UserTest.BASE_PATH + "/" + id);
        return this;
    }

    public RequestHandler forProducts() {
        requestSpecBuilder.setBasePath(ProductTest.BASE_PATH);
        return this;
    }

    public RequestHandler forProduct(long id) {
        requestSpecBuilder.setBasePath(ProductTest.BASE_PATH + "/" + id);
        return this;
    }

    public RequestHandler setPath(String path) {
        requestSpecBuilder.setBasePath(path);
        return this;
    }

    public RequestHandler addParam(String key, int value) {
        requestSpecBuilder.addParam(key, value);
        return this;
    }

    public RequestHandler addParam(Map<String, String> params) {
        params.entrySet()
                .forEach(e -> requestSpecBuilder.addParam(e.getKey(), e.getValue()));
        return this;
    }

    public RequestHandler typeJSON() {
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        return this;
    }

    public RequestHandler body(String body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public RequestHandler body(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public Response get() {
        response = RestAssured.given(requestSpecBuilder.build()).get();
        return response;
    }

    public Response post() {
        typeJSON();
        response = RestAssured.given(requestSpecBuilder.build()).post();
        return response;
    }

    public Response put() {
        typeJSON();
        response = RestAssured.given(requestSpecBuilder.build()).put();
        return response;
    }

    public Response delete() {
        response = RestAssured.given(requestSpecBuilder.build()).delete();
        return response;
    }
}
