package restassure.reqres.data;

import org.testng.annotations.DataProvider;
import restassure.reqres.requestModel.Product;
import restassure.reqres.requestModel.User;

public class Data {

    @DataProvider(name = "user-create")
    public static Object[][] userForCreate() {
        User user = new User(
                "murphin123@yahoo.com",
                "Mania",
                "Murphin",
                "developper"
        );
        User user1 = new User(
                "hugo@gmail.com",
                "Boga",
                "Hugo",
                "teacher"
        );
        User user2 = new User(
                "nick.vitas@gmail.com",
                "Nick",
                "Vitas",
                "BA"
        );
        User user3 = new User(
                "nguyet@gmail.com",
                "Nguyet",
                "Nguyen",
                "tester"
        );
        return new User[][] {{user}, {user1}, {user2}, {user3}};
    }



    @DataProvider(name = "product-create")
    public static Object[][] productForCreate() {
        Product product = new Product(
                "yellow daisy",
                "2008",
                "#12FF04",
                "13_2487"
        );
        Product product1 = new Product(
                "black jack",
                "1993",
                "#AAAAA",
                "14_7563"
        );
        Product product2 = new Product(
                "Lalaland",
                "2018",
                "#D2BAA",
                "2_1342"
        );
        return new Product[][]{{product}, {product1}, {product2}};
    }
}
