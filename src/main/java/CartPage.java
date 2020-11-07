import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By plusItemButton = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[3]/div[2]/div[1]/div/button[2]");
    By currentPriceText = By.className("price__current");

    By cartProduct = By.className("cart-items__product");

    By curretGlobalPriceText = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div/span[3]");

    public void PressPlusItemButton() {
        //driver.findElement(plusItemButton).click();
        Actions action_click = new Actions(driver);
        action_click.clickAndHold(driver.findElement(plusItemButton)).build().perform();

        action_click.release().build().perform();
    }

    public Boolean TestCart() {
        int currentPrice = Integer.parseInt(driver.findElement(cartProduct).findElement(currentPriceText).
                getText().replace("₽", "").replace(" ", ""));

        while (currentPrice < 2999) {
            System.out.println(currentPrice);

            new WebDriverWait(driver, 10).
                    until(driver1 -> driver1.findElement(plusItemButton).isEnabled());

            PressPlusItemButton();

            try {
                new WebDriverWait(driver, 10).
                        until(driver1 -> driver1.findElement(By.tagName("HAHA_ISHI_LOX")).isEnabled());
            } catch (Exception ignored) {
                System.out.println("Мы просто подождали 10 секунды");
            }

            currentPrice = Integer.parseInt(driver.findElement(cartProduct).findElement(currentPriceText).
                    getText().replace("₽", "").replace(" ", ""));
        }

        if (driver.findElement(currentPriceText).getText().equals(driver.findElement(curretGlobalPriceText).getText()))
            return true;

        return false;
    }
}
