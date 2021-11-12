package restassure.reqres.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public abstract class ApiHelper {

    protected RequestSpecBuilder requestSpecBuilder = null;
    protected Response response = null;

    public void resetRequestHelper() {
        requestSpecBuilder = null;
        response = null;
    }
}
