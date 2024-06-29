import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;

public class RegistrationTest {
    private WebDriver driver;
    String email;
    String password;
    String name;
    String json;
    User users = new User();
    String accessToken = "";
    String delete = "Bearer ";

    @Before
    public void setUp(){
        //Запускам браузер
        BaseTest baseTest = new BaseTest();
        driver = baseTest.setup("Chrome");

        Random random = new Random();
        name = "praktikum33";
        email = "praktikum" + random.nextInt(10000000) + "@yandex.ru";
        password = "qwerty"  + random.nextInt(10000000);
        json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\" }";
    }

    @DisplayName("Проверка успешной регистрации")
    @Test
    public void checkPassRegistrationTest(){
        Entrance entrance = new Entrance(driver);
        entrance.clickPrivateOffice();
        entrance.clickRegisterButton();
        entrance.registrationNameInput(name);
        entrance.inputEmailPassword(email,password);
        entrance.clickRegistration();

        entrance.clickPrivateOffice();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();

        Assert.assertTrue(entrance.checkLogin());

        accessToken = users.login(json)
                .extract().body().path("accessToken");

        accessToken = accessToken.replace (delete, "");
        users.delete(accessToken,json);
    }

    @DisplayName("Проверка некоректного пароля при регистрации")
    @Test
    public void checkFailRegistrationTest(){
        password = "qwer";

        Entrance entrance = new Entrance(driver);
        entrance.clickPrivateOffice();
        entrance.clickRegisterButton();

        entrance.registrationNameInput(name);
        entrance.inputEmailPassword(email,password);
        entrance.clickRegistration();

        Assert.assertTrue(entrance.issuedErrorRegistration());
    }

    @After
    public void quit() {
        driver.quit();
    }
}
