package co.com.sofka.question.reqres.getuser;

import co.com.sofka.model.reqres.user.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUserQuestion implements Question {

    @Override
    public User answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(User.class);
    }
}
