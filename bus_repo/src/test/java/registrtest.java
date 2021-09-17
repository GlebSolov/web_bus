import DAO.usersDAO;
import DAO.user_tripDAO;
import classes.users;
import classes.user_trip;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class registrtest {
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
    public void userregtest() throws SQLException {
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("reg")).click();
        // перешли на страницу регистрации

        usersDAO usersdao = new usersDAO();
        List<users> all = usersdao.loadAll();

        driver.findElement(By.id("name")).sendKeys("ffff");
        driver.findElement(By.id("phone")).sendKeys("+8(888)888-88-88");
        driver.findElement(By.id("submit_reg")).click();

        List<users> new_all = usersdao.loadAll();

        System.out.println(new_all.size());
        System.out.println(all.size());
        assert(new_all.size() == (all.size() + 1));


        driver.get("http://localhost:8080/");
        driver.findElement(By.id("enter_id")).sendKeys("1");
        driver.findElement(By.id("enter_submit")).click();
        // перешли на домашнюю страницу пользователя
        driver.findElement(By.id("enter_admin")).click();
        // перешли на домашнюю страницу администратора

        List<users> rall = usersdao.loadAll();

        int num = driver.findElements(By.name("delete_user_id")).size();
        driver.findElements(By.name("delete_user_id")).get(num - 1).click();

        List<users> rnew_all = usersdao.loadAll();

        System.out.println(rnew_all.size());
        System.out.println(rall.size());
        assert(rnew_all.size() == (rall.size() - 1));

    }

    @Test
    public void ordertest() throws SQLException{
        user_tripDAO users_in_tripsdao = new user_tripDAO();
        List<user_trip> all = users_in_tripsdao.loadAll();

        driver.get("http://localhost:8080/");
        driver.findElement(By.id("enter_id")).sendKeys("1");
        driver.findElement(By.id("enter_submit")).click();
        // перешли на домашнюю страницу пользователя

        new Select(driver.findElement(By.id("from"))).selectByValue("1");
        new Select(driver.findElement(By.id("to"))).selectByValue("2");
        driver.findElement(By.id("observe_trips")).click();
        // выбрали станции отправления и прибытия, теперь выберем рейс на странице выбора рейса

        new Select(driver.findElement(By.id("trip_id"))).selectByValue("1");
        driver.findElement(By.id("finish_order")).click();

        List<user_trip> new_all = users_in_tripsdao.loadAll();
        assert(all.size() == new_all.size() - 1);
    }


}
