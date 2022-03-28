package co.com.sofka.stepdefinition.jsonplaceholder.deletealbum;

import co.com.sofka.stepdefinition.jsonplaceholder.common.ServiceSetUpJSONPlaceholder;
import co.com.sofka.stepdefinition.jsonplaceholder.createpost.CreatePostTestStepDefinition;
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

import static co.com.sofka.task.DoDelete.doDelete;
import static co.com.sofka.util.ResourceCases.DELETE_ALBUM;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteAlbumTestStepDefinition extends ServiceSetUpJSONPlaceholder{
    private final HashMap<String, Object> headers = new HashMap<>();
    private static final String SOAP_ACTION = "SOAPAction";
    private final Actor actor = Actor.named("Carlos");
    private static final Logger LOGGER = Logger.getLogger(CreatePostTestStepDefinition.class);

    @Dado("que el usuario esta en el recurso web")
    public void queElUsuarioEstaEnElRecursoWeb() {
        generalSetUp();
        actor.can(CallAnApi.at(RestAssured.baseURI));
        headers.put(SOAP_ACTION, "");
    }

    @Cuando("el usuario desea eliminar un album con el id interno {int}")
    public void elUsuarioDeseaEliminarUnAlbumConElIdInterno(Integer extension) {
        actor.attemptsTo(
                doDelete()
                        .usingTheResource(DELETE_ALBUM.getValue()+extension)
                        .withHeaders(headers)
        );
    }

    @Entonces("el usuario obtendra un codigo de estado exitoso")
    public void elUsuarioObtendraUnCodigoDeEstadoExitoso() {
        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                )
        );
    }
}
