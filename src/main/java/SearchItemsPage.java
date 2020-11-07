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

    By minValuePriceField = By.xpath("//*[@id=\"catalog-items-page\"]/div[4]/div[1]/div/div[3]/div[4]/div/div/div[1]/input");
    By maxValuePriceField = By.xpath("//*[@id=\"catalog-items-page\"]/div[4]/div[1]/div/div[3]/div[4]/div/div/div[2]/input");

    By acceptChangesButton = By.xpath("//*[@id=\"catalog-items-page\"]/div[4]/div[1]/div/div[4]/div/button[1]");

    By catalogItem = By.className("catalog-item");
    By currentPriceText = By.className("product-min-price__current");
    By catalogItemsLoader = By.xpath("//a[@class='loader hide']");

    By nextPageArrow = By.xpath("//a[@class='pagination-widget__page-link pagination-widget__page-link_next ']");

    By buyButton = By.className("primary-btn");

    public void SendKeysToMinValuePriceField(int price) {
        driver.findElement(minValuePriceField).sendKeys(Integer.toString(price));
    }

    public void SendKeysToMaxValuePriceField(int price) {
        driver.findElement(maxValuePriceField).sendKeys(Integer.toString(price));
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


//        Actions scrollUp = new Actions(driver);
////        scrollUp.moveToElement(driver.findElement(By.id("header-search"))).build().perform();
////        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, 0);");
//        ((JavascriptExecutor)driver).executeScript("window.scrollBy(" + 0 + "," + -10000 + ");");
//        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
//        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//        ((JavascriptExecutor)driver).executeScript("window.scrollBy(" + 0 + "," + -10000 + ");");
//        try {
//            new WebDriverWait(driver, 3).
//                    until(driver1 -> driver1.findElement(By.tagName("HAHA_ISHI_LOX")).isEnabled());
//        } catch (Exception ignored) {
//            System.out.println("Мы просто подождали 10 секунды");
//        }
//        ((JavascriptExecutor)driver).executeScript("window.scrollBy(" + 0 + "," + -10000 + ");");


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
