package api.apps.calculator.login;

import api.apps.calculator.login.messages.MessageLoginAndPassworFailed;
import framework.core.anatations.Name;
import framework.core.elements.UiObject;
import framework.core.elements.UiSelector;
import lombok.Getter;

import java.util.List;

/**
 * Класс содержащий все элементы страницы 'Логин'
 *
 * @author Arkadiy
 */
public class LoginPage {

    @Name("Кнопка 'A' - в app Calculator, которая ведет к настройкам")
    @Getter(lazy = true) private final UiObject btnSettingCalculator = new UiSelector()
            .resourceId("io.crash.air:id/debug_setting").makeUiObject();

    @Name("Поле 'Логин'")
    @Getter(lazy = true) private final UiObject loginField= new UiSelector()
            .resourceId("io.crash.air:id/login").makeUiObject();

    @Name("Поле 'Пароль'")
    @Getter(lazy = true) private final UiObject passwordField = new UiSelector()
            .resourceId("io.crash.air:id/password").makeUiObject();

    @Name("Кнопка 'Войти'")
    @Getter(lazy = true) private final UiObject btnLogin = new UiSelector()
            .resourceId("io.crash.air:id/login_button").makeUiObject();

    @Name("Кнопка 'Отделение'")
    @Getter(lazy = true) private final UiObject btnBranchOfTheBank = new UiSelector()
            .resourceId("io.crash.air:id/main_widget_title")
            .text("Отделения").makeUiObject();

    @Name("Кнопка 'Банкоматы'")
    @Getter(lazy = true) private final UiObject btnAtmsOfTheBank = new UiSelector()
            .resourceId("io.crash.air:id/main_widget_title")
            .text("Банкоматы").makeUiObject();

    @Name("Кнопка 'Новости'")
    @Getter(lazy = true) private final UiObject btnNewsOfTheBank = new UiSelector()
            .resourceId("io.crash.air:id/main_widget_title")
            .text("Новости").makeUiObject();

    @Name("Список версий приложений Calculator в Андроид")
    @Getter(lazy = true) private final List<UiObject> listName = new UiSelector()
            .resourceId("io.crash.air:id/previous_build_version").makeListUiObjects();

    @Name("Список кнопок 'Download' - нужной версии")
    @Getter(lazy = true) private final List<UiObject> listBtnDownload = new UiSelector()
            .resourceId("io.crash.air:id/previous_build_install_button").makeListUiObjects();

    @Name("Текст 'Позвонить в Банк'")
    @Getter(lazy = true) private final UiObject textCallTheBank = new UiSelector()
            .className("android.widget.TextView")
            .text("Позвонить в Банк").makeUiObject();

    @Name("Сообщение 'Логин или пароль указаны неверно'")
    @Getter(lazy = true) private final MessageLoginAndPassworFailed messageLoginAndPassworFailed =
            new MessageLoginAndPassworFailed();
}
