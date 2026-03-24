
package api.tests;

import api.base.BaseAPI;
import api.client.DogApiClient;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DogApiTest extends BaseAPI {

    DogApiClient client;

    @Before
    public void setup(){
        client = new DogApiClient();
    }

    @Test
    public void deveListarRacas(){
        Response res = client.listBreeds();
        Assert.assertEquals(200,res.statusCode());
        Assert.assertTrue(res.jsonPath().getMap("message").size()>0);
    }

    @Test
    public void deveRetornarImagem(){
        Response res = client.randomImage();
        Assert.assertEquals(200,res.statusCode());
    }
}
