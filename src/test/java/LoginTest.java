import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;

public class LoginTest {
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

        new User().createUser(json)
                .statusCode(200);
        accessToken = users.login(json)
                .extract().body().path("accessToken");
    }

    @DisplayName("Проверка входа с главной страницы")
    @Test
    public void checkAuthenticationMainPageTest(){
        Entrance entrance = new Entrance(driver);

        entrance.clickLoginMainPage();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();

        Assert.assertTrue(entrance.checkLogin());
    }

    @DisplayName("Проверка входа через Личный кабинет")
    @Test
    public void checkAuthenticationTest(){
        Entrance entrance = new Entrance(driver);

        entrance.clickPrivateOffice();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();

        Assert.assertTrue(entrance.checkLogin());
    }

    @DisplayName("Проверка входа через форму регистрации")
    @Test
    public void checkAuthenticationAfterRegister(){
        Entrance entrance = new Entrance(driver);
        entrance.clickPrivateOffice();
        entrance.clickRegisterButton();
        entrance.clickLoginRegistrationPage();

        entrance.inputEmailPassword(email,password);
        entrance.clickCome();

        Assert.assertTrue(entrance.checkLogin());
    }

    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    @Test
    public void checkAuthenticationRestorationPassword(){
        Entrance entrance = new Entrance(driver);  //Регистрация
        entrance.clickPrivateOffice();

        entrance.clickRestorationPassword();

        entrance.inputEmailPassword(email,password);
        entrance.clickCome();

        Assert.assertTrue(entrance.checkLogin());
    }

    @After
    public void deleteUser(){
        if(!accessToken.isEmpty()) {
            accessToken = users.login(json)
                    .extract().body().path("accessToken");

            System.out.println(accessToken);
            accessToken = accessToken.replace (delete, "");
            users.delete(accessToken,json);
        }
        driver.quit();
    }
}
