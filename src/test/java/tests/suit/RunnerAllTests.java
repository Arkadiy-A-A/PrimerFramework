package tests.suit;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Класс для запуска всех тестов
 *
 * @author Arkadiy
 */
@CucumberOptions(
        features = {"src/test/java/tests/features"},
        glue = {"tests/hooks", "tests/steps"},
        plugin = {"tests.utils.TestInfo"},
        snippets = SnippetType.CAMELCASE,
        tags = {"@InstallAndSettingsApp, @All, @EndTest"}
)
public class RunnerAllTests extends AbstractTestNGCucumberTests {

}
