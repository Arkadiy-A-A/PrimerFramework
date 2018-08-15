package tests.hooks;

import api.android.Android;
import framework.core.managers.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.testng.Assert;

import static api.apps.Apps.closeAllApp;

/**
 * Класс для выполнения некоторых действий перед и после прохождения сценариев.
 *
 * @author Arkadiy
 */
public class Hooks {

    @Before(value = "@InstallAndSettingsApp")
    public void setUp() {
        try {
            DriverManager.createDriver();
        } catch (Exception e) {
            Assert.fail("Произошла ошибка в методе 'setUp' - Before!!!", e);
        }
    }

    @After(value = "@InstallAndSettingsApp")
    public void tearDownInstallAndSettingsApp() {
        try {
            closeAllApp();
        } catch (Exception e) {
            Assert.fail("Произошла ошибка в методе 'tearDownInstallAndSettingsApp' - After!!!", e);
        }
    }

    @After(value = "@All, ~@InstallAndSettingsApp")
    public void tearDownAll() {
        try {
            Android.adb.forceStopApp(Android.apps.getCalculator().getPackageId());
        } catch (Exception e) {
            Assert.fail("Произошла ошибка в методе 'tearDownAll' - After!!!", e);
        }
    }

    @After(value = "@EndTest")
    public void tearDownEndTest() {
        try {
            DriverManager.killDriver();
        } catch (Exception e) {
            Assert.fail("Произошла ошибка в методе 'tearDownEndTest' - After!!!", e);
        }
    }

}
