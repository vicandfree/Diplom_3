package site.nomoreparties.stellarburgers.model;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User generateRandom() {
        String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(8);

        return new User(email, password, name);
    }
}