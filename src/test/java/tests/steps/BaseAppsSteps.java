package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.*;
import framework.core.MyLogger;
import framework.core.Timer;
import org.testng.Assert;

/**
 * Общие действия над приложениями
 *
 * @author Arkadiy
 */
public class BaseAppsSteps {

    @Допустим("^запущенно приложение \"([^\"]*)\"$")
    public void запущенноПриложение(String nameApp) throws Throwable {
        switch (nameApp) {
            case "XXX":
                if (!Android.adb.getForegroundActivity().contains(Android.apps.getCalculator().getPackageId())) {
                    открытьПриложение("XXX");
                }
                break;
            default:
                Assert.fail("Приложение с наименованием '" + nameApp + "' не корректно, " +
                        "так как не предназначено для тестирования!!!");
        }
    }

    public void открытьПриложение(String nameApp) throws Throwable {
        switch (nameApp) {
            case "XXX":
                Android.apps.getCalculator().open().waitToLoad();
                break;
            default:
                Assert.fail("Приложение с наименованием '" + nameApp + "' не корректно, " +
                        "так как не предназначено для тестирования!!!");
        }
    }


    @И("^останавливаем эмулятор$")
    public void останавливаемЭмулятор() throws Throwable {
        MyLogger.log.info("Внимание перед остановкой эмулятора произойдет сброс некоторых настроект!!!");
        // Удаляем приложения которые устанавливали
        Android.adb.uninstallApp(Android.apps.getCalculator().getPackageId());
        Timer.waitInMilliSeconds(3000);
        MyLogger.log.info("Удаление приложений Calculator не произошло успешно!!!");
    }
}
