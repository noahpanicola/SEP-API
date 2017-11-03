package com.mydevgeek.repo;

import com.mydevgeek.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by DAM on 2/25/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user u WHERE u.email = :email", nativeQuery = true)
    public User findByEmail(@Param("email") String email);

}
