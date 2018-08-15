package api.apps.calculator.baseelement;

import framework.core.Timer;
import framework.core.anatations.Name;
import framework.core.elements.UiObject;
import framework.core.elements.UiSelector;
import lombok.Getter;
import org.testng.Assert;

import java.util.NoSuchElementException;

/**
 * Класс содержит Элементы навигации, которые существуют на некоторых страницах
 *
 * @author Arkadiy
 */
public class Navigation {

    @Name("Кнопка навигации '<-' вернуться назад")
    @Getter(lazy = true) private final UiObject btnBack = new UiSelector()
            .className("android.widget.ImageButton").instance(0).makeUiObject();

    /**
     * Клик по кнопке "<-"
     */
    public void tapBtnBackCalculator() {
        try {
            Assert.assertTrue(getBtnBack().waitToAppear(10).isClickable(),
                    "Кнопка '<-' не кликабельная.");
            getBtnBack().tap();
            Timer.waitInMilliSeconds(500);
        } catch (NoSuchElementException e) {
            Assert.fail("Невозможно кликнуть на кнопка '<-', элемент отсутсвует или заблокирован", e);
        }
    }
}
