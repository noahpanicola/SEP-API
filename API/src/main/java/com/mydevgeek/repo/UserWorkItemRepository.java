package com.mydevgeek.repo;

import com.mydevgeek.domain.UserWorkItem;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

public interface UserWorkItemRepository extends JpaRepository<UserWorkItem, Long>{

}
