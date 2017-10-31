package com.mydevgeek.repo;

import com.mydevgeek.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by DAM on 2/25/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//@Query("select u from user u where u.email = ?1")
	//User findByEmail(String email);
	
}
