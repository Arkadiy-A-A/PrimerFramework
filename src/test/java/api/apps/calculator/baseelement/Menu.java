package api.apps.calculator.baseelement;

import api.android.Android;
import api.apps.calculator.login.LoginHelper;
import framework.core.Timer;
import framework.core.anatations.Name;
import framework.core.elements.UiObject;
import framework.core.elements.UiSelector;
import lombok.Getter;
import org.testng.Assert;

import java.util.NoSuchElementException;


/**
 * Класс содержит элементы меню
 *
 * @author Arkadiy
 */
public class Menu {

    @Name("Кнопка меню 'три точки' на верхней плашке")
    @Getter(lazy = true) private final UiObject btnMenuInToolbar = new UiSelector()
            .className("android.widget.TextView").description("Меню").makeUiObject();

    @Name("Пункт меню 'О банке'")
    @Getter(lazy = true) private final UiObject btnAboutTheBankInMenu = new UiSelector()
            .resourceId("io.crash.air:id/title").text("О Банке").makeUiObject();

    @Name("Пункт меню 'О программе'")
    @Getter(lazy = true) private final UiObject btnAboutTheProgramInMenu = new UiSelector()
            .resourceId("io.crash.air:id/title").text("О программе").makeUiObject();

    /**
     * Клик по кнопке меню 'три точки'
     *
     * @return LoginHelper
     */
    public Menu tapBtnMenuInToolbar() {
        try {
            Assert.assertTrue(getBtnMenuInToolbar().waitToAppear(10).isClickable(),
                    "Кнопка меню 'три точки' не кликабельная.");
            getBtnMenuInToolbar().tap();
            Timer.waitInMilliSeconds(500);
        } catch (NoSuchElementException e) {
            Assert.fail("Невозможно кликнуть на кнопку меню 'три точки', элемент отсутсвует или заблокирован", e);
        }
        return this;
    }

    /**
     * Клик по пункту меню 'О Банке'
     *
     * @return LoginHelper
     */
    public LoginHelper tapBtnAboutTheBankInMenu() {
        try {
            getBtnAboutTheBankInMenu().waitToAppear(10).tap().waitToDisappear(10);
        } catch (NoSuchElementException e) {
            Assert.fail("Невозможно кликнуть на пункт меню 'О Банке', элемент отсутсвует или заблокирован", e);
        }
        return Android.apps.getCalculator().getLoginHelper().waitToLoad();
    }

    /**
     * Клик по пункту меню 'О программе'
     *
     * @return LoginHelper
     */
    public LoginHelper tapBtnAboutTheProgramInMenu() {
        try {
            getBtnAboutTheProgramInMenu().waitToAppear(10).tap().waitToDisappear(10);
        } catch (NoSuchElementException e) {
            Assert.fail("Невозможно кликнуть на пункт меню 'О программе', элемент отсутсвует или заблокирован", e);
        }
        return Android.apps.getCalculator().waitToLoad();
    }
}
