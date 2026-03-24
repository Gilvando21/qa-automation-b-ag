
package api.base;

import io.restassured.RestAssured;

public class BaseAPI {
    static {
        RestAssured.baseURI = "https://dog.ceo/api";
    }
}
