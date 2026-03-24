
package api;

import io.restassured.RestAssured;
import org.junit.Test;
import static org.junit.Assert.*;

public class DogApiTest {

    @Test
    public void testListBreeds(){
        var res = RestAssured.get("https://dog.ceo/api/breeds/list/all");
        assertEquals(200, res.statusCode());
    }
}
