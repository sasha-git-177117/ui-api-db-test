package tests.base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import consts.ConfigEnum;
import database.utils.DBConnectionUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setupClass() {
        AqualityServices.getLogger().info("Настройка браузера");
        ISettingsFile environment = new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label);
        String url = environment.getValue(ConfigEnum.BASE_URL_WEB.label).toString();
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        AqualityServices.getBrowser().goTo(url);
        DBConnectionUtil.getConnection();
    }
    @AfterClass
    public void tearDown() {
        AqualityServices.getBrowser().quit();
        DBConnectionUtil.closeConnection();
    }
}
