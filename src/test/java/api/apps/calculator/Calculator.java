package api.apps.calculator;

import api.android.Android;
import api.apps.calculator.baseelement.Menu;
import api.apps.calculator.baseelement.Navigation;
import api.apps.calculator.login.LoginHelper;
import api.interfaces.Activity;
import api.interfaces.Application;
import lombok.Getter;

/**
 * Класс для работы с приложение
 *
 * @author Arkadiy
 */
public class Calculator implements Application, Activity {

    /**
     * Инициализация Хелпера LoginHelper
     */
    @Getter(lazy = true) private final LoginHelper loginHelper = new LoginHelper();

//    /**
//     * Инициализация Хелпера AboutTheBankHelper
//     */
//    @Getter(lazy = true) private final AboutTheBankHelper aboutTheBankHelper = new AboutTheBankHelper();
//
//    /**
//     * Инициализация Хелпера AboutTheProgrammHelper
//     */
//    @Getter(lazy = true) private final AboutTheProgrammHelper aboutTheProgrammHelper = new AboutTheProgrammHelper();
//
//    /**
//     * Инициализация Хелпера SettingHelper
//     */
//    @Getter(lazy = true) private final SettingHelper settingHelper = new SettingHelper();
//
//    /**
//     * Инициализация Хелпера QuickLoginHelper
//     */
//    @Getter(lazy = true) private final QuickLoginHelper quickLoginHelper = new QuickLoginHelper();
//
//    /**
//     * Инициализация Хелпера CalculatorCheckHelper
//     */
//    @Getter(lazy = true) private final CalculatorCheckHelper calculatorCheckHelper = new CalculatorCheckHelper();
//
//    /**
//     * Инициализация Хелпера RootHelper
//     */
//    @Getter(lazy = true) private final RootHelper rootHelper = new RootHelper();

    /**
     * Инициализация Меню
     */
    @Getter(lazy = true) private final Menu menu = new Menu();

    /**
     * Инициализация Навигации в проложении
     */
    @Getter(lazy = true) private final Navigation navigation = new Navigation();


    @Override
    public void forceStop() {
        Android.adb.forceStopApp(getPackageId());
    }

    @Override
    public void closeAndClearData() {
        Android.adb.closeAndClearAppsData(getPackageId());
    }

    @Override
    public Calculator open() {
        Android.adb.openAppsActivity(getPackageId(), getActivityId());
        return this;
    }

    @Override
    public String getPackageId() {
        return "io.crash.air";
    }

    @Override
    public String getActivityId() {
        return "io.crash.air.presentation.feature.main.activity.MainActivity";
    }

    @Override
    public LoginHelper waitToLoad() {
        return getLoginHelper().waitToLoad();
    }
}
