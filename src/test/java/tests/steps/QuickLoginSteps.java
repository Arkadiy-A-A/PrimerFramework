package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.Также;
import org.testng.Assert;

/**
 * Класс отвечающий за щаги на странице 'Быстрый вход'
 *
 * @author Arkadiy
 */
public class QuickLoginSteps {

    @Также("^кликнуть по кнопке \"([^\"]*)\" на странице 'Быстрый вход'$")
    public void кликнутьПоКнопкеНаСтраницеБыстрыйВход(String nameButton) throws Throwable {
        switch (nameButton) {
            case "В следующий раз":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            default:
                Assert.fail("Кнопки с наименованием '" + nameButton + "' не существует на странице 'Быстрый вход'");
        }
    }
}
