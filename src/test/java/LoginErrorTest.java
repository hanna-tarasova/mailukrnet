
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.codeborne.selenide.Condition;
import java.io.IOException;
import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.*;


public class LoginErrorTest extends BaseTest {
    @BeforeClass
    public void openLoginPage() throws InterruptedException {
        logger.info("Open Login Page");
        open("https://mail.ukr.net");  //відкриваємо сторінку входу до поштової скриньки
    }

    @Test
    public void checkWrongDataRu() throws InterruptedException {
        $(By.xpath("//div[2]/button[2]/span[1]")).shouldBe(Condition.text("Русский")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasov!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(5000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Неправильные данные", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();

        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);

    }




    @Test
    public void checkWrongDataUkr() throws InterruptedException {
        $(By.xpath("//div[2]/button[1]/span[1]")).shouldBe(Condition.text("Українська")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasov!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(5000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Неправильні дані", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);

    }

    @Test
    public void checkWrongDataEn() throws InterruptedException {
        $(By.xpath("//div[2]/button[3]/span[1]")).shouldBe(Condition.text("English")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasov!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(5000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Wrong data", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(5000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }







    @Test
    public void checkEmptyDataRu() throws InterruptedException {
        $(By.xpath("//div[2]/button[2]/span[1]")).shouldBe(Condition.text("Русский")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("");
        $(By.cssSelector("#id-p")).sendKeys("");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Поле должно быть заполнено", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }


    @Test
    public void checkEmptyDataUkr() throws InterruptedException {
        $(By.xpath("//div[2]/button[1]/span[1]")).shouldBe(Condition.text("Українська")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("");
        $(By.cssSelector("#id-p")).sendKeys("");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Поле має бути заповнене", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }

    @Test
    public void checkEmptyDataEn() throws InterruptedException {
        $(By.xpath("//div[2]/button[3]/span[1]")).shouldBe(Condition.text("English")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("");
        $(By.cssSelector("#id-p")).sendKeys("");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("You can’t leave this empty", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }


    @Test
    public void checkPwdChangedRu() throws InterruptedException {
        $(By.xpath("//div[2]/button[2]/span[1]")).shouldBe(Condition.text("Русский")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);
        $(By.cssSelector(".login__login-button")).hover();

        $(By.id("login__logout")).click();
        Thread.sleep(2000);
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova_");
        $(By.cssSelector(".button")).click();

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Пароль изменен. Введите новый пароль", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);

    }



    @Test
    public void checkPwdChangedUkr() throws InterruptedException {
        $(By.xpath("//div[2]/button[1]/span[1]")).shouldBe(Condition.text("Українська")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);
        $(By.cssSelector(".login__login-button")).hover();

        $(By.id("login__logout")).click();
        Thread.sleep(2000);
        $(By.xpath("//div[2]/button[1]/span[1]")).shouldBe(Condition.text("Українська")).click();
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova_");
        $(By.cssSelector(".button")).click();

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Пароль змінено. Введіть новий пароль", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);

    }


    @Test
    public void checkPwdChangedEng() throws InterruptedException {
        $(By.xpath("//div[2]/button[3]/span[1]")).shouldBe(Condition.text("English")).click();
        //включаємо рос мову
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");
        $(By.cssSelector(".button")).click();
        Thread.sleep(2000);
        $(By.cssSelector(".login__login-button")).hover();

        $(By.id("login__logout")).click();
        Thread.sleep(2000);
        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova_");
        $(By.cssSelector(".button")).click();

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Password is changed. Enter a new password", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        Thread.sleep(3000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);

    }



    @Test
    public void checkNoConnectionRu() throws InterruptedException, IOException {
        $(By.xpath("//div[2]/button[2]/span[1]")).shouldBe(Condition.text("Русский")).click();
        //включаємо рос мову

        Runtime.getRuntime().exec ("netsh wlan disconnect");
        Thread.sleep(8000);

        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");



        $(By.cssSelector(".button")).click();
        Thread.sleep(8000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Отсутствует подключение к Интернету", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();

        Runtime.getRuntime().exec ("netsh wlan connect name=Xiaomi_Home");
        Thread.sleep(3000);
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }


    @Test
    public void checkNoConnectionUkr() throws InterruptedException, IOException {
        $(By.xpath("//div[2]/button[1]/span[1]")).shouldBe(Condition.text("Українська")).click();
        //включаємо рос мову

        Runtime.getRuntime().exec ("netsh wlan disconnect");
        Thread.sleep(8000);

        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");



        $(By.cssSelector(".button")).click();
        Thread.sleep(8000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("Відсутнє підключення до Інтернету", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();

        Runtime.getRuntime().exec ("netsh wlan connect name=Xiaomi_Home");
        Thread.sleep(3000);
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();

        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }

    @Test
    public void checkNoConnectionEng() throws InterruptedException, IOException {
        $(By.xpath("//div[2]/button[3]/span[1]")).shouldBe(Condition.text("English")).click();
        //включаємо рос мову

        Runtime.getRuntime().exec ("netsh wlan disconnect");
        Thread.sleep(8000);

        $(By.cssSelector("#id-l")).sendKeys("hanna_a");
        $(By.cssSelector("#id-p")).sendKeys("tarasova!");



        $(By.cssSelector(".button")).click();
        Thread.sleep(8000);

        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertEquals("There is no Internet connection", $(By.className("form__error")).getText(), "Шото не то");

        softAssertion.assertAll();

        Runtime.getRuntime().exec ("netsh wlan connect name=Xiaomi_Home");
        Thread.sleep(3000);
        $(By.cssSelector("#id-l")).clear();
        $(By.cssSelector("#id-p")).clear();

        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }

}







