package restassure.reqres.test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import restassure.reqres.data.Data;
import restassure.reqres.requestModel.Product;

import java.util.Random;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ProductTest extends ReqresBaseTest {

    public static final String BASE_PATH = "products";

    private int TOTAL_PRODUCT = 12;
    private int ID = new Random().nextInt(TOTAL_PRODUCT) + 1;

    @Severity(SeverityLevel.NORMAL)
    @Description("Read list of products")
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

    @Severity(SeverityLevel.NORMAL)
    @Description("Read a product")
    @Test
    public void getSingleProduct() {
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

    @Severity(SeverityLevel.NORMAL)
    @Description("Create a product")
    @Test(dataProvider = "product-create", dataProviderClass = Data.class)
    public void createProduct(Product product) {
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

    @Severity(SeverityLevel.NORMAL)
    @Description("Update a product")
    @Test
    public void updateProduct() {
        Product product = new Product(
                "Green Aloha",
                "1999"
        );
        LOGGER.info("Make PUT request to update product with id = " + ID);
        response = helper.setupRequestBuilder()
                .forProduct(ID)
                .body(product)
                .put();
        response.then().log().body().assertThat()
                .statusCode(SC_OK)
                .body("updatedAt", notNullValue());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Delete a product")
    @Test
    public void deleteProduct() {
        LOGGER.info("Make DELETE request to delete product with id = " + ID);
        response = helper.setupRequestBuilder()
                .forProduct(ID)
                .delete();
        response.then().log().body().assertThat()
                .statusCode(SC_NO_CONTENT);
    }
}
