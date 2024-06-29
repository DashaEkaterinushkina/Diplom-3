import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Entrance {
    private final WebDriver driver;
    //Кнопка Личный кабинет
    private final By privateOffice = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2'][text()='Личный Кабинет']");
    //Кнопка регистрации
    private final By registerButton = By.className("Auth_link__1fOlj");
    //Ввод данных
    private final By nameInput =  By.xpath("//label[text() = 'Имя']/following-sibling::input[@class='text input__textfield text_type_main-default']");
    private final By emailInput =  By.xpath("//label[text() = 'Email']/following-sibling::input[@class='text input__textfield text_type_main-default']");
    private final By passwordInput = By.xpath("//input[@class='text input__textfield text_type_main-default'][@type='password']");
    //Проверка регистрации
    private final By registration = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorLocator = By.xpath(".//p[text()='Некорректный пароль']");
    //Локатор для кнопки Войти
    private final By locatorLoginButton = By.xpath(".//button[contains(@class,'button_type_primary__1O7Bx')]");
    //Кнопка для логотипа Stellar Burgers
    private final By locatorLogotypeBurger = By.xpath(".//div[contains(@class,'header')]/a");
    //Проверка входа пользователя - можно оформить заказ
    private final By checkLoginLocation = By.xpath(".//button[text()='Оформить заказ']");
    //Проверка входа с главной страницы
    private final By locatorLoginMainPage = By.xpath(".//button[text()='Войти в аккаунт']");
    //Проверка входа со страницы восстановления пароля
    private final By locatorRestorationPassword = By.xpath(".//a[text()='Восстановить пароль']");
    private final By clickComeRestoration = By.xpath("//a[text()='Войти']");

    public Entrance(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickPrivateOffice() {
        new WebDriverWait(driver, 9).until(ExpectedConditions.elementToBeClickable(privateOffice));
        driver.findElement(privateOffice).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Ввод имени")
    public void registrationNameInput(String name) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(nameInput));
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("ВВод Email и Password")
    public void inputEmailPassword(String email, String password){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(emailInput));
        WebElement emailInputClick = driver.findElement(emailInput);
        emailInputClick.click();
        emailInputClick.sendKeys(email);

        WebElement passwordInputClick = driver.findElement(passwordInput);
        passwordInputClick.click();
        passwordInputClick.sendKeys(password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegistration(){
        driver.findElement(registration).click();
    }

    @Step("Клик по логотипу Stellar Burgers")
    public void clickLogotypeBurger(){
        driver.findElement(locatorLogotypeBurger).click();
    }

    @Step("Клик по кнопке Войти в аккаунт на главной странице")
    public void clickLoginMainPage(){
        driver.findElement(locatorLoginMainPage).click();
    }

    @Step("Клик по кнопке Войти в форме регистрации")
    public void clickLoginRegistrationPage(){driver.findElement(clickComeRestoration).click();}

    @Step("Клик по восстановлению пароля + клик по кнопке Войти")
    public void clickRestorationPassword(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(locatorRestorationPassword));
        WebElement restoration = driver.findElement(locatorRestorationPassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", restoration);
        restoration.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(emailInput));
        driver.findElement(clickComeRestoration).click();
    }

    @Step("Проверка некоректного пароля")
    public boolean issuedErrorRegistration() {
        return driver.findElement(errorLocator).isDisplayed();
    }

    @Step("Проверка успешного входа")
    public boolean checkLogin(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(checkLoginLocation));
        return driver.findElement(checkLoginLocation).isDisplayed();
    }

    @Step("Клик по кнопке Войти ")
    public void clickCome(){
        driver.findElement(locatorLoginButton).click();
    }

    @Step("Проверка выхода - возможность зарегистрироваться")
    public boolean checkExit(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(registerButton));
        WebElement restoration = driver.findElement(registerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", restoration);
        return driver.findElement(registerButton).isDisplayed();
    }
}
