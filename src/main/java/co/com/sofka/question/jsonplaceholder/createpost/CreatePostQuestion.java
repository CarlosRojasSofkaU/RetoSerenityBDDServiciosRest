package co.com.sofka.question.jsonplaceholder.createpost;

import co.com.sofka.model.jsonplaceholder.post.Post;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CreatePostQuestion implements Question{

    @Override
    public Post answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Post.class);
    }
}
