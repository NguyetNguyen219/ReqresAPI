package restassure.reqres.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import restassure.reqres.BaseTest;
import restassure.reqres.utils.RequestBuilderCreator;

public abstract class ReqresBaseTest extends BaseTest {

    public static final String BASE_URI = "https://reqres.in/api";

    protected RequestBuilderCreator helper;
    protected Response response;

    @BeforeEach
    public void setup() {
        helper = new RequestBuilderCreator();
    }

    @AfterEach
    public void close() {
        helper.resetRequestHelper();
    }
}
