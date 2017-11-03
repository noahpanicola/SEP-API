package com.mydevgeek.repo;

import com.mydevgeek.domain.UserProperty;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserPropertyRepository extends JpaRepository<UserProperty, Long> {
	
	@Query(value = "SELECT * FROM user_property up WHERE up.property_id = :pid", nativeQuery = true)
    public List<UserProperty> findByPropertyId(@Param("pid") Long pid);
	
}
