import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SecuritySettings extends BaseTest
{

    @BeforeClass
    public void openLoginPage() throws InterruptedException {
        logger.info("Open Login Page");
        open("https://mail.ukr.net");  //відкриваємо сторінку входу до поштової скриньки
     $(By.cssSelector("#id-l")).sendKeys("hanna_a");
     $(By.cssSelector("#id-p")).sendKeys("tarasova!");
     $(By.xpath("//main/form/button")).click();
      Thread.sleep(2000);}
////
////        SoftAssert softAssertion = new SoftAssert();
////
////        softAssertion.assertEquals("Написать письмо", $(By.cssSelector("button.default.compose")).getText(), "Кнопка 'Написати листа' не відображається");
////
////        softAssertion.assertAll();}

        @Test
        public void TestCorrectSignature() throws InterruptedException{
        $(By.className("login-button__user")).click();
        Thread.sleep(2000);

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals("Учетная запись", $(By.cssSelector("a.link2.login-popup__settings-item")).getText(), "Oshibka");

        softAssertion.assertAll();

        $(By.cssSelector("a.link2.login-popup__settings-item")).click();
        $(By.cssSelector("textarea.input")).setValue("Meeeeee");
        $(By.cssSelector(".settings__account button")).click();
        Thread.sleep(10000);
    }
}

