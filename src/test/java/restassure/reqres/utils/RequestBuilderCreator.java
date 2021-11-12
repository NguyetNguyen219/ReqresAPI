package restassure.reqres.utils;

import io.restassured.builder.RequestSpecBuilder;
import restassure.reqres.test.ReqresBaseTest;

public class RequestBuilderCreator extends ApiHelper {

    public RequestHandler setupRequestBuilder() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ReqresBaseTest.BASE_URI);
        return new RequestHandler(requestSpecBuilder);
    }
}
