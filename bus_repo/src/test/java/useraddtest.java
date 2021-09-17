import DAO.usersDAO;
import classes.users;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class useraddtest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        String fireFoxDriver = "src/test/geckodriver";
        System.setProperty("webdriver.gecko.driver", fireFoxDriver);
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile testprofile = profile.getProfile("webcourse");
        FirefoxOptions opt = new FirefoxOptions();
        opt.setProfile(testprofile);
        driver =  new FirefoxDriver(opt);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void useraddtest() throws SQLException {
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("enter_id")).sendKeys("1");
        driver.findElement(By.id("enter_submit")).click();
        // перешли на домашнюю страницу пользователя
        driver.findElement(By.id("enter_admin")).click();
        // перешли на домашнюю страницу администратора
        driver.findElement(By.id("enter_adduser")).click();
        // перешли на страницу создания нового пользователя
        usersDAO usersdao = new usersDAO();
        List<users> all = usersdao.loadAll();
        driver.findElement(By.id("name")).sendKeys("w w w");
        driver.findElement(By.id("passw")).sendKeys("mcwmc");
        driver.findElement(By.id("phone")).sendKeys("+3(333)333-33-33");
        driver.findElement(By.id("enter_submit_adduser")).click();
        List<users> new_all = usersdao.loadAll();
        System.out.println(new_all.size());
        System.out.println(all.size());
        assert(new_all.size() == (all.size() + 1));
    }


    @Test
    public void userdeltest() throws SQLException {
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("enter_id")).sendKeys("1");
        driver.findElement(By.id("enter_submit")).click();
        // перешли на домашнюю страницу пользователя
        driver.findElement(By.id("enter_admin")).click();
        // перешли на домашнюю страницу администратора

        usersDAO usersdao = new usersDAO();
        List<users> all = usersdao.loadAll();

        int num = driver.findElements(By.name("delete_user_id")).size();
        driver.findElements(By.name("delete_user_id")).get(num - 1).click();

        List<users> new_all = usersdao.loadAll();

        System.out.println(new_all.size());
        System.out.println(all.size());
        assert(new_all.size() == (all.size() - 1));
    }
}
