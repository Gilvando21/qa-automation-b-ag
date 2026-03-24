package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DogApiTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://dog.ceo/api";
    }

    @Test
    public void deveListarRacas() {

        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/breeds/list/all");

        // Status code
        Assert.assertEquals(200, response.getStatusCode());

        // Conteúdo da resposta
        Assert.assertTrue(response.jsonPath().getMap("message").size() > 0);

        // Diferencial Sênior (performance)
        Assert.assertTrue("Tempo de resposta acima do esperado",
                response.getTime() < 2000);
    }

    @Test
    public void deveRetornarImagemAleatoria() {

        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/breeds/image/random");

        // Status code
        Assert.assertEquals(200, response.getStatusCode());

        // Valida URL da imagem
        String imageUrl = response.jsonPath().getString("message");
        Assert.assertTrue(imageUrl.contains("http"));

        // Performance
        Assert.assertTrue("Tempo de resposta acima do esperado",
                response.getTime() < 2000);
    }

    @Test
    public void naoDeveEncontrarRacaInvalida() {

        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/breed/racaInvalidaXYZ/images");

        // Status esperado (erro)
        Assert.assertEquals(404, response.getStatusCode());
    }
}