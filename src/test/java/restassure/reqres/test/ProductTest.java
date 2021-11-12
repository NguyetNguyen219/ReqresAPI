package restassure.reqres.test;

import org.junit.jupiter.api.Test;
import restassure.reqres.models.Product;

import java.util.Random;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ProductTest extends ReqresBaseTest {

    public static final String BASE_PATH = "products";

    private int TOTAL_PRODUCT = 12;

    @Test
    public void getListProducts() {
        LOGGER.info("Make GET request to get list of products");
        response = helper.setupRequestBuilder()
                .forProducts()
                .get();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("page", equalTo(1))
                .body("total", equalTo(TOTAL_PRODUCT))
                .body("total_pages", equalTo(2));
    }

    @Test
    public void getSingleProduct() {
        int ID = new Random().nextInt(TOTAL_PRODUCT) + 1;
        LOGGER.info("Make GET request to get a product with id = " + ID);
        response = helper.setupRequestBuilder()
                .forProduct(ID)
                .get();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("data.size()", equalTo(5))
                .body("data.id", equalTo(ID))
                .body("data.name", notNullValue())
                .body("data.year", notNullValue())
                .body("data.color", notNullValue());
    }

    @Test
    public void createProduct() {
        Product product = new Product(
                "yellow daisy",
                "2008",
                "#12FF04",
                "13_2487"
        );
        LOGGER.info("Make POST request to create new product");
        response = helper.setupRequestBuilder()
                .forProducts()
                .body(product)
                .post();

        response.then().log().body().assertThat()
                .statusCode(SC_CREATED)
                .body("id", notNullValue())
                .body("name", equalTo(product.getName()))
                .body("year", equalTo(product.getYear()))
                .body("createdAt", notNullValue());
    }

    @Test
    public void updateProduct() {
        int ID = 8;
        String body = """
                {
                "name": "Green Aloha",
                "year": "1999"
                }
                """;
        LOGGER.info("Make PUT request to update product with id = " + ID);
        response = helper.setupRequestBuilder()
                .forProduct(ID)
                .body(body)
                .put();

        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("updatedAt", notNullValue());
    }
}
