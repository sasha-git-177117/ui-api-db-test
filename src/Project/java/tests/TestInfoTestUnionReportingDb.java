package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.common.collect.Comparators;
import consts.*;
import models.*;
import org.apache.http.entity.ContentType;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProjectPage;
import pages.TestInfoPage;
import repositories.*;
import steps.Steps;
import tests.base.BaseTest;
import utils.ApiUtil;
import utils.TestUtil;

import java.util.Comparator;
import java.util.List;

public class TestInfoTestUnionReportingDb extends BaseTest {

    @Test
    public void testInfoTestUnionReportingDb() {
        Logger log = AqualityServices.getLogger();
        ISettingsFile configData = new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label);
        ISettingsFile testData = new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label);
        ISettingsFile js = new JsonSettingsFile(JsScriptEnum.JS_SCRIPT_PATH.label);

        log.info("Шаг 1 - Получение токена запросом к апи");
        String token = ApiUtil.getToken(Endpoints.TOKEN,testData.getValue(TestEnum.VARIANT.label).toString());

        log.info("Шаг 2 - Переход на сайт. Прохождение авторизации. Передача токена. Обновление страницы.");
        AqualityServices.getBrowser().goTo(String.format(configData.getValue(ConfigEnum.AUTH_URL.label).toString(),
                configData.getValue(ConfigEnum.LOGIN_WEB.label),
                configData.getValue(ConfigEnum.PASSWORD_WEB.label)));

        Cookie cookie = new Cookie.Builder(Params.TOKEN.label,token).build();

        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(),"Страница проектов не открылась");

        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
        AqualityServices.getBrowser().getDriver().navigate().refresh();

        Assert.assertEquals(testData.getValue(TestEnum.VARIANT.label),
                TestUtil.getNumberFromString(mainPage.getVersionText()).toString(),"Варианты не равны");

        log.info("Шаг 3 - Переход на страницу проекта Nexage. Запросом к БД получение списка тестов проекта.");
        ProjectPage nexagePage =  mainPage.clickProject(ProjectNames.NEXAGE.label);

        List<TestInfoModel> uiTableTests =  nexagePage.getTestTable().getDataTests();
        List<TestInfoModel> dataBaseTableTests = ApiUtil.getTestsInfo(Endpoints.TEST_INFO_JSON,
                testData.getValue(TestEnum.PROJECT_ID.label).toString());

        Assert.assertTrue(Comparators.isInOrder(uiTableTests, Comparator.reverseOrder()),
                "Тесты на странице отсортированы не по убыванию даты");
        Assert.assertTrue(TestUtil.isAllListItemsContainsInList(uiTableTests,dataBaseTableTests),
                "Тесты с веб-страницы не соответствуют тестам полученным с БД");

        log.info("Шаг 4 - Возврат на предыдущую страницу. Нажатие на +Add. Ввод названия проекта, и сохранение. " +
                "Закрытие алерта с информацией о сохранённом проекте. Обновление страницы");
        mainPage = nexagePage.clickHomeLink();
        Assert.assertTrue(mainPage.state().waitForDisplayed(),"Страница проектов не открылась");

        String projectName = TestUtil.getGeneratorText(TextConstEnum.FIRST_SYMBOL_CODE.label,
                TextConstEnum.LAST_SYMBOL_CODE.label,TextConstEnum.COUNT_SYMBOLS.label);

        Steps.AddProject(projectName,mainPage);
        Assert.assertTrue(mainPage.getAddProjectForm().isDisplayedAlertSuccess(),"Проект не сохранился");

        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
        AqualityServices.getBrowser().getDriver()
                .executeScript(js.getValue(JsScriptEnum.JS_SCRIPT_CLOSE_POP_UP.label).toString());
        Assert.assertTrue(mainPage.getAddProjectForm().state().waitForNotDisplayed(),"Окно добавления не закрылось");

        AqualityServices.getBrowser().getDriver().navigate().refresh();
        Assert.assertTrue(mainPage.isDisplayedProjectName(projectName),"Проект не появился в списке");

        log.info("Шаг 5 - Переход на страницу созданного проекта." +
                " Добавления теста через БД(вместе с логом и скриншотом текущей страницы).");
        ProjectPage newProjectPage = mainPage.clickProject(projectName);
        Assert.assertTrue(newProjectPage.state().waitForDisplayed(),"Страница проекта не открылась");

        String testName = TestUtil.getGeneratorText(TextConstEnum.FIRST_SYMBOL_CODE.label,
                TextConstEnum.LAST_SYMBOL_CODE.label,TextConstEnum.COUNT_SYMBOLS.label);
        String methodName = TestUtil.getGeneratorText(TextConstEnum.FIRST_SYMBOL_CODE.label,
                TextConstEnum.LAST_SYMBOL_CODE.label,TextConstEnum.COUNT_SYMBOLS.label);
        String env = TestUtil.getGeneratorText(TextConstEnum.FIRST_SYMBOL_CODE.label,
                TextConstEnum.LAST_SYMBOL_CODE.label,TextConstEnum.COUNT_SYMBOLS.label);
        String sessionKey = TestUtil.getGeneratorText(TextConstEnum.FIRST_SYMBOL_CODE.label,
                TextConstEnum.LAST_SYMBOL_CODE.label,TextConstEnum.COUNT_SYMBOLS.label);
        Integer sessionId = SessionRepo.addSession(new Session(sessionKey));
        Integer projectId = ProjectRepo.getProjectByName(projectName).getId();

        Integer testId = TestRepo.addTest(new TestDb(testName,methodName, projectId,sessionId,env));

        String testLog = AqualityServices.getBrowser().getLogs(LogType.BROWSER).getAll().toString();
        LogRepo.addLog(new Log(testLog,testId));

        byte[] screenshot = AqualityServices.getBrowser().getScreenshot();
        AttachmentRepo.addAttachment(new Attachment(screenshot,
                ContentType.IMAGE_PNG.getMimeType(),testId));

        newProjectPage.getTestTable().waitToTableRefresh();
        TestInfoModel uiTest =  TestUtil.getTestInfoByTestName
                (testName,newProjectPage.getTestTable().getDataTests());
        Assert.assertEquals(testName,uiTest.getName(),"Тест не отображается на странице");

        log.info("Шаг 6 - Переход на страницу созданного теста.");
        TestInfoPage testInfoPage =  newProjectPage.clickTestButton(testName);
        Assert.assertTrue(testInfoPage.state().waitForDisplayed(),"Страница с информацией о тесте не открылась");
        Assert.assertEquals(testName,testInfoPage.getTestFieldText(TestInfoEnum.TEST_NAME.label),"Названия тестов не совпадают");
        Assert.assertEquals(projectName,testInfoPage.getTestFieldText(TestInfoEnum.PROJECT_NAME.label),"Имена проектов не совпадают");
        Assert.assertEquals(methodName,testInfoPage.getTestFieldText(TestInfoEnum.TEST_METHOD_NAME.label),"Имена методов не совпадают");
        Assert.assertEquals(testLog,testInfoPage.getLog(),"Логи тестов не совпадают");
        Assert.assertEquals(env,testInfoPage.getTestFieldText(TestInfoEnum.ENVIRONMENT.label),"Окружение тестов не совпадает");
        Assert.assertTrue(testInfoPage.getSrcAttachImage().contains(TestUtil.parseByteArrayToBase64(screenshot)), "Картинки тестов не совпадают");

    }
}
