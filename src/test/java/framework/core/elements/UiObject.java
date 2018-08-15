package framework.core.elements;

import api.android.Android;
import framework.core.MyLogger;
import framework.core.Timer;
import framework.core.constants.SwipeEnum;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static framework.core.AndroidSwipe.swipe;

/**
 * Обертка над WebElement с добавлением функционала элементов Android
 *
 * @author Arkadiy
 */
public class UiObject {

    /**
     * Локатор
     */
    private String locator;

    /**
     * Конструктор
     *
     * @param locator локатор элемента
     */
    UiObject(String locator) {
        this.locator = locator;
        MyLogger.log.debug("Создание нового UiObject: " + this.locator);
    }

    /**
     * Проверка локатора на содержание обычного хPath или кастомного
     *
     * @return true - если обычный xPath
     */
    private boolean isXpath() {
        return !locator.contains("UiSelector");
    }

    /**
     * Проверка отображения элемента
     *
     * @return true - элемент отображается на экрне
     */
    public boolean exists() {
        try {
            MyLogger.log.debug("Проверяем существования элемента " + locator);
            WebElement element;
            if (isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Проверка св-ва checked
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isChecked() {
        MyLogger.log.debug("Проверяем значения атрибута checked в элемента " + locator);
        return findUiObject().getAttribute("checked").equals("true");
    }


    /**
     * Проверка св-ва checkable
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isCheckable() {
        MyLogger.log.debug("Проверяем значения атрибута checkable в элемента " + locator);
        return findUiObject().getAttribute("checkable").equals("true");
    }

    /**
     * Проверка св-ва clickable
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isClickable() {
        MyLogger.log.debug("Проверяем значения атрибута clickable в элемента " + locator);
        return findUiObject().getAttribute("clickable").equals("true");
    }

    /**
     * Проверка св-ва enabled
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isEnabled() {
        MyLogger.log.debug("Проверяем значения атрибута enabled в элемента " + locator);
        return findUiObject().getAttribute("enabled").equals("true");
    }

    /**
     * Проверка св-ва focusable
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isFocusable() {
        MyLogger.log.debug("Проверяем значения атрибута focusable в элемента " + locator);
        return findUiObject().getAttribute("focusable").equals("true");
    }

    /**
     * Проверка св-ва focused
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isFocused() {
        MyLogger.log.debug("Проверяем значения атрибута focused в элемента " + locator);
        return findUiObject().getAttribute("focused").equals("true");
    }

    /**
     * Проверка св-ва scrollable
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isScrollable() {
        MyLogger.log.debug("Проверяем значения атрибута scrollable в элемента " + locator);
        return findUiObject().getAttribute("scrollable").equals("true");
    }

    /**
     * Проверка св-ва longClickable
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isLongClickable() {
        MyLogger.log.debug("Проверяем значения атрибута longClickable в элемента " + locator);
        return findUiObject().getAttribute("longClickable").equals("true");
    }

    /**
     * Проверка св-ва selected
     *
     * @return true - если св-во элемента установлено в true
     */
    public boolean isSelected() {
        MyLogger.log.debug("Проверяем значения атрибута selected в элемента " + locator);
        return findUiObject().getAttribute("selected").equals("true");
    }

    /**
     * Получить кординат элемента
     *
     * @return Point
     */
    public Point getLocation() {
        MyLogger.log.debug("Поучем координат элемента " + locator);
        return findUiObject().getLocation();
    }

    /**
     * Получить текст элемента
     *
     * @return String
     */
    public String getText() {
        MyLogger.log.debug("Поучем значения атрибута name(text) в элемента " + locator);
        return findUiObject().getAttribute("name");
    }

    /**
     * Получить id элемента
     *
     * @return String
     */
    public String getResourceId() {
        MyLogger.log.debug("Поучем значения атрибута resourceId в элемента " + locator);
        return findUiObject().getAttribute("resourceId");
    }

    /**
     * Получить className элемента
     *
     * @return String
     */
    public String getClassName() {
        MyLogger.log.debug("Поучем значения атрибута className в элемента " + locator);
        return findUiObject().getAttribute("className");
    }

    /**
     * Получить contentDesc элемента
     *
     * @return String
     */
    public String getContentDesc() {
        MyLogger.log.debug("Поучем значения атрибута contentDesc в элемента " + locator);
        return findUiObject().getAttribute("contentDesc");
    }

    /**
     * Очистка поля
     *
     * @return UiObject
     */
    public UiObject clearText() {
        MyLogger.log.debug("Очищаем поле " + locator);
        findUiObject().clear();
        return this;
    }

    /**
     * Заполнить поле текстом
     *
     * @param value теск которым заполняется поле
     * @return UiObject
     */
    public UiObject setText(String value) {
        MyLogger.log.debug("Вводим текст " + value + " в поле " + locator);
        findUiObject().sendKeys(value);
        return this;
    }

    /**
     * Клик по элементу
     *
     * @return UiObject
     */
    public UiObject tap() {
        MyLogger.log.debug("Кликаем по элементу " + locator);
        findUiObject().click();
        return this;
    }

    /**
     * Ожидаем появления элемента
     *
     * @param seconds количество секкунд которые мы ожидаем
     * @return UiObject
     */
    public UiObject waitToAppear(int seconds) {
        MyLogger.log.debug("Ожидаем " + seconds + " секунд до появления элемента " + locator);
        int intervalRequest = seconds/10 * 1000;
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(seconds)) {
            Timer.waitInMilliSeconds(intervalRequest);
            if (exists()) break;
        }
        if (timer.expired(seconds) && !exists()) {
            Assert.fail("Элемент " + locator + " не появился за " + seconds + " сек.");
        }
        return this;
    }

    /**
     * Ожидаем исчезновения элемента
     *
     * @param seconds количество секкунд которые мы ожидаем
     * @return UiObject
     */
    public UiObject waitToDisappear(int seconds) {
        MyLogger.log.debug("Ожидаем " + seconds + " секунд до исчезновения элемента " + locator);
        int intervalRequest = seconds/10 * 1000;
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(seconds)) {
            Timer.waitInMilliSeconds(intervalRequest);
            if (!exists()) break;
        }
        if (timer.expired(seconds) && exists()) {
            Assert.fail("Элемент " + locator  + " не исчез за " + seconds + " сек.");
        }
        return this;
    }

    /**
     * Свайп до элемента (поиск элемента по тексту)
     * Метод должен вызываться только относительно списка элементов
     * Список элементов определяется UiSelector-пути
     *
     * @param swipeConstant направление свайпа
     * @param value         значение которое хотим найти
     * return UiObject - Возвращаем конкретный найдный элемент
     */
    public UiObject swipeToElement(SwipeEnum swipeConstant, String value) {
        UiObject elementFind = new UiObject(locator + ".text(\"" + value + "\")");
        String element;
        String tempElement;
        do {
            element = this.getText();
            if (elementFind.exists()) {
                MyLogger.log.debug("Элемент с текстом '" + value + "' найден");
                return elementFind;
            } else {
                swipe(swipeConstant);
            }
            tempElement = this.getText();
        } while (!element.equals(tempElement));
        MyLogger.log.debug("При swipe - " + swipeConstant + " элемент не был найден");
        return elementFind;
    }

    /**
     * Поиск элемента либо по xPath либо по кастомному пути через UIAutomator
     *
     * @return WebElement
     */
    public WebElement findUiObject() {
        try {
            MyLogger.log.debug("Поиск элемента " + locator);
            if (isXpath()) {
                return (WebElement) Android.driver.findElementByXPath(locator);
            } else {
                return (WebElement) Android.driver.findElementByAndroidUIAutomator(locator);
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Элемент " + locator + " не удалось найти!!!", e);
        }
        return null;
    }

    /**
     * Поиск элементов либо по xPath либо по кастомному пути через UIAutomator
     *
     * @return WebElement
     */
    public List<WebElement> findUiObjects() {
        try {
            MyLogger.log.debug("Поиск элементов " + locator);
            if (isXpath()) {
                return Android.driver.findElementsByXPath(locator);
            } else {
                return Android.driver.findElementsByAndroidUIAutomator(locator);
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Элементы " + locator + " не найдены!!!", e);
        }
        return null;
    }

}
