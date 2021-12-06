package restassure.reqres.test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassure.reqres.requestModel.UserLogin;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class RegisterTest extends ReqresBaseTest {

    public static final String BASE_PATH = "register";

    @Severity(SeverityLevel.CRITICAL)
    @Description("Register for user with email & password")
    @Test
    public void register() {
        UserLogin user = new UserLogin(
                "eve.holt@reqres.in",
                "pistol"
        );
        LOGGER.info("Legal register with email & password");
        response = helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(user)
                .post();

        Assert.assertEquals(response.statusCode(), SC_OK);

        response.then().log().body().assertThat()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Register for user with email only")
    @Test
    public void registerNoPassword() {
        UserLogin user = new UserLogin(
                "sydney@fife"
        );
        LOGGER.info("Register with no password");
        response= helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(user)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}
