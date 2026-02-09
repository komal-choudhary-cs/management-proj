package com.common.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.common.management.entity.FoodMenuEntity;

@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenuEntity, Long> {
}

