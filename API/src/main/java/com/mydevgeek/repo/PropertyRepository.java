package com.mydevgeek.repo;

import com.mydevgeek.domain.Property;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	@Query(value = "SELECT * FROM property p WHERE p.state = :state", nativeQuery = true)
    public List findByState(@Param("state") String state);
	
	@Query(value = "SELECT * FROM property p WHERE p.coord_lat = :coord_lat", nativeQuery = true)
    public Property findByLatitude(@Param("coord_lat") double coord_lat);
	
	@Query(value = "SELECT * FROM property p WHERE p.coord_long = :coord_long", nativeQuery = true)
    public Property findByLongitude(@Param("coord_long") String coord_long);
	
}
