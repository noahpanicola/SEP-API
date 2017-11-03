package com.mydevgeek.repo;

import com.mydevgeek.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by DAM on 2/25/17.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
