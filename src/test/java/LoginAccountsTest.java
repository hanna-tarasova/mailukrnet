import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginAccountsTest extends BaseTest {

    @BeforeClass
    public void openLoginPage() {
        logger.info("Open Login Page");
        open("https://mail.ukr.net");  //відкриваємо сторінку входу до поштової скриньки
    }

    @Test
    public void TestCorrectLogin() throws InterruptedException {
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Написать письмо", $(By.cssSelector("button.default.compose")).getText(), "Кнопка 'Написати листа' не відображається");

        softAssertion.assertAll();
    }

}
