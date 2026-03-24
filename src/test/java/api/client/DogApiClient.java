
package api.client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DogApiClient {

    public Response listBreeds(){
        return given().when().get("/breeds/list/all");
    }

    public Response imagesByBreed(String breed){
        return given().pathParam("breed",breed)
                .when().get("/breed/{breed}/images");
    }

    public Response randomImage(){
        return given().when().get("/breeds/image/random");
    }
}
