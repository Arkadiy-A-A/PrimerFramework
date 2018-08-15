package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Также;
import org.testng.Assert;

/**
 * Класс отвечающий за щаги на странице 'Root'
 *
 * @author Arkadiy
 */
public class RootSteps {

    @И("^проверяем Spotlight \"([^\"]*)\" на странице 'Root'$")
    public void проверяемSpotlightНаСтраницеRoot(String nameMessage) throws Throwable {
        switch (nameMessage) {
            case "Платежи и шаблоны":
                Android.apps.getCalculator().getLoginHelper().checkMessageLoginAndPasswordFailed();
                break;
            case "Перевод между счетами":
                Android.apps.getCalculator().getLoginHelper().checkMessageLoginAndPasswordFailed();
                break;
            case "Calculator-чек":
                Android.apps.getCalculator().getLoginHelper().checkMessageLoginAndPasswordFailed();
                break;
            default:
                Assert.fail("Spotlight '" + nameMessage + "' не существует на странице 'Root'");
        }
    }

    @Также("^кликнуть по кнопке \"([^\"]*)\" на странице 'Root'$")
    public void кликнутьПоКнопкеНаСтраницеRoot(String nameButton) throws Throwable {
        switch (nameButton) {
            case "Платежи и переводы":
                Android.apps.getCalculator().getLoginHelper().checkMessageLoginAndPasswordFailed();
                break;
            default:
                Assert.fail("Кнопки с наименованием '" + nameButton + "' не существует на странице 'Root'");
        }
    }
}
