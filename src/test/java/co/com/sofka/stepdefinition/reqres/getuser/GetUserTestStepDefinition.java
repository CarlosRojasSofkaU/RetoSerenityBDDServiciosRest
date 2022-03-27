package co.com.sofka.stepdefinition.reqres.getuser;

import co.com.sofka.model.reqres.user.Data;
import co.com.sofka.question.reqres.getuser.GetUserQuestion;
import co.com.sofka.stepdefinition.reqres.common.ServiceSetUpReqRes;
import static co.com.sofka.task.GetResource.getResource;
import static co.com.sofka.util.ResourceCases.SINGLE_USER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class GetUserTestStepDefinition extends ServiceSetUpReqRes {
    private final HashMap<String, Object> headers = new HashMap<>();
    private static final String SOAP_ACTION = "SOAPAction";
    private final Actor actor = Actor.named("Carlos");
    private static final Logger LOGGER = Logger.getLogger(GetUserTestStepDefinition.class);

    @Dado("que el usuario esta en el recurso web indicando el id {int}")
    public void queElUsuarioEstaEnElRecursoWebIndicandoElId(Integer id) {
        generalSetUp();
        actor.can(CallAnApi.at(RestAssured.baseURI+RestAssured.basePath));
        headers.put(SOAP_ACTION, "");
    }

    @Cuando("el usuario desea buscar una cuenta de usuario en la plataforma")
    public void elUsuarioDeseaBuscarUnaCuentaDeUsuarioEnLaPlataforma() {

            actor.attemptsTo(
                getResource().usingTheResource(SINGLE_USER.getValue()+"/2")
                .withHeaders(headers)
            );
    }
    @Entonces("visualizara el email {string}, el primer nombre {string}, el apellido {string} y el avatar {string}")
    public void visualizaraElEmailElPrimerNombreElApellidoYElAvatar(String email, String firstName, String lastName, String avatar) {
        LastResponse.received().answeredBy(actor).prettyPrint();
        Data user = new GetUserQuestion().answeredBy(actor).getData();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                ),
                seeThat("usuario no es nulo", actor1 -> user, notNullValue())
        );
        actor.should(
                seeThat("el email del usuario es:", actor1 -> user.getEmail(), equalTo(email)),
                seeThat("el nombre del usuario es:", actor1 -> user.getFirstName(), equalTo(firstName)),
                seeThat("el apellido del usuario es:", actor1 -> user.getLastName(), equalTo(lastName)),
                seeThat("el avatar del usuario es:", actor1 -> user.getAvatar(), equalTo(avatar))
        );
    }
}
