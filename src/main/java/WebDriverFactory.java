import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserType) {
        if (browserType.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }  else {
            //Для запуска браузера yandex, необходимо заменить путь до yandexdriver.exe
            System.setProperty("webdriver.chrome.driver", "D:\\yandexdriver.exe");
            return new ChromeDriver();
        }
    }
}