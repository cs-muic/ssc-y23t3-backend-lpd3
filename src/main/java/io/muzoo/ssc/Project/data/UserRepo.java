package io.muzoo.ssc.Project.data;


import org.springframework.data.jdbc.repository.query.Query;
<<<<<<< HEAD
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Table(name="user")
=======
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

>>>>>>> 9b7b3ad516e5445ec9243828988cd6bf31a67a4f
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(""" 
<<<<<<< HEAD
            select u.* from "user" u inner join token t on u.id = t."user"
=======
            select u.* from user u inner join token t on u.id = t.user
>>>>>>> 9b7b3ad516e5445ec9243828988cd6bf31a67a4f
            where u.id =:id and t.refresh_token = :refreshToken and t.expired_at >= :expiredAt
    """)
    Optional<User> findByIdAndTokenRefreshTokenAndTokensExpiredAtGreaterThan(Long id, String refreshToken, LocalDateTime expiredAt);

    @Query("""
<<<<<<< HEAD
        SELECT u.* FROM "user" u 
        INNER JOIN password_recovery pr ON u.id = pr."user"
        WHERE pr.token = :token""")

=======
            select u.* from user u inner join password_recovery pr on.id = pr.user
            where pr.token = :token
    """)
>>>>>>> 9b7b3ad516e5445ec9243828988cd6bf31a67a4f
    Optional<User> findByPasswordRecoveriesToken(String token);

}
