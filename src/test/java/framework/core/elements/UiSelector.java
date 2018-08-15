package framework.core.elements;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Обертка класс для работы с селекторами UiObjects.
 *
 * @author Arkadiy
 */
public class UiSelector {

    /**
     * Локатор UIElement (UiObject)
     */
    private String locator = "new UiSelector()";

    /**
     * Формирование локатора по id элемента
     *
     * @param value id элемента
     * @return UiSelector
     */
    public UiSelector resourceId(String value){
        locator += ".resourceId(\""+value+"\")";
        return this;
    }

    /**
     * Формирование локатора по className элемента
     *
     * @param value className элемента
     * @return UiSelector
     */
    public UiSelector className(String value){
        locator += ".className(\""+value+"\")";
        return this;
    }

    /**
     * Формирование локатора по маске className элемента
     *
     * @param regex маска className элемента
     * @return UiSelector
     */
    public UiSelector classNameMatches(String regex){
        locator += ".classNameMatches(\""+regex+"\")";
        return this;
    }

    /**
     * Формирование локатора по text элемента
     *
     * @param value text элемента
     * @return UiSelector
     */
    public UiSelector text(String value){
        locator += ".text(\""+value+"\")";
        return this;
    }

    /**
     * Формирование локатора по совпадению в text элемента
     *
     * @param value сопадающий text элемента
     * @return UiSelector
     */
    public UiSelector textContains(String value){
        locator += ".textContains(\""+value+"\")";
        return this;
    }

    /**
     * Формирование локатора по index элемента
     *
     * @param value index элемента
     * @return UiSelector
     */
    public UiSelector index(int value){
        locator += ".index("+value+")";
        return this;
    }

    /**
     * Формирование локатора по instance элемента
     *
     * @param value instance элемента
     * @return UiSelector
     */
    public UiSelector instance(int value){
        locator += ".instance("+value+")";
        return this;
    }

    /**
     * Формирование локатора по clickable элемента
     *
     * @param value clickable элемента
     * @return UiSelector
     */
    public UiSelector clickable(boolean value){
        locator += ".clickable("+value+")";
        return this;
    }

    /**
     * Формирование локатора по checked элемента
     *
     * @param value checked элемента
     * @return UiSelector
     */
    public UiSelector checked(boolean value){
        locator += ".checked("+value+")";
        return this;
    }

    /**
     * Формирование локатора по enabled элемента
     *
     * @param value enabled элемента
     * @return UiSelector
     */
    public UiSelector enabled(boolean value){
        locator += ".enabled("+value+")";
        return this;
    }

    /**
     * Формирование локатора по content-desc элемента
     *
     * @param value content-desc элемента
     * @return UiSelector
     */
    public UiSelector description(String value){
        locator += ".description(\""+value+"\")";
        return this;
    }

    /**
     * Формирование локатора по совпадению в content-desc элемента
     *
     * @param value совпадению в content-desc элемента
     * @return UiSelector
     */
    public UiSelector descriptionContains(String value){
        locator += ".descriptionContains(\""+value+"\")";
        return this;
    }

    /**
     * Формирование локатора по маске в content-desc элемента
     *
     * @param regex маске в content-desc элемента
     * @return UiSelector
     */
    public UiSelector descriptionMatches(String regex){
        locator += ".descriptionMatches(\""+regex+"\")";
        return this;
    }

    /**
     * Формирование локатора по xPath элемента
     *
     * @param xPath элемента
     * @return UiSelector
     */
    public UiSelector xPath(String xPath){
        locator = xPath;
        return this;
    }

    /**
     * Формирование объекта UIObject
     *
     * @return UIObject
     */
    public UiObject makeUiObject(){
        return new UiObject(locator);
    }

    /**
     * Формирование список объектов UIObjects
     *
     * @return List<UIObjects>
     */
    public List<UiObject> makeListUiObjects(){
        // передавать локатор на прямую в цикл нельзя так как произойдет рекурсивное формирование локатора
        String seveLocator = locator;
        List<UiObject> list = new ArrayList<>();
        int i = 0;
        for (WebElement element : new UiObject(locator).findUiObjects()) {
            list.add(xPath(seveLocator).instance(i).makeUiObject());
            i++;
        }
        return list;
    }

}
