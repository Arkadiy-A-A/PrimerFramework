package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.И;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;

/**
 * Класс отвечающий за степы по навигации в приложении
 *
 * @author Arkadiy
 */
public class NavigateSteps {

    @И("^кликнуть по AndroidKey \"([^\"]*)\"$")
    public void нажимаемНаAndroidKey(String nameKey) throws Throwable {
        switch (nameKey) {
            case "BACK":
                Android.driver.pressKey(new KeyEvent(AndroidKey.BACK));
                break;
            case "HOME":
                Android.driver.pressKey(new KeyEvent(AndroidKey.HOME));
                break;
            case "APP_SWITCH":
                Android.driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
                break;
            default:
                Assert.fail("AndroidKey с наименованием '" + nameKey + "' не существует в Android");
        }
    }

    @И("^кликнуть по кнопке навигации \"([^\"]*)\"$")
    public void кликнутьНаКнопкуНавигации(String nameBtn) throws Throwable {
        switch (nameBtn) {
            case "BACK":
                Android.apps.getCalculator().getNavigation().tapBtnBackCalculator();
                break;
            default:
                Assert.fail("Кнопки навигации с наименованием '" + nameBtn + "' не существует в приложении");
        }
    }
}
