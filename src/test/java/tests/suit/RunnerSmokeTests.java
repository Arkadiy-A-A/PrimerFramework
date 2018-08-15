package tests.suit;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Класс для запуска Smoke-тестов
 *
 * @author Arkadiy
 */
@CucumberOptions(
        features = {"src/test/java/tests/features"},
        glue = {"tests/hooks", "tests/steps"},
        plugin = {"tests.utils.TestInfo"},
        snippets = SnippetType.CAMELCASE,
        tags = {"@InstallAndSettingsApp, @@Authorization"}
)
public class RunnerSmokeTests extends AbstractTestNGCucumberTests {

}
