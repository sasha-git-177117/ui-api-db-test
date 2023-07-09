package apiutil;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBodyExtractionOptions;

import java.util.HashMap;

public class ApiUtils {

    private ApiUtils() {}

    public static ResponseBodyExtractionOptions getResponse(String url, HashMap<String,String> params) {
        return RestAssured.given()
                .params(params)
                .when()
                .post(url)
                .then()
                .extract()
                .body();
    }
}
