package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlogPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BlogPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By resultados = By.cssSelector("article");

    public void acessar(){
        driver.get("https://blogdoagi.com.br/");
    }

    public void buscar(String termo){
        driver.get("https://blogdoagi.com.br/?s=" + termo);
    }

    public boolean temResultados(){
        wait.until(driver ->
                driver.findElements(resultados).size() > 0
        );
        return driver.findElements(resultados).size() > 0;
    }
}