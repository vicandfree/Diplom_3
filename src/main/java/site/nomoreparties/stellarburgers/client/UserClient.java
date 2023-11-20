package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import site.nomoreparties.stellarburgers.model.User;

import static io.restassured.RestAssured.*;

public class UserClient extends RestClient {
    private static final String REGISTER_URL = "auth/register";
    private static final String USER_URL = "auth/user";


    @Step("Регистрация пользователя")
    public void create(User user) {
        given()
                .spec(spec())
                .body(user)
                .when()
                .post(REGISTER_URL)
                .then();
    }

    @Step("Удаление пользователя")
    public void delete(String accessToken) {
        given()
                .spec(spec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_URL)
                .then();
    }
}