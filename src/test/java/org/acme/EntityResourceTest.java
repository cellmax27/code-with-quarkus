package org.acme;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;


@QuarkusTest
public class EntityResourceTest {

    @Test
    public void testHelloEndpoint() {
        RestAssured.given()
          .when().get("/user")
          .then()
             .statusCode(200)
             .body(is("Hello user from Quarkus REST!"));
    }
}