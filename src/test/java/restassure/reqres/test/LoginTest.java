package restassure.reqres.test;

import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import restassure.reqres.requestModel.UserLogin;
import restassure.reqres.utils.MyListener;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

@Listeners(MyListener.class)
public class LoginTest extends ReqresBaseTest {

    public static final String BASE_PATH = "login";

    @Description("User login with correct email & password")
    @Test
    public void login() {
        UserLogin userLogin = new UserLogin(
                "eve.holt@reqres.in",
                "cityslicka"
        );
        LOGGER.info("Login with correct email and password");
        response = helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(userLogin)
                .post();
        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Description("User login with email only")
    @Test
    public void loginNoPassword() {
        UserLogin userLogin = new UserLogin(
                "peter@klaven"
        );
        LOGGER.info("Login with email but no password");
        response = helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(userLogin)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}
