package framework.core;

import api.android.Android;
import framework.core.constants.SwipeEnum;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для swipe
 *
 * @author Arkadiy
 */
public class AndroidSwipe {

    /**
     * Метод для вычесления координат swipe
     *
     * @param swipeConstant направление swipe
     * @return List<Point> координаты
     */
    private static List<Point> getSwipePoints(SwipeEnum swipeConstant) {
        MyLogger.log.debug("Определение точек свайпа - " + swipeConstant);
        Dimension size = Android.driver.manage().window().getSize();
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);
        switch (swipeConstant) {
            case UP:
                start.x = size.width / 2;
                end.x = size.width / 2;
                start.y = (int) (size.height * 0.8);
                end.y = (int) (size.height * 0.2);
                break;
            case DOWN:
                start.x = size.width / 2;
                end.x = size.width / 2;
                start.y = (int) (size.height * 0.2);
                end.y = (int) (size.height * 0.8);
                break;
            case LEFT:
                start.x = (int) (size.width * 0.8);
                end.x = (int) (size.width * 0.2);
                start.y = size.height / 2;
                end.y = size.height / 2;
                break;
            case RIGHT:
                start.x = (int) (size.width * 0.2);
                end.x = (int) (size.width * 0.8);
                start.y = size.height / 2;
                end.y = size.height / 2;
                break;
        }
        return Arrays.asList(start, end);
    }

    /**
     * Свайп
     *
     * @param swipeConstant направление свайпа
     */
    public static void swipe(SwipeEnum swipeConstant) {
        try {
            Thread.sleep(1500);
            List<Point> points = getSwipePoints(swipeConstant);
            MyLogger.log.debug("Выполняем swipe - " + swipeConstant);
            new TouchAction(Android.driver).press(PointOption.point(points.get(0).x, points.get(0).y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(points.get(1).x, points.get(1).y)).release().perform();
            Thread.sleep(1500);
        } catch (Throwable t) {
            Assert.fail("Произошла ошибка в методе 'swipe'", t);
        }
    }

}
