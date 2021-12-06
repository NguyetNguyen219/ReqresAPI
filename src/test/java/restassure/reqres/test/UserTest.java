package restassure.reqres.test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;
import restassure.reqres.requestModel.User;
import restassure.reqres.data.Data;

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
    private int ID = new Random().nextInt(TOTAL_USER) + 1;
    private int PAGE = 2;

    @Severity(SeverityLevel.CRITICAL)
    @Description("Read a list of users")
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

    @Severity(SeverityLevel.NORMAL)
    @Description("Read list of users in one page")
    @Test
    public void getListUsersInPage() {
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("Read an user")
    @Test
    public void getSingleUser() {
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

    @Severity(SeverityLevel.BLOCKER)
    @Description("Create a new user")
    @Test(dataProvider = "user-create", dataProviderClass = Data.class)
    public void createUser(User user) {
        LOGGER.info("POST request to create an user " + user.getEmail());
        response = helper.setupRequestBuilder()
                .forUsers()
                .body(user)
                .post();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().log().body().assertThat()
                .statusCode(SC_CREATED)
                .body("id", notNullValue())
                .body("email", equalTo(user.getEmail()))
                .body("first_name", equalTo(user.getFirst_name()))
                .body("last_name", equalTo(user.getLast_name()))
                .body("createdAt", containsString(date));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Update an user")
    @Test
    public void updateUser() {
        User user = new User(
                "Nana",
                "Halo",
                "recruiter"
        );
        LOGGER.info("Make PUT request to update an user");
        response = helper.setupRequestBuilder()
                .forUser(ID)
                .body(user)
                .put();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("updatedAt", containsString(date));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Delete an user")
    @Test
    public void deleteUser() {
        LOGGER.info("Make DELETE request to delete an user");
        response = helper.setupRequestBuilder()
                .forUser(ID)
                .delete();

        response.then().log().body().assertThat()
                .statusCode(SC_NO_CONTENT);
    }
}