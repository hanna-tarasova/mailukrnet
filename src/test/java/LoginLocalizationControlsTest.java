import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;





        public class LoginLocalizationControlsTest extends BaseTest
        {
            @BeforeClass
            public void openLoginPage() throws InterruptedException {
                logger.info("Open Login Page");
                open("https://mail.ukr.net");  //відкриваємо сторінку входу до поштової скриньки
            }

            @Test
            public void checkControlsLocalization() throws InterruptedException {
                $(By.xpath("//div[2]/button[1]/span[1]")).shouldBe(Condition.text("Українська")).click();
                //включаємо українську мову



                $(By.cssSelector(".button")).isDisplayed();

                //$("#ytLogout").shouldBe(Condition.visible).waitUntil(Condition.visible, 30000).click();



                SoftAssert softAssertion = new SoftAssert();
                softAssertion.assertEquals("Увійти", $(By.cssSelector(".button")).getText(), "Неправильний текст для кнопки логіну");
              //softAssertion.assertEquals("Не вдається уувійти", $(By.cssSelector(".form__controls")).getText(), "Неправильний текст для кнопки відновлення доступу");
                softAssertion.assertEquals("Не вдається увійти?", $(By.xpath("//div[4]/a[1]")).getText(), "Неправильний текст для кнопки відновлення доступу (укр)");
                softAssertion.assertEquals("Створити скриньку", $(By.xpath("//div[4]/a[2]")).getText(), "Неправильний текст для кнопки реєстрації (укр)");
                Thread.sleep(2000);



                $(By.xpath("//div[2]/button[2]/span[1]")).shouldBe(Condition.text("Русский")).click();
                softAssertion.assertEquals("Войти", $(By.cssSelector(".button")).getText(), "Неправильний текст для кнопки логіну (рус)");
                softAssertion.assertEquals("Не удается войти?", $(By.xpath("//div[4]/a[1]")).getText(), "Неправильний текст для кнопки відновлення доступу (рус)");
                softAssertion.assertEquals("Создать ящик", $(By.xpath("//div[4]/a[2]")).getText(), "Неправильний текст для кнопки реєстрації (рус)");
                Thread.sleep(2000);


                $(By.xpath("//div[2]/button[3]/span[1]")).shouldBe(Condition.text("English")).click();
                softAssertion.assertEquals("Trouble signing in?", $(By.xpath("//div[4]/a[1]")).getText(), "Неправильний текст для кнопки відновлення доступу (англ)");
                softAssertion.assertEquals("Sign up", $(By.xpath("//div[4]/a[2]")).getText(), "Неправильний текст для кнопки реєстрації (англ)");
                Thread.sleep(2000);
                softAssertion.assertAll();}

            }
