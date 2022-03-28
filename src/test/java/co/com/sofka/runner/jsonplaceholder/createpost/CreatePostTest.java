package co.com.sofka.runner.jsonplaceholder.createpost;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/jsonplaceholder/createpost/createPost.feature"},
        glue = {"co.com.sofka.stepdefinition.jsonplaceholder.createpost"}
)

public class CreatePostTest {
}
