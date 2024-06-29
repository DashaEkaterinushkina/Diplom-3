import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Transition {
    private final WebDriver driver;
    //Проверка профиля
    private final By checkProfileLocation = By.xpath("//button[text()='Сохранить']");
    //Переход из личного кафинета в конструктор
    private final By locatorConstructor = By.xpath(".//p[text()='Конструктор']");
    //Выход из Личного кабинета
    private final By locatorExit = By.xpath(".//button[contains(@class,'Account_button__14Yp3')]");
    //Переходы по разделам
    private final By bunLocation = By.xpath(".//span[text()='Булки']");
    private final By sauceLocation = By.xpath(".//span[text()='Соусы']");
    private final By toppingLocation = By.xpath(".//span[text()='Начинки']");
    private final By bunCheckLocation = By.xpath(".//div[contains(@class,'tab_tab__1SPyG')]/span[text()='Булки']");
    private final By sauceCheckLocation = By.xpath(".//div[contains(@class,'current')]");
    private final By toppingCheckLocation = By.xpath(".//div[contains(@class,' pt-4 pr-10 pb-4 pl-10')]/span[text()='Начинки']");

    public Transition(WebDriver driver) {
        this.driver = driver;
    }
    //Проверка открытия профиля
    public boolean checkProfile(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(checkProfileLocation));
        WebElement restoration = driver.findElement(checkProfileLocation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", restoration);
        return driver.findElement(checkProfileLocation).isDisplayed();
    }

    public void clickConstructor(){
        driver.findElement(locatorConstructor).click();
    }

    public void clickExit(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(locatorExit));
        WebElement restoration = driver.findElement(locatorExit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", restoration);
        driver.findElement(locatorExit).click();
    }

    public String checkBun(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(bunLocation));
        driver.findElement(bunLocation).click();
        WebElement element = driver.findElement(bunCheckLocation);
        return element.getText();
    }

    public String checkSauce(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(sauceLocation));
        driver.findElement(sauceLocation).click();
        WebElement element = driver.findElement(sauceCheckLocation);
        return element.getText();
    }

    public String checkTopping(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(toppingLocation));
        driver.findElement(toppingLocation).click();
        WebElement element = driver.findElement(toppingCheckLocation);
        return element.getText();
    }
}
