package tests.utils;

import api.android.Android;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.cucumberjvm.AllureReporter;

/**
 * Класс для тестовой информации здесь нужно прикрутить скриншоты
 *
 * @author Arkadiy
 */
public class TestInfo extends AllureReporter {

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] makeScreenshot() {
        return ((TakesScreenshot) Android.driver).getScreenshotAs(OutputType.BYTES);
    }
}
