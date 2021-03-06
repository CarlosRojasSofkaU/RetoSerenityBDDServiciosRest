package co.com.sofka.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;

public class GetResource implements Task {

    private String resource;
    private HashMap<String, Object> headers = new HashMap<>();

    public GetResource usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public GetResource withHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
                        .with(requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .headers(headers)
                        )
        );
    }

    public static GetResource getResource () { return new GetResource() ;}
}
