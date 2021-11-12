package restassure.reqres.test;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class LoginTest extends ReqresBaseTest {

    public static final String BASE_PATH = "login";

    @Test
    public void login() {
        String body = """
                {
                "email": "eve.holt@reqres.in",
                "password": "cityslicka"
                }
                """;
        LOGGER.info("Login with correct email and password");
        response = helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(body)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void loginNoPassword() {
        String body = """
                {
                "email": "peter@klaven"
                }
                """;
        LOGGER.info("Login with email but no password");
        response = helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(body)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}
