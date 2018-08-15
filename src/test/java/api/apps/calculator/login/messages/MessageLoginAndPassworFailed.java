package api.apps.calculator.login.messages;

import framework.core.anatations.Name;
import framework.core.elements.UiObject;
import framework.core.elements.UiSelector;
import lombok.Getter;
import org.testng.Assert;

/**
 * Класс отвечающий за работу над сообщением 'Логин или пароль указаны неверно'
 *
 * @author Arkadiy
 */
public class MessageLoginAndPassworFailed {

    @Name("Заголовок сообщения 'Calculator'")
    @Getter(lazy = true) private final UiObject title = new UiSelector()
            .resourceId("android:id/alertTitle")
            .text("Calculator").makeUiObject();

    @Name("Текст сообщения 'Логин или пароль указаны неверно'")
    @Getter(lazy = true) private final UiObject messageContent = new UiSelector()
            .className("android.widget.TextView")
            .text("Логин или пароль указаны неверно").makeUiObject();

    @Name("Заголовок сообщения 'Calculator'")
    @Getter(lazy = true) private final UiObject btnClose = new UiSelector()
            .resourceId("android:id/button1")
            .text("ЗАКРЫТЬ").makeUiObject();


    /**
     * Проверка появления сообщения
     * @return true - значит появилось
     */
    public boolean exists() {
        getMessageContent().waitToAppear(10);
        return getMessageContent().exists();
    }

    /**
     * Закрыть сообщение
     */
    public void close() {
        try {
            Assert.assertTrue(getBtnClose().isClickable(),
                    "Кнопка 'ЗАКРЫТЬ' сообщения 'Логин или пароль указаны неверно' не кликабельная.");
            getBtnClose().tap();
        } catch (Throwable t) {
            Assert.fail("Невозможно кликнуть на кнопку 'ЗАКРЫТЬ' сообщения 'Логин или пароль указаны неверно'," +
                    " элемент отсутсвует или заблокирован", t);
        }
    }

}
