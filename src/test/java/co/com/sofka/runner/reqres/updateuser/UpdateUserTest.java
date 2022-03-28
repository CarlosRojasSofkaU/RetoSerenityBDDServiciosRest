package co.com.sofka.runner.reqres.updateuser;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/reqres/updateuser/updateUser.feature"},
        glue = {"co.com.sofka.stepdefinition.reqres.updateuser"}
)
public class UpdateUserTest {
}
