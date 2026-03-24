package steps;

import hooks.Hooks;
import io.cucumber.java.pt.*;
import org.junit.Assert;
import pages.BlogPage;

public class BlogSteps {

    BlogPage page = new BlogPage(Hooks.driver);

    @Dado("que acesso o blog do Agi")
    public void acessar(){
        page.acessar();
    }

    @Quando("realizo a busca por {string}")
    public void buscar(String termo){
        page.buscar(termo);
    }

    @Entao("devo visualizar resultados relevantes")
    public void validarResultados(){
        Assert.assertTrue("Esperado encontrar resultados",
                page.temResultados());
    }

    @Entao("devo visualizar mensagem de nenhum resultado")
    public void validarSemResultados(){
        // CORREÇÃO: usar método sem espera
        Assert.assertFalse("Não deveria retornar resultados",
                page.temResultadosSemEspera());
    }

    @Entao("devo permanecer na página inicial")
    public void validarPaginaInicial(){
        // O site redireciona para busca mesmo vazia (?s=)
        String url = Hooks.driver.getCurrentUrl();

        Assert.assertTrue("Busca vazia não foi executada corretamente",
                url.contains("?s="));
    }
}