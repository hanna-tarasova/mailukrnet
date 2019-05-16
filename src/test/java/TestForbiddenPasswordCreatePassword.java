import helpers.ApachePOIreadHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestForbiddenPasswordCreatePassword extends BaseTest {
    final static Logger logger = Logger.getLogger(TestForbiddenPasswordCreatePassword.class);

    //јктивац≥¤ дата провайдера
    @DataProvider
    public Object[][] errorDataForPasswordUa (Method method){

        ApachePOIreadHelper excelReader = new ApachePOIreadHelper();
        File file = new File("C:/Users/hanna/IdeaProjects/mailukrnet/data_file/forbidden_password.xlsx");
        excelReader.setExcelFile(String.valueOf(file), "ForbiddenTest");
        List rowsNo = excelReader.getRowContains(method.getName(), 0 );
        return excelReader.getTableArray(rowsNo);
    }

    @Test(dataProvider = "errorDataForPasswordUa")
    //формуванн¤ масиву даних з файла, що був оброблений дата провайдером
    public void forbiddenPassword(ArrayList data) throws InterruptedException {
        String testName = String.valueOf(data.get(0));
        String inputPassword = String.valueOf(data.get(1));
        String displayedError = String.valueOf(data.get(2));
        logger.info("testName = " + testName);
        logger.info("inputPassword = " + inputPassword);
        logger.info("displayedError = " + displayedError);

        open("https://accounts.ukr.net/registration");
        Thread.sleep(2000);

        //¬же сам тест
        $(By.cssSelector("#id-password")).sendKeys(inputPassword);
        $(By.cssSelector("#id-password-repeat")).click();
        $(By.cssSelector(".input-default__error.is-size-normal")).shouldHave(text(displayedError));
        assertTrue("No user found.",$(By.cssSelector(".input-default__error.is-size-normal")).getText().equals(displayedError));
        Thread.sleep(2000);
        refresh();
    }

    private void assertTrue(String s, boolean equals) {
    }
}