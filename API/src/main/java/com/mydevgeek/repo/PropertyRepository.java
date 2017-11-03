package com.mydevgeek.repo;

import com.mydevgeek.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	@Query(value = "SELECT * FROM property p WHERE p.state = :state", nativeQuery = true)
    public Property findByState(@Param("state") String state);
	
}
