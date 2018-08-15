package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.Тогда;

/**
 * Класс отвечающий за степы в меню
 *
 * @author Arkadiy
 */
public class MenuSteps {

    @Тогда("^кликнуть по меню \"([^\"]*)\"$")
    public void кликнуть(String menuName) throws Throwable {
        switch (menuName) {
            case "три точки":
                Android.apps.getCalculator().getMenu().tapBtnMenuInToolbar();
                break;
            case "О Банке":
                Android.apps.getCalculator().getMenu().tapBtnAboutTheBankInMenu();
                break;
            case "О программе":
                Android.apps.getCalculator().getMenu().tapBtnAboutTheProgramInMenu();
                break;
        }
    }
}
