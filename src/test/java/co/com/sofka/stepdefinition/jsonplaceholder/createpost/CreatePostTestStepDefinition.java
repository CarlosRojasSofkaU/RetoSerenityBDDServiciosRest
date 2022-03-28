package co.com.sofka.stepdefinition.jsonplaceholder.createpost;

import co.com.sofka.model.jsonplaceholder.post.Post;
import co.com.sofka.question.jsonplaceholder.createpost.CreatePostQuestion;
import co.com.sofka.stepdefinition.jsonplaceholder.common.ServiceSetUpJSONPlaceholder;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static co.com.sofka.task.DoPost.doPost;
import static co.com.sofka.util.CreatePostCodeKey.*;
import static co.com.sofka.util.FileUtilities.readFile;
import static co.com.sofka.util.ResourceCases.CREATE_POST;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreatePostTestStepDefinition extends ServiceSetUpJSONPlaceholder {
    private final HashMap<String, Object> headers = new HashMap<>();
    private static final String SOAP_ACTION = "SOAPAction";
    private final Actor actor = Actor.named("Carlos");
    private static final Logger LOGGER = Logger.getLogger(CreatePostTestStepDefinition.class);
    private String bodyRequest;
    private String CONVERTED_FILE;
    private static final String CREATE_POST_CODE_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\jsonplaceholder\\createPost.json";

    @Dado("que el usuario esta en el recurso web indicando el titulo {string}, el cuerpo {string} y el userId {int}")
    public void queElUsuarioEstaEnElRecursoWebIndicandoElTituloElCuerpoYElUserId(String titulo, String cuerpo, Integer idUsuario) {
        generalSetUp();
        actor.can(CallAnApi.at(RestAssured.baseURI));
        headers.put("Content-Type", ContentType.JSON);
        headers.put(SOAP_ACTION, "");
        bodyRequest =defineBodyRequest(titulo, cuerpo, idUsuario);
        LOGGER.info(bodyRequest);
    }

    @Cuando("el usuario desea crear un post")
    public void elUsuarioDeseaCrearUnPost() {
        actor.attemptsTo(
                doPost()
                        .usingTheResource(CREATE_POST.getValue())
                        .withHeaders(headers)
                        .andBodyRequest(bodyRequest)
        );
    }

    @Entonces("el usuario observara los datos de su post como id usuario {int}, el titulo {string}, el cuerpo {string} y el id interno {int}")
    public void elUsuarioObservaraLosDatosDeSuPostComoIdUsuarioElTituloElCuerpoYElIdInterno(Integer usuarioId, String titulo, String cuerpo, Integer id) {
        LastResponse.received().answeredBy(actor).prettyPrint();
        Post post = new CreatePostQuestion().answeredBy(actor);

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_CREATED,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_CREATED)
                ),
                seeThat("el post no es nulo", actor1 -> post, notNullValue())
        );
        actor.should(
                seeThat("el id del usuario es:", actor1 -> post.getUserId(), equalTo(usuarioId)),
                seeThat("el id interno es:", actor1 -> post.getId(), equalTo(id)),
                seeThat("el titulo es:", actor1 -> post.getTitle(), equalTo(titulo)),
                seeThat("el cuerpo del post es:", actor1 -> post.getBody(), equalTo(cuerpo))
        );
    }

    private String defineBodyRequest(String titulo, String cuerpo, int idUsuario){
        CONVERTED_FILE = readFile(CREATE_POST_CODE_JSON).replace(TITLE_CODE.getValue(), titulo);
        CONVERTED_FILE = CONVERTED_FILE.replace(BODY_CODE.getValue(), cuerpo);
        CONVERTED_FILE = CONVERTED_FILE.replace(USER_ID_CODE.getValue(), String.valueOf(idUsuario));
        return CONVERTED_FILE;
    }
}
