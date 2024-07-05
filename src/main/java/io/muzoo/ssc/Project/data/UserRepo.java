package io.muzoo.ssc.Project.data;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Table(name="user")
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(""" 
            select u.* from "user" u inner join token t on u.id = t."user"
            where u.id =:id and t.refresh_token = :refreshToken and t.expired_at >= :expiredAt
    """)
    Optional<User> findByIdAndTokenRefreshTokenAndTokensExpiredAtGreaterThan(Long id, String refreshToken, LocalDateTime expiredAt);

    @Query("""
        SELECT u.* FROM "user" u 
        INNER JOIN password_recovery pr ON u.id = pr."user"
        WHERE pr.token = :token""")

    Optional<User> findByPasswordRecoveriesToken(String token);

}