package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

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
        Assert.assertEquals("Status code incorreto", 200, response.getStatusCode());

        // Validação de conteúdo
        Map<String, Object> breeds = response.jsonPath().getMap("message");
        Assert.assertNotNull("Lista de raças não deveria ser nula", breeds);
        Assert.assertTrue("Lista de raças deveria conter itens", breeds.size() > 0);

        // SLA (tempo de resposta)
        Assert.assertTrue("Tempo de resposta acima de 2s",
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
        Assert.assertEquals("Status code incorreto", 200, response.getStatusCode());

        // Validação de conteúdo
        String imageUrl = response.jsonPath().getString("message");
        Assert.assertNotNull("URL da imagem não deveria ser nula", imageUrl);
        Assert.assertTrue("URL da imagem inválida", imageUrl.startsWith("http"));

        // SLA
        Assert.assertTrue("Tempo de resposta acima de 2s",
                response.getTime() < 2000);
    }

    @Test
    public void deveBuscarImagensPorRaca() {

        String breed = "hound";

        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/breed/" + breed + "/images");

        // Status code
        Assert.assertEquals("Status code incorreto", 200, response.getStatusCode());

        // Validação de conteúdo
        Assert.assertNotNull("Lista de imagens não deveria ser nula",
                response.jsonPath().getList("message"));

        Assert.assertTrue("Lista de imagens deveria conter itens",
                response.jsonPath().getList("message").size() > 0);

        // SLA
        Assert.assertTrue("Tempo de resposta acima de 2s",
                response.getTime() < 2000);
    }

    @Test
    public void naoDeveEncontrarRacaInvalida() {

        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/breed/racaInvalidaXYZ/images");

        // Status esperado
        Assert.assertEquals("Esperado erro para raça inválida", 404, response.getStatusCode());

        // Validação de mensagem de erro (extra profissional)
        String status = response.jsonPath().getString("status");
        Assert.assertEquals("Status da API deveria ser 'error'", "error", status);
    }
}