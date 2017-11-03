package com.mydevgeek.repo;

import com.mydevgeek.domain.Property;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	@Query(value = "SELECT * FROM property p WHERE p.state = :state", nativeQuery = true)
    public List<Property> findByState(@Param("state") String state);
	
	@Query(value = "SELECT * FROM property p WHERE p.coord_lat = :coord_lat", nativeQuery = true)
    public Property findByLatitude(@Param("coord_lat") double coord_lat);
	
	@Query(value = "SELECT * FROM property p WHERE p.coord_long = :coord_long", nativeQuery = true)
    public Property findByLongitude(@Param("coord_long") String coord_long);
	
	@Query(value = "SELECT * FROM property p WHERE p.property_id IN (SELECT pr.property_id FROM user_property pr WHERE pr.is_manager = 1 AND pr.user_id = :uid);", nativeQuery = true)
    public List<Property> findByUserId(@Param("uid") Long uid);
	
}
