package com.mydevgeek.repo;

import com.mydevgeek.domain.WorkItem;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long>{

	@Query(value = "SELECT * FROM work_item wi WHERE wi.work_item_id IN \n"
			+ "(SELECT uwi.user_work_item_id FROM user_work_item uwi "
			+ "WHERE uwi.user_id = :uid)", nativeQuery = true)
	public List<WorkItem> findByUserId(@Param("uid") Long uid);
	
	@Query(value = "SELECT * FROM work_item wi WHERE wi.property_id = :pid", nativeQuery = true)
	public List<WorkItem> findByPropertyId(@Param("pid") Long pid);
	
}
