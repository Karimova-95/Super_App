package ru.bell.dao;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.bell.security.UserSec;

@Repository
public class UserDAO extends AbstractDAO{

    protected UserDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public Mono<UserSec> findByUsername(String username) {
        return this.connection()
                .flatMapMany(c -> c.createStatement("SELECT user.name, user.id, user.password_hash, " +
                                "user.id_group, gr.name AS group_name FROM sec_user AS user " +
                                "LEFT JOIN sec_group AS gr WHERE user.name = ($1)")
                        .bind("$1", username)
                        .execute())
                .flatMap(r -> r.map((row, rowMetadata) ->
                        new UserSec(row.get("id", Integer.class),
                                row.get("group_name", String.class),
                                row.get("id_group", Integer.class),
                                row.get("name", String.class),
                                row.get("password_hash", String.class))))
                .next();
    }
}
