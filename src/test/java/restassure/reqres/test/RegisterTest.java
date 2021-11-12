package restassure.reqres.test;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class RegisterTest extends ReqresBaseTest {

    public static final String BASE_PATH = "register";

    @Test
    public void register() {
        String body = """
                {
                "email": "eve.holt@reqres.in",
                "password": "pistol"
                }
                """;
        LOGGER.info("Legal register with email & password");
        response = helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(body)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    @Test
    public void registerNoPassword() {
        String body = """
                {
                "email": "sydney@fife"
                }
                """;
        LOGGER.info("Register with no password");
        response= helper.setupRequestBuilder()
                .setPath(BASE_PATH)
                .body(body)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}
