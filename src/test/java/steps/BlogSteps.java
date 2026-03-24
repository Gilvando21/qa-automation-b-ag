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
    public void validar(){
        Assert.assertTrue(page.temResultados());
    }
}