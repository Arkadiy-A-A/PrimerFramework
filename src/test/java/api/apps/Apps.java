package api.apps;

import api.android.Android;
import api.apps.calculator.Calculator;
import framework.core.Timer;
import framework.core.elements.UiSelector;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lombok.Getter;
import org.testng.Assert;

/**
 * Класс содержит список приложений и общие методы работы с приложениями
 *
 * @author Arkadiy
 */
public class Apps {

    /**
     * Инициализация приложения
     */
    @Getter(lazy = true) private final Calculator calculator = new Calculator();


    /**
     * Закрыть все приложений с сохранением их настроект, но не сессий
     */
    public static void closeAllApp() {
        try {
            Timer.waitInMilliSeconds(1000);
            Android.driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
            Timer.waitInMilliSeconds(2000);
            new UiSelector().resourceId("com.android.systemui:id/button").text("CLEAR ALL").makeUiObject().waitToAppear(15).tap();
        } catch (Exception e) {
            Assert.fail("Не удалось кликнуть по элемент 'CLEAR ALL', элемент недоступен или заблокирован");
        }
    }

    /**
     * Свернуть все приложений с сохранением их настроект, но не сессий
     */
    public static void hideAllApp() {
        try {
            Timer.waitInMilliSeconds(1000);
            Android.driver.pressKey(new KeyEvent(AndroidKey.HOME));
            Timer.waitInMilliSeconds(1500);
        } catch (Exception e) {
            Assert.fail("Не удалось вызвать кнопку 'HOME', элемент недоступен или заблокирован");
        }
    }

}
