import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class User {
    @Step("Создание пользователя")
    public ValidatableResponse createUser(String json) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(Constant.URL_CONST)
                .body(json)
                .post(Constant.USER_CREATE)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse login(String json) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(Constant.URL_CONST)
                .body(json)
                .post(Constant.USER_LOGIN)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String token,String json) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .baseUri(Constant.URL_CONST)
                .body(json)
                .delete(Constant.USER_DELETE)
                .then();
    }
}
