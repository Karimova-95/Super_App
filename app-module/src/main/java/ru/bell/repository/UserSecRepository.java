package ru.bell.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;
import ru.bell.security.UserSec;

public interface UserSecRepository extends ReactiveSortingRepository<UserSec, Integer> {

    @Query("SELECT user.name, user.id, user.password_hash, user.id_group, gr.name AS group_name FROM sec_user AS user LEFT JOIN sec_group AS gr ON user.id_group = gr.id WHERE user.name = :username")
    Mono<UserSec> getUserSecByName(@Param(value = "username")String username);
}
