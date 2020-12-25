import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchItemsPage {
    WebDriver driver;

    public SearchItemsPage(WebDriver driver) {
        this.driver = driver;
    }

    // ui-collapse__content ui-collapse__content_list ui-collapse__content_default-in
//    By minValuePriceField = By.xpath("//*[@class='ui-collapse__content ui-collapse__content_list ui-collapse__content_default-in']/div/div[0]");
    By minValuePriceField = By.xpath("//*[@class='ui-input-small__input ui-input-small__input_list']");
//    By maxValuePriceField = By.xpath("//*[@class='ui-collapse__content ui-collapse__content_list ui-collapse__content_default-in']/div/div[1]");
    By maxValuePriceField = By.xpath("//*[@class='ui-input-small__input ui-input-small__input_list']");

    By acceptChangesButton = By.xpath("//*[@class='button-ui button-ui_brand left-filters__button']");

    By catalogItem = By.className("catalog-item");
    By currentPriceText = By.className("product-min-price__current");
    By catalogItemsLoader = By.xpath("//a[@class='loader hide']");

    By nextPageArrow = By.xpath("//a[@class='pagination-widget__page-link pagination-widget__page-link_next ']");

    By buyButton = By.className("primary-btn");

    public void SendKeysToMinValuePriceField(int price) {
        driver.findElements(minValuePriceField).get(0).sendKeys(Integer.toString(price));
    }

    public void SendKeysToMaxValuePriceField(int price) {
        driver.findElements(maxValuePriceField).get(0).sendKeys(Integer.toString(price));
    }

    public void PressAcceptChangesButton() {
        driver.findElement(acceptChangesButton).click();
    }

    public void ClickNextPageArrow() {
        driver.findElement(nextPageArrow).click();
    }

    public void FindTestToothBrush() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight/5);");

        new WebDriverWait(driver, 2).until(driver1 -> driver1.findElements(minValuePriceField));
        new WebDriverWait(driver, 3).until(driver1 -> driver1.findElements(maxValuePriceField));

        SendKeysToMinValuePriceField(999);
        SendKeysToMaxValuePriceField(1999);

        driver.findElement(acceptChangesButton).getLocation();

        Actions scrollDown = new Actions(driver);
        scrollDown.moveToElement(driver.findElement(acceptChangesButton)).build().perform();

        new WebDriverWait(driver, 10).until(driver1 -> driver1.findElements(catalogItemsLoader));
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));

        PressAcceptChangesButton();

        driver.navigate().refresh();

        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        // Лоадер - это загрузчик товаров. Лоадер hide значит, что он отработал и скрылся.
        new WebDriverWait(driver, 10).until(driver1 -> driver1.findElements(catalogItemsLoader));

        //Бывший Var
        driver.findElements(catalogItem).forEach(item -> {
            System.out.println(item.findElement(currentPriceText).getText());
            int itemPrice = Integer.parseInt(item.findElement(currentPriceText).getText().
                    replace("₽", "").
                    replace(" ", ""));
            if (itemPrice < 999 && itemPrice > 1999)
                throw new ArithmeticException();
        });


        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));

        ClickNextPageArrow();

        driver.navigate().refresh();

        new WebDriverWait(driver, 10).until(driver1 -> driver1.findElements(catalogItemsLoader));

        //Бывший Var
        driver.findElements(catalogItem).forEach(item -> {
            System.out.println(item.findElement(currentPriceText).getText());
            int itemPrice = Integer.parseInt(item.findElement(currentPriceText).getText().
                    replace("₽", "").
                    replace(" ", ""));
            if (itemPrice < 999 && itemPrice > 1999)
                throw new ArithmeticException();
        });

        WebElement buttonBuy = driver.findElements(catalogItem).get(0).findElement(buyButton).findElement(By.tagName("button"));

        new WebDriverWait(driver, 10).until(driver1 -> driver1.findElements(catalogItemsLoader));

        buttonBuy.click();


        new WebDriverWait(driver, 10).until(ExpectedConditions.
                textToBePresentInElement(buttonBuy, "В корзине"));

        buttonBuy.click();
    }
}
