import DAO.scheduleDAO;
import classes.schedule;
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

import org.openqa.selenium.*;


public class addtriptest {
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
    public void tripaddtest() throws SQLException {
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("enter_id")).sendKeys("1");
        driver.findElement(By.id("enter_submit")).click();
        // перешли на домашнюю страницу пользователя
        driver.findElement(By.id("enter_admin")).click();
        // перешли на домашнюю страницу администратора
        driver.findElement(By.id("enter_addtrip")).click();
        // перешли на страницу создания нового пользователя

        scheduleDAO scheduledao = new scheduleDAO();
        List<schedule> all = scheduledao.getAllTrips();

        new Select(driver.findElement(By.id("routeselect"))).selectByValue("1");
        WebElement datebox = driver.findElement(By.id("datetime"));
        datebox.sendKeys("2017-06-01T08:30");
        driver.findElement(By.id("seats")).sendKeys("1");
        driver.findElement(By.id("enter_submit_addtrip")).click();

        List<schedule> new_all = scheduledao.getAllTrips();

        System.out.println(new_all.size());
        System.out.println(all.size());
        assert(new_all.size() == (all.size() + 1));
    }

    @Test
    public void tripdeltest() throws SQLException {
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("enter_id")).sendKeys("1");
        driver.findElement(By.id("enter_submit")).click();
        // перешли на домашнюю страницу пользователя
        driver.findElement(By.id("enter_admin")).click();
        // перешли на домашнюю страницу администратора

        scheduleDAO scheduledao = new scheduleDAO();
        List<schedule> all = scheduledao.getAllTrips();

        int num = driver.findElements(By.name("trip_id")).size();
        driver.findElements(By.name("trip_id")).get(num - 1).click();

        List<schedule> new_all = scheduledao.getAllTrips();

        System.out.println(new_all.size());
        System.out.println(all.size());
        assert(new_all.size() == (all.size() - 1));
    }



}
