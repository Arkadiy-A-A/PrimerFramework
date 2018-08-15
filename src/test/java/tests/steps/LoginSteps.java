package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Также;
import cucumber.api.java.ru.Тогда;
import org.testng.Assert;

/**
 * Класс отвечающий за щаги на странице 'Логин'
 *
 * @author Arkadiy
 */
public class LoginSteps {

    @Тогда("^вводим в поле \"([^\"]*)\" значение \"([^\"]*)\" на странице 'Логин'$")
    public void вводиВПолеЗначениеНаСтраницеЛогин(String nameField, String value) throws Throwable {
        switch (nameField) {
            case "логин":
                Android.apps.getCalculator().getLoginHelper().setTextLoginField(value);
                break;
            case "пароль":
                Android.apps.getCalculator().getLoginHelper().setTextPassword(value);
                break;
        }
    }

    @Также("^кликнуть по кнопке \"([^\"]*)\" на странице 'Логин'$")
    public void кликнутьПоКнопкеНаСтраницеЛогин(String nameButton) throws Throwable {
        switch (nameButton) {
            case "Войти":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            default:
                Assert.fail("Кнопки с наименованием '" + nameButton + "' не существует на странице 'Логин'");
        }
    }


    @И("^проверяем сообщение \"([^\"]*)\" на странице 'Логин'$")
    public void проверяемСообщениеНаСтраницеЛогин(String nameMessage) throws Throwable {
        switch (nameMessage) {
            case "Логин или пароль указаны неверно":
                Android.apps.getCalculator().getLoginHelper().checkMessageLoginAndPasswordFailed();
                break;
            default:
                Assert.fail("Сообщение '" + nameMessage + "' не существует на странице 'Логин'");
        }
    }

}
