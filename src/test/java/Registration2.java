import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration2 {
    WebDriver driver;

    @BeforeClass
    public void initWebdriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver12.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void startUp() {
        driver.get("https://dev.by/registration");
        WebElement button =
                driver.findElement(By.
                        xpath("/html/body/div/div/div[1]/div/div[2]/ul/li[2]/a"));
        button.click();
        ;
    }

    @AfterMethod
    public void closeBrowser() {
        //  driver.manage().deleteAllCookies();
        // driver.quit();
    }

    @Test // Написать тест на открытие страницы регистрации на сайте dev.by
    // Проверка, что отображаются основные поля и элементы формы
    public void Chek2() {

        WebElement username1 = driver
                .findElement(By.id("user_username"));
        Assert.assertTrue(username1.isDisplayed(), "нет поля ввода юзернейма");


        WebElement user_email1 = driver
                .findElement(By.id("user_email"));
        Assert.assertTrue(user_email1.isDisplayed(), "нет поля ввода имейла");
        WebElement user_password1 = driver
                .findElement(By.id("user_password"));
        Assert.assertTrue(user_password1.isDisplayed(), "нет поля ввода пароля");
        WebElement user_password_confirmation1 = driver
                .findElement(By.id("user_password_confirmation"));
        Assert.assertTrue(user_password_confirmation1.isDisplayed(), "нет поля ввода подтверждения пароля");
        WebElement user_first_name1 = driver
                .findElement(By.id("user_first_name"));
        Assert.assertTrue(user_first_name1.isDisplayed(), "нет поля ввода Имени");

        WebElement user_last_name1 = driver
                .findElement(By.id("user_last_name"));
        Assert.assertTrue(user_last_name1.isDisplayed(), "нет поля ввода фамилии");

        WebElement user_current_position1 = driver
                .findElement(By.id("user_current_position"));
        Assert.assertTrue(user_current_position1.isDisplayed(), "нет поля ввода должности");

        WebElement user_agreement1 = driver
                .findElement(By.id("user_agreement"));
        Assert.assertFalse(user_agreement1.isDisplayed(), "нет чекбокса");

        WebElement commit1 = driver
                .findElement(By.name("commit"));
        Assert.assertTrue(commit1.isDisplayed(), "нет кнопки зарегестрироваться");

        WebElement company1 = driver
                .findElement(By.xpath("//*[@id=\"s2id_current_company_id\"]/a/span[2]/b"));
        Assert.assertTrue(company1.isDisplayed(), "нет кнопки выбрать компанию");

        WebElement file1 = driver
                .findElement(By.name("file"));
        Assert.assertFalse(file1.isDisplayed(), "нет ссылки на загрузку аватара");

        WebElement male1 = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[2]/div[2]/div[1]"));
        Assert.assertTrue(male1.isDisplayed(), "нет ссылки на выбор мужского пола");

        WebElement famale1 = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[2]/div[2]/div[2]"));
        Assert.assertTrue(famale1.isDisplayed(), "нет ссылки на выбор женского пола");

        WebElement text11 = driver
                .findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/span"));
        Assert.assertTrue(text11.isDisplayed(), "нет текста на выбор способа регистрации");

        WebElement text21 = driver
                .findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/ul/li[1]/a"));
        Assert.assertTrue(text21.isDisplayed(), "нет кнопки на выбор способа регистрации гуглом");

        WebElement text31 = driver
                .findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/ul/li[2]/a"));
        Assert.assertTrue(text31.isDisplayed(), "нет кнопки на выбор способа регистрации фейсбуком");


    }

    @Test
    //2. Проверка, что если не совпадают значения полей пароля и подверждения
    //пароля отображается ошибка с соответствующим текстом
    public void Chek3() {

        WebElement field =
                driver.findElement(By.id("user_password"));
        field.sendKeys("12345678");

        WebElement field1 =
                driver.findElement(By.id("user_password_confirmation"));
        field1.sendKeys("12345677");

        WebElement formErrorContent = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/div/div/div[1]/div[1]"));
        Assert.assertTrue(formErrorContent.isDisplayed(), "нет оповещения о несовпадении паролей");
    }

    // 3. Проверка, что если в поле ввода email ввести некорретный адрес,
    //отображается ошибка с соответствующим текстом
    @Test
    public void Chek4() {

        WebElement field =
                driver.findElement(By.id("user_email"));
        field.sendKeys("12345678");


        WebElement formErrorContentEmail = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[4]/div/div/div/div[1]"));
        Assert.assertTrue(formErrorContentEmail.isDisplayed(), "нет оповещения о Неверном формате email");
    }

    //4.
    @Test
    public void Chek5() {

        WebElement user_username =
                driver.findElement(By.id("user_username"));
        user_username.sendKeys("12345678901234567");


        WebElement formErrorContentEmail = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[3]/div/div/div/div[1]"));
        Assert.assertTrue(formErrorContentEmail.isDisplayed(), "нет оповещения о длинном юзернейме");
    }

    //5. Проверить, что для пустых незаполненных обязательных полей
    //отображаются всплывающие подсказки (потребуется xpath) (проверять и
    //текст ошибок)
    @Test
    public void Field2() {
       WebElement button =
                driver.findElement(By.
                        xpath("//*[@id=\"new_user\"]/div[10]/div/div/input[5]"));
        button.click();

        WebElement username =
                driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[3]/div/div/div/div[1]"));
        Assert.assertTrue(username.isDisplayed(), "нет оповещения о пустой форме юзернейма");

    }
    //5. Проверить, что для пустых незаполненных обязательных полей
    //отображаются всплывающие подсказки (потребуется xpath) (проверять и
    //текст ошибок)
    @Test
    public void Field() {
        WebElement button =
                driver.findElement(By.
                        name("commit"));
        button.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement username =
                driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[3]/div/div/div/div[1]"));
        Assert.assertTrue(username.isDisplayed(), "нет оповещения о пустой форме юзернейма");

        WebElement email =
                driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[4]/div/div/div/div[1]"));
        Assert.assertTrue(email.isDisplayed(), "нет оповещения о пустой форме email");

        WebElement pass =
                driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/div/div/div[1]/div[1]"));
        Assert.assertTrue(pass.isDisplayed(), "нет оповещения о пустой форме пароля");

        WebElement pass_re =
                driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/div/div/div[2]/div[1]"));
        Assert.assertTrue(pass_re.isDisplayed(), "нет оповещения о пустой форме подтверждения пароля");

        WebElement checkbox =
                driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[9]/div/div/span/div/div[1]"));
        Assert.assertTrue(checkbox.isDisplayed(), "нет оповещения отсутствии галки в согласии с правилами");
    }
}
