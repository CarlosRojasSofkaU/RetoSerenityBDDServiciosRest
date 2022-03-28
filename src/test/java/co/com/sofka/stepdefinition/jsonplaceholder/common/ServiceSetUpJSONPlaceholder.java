package co.com.sofka.stepdefinition.jsonplaceholder.common;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;

public class ServiceSetUpJSONPlaceholder {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    protected void generalSetUp(){
        setUpLog4j2();
        setUpBases();
    }

    private void setUpLog4j2(){
        PropertyConfigurator.configure(USER_DIR.getValue()  + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBases(){
        RestAssured.baseURI = BASE_URI;
    }
}
