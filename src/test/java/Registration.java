import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration {

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
                        xpath("/html/body/div/div/div[1]/div/div[2]/ul/li[1]/a"));
        button.click();
        ;
    }

    @AfterMethod
    public void closeBrowser() {
        //  driver.manage().deleteAllCookies();
        //  driver.quit();
    }


    @Test // 1. Написать тест на открытие страницы регистрации на сайте dev.by
    //Проверка, что отображаются основные поля и элементы формы

    public void Chek1() {

        WebElement username = driver
                .findElement(By.id("user_username"));
        Assert.assertTrue(username.isDisplayed(), "нет поля ввода юзернейма");

        WebElement user_email = driver
                .findElement(By.id("user_email"));
        Assert.assertTrue(user_email.isDisplayed(), "нет поля ввода имейла");
        WebElement user_password = driver
                .findElement(By.id("user_password"));
        Assert.assertTrue(user_password.isDisplayed(), "нет поля ввода пароля");
        WebElement user_password_confirmation = driver
                .findElement(By.id("user_password_confirmation"));
        Assert.assertTrue(user_password_confirmation.isDisplayed(), "нет поля ввода подтверждения пароля");
        WebElement user_first_name = driver
                .findElement(By.id("user_first_name"));
        Assert.assertTrue(user_first_name.isDisplayed(), "нет поля ввода Имени");

        WebElement user_last_name = driver
                .findElement(By.id("user_last_name"));
        Assert.assertTrue(user_last_name.isDisplayed(), "нет поля ввода фамилии");

        WebElement user_current_position = driver
                .findElement(By.id("user_current_position"));
        Assert.assertTrue(user_current_position.isDisplayed(), "нет поля ввода должности");

        WebElement user_agreement = driver
                .findElement(By.id("user_agreement"));
        Assert.assertFalse(user_agreement.isDisplayed(), "нет чекбокса");

        WebElement commit = driver
                .findElement(By.name("commit"));
        Assert.assertTrue(commit.isDisplayed(), "нет кнопки зарегестрироваться");

        WebElement company = driver
                .findElement(By.xpath("//*[@id=\"s2id_current_company_id\"]/a/span[2]/b"));
        Assert.assertTrue(company.isDisplayed(), "нет кнопки выбрать компанию");

        WebElement file = driver
                .findElement(By.name("file"));
        Assert.assertFalse(file.isDisplayed(), "нет ссылки на загрузку аватара");

        WebElement male = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[2]/div[2]/div[1]"));
        Assert.assertTrue(male.isDisplayed(), "нет ссылки на выбор мужского пола");

        WebElement famale = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[2]/div[2]/div[2]"));
        Assert.assertTrue(famale.isDisplayed(), "нет ссылки на выбор женского пола");

        WebElement text1 = driver
                .findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/span"));
        Assert.assertTrue(text1.isDisplayed(), "нет текста на выбор способа регистрации");

        WebElement text2 = driver
                .findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/ul/li[1]/a"));
        Assert.assertTrue(text2.isDisplayed(), "нет кнопки на выбор способа регистрации гуглом");

        WebElement text3 = driver
                .findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/ul/li[2]/a"));
        Assert.assertTrue(text3.isDisplayed(), "нет кнопки на выбор способа регистрации фейсбуком");
    }

    @Test
    //2. Проверка, что если не совпадают значения полей пароля и подверждения
    //пароля отображается ошибка с соответствующим текстом
    public void Chek2() {

        WebElement password =
                driver.findElement(By.id("user_password"));
        password.sendKeys("12345678");

        WebElement password_confirmation =
                driver.findElement(By.id("user_password_confirmation"));
        password_confirmation.sendKeys("12345677");

        WebElement formErrorContent = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/div/div/div[1]/div[1]"));
        Assert.assertTrue(formErrorContent.isDisplayed(), "нет оповещения о несовпадении паролей");
    }

    // 3. Проверка, что если в поле ввода email ввести некорретный адрес,
    //отображается ошибка с соответствующим текстом
    @Test
    public void Chek3() {

        WebElement email =
                driver.findElement(By.id("user_email"));
        email.sendKeys("12345678");


        WebElement formErrorContentEmail = driver
                .findElement(By.xpath("//*[@id=\"new_user\"]/div[4]/div/div/div/div[1]"));
        Assert.assertTrue(formErrorContentEmail.isDisplayed(), "нет оповещения о Неверном формате email");
    }

    //4. Проверка, что при вводе юзернейма длиной больше 16 символов,
    //отображается ошибка с соответствующим текстом
    @Test
    public void Chek4() {

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