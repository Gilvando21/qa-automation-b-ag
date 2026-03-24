package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup(){
        driver = DriverFactory.createDriver();
    }

    @After(order = 1)
    public void tearDown(){
        try {
            if(driver != null){
                driver.quit();
            }
        } catch (Exception e){
            System.out.println("Erro ao fechar driver: " + e.getMessage());
        }
    }
}