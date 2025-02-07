//используем REST Assured - это Java-библиотека для автоматизированного тестирования REST API


package demo.practice.Rest_API;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class apiTests {

    @Test
    void successfulLoginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given() // given() – начало описания тестового запроса в стиле BDD (Behavior-Driven Development) с использованием библиотеки Rest Assured.
                .log().uri() //логирует URI запроса, чтобы его можно было увидеть в выводе теста.
                .body(body)//  указывает, что тело запроса будет содержать JSON с учетными данными (email и password)
                .contentType(ContentType.JSON)
                .when() // указывает, что дальше идет действие, то есть отправка запроса.
                .post("https://reqres.in/api/login")//выполняет HTTP POST-запрос на указанный URL.
                .then()//начало блока проверок ответа
                .log().status()//логирует HTTP-статус ответа.
                .log().body()//логирует тело ответа.
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unSuccessfulLoginWithMissingEmailTest() {
        String body = "{\"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void getUsersPage2Test() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("page", is(2))  // Проверяем, что страница = 2
                .body("per_page", is(6))  // Проверяем, что на странице 6 пользователей
                .body("total", is(12))  // Проверяем общее количество пользователей = 12
                .body("total_pages", is(2))  // Проверяем, что всего 2 страницы
                .body("data.size()", is(6))  // Проверяем, что в массиве "data" 6 элементов
                .body("data.id", contains(7, 8, 9, 10, 11, 12))  // Проверяем, что ID пользователей верные
                .body("data.email", hasItems(
                        "michael.lawson@reqres.in",
                        "lindsay.ferguson@reqres.in",
                        "tobias.funke@reqres.in",
                        "byron.fields@reqres.in",
                        "george.edwards@reqres.in",
                        "rachel.howell@reqres.in"
                ))  // Проверяем, что email-ы пользователей корректны
                .body("data.first_name", hasItems(
                        "Michael",
                        "Lindsay",
                        "Tobias",
                        "Byron",
                        "George",
                        "Rachel"
                ))  // Проверяем, что имена пользователей верные
                .body("support.url", is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"))  // Проверяем support URL
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));  // Проверяем support текст
    }

    @Test
    void createUserTest() {
        // Данные запроса
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201) // Проверяем, что код ответа 201 (Created)
                .contentType(ContentType.JSON) // Проверяем, что ответ в формате JSON
                .body("name", is("morpheus")) // Проверяем, что в ответе name = "morpheus"
                .body("job", is("leader")) // Проверяем, что в ответе job = "leader"
                .body("id", notNullValue()) // Проверяем, что поле "id" присутствует
                .body("createdAt", notNullValue()); // Проверяем, что поле "createdAt" присутствует
    }

    @Test
    void deleteUserTest() {
        given()
                .log().uri() // Логируем URI перед отправкой запроса
                .when()
                .delete("https://reqres.in/api/users/2") // Отправляем DELETE-запрос
                .then()
                .log().status() // Логируем статус ответа
                .statusCode(204); // Проверяем, что статус-код = 204 (No Content)
    }
}
