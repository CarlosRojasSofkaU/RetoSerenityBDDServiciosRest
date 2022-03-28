package co.com.sofka.stepdefinition.reqres.updateuser;

import co.com.sofka.stepdefinition.reqres.common.ServiceSetUpReqRes;

import static co.com.sofka.question.reqres.updateuser.UpdateUserQuestion.response;
import static co.com.sofka.task.DoPut.doPut;
import static co.com.sofka.util.FileUtilities.readFile;
import static co.com.sofka.util.ResourceCases.UPDATE_USER;
import static co.com.sofka.util.UpdateUserCodeKey.JOB_CODE;
import static co.com.sofka.util.UpdateUserCodeKey.NAME_CODE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;

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

public class UpdateUserTestStepDefinition extends ServiceSetUpReqRes{
    private final HashMap<String, Object> headers = new HashMap<>();
    private static final String SOAP_ACTION = "SOAPAction";
    private final Actor actor = Actor.named("Carlos");
    private static final Logger LOGGER = Logger.getLogger(UpdateUserTestStepDefinition.class);
    private String bodyRequest;
    private String CONVERTED_FILE;
    private static final String UPDATE_USER_CODE_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\reqres\\updateUser.json";


    @Dado("que el usuario esta en el recurso web de actualizacion de datos indicando el nombre {string} y el trabajo {string}")
    public void queElUsuarioEstaEnElRecursoWebDeActualizacionDeDatosIndicandoElNombreYElTrabajo(String nombre, String trabajo) {
        generalSetUp();
        actor.can(CallAnApi.at(RestAssured.baseURI+RestAssured.basePath));
        headers.put("Content-Type", ContentType.JSON);
        headers.put(SOAP_ACTION, "");
        bodyRequest =defineBodyRequest(nombre, trabajo);
        LOGGER.info(bodyRequest);
    }

    @Cuando("el usuario desea actualizar la informacion de una cuenta")
    public void elUsuarioDeseaActualizarLaInformacionDeUnaCuenta() {
        actor.attemptsTo(
                doPut()
                        .usingTheResource(UPDATE_USER.getValue())
                        .withHeaders(headers)
                        .andBodyRequest(bodyRequest)
        );
    }

    @Entonces("el usuario observara la fecha en la que la cuenta fue actualizada")
    public void elUsuarioObservaraLaFechaEnLaQueLaCuentaFueActualizada() {
        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                ),
                seeThat("El campo updated at no puede ser nulo",
                        response(), notNullValue())
        );
    }

    private String defineBodyRequest(String nombre, String trabajo){
        CONVERTED_FILE = readFile(UPDATE_USER_CODE_JSON).replace(NAME_CODE.getValue(), nombre);
        CONVERTED_FILE = CONVERTED_FILE.replace(JOB_CODE.getValue(), trabajo);
        return CONVERTED_FILE;
    }
}
