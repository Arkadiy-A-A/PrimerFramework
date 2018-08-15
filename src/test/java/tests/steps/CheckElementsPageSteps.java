package tests.steps;

import api.android.Android;
import cucumber.api.java.en_scouse.An;
import cucumber.api.java.ru.И;

/**
 * Класс отвечающий за проверку отображение сразу всех элементов на странице
 *
 * @author Arkadiy
 */
public class CheckElementsPageSteps {

    @И("^проверить отображение элементов на странице \"([^\"]*)\"$")
    public void проверитьОтображениеЭлементовНаСтранице(String namePage) throws Throwable {
        switch (namePage) {
            case "О Банке":
                Android.apps.getCalculator().getLoginHelper().checkMessageLoginAndPasswordFailed();
                break;
            case "О приложении":
                break;
        }
    }
}
