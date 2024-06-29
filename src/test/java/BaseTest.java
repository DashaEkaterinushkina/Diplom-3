import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    private WebDriver driver ;

    //Открыть браузер
    public WebDriver setup(String browser){
        ChromeOptions options = new ChromeOptions(); // Драйвер для браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = WebDriverFactory.getWebDriver(System.getProperty("browser", browser));
        driver.get(Constant.URL_CONST);
        return driver;
    }
}