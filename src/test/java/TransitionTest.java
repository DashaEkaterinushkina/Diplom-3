import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;

public class TransitionTest {
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
    @DisplayName("Проверка перехода по клику на Личный кабинет")
    @Test
    public void checkTransitionPrivateOfficeTest(){
        Entrance entrance = new Entrance(driver);
        entrance.clickLoginMainPage();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();

        entrance.clickPrivateOffice();
        Transition transitions = new Transition(driver);
        Assert.assertTrue(transitions.checkProfile());
    }

    @DisplayName("Проверь переход из Личного кабинета по клику на Конструктор")
    @Test
    public void checkTransitionConstructor(){
        Entrance entrance = new Entrance(driver);
        entrance.clickLoginMainPage();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();
        entrance.clickPrivateOffice();

        Transition transitions = new Transition(driver);
        transitions.clickConstructor();

        Assert.assertTrue(entrance.checkLogin());
    }

    @DisplayName("Проверь переход из Личного кабинета по клику на логотип Stellar Burgers")
    @Test
    public void checkTransitionLogotypeText(){
        Entrance entrance = new Entrance(driver);
        entrance.clickLoginMainPage();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();
        entrance.clickPrivateOffice();

        entrance.clickLogotypeBurger();     //Клик по логотипу

        Assert.assertTrue(entrance.checkLogin());
    }

    @DisplayName("Проверка выхода по кнопке Выйти в личном кабинете")
    @Test
    public void checkExitTest(){
        Entrance entrance = new Entrance(driver);
        entrance.clickLoginMainPage();
        entrance.inputEmailPassword(email,password);
        entrance.clickCome();
        entrance.clickPrivateOffice();

        Transition transitions = new Transition(driver);
        transitions.clickExit();

        Assert.assertTrue(entrance.checkExit());
    }

    @Test
    @DisplayName("Проверка переходок к разделам")
    public void checkIngredientMainPageTest(){
        Transition transitions = new Transition(driver);
        Assert.assertEquals("Соусы",transitions.checkSauce());
        Assert.assertEquals("Булки",transitions.checkBun());
        Assert.assertEquals("Начинки",transitions.checkTopping());
        //Знаю, что нельзя делать несколько проверок в одном тесте.
        //По заданию необходимо проверить переходЫ к разделам.
    }

    @After
    public void deleteUser(){
        if(!accessToken.isEmpty()) {
            accessToken = users.login(json)
                    .extract().body().path("accessToken");

            accessToken = accessToken.replace (delete, "");
            users.delete(accessToken,json);
        }
        driver.quit();
    }
}
