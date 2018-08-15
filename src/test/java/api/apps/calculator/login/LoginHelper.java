package api.apps.calculator.login;

import api.android.Android;
import api.interfaces.Activity;
import framework.core.Timer;
import org.testng.Assert;

import java.util.NoSuchElementException;

/**
 * Хелпер для работы со страницой 'Логин'
 *
 * @author Arkadiy
 */
public class LoginHelper implements Activity {

    /**
     * Инициализация страницы Логина приложении
     */
    private LoginPage page = new LoginPage();

    /**
     * Клик по кнопке "A" app Calculator
     *
     * @return LoginHelper
     */
    public LoginHelper tapBtnSettingCalculator() {
        try {
            Assert.assertTrue(page.getBtnSettingCalculator().waitToAppear(10).isClickable(),
                    "Кнопка 'A' не кликабельная.");
            page.getBtnSettingCalculator().tap();
            Timer.waitInMilliSeconds(500);
        } catch (NoSuchElementException e) {
            Assert.fail("Невозможно кликнуть на кнопка 'A', элемент отсутсвует или заблокирован", e);
        }
        return this;
    }

    /**
     * Заполнения поля 'логин'
     *
     * @param login логин пользователя
     * @return LoginHelper
     */
    public LoginHelper setTextLoginField(String login) {
        try {
            Assert.assertTrue(page.getLoginField().waitToAppear(5).isClickable(),
                    "Поле 'Логин' не кликабельная.");
            page.getLoginField().tap().clearText().setText(login);
            Timer.waitInMilliSeconds(500);
        } catch (NoSuchElementException e) {
            Assert.fail("При заполнение элемент 'login' текстом, " +
                    "произошла ошибка - метод 'setTextLoginField'", e);
        }
        return this;
    }

    /**
     * Заполнения поля 'password'
     *
     * @param password пароль пользователя
     * @return LoginHelper
     */
    public LoginHelper setTextPassword(String password) {
        try {
            Assert.assertTrue(page.getPasswordField().waitToAppear(5).isClickable(),
                    "Поле 'Enter your password' не кликабельная.");
            page.getPasswordField().tap().clearText().setText(password);
            Timer.waitInMilliSeconds(500);
        } catch (NoSuchElementException e) {
            Assert.fail("При заполнение элемент 'password' текстом, " +
                    "произошла ошибка - метод 'setTextPassword'", e);
        }
        return this;
    }

    /**
     * Клик по кнопке "Войти" app Calculator
     *
     * @return LoginHelper
     */
    public LoginHelper tapBtnLogin() {
        try {
            Assert.assertTrue(page.getBtnLogin().waitToAppear(5).isClickable(),
                    "Кнопка 'Войти' не кликабельная.");
            page.getBtnLogin().tap();
            Timer.waitInMilliSeconds(500);
        } catch (NoSuchElementException e) {
            Assert.fail("Невозможно кликнуть на кнопка 'A', элемент отсутсвует или заблокирован", e);
        }
        return this;
    }

    /**
     * Проверка сообщения 'Логин или пароль указаны неверно'
     *
     * @return LoginHelper
     */
    public LoginHelper checkMessageLoginAndPasswordFailed() {
        try {
            Assert.assertTrue(page.getMessageLoginAndPassworFailed().exists(),
                    "Сообщение 'Логин или пароль указаны неверно' не появилось");
            page.getMessageLoginAndPassworFailed().close();
        } catch (NoSuchElementException e) {
            Assert.fail("Произошла ошибка при работе с сообщение 'Логин или пароль указаны неверно'", e);
        }
        return this;
    }


    @Override
    public LoginHelper waitToLoad() {
        try {
            page.getBtnSettingCalculator().waitToAppear(20);
        } catch (Throwable t) {
            Assert.fail("Страница 'Логина' - не загрузилась/не открылась", t);
        }
        return Android.apps.getCalculator().getLoginHelper();
    }

}
