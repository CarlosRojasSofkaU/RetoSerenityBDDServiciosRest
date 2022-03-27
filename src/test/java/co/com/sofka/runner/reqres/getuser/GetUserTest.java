package co.com.sofka.runner.reqres.getuser;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/reqres/getuser/getUser.feature"},
        glue = {"co.com.sofka.stepdefinition.reqres.getuser"}
)
public class GetUserTest {
}
