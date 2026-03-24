package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

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

        Assert.assertEquals("Status code incorreto", 200, response.getStatusCode());

        Map<String, Object> breeds = response.jsonPath().getMap("message");
        Assert.assertNotNull("Lista de raças não deveria ser nula", breeds);
        Assert.assertTrue("Lista de raças deveria conter itens", breeds.size() > 0);

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

        int status = response.getStatusCode();

        System.out.println("Status imagem: " + status);

        // Tratamento de API instável
        Assert.assertTrue("Status inesperado",
                status == 200 || status == 503);

        if (status == 200) {
            String imageUrl = response.jsonPath().getString("message");
            Assert.assertNotNull("URL da imagem não deveria ser nula", imageUrl);
            Assert.assertTrue("URL da imagem inválida", imageUrl.startsWith("http"));
        }
    }

    @Test
    public void deveBuscarImagensPorRaca() {

        String breed = "hound";

        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/breed/" + breed + "/images");

        Assert.assertEquals("Status code incorreto", 200, response.getStatusCode());

        List<String> images = response.jsonPath().getList("message");
        Assert.assertNotNull("Lista de imagens não deveria ser nula", images);
        Assert.assertTrue("Lista de imagens deveria conter itens", images.size() > 0);

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

        Assert.assertEquals("Esperado erro para raça inválida", 404, response.getStatusCode());

        String status = response.jsonPath().getString("status");
        Assert.assertEquals("Status da API deveria ser 'error'", "error", status);
    }

    /**
     * Teste de carga leve (sequencial)
     */
    @Test
    public void deveResponderDentroDoSLAEmCargaLeve() {

        int repeticoes = 10;

        for (int i = 0; i < repeticoes; i++) {

            Response response = RestAssured
                    .given()
                    .when()
                    .get("/breeds/list/all");

            Assert.assertEquals("Status incorreto na execução " + i,
                    200, response.getStatusCode());

            Assert.assertTrue("Tempo acima do SLA na execução " + i,
                    response.getTime() < 2000);
        }
    }

    /**
     * Teste paralelo com métricas (ULTRA SDET)
     */
    @Test
    public void deveValidarPerformanceComMetricas() throws Exception {

        int totalRequisicoes = 20;
        int threads = 5;

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Long>> resultados = new ArrayList<>();

        for (int i = 0; i < totalRequisicoes; i++) {

            resultados.add(executor.submit(() -> {
                Response response = RestAssured
                        .given()
                        .when()
                        .get("/breeds/list/all");

                Assert.assertEquals(200, response.getStatusCode());

                return response.getTime();
            }));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        List<Long> tempos = new ArrayList<>();

        for (Future<Long> resultado : resultados) {
            tempos.add(resultado.get());
        }

        long min = Collections.min(tempos);
        long max = Collections.max(tempos);
        double media = tempos.stream().mapToLong(Long::longValue).average().orElse(0);

        Collections.sort(tempos);
        int index95 = (int) Math.ceil(tempos.size() * 0.95) - 1;
        long p95 = tempos.get(index95);

        System.out.println("\n===== RELATÓRIO PERFORMANCE =====");
        System.out.println("Total requests: " + tempos.size());
        System.out.println("Min: " + min + " ms");
        System.out.println("Max: " + max + " ms");
        System.out.println("Avg: " + media + " ms");
        System.out.println("P95: " + p95 + " ms");
        System.out.println("=================================\n");

        try {
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("target"));

            java.nio.file.Files.write(
                    java.nio.file.Paths.get("target/performance-report.txt"),
                    java.util.Arrays.asList(
                            "Min: " + min,
                            "Max: " + max,
                            "Avg: " + media,
                            "P95: " + p95
                    ),
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND
            );

            System.out.println("ARQUIVO GERADO COM SUCESSO");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (p95 >= 2000) {
            System.out.println("SLA VIOLADO, mas arquivo foi gerado");
        }

        Assert.assertTrue("P95 acima do SLA", p95 < 2000);
    }
}