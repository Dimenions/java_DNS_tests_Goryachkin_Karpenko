import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class TestsDNS {

    @Test
    void AuthTest() {
        System.setProperty("webdriver.opera.driver", "C:\\operadriver_win64\\operadriver.exe");
        OperaDriver driver = new OperaDriver();
        driver.get("https://www.dns-shop.ru");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        DNSMainPage dnsMainPage = new DNSMainPage(driver);

        Assert.assertTrue(dnsMainPage.EnterWithPassTest());
    }

    @Test(dataProvider = "CitysOfDNS")
    void CityChangeTest(String data) {
        System.setProperty("webdriver.opera.driver", "C:\\operadriver_win64\\operadriver.exe");
        OperaDriver driver = new OperaDriver();
        driver.get("https://www.dns-shop.ru");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        DNSMainPage dnsMainPage = new DNSMainPage(driver);

        Assert.assertTrue(dnsMainPage.CityChangeTest(data));
    }

    @Test
    void CartTesting() {
        System.setProperty("webdriver.opera.driver", "C:\\operadriver_win64\\operadriver.exe");
        OperaDriver driver = new OperaDriver();
        driver.get("https://www.dns-shop.ru");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        DNSMainPage dnsMainPage = new DNSMainPage(driver);

        dnsMainPage.SendKeysToSearch("Электрическая зубная щётка");
        dnsMainPage.SendEnterToSearch();

        SearchItemsPage searchItemsPage = new SearchItemsPage(driver);

        searchItemsPage.FindTestToothBrush();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.TestCart());
    }

    @DataProvider
    public Object[] CitysOfDNS() {
        return new String[]{
                "Камышин",
                "Волгоград",
                "Владивосток"};
    }
}
