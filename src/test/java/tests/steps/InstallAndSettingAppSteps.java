package tests.steps;

import api.android.Android;
import cucumber.api.java.ru.*;
import org.testng.Assert;

/**
 * Класс для степов по настройки приложений
 *
 * @author Arkadiy
 */
public class InstallAndSettingAppSteps {

    @Когда("^скачать последнюю версию приложения Calculator 'Development debug'$")
    public void скачатьПоследнююВерсиюПриложенияCalculator() throws Throwable {
        Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
    }

    @Тогда("^настраиваем приложение Calculator$")
    public void настраиваемПриложениеCalculator() throws Throwable {
        Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
    }

    @И("^проверяем сообщение \"([^\"]*)\" на странице 'Установки и Настройки'$")
    public void проверяемСообщениеНаСтраницеУстановкиИНастройки(String nameMessage) throws Throwable {
        switch (nameMessage) {
            case "Обновление Calculator":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "Calculator - стороннее приложением":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "'App Installed.' - успешной установки":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            default:
                Assert.fail("Сообщение '" + nameMessage + "' не существует на странице 'Установки и Настройки'");
        }
    }

    @Также("^кликнуть по кнопке \"([^\"]*)\" на странице 'Установки и Настройки'$")
    public void кликнутьПоКнопкеНаСтраницеУстановкиИНастройки(String nameButton) throws Throwable {
        switch (nameButton) {
            case "'ОК' - соглашение с обновлением":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "'SETTINGS' - для настройки разрешения":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "'INSTALL' - Calculator2":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "'INSTALL' - Calculator":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "'OPEN' - открыть приложение":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "NEXT":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            case "A":
                Android.apps.getCalculator().waitToLoad().tapBtnSettingCalculator();
                break;
            default:
                Assert.fail("Кнопки с наименованием '" + nameButton + "' " +
                        "не существует на странице 'Установки и Настройки'");
        }
    }

    @Также("^установить переключатель \"([^\"]*)\" в состоянеие \"([^\"]*)\" на странице 'Установки и Настройки'$")
    public void установитьПереключательВСостояниеНаСтраницеУстановкиИНастройки(String nameSwitch, boolean flag) throws Throwable {
        switch (nameSwitch) {
            case "Установка Calculator":
                Android.apps.getCalculator().getLoginHelper().tapBtnLogin();
                break;
            default:
                Assert.fail("Переключателя с наименованием '" + nameSwitch + "' " +
                        "не существует на странице 'Установки и Настройки'");
        }
    }

}
