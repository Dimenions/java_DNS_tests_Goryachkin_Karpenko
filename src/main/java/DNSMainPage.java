import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DNSMainPage {
    WebDriver driver;

    By button_login = By.xpath("//*[@id=\"header-search\"]/div/div[4]/button");
    By buttonEnterWithPassword = By.className("block-other-login-methods__password-caption");

    By fieldLoginPhone = By.xpath("/html/body/header/div[2]/div[2]/div/div/div[2]/div/input");
    By fieldPassword = By.xpath("/html/body/header/div[2]/div[2]/div/div/div[3]/div/input");

    By profileImg = By.xpath("//*[@id=\"header-search\"]/div/div[4]/img[1]");


    public DNSMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void PressButtonEnter() {
        driver.findElement(button_login).click();
    }

    public void PressButtonEnterWithPassword() {
        driver.findElement(buttonEnterWithPassword).click();
    }

    public void SendKeysToFieldLogin(String login) {
        driver.findElement(fieldLoginPhone).sendKeys(login);
    }

    public void SendKeysToFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void SendEnterToFieldPassword() {
        driver.findElement(fieldPassword).sendKeys(Keys.ENTER);
    }

    public Boolean IsProfileLogoLoaded() {
        return driver.findElement(profileImg).isEnabled();
    }


    public Boolean EnterWithPassTest() {
        new WebDriverWait(driver, 10).until(driver -> driver.findElement(button_login));
        PressButtonEnter();
        new WebDriverWait(driver, 10).until(driver -> driver.findElement(buttonEnterWithPassword));
        new WebDriverWait(driver, 10).until(driver -> driver.findElement(fieldLoginPhone));
        PressButtonEnterWithPassword();

        new WebDriverWait(driver, 10).until(driver -> driver.findElement(fieldLoginPhone));
        SendKeysToFieldLogin("89377466887");
        new WebDriverWait(driver, 10).until(driver -> driver.findElement(fieldPassword));
        SendKeysToFieldPassword("odeliL");
        SendEnterToFieldPassword();

        new WebDriverWait(driver, 10).until(driver -> driver.findElement(profileImg));

        return IsProfileLogoLoaded();
    }


    By citySelectElementFirst = By.className("w-choose-city-widget-label");
    By buttonOkCityIsCurrent = By.xpath("//a[@class='btn btn-additional']");

    By findCityTextField = By.className("form-control");

    public void PressCityFirst() {
        driver.findElement(citySelectElementFirst).click();
    }

    public String CityFirstGetText() {
        return driver.findElement(citySelectElementFirst).getText();
    }

    public void PressButtonOkCityIsCurrent() {
        driver.findElement(buttonOkCityIsCurrent).click();
    }

    public void SendKeysFindCityTextField(String city) {
        driver.findElement(findCityTextField).sendKeys(city);
    }

    public void SendEnterFindCityTextField() {
        driver.findElement(findCityTextField).sendKeys(Keys.ENTER);
    }


    public Boolean CityChangeTest(String city) {
        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));

        try {
            PressButtonOkCityIsCurrent();
            PressCityFirst();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println("Тут ващета нету элемента!");
            PressCityFirst();
        }

        SendKeysFindCityTextField(city);
        SendEnterFindCityTextField();

        new WebDriverWait(driver, 10).until(driver -> driver.findElement(button_login));
        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));

        try {
            new WebDriverWait(driver, 3).
                    until(driver1 -> driver1.findElement(By.tagName("HAHA_ISHI_LOX")).isEnabled());
        } catch (Exception ignored) {
            System.out.println("Мы просто подождали три секунды.");
        }

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        if (EnterWithPassTest()) {
            System.out.println(CityFirstGetText());
            return CityFirstGetText().equals("Саратов") == false && CityFirstGetText().equals(city) == true;
        }
        return false;
    }

    By searchField = By.xpath("/html/body/header/nav/div/form/div/input");

    public void SendKeysToSearch(String query) {
        driver.findElement(searchField).sendKeys(query);
    }

    public void SendEnterToSearch() {
        driver.findElement(searchField).sendKeys(Keys.ENTER);
    }

}
