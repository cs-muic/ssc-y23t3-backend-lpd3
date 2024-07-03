package io.muzoo.ssc.webapp.Project;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Getter
@Setter
@ToString
public class User {
    @Id private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public static User of(String firstname, String lastname, String email, String password){
        return new User(null, firstname, lastname, email, password);
    }

    @PersistenceConstructor
    private User(Long id, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

    }
}
