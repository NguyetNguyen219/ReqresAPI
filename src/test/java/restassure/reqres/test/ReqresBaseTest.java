package restassure.reqres.test;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import restassure.reqres.BaseTest;
import restassure.reqres.utils.RequestBuilderCreator;

public abstract class ReqresBaseTest extends BaseTest {

    public static final String BASE_URI = "https://reqres.in/api";

    protected RequestBuilderCreator helper;
    protected Response response;

    @BeforeMethod
    public void setup() {
        helper = new RequestBuilderCreator();
        System.out.println("Before method");
    }

    @AfterMethod
    public void close() {
        System.out.println("After method");
        helper.resetRequestHelper();
    }
}
