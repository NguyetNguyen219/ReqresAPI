package restassure.reqres.test;

import org.junit.jupiter.api.Test;
import restassure.reqres.models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

/**
 * Class contains methods to test api for the users
 */
public class UserTest extends ReqresBaseTest {

    public static final String BASE_PATH = "users";

    private int TOTAL_USER = 12;

    @Test
    public void getListUsers() {
        LOGGER.info("Make GET request to get list of " + TOTAL_USER + " users");
        response = helper.setupRequestBuilder()
                .forUsers()
                .addParam("per_page", TOTAL_USER)
                .get();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("per_page", equalTo(TOTAL_USER))
                .body("total", equalTo(TOTAL_USER))
                .body("data.size()", equalTo(TOTAL_USER));
    }

    @Test
    public void getListUsersInPage() {
        int PAGE = 2;
        LOGGER.info("Make GET request to get list of users in page " + PAGE);

        response = helper.setupRequestBuilder()
                .forUsers()
                .addParam("page", PAGE)
                .get();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("page", samePropertyValuesAs(PAGE))
                .body("data", notNullValue());
    }

    @Test
    public void getSingleUser() {
        int ID = new Random().nextInt(TOTAL_USER) + 1;
        LOGGER.info("Make GET request to get user with id = " + ID);

        response = helper.setupRequestBuilder()
                .forUser(ID)
                .get();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("data.size()", greaterThan(4))
                .body("data.id", equalTo(ID))
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue());
    }

    @Test
    public void createUser() {
        User newUser = new User(
                "murphin123@yahoo.com",
                "Mania",
                "Murphin",
                "developper"
        );

        LOGGER.info("Make POST request to create an user");
        response = helper.setupRequestBuilder()
                .forUsers()
                .body(newUser)
                .post();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().log().body().assertThat()
                .statusCode(SC_CREATED)
                .body("id", notNullValue())
                .body("email", equalTo(newUser.getEmail()))
                .body("first_name", equalTo(newUser.getFirst_name()))
                .body("last_name", equalTo(newUser.getLast_name()))
                .body("createdAt", containsString(date));
    }

    @Test
    public void updateUser() {
        int ID = 8;
        String body = """
                {
                "first_name": "Nana",
                "job": "leader"
                }
                """;

        LOGGER.info("Make PUT request to update an user");
        response = helper.setupRequestBuilder()
                .forUser(ID)
                .body(body)
                .put();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("updatedAt", containsString(date));
    }

    @Test
    public void deleteUser() {
        int ID = 11;

        LOGGER.info("Make DELETE request to update an user");
        response = helper.setupRequestBuilder()
                .forUsers()
                .delete();

        response.then().log().body().assertThat()
                .statusCode(SC_NO_CONTENT);
    }
}