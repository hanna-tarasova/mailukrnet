import com.codeborne.selenide.Condition;
import helpers.ApachePOIreadHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;



public class LoginErrorsUkrTest extends BaseTest {
     private final static Logger logger = Logger.getLogger(LoginErrorsUkrTest.class);


    //јктивац≥¤ дата провайдера
    @DataProvider
    public Object[][] errorLoginUkr(Method method) {

        ApachePOIreadHelper excelReader = new ApachePOIreadHelper();
        File file = new File("C:/Users/hanna/IdeaProjects/mailukrnet/data_file/login_errors_ukr.xlsx");
        excelReader.setExcelFile(String.valueOf(file), "errors");
        List rowsNo = excelReader.getRowContains(method.getName(), 0);
        return excelReader.getTableArray(rowsNo);
    }
    @BeforeClass
    public void openLoginPage() throws InterruptedException {
        logger.info("Open Login Page");
        open("https://mail.ukr.net");  //відкриваємо сторінку входу до поштової скриньки
    }

    @Test(dataProvider = "errorLoginUkr")
    //формуванн¤ масиву даних з файла, що був оброблений дата провайдером
    public void errorLoginUkr(ArrayList data) throws InterruptedException {
        String loginName = String.valueOf(data.get(1));
        String inputPassword = String.valueOf(data.get(2));
        String displayedError = String.valueOf(data.get(3));
        logger.info("loginName = " + loginName);
        logger.info("inputPassword = " + inputPassword);
        logger.info("displayedError = " + displayedError);




        open("https://accounts.ukr.net/login");
        Thread.sleep(2000);

        //¬же сам тест
        $(By.cssSelector("#id-l")).sendKeys(loginName);
        $(By.cssSelector("#id-p")).sendKeys(inputPassword);
        $(By.cssSelector(".button")).click();

        $(By.className("form__error")).shouldHave(text(displayedError));
        assertTrue("Bebebe.", $(By.className("form__error")).getText().equals(displayedError));
        Thread.sleep(2000);
        refresh();
        $(By.id("id-l")).sendKeys(Keys.F5);
    }

    private void assertTrue(String s, boolean equals) {
    }
}

