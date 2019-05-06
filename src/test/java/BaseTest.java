import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    final static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public static void setup() {
        logger.info("Run browser...");
        System.setProperty("selenide.browser", "chrome");
        System.setProperty("webdriver.chrome.driver", "C:/mailukrnet/driver/chromedriver.exe");
    }

//    @BeforeTest
//    public void openLoginPage() throws InterruptedException {
//        logger.info("Open Login Page");
//        open("https://mail.ukr.net");  //відкриваємо сторінку входу до поштової скриньки
//        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
//        $(By.cssSelector("#id-p")).sendKeys("tarasova!");
//        $(By.cssSelector(".button")).click();
//    }

    @AfterClass
    public static void tearDown(){
        logger.info("tearDown()");

        WebDriver webDriver = WebDriverRunner.getWebDriver();
        logger.info(webDriver.getClass());
        webDriver.quit();
    }
}

