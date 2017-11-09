package com.mydevgeek.repo;

import com.mydevgeek.domain.Setting;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
	@Query(value = "SELECT * FROM settings s WHERE s.category = :category AND s.name = :name", nativeQuery = true)
	public Setting findByCategoryAndName(@Param("category") String category, @Param("name") String name);
}
