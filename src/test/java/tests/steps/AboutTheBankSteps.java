package tests.steps;

import cucumber.api.java.ru.Также;
import org.testng.Assert;

public class AboutTheBankSteps {

    @Также("^кликнуть по кнопке \"([^\"]*)\" на странице 'О Банке'$")
    public void кликнутьПоКнопкеНаСтраницеЛогин(String nameButton) throws Throwable {
        switch (nameButton) {
            case "Войти":
                break;
            default:
                Assert.fail("Кнопки с наименованием '" + nameButton + "' не существует на странице 'XXX'");
        }
    }
}
