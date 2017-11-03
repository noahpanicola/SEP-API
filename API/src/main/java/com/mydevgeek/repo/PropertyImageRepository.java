package com.mydevgeek.repo;

import com.mydevgeek.domain.PropertyImage;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
	
	@Query(value = "SELECT * FROM property_image pi WHERE pi.property_id = :pid", nativeQuery = true)
    public List<PropertyImage> findByPropertyId(@Param("pid") Long pid);
	
}
