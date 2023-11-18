package site.nomoreparties.stellarburgers.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
}