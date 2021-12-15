package com.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rms.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	@Query(value = "select * from Menu m where lower(m.name) like lower(concat('%',:keyword,'%')) or lower(m.description) like lower(concat('%',:keyword,'%')) or lower(m.additional) like lower(concat('%',:keyword,'%'))", nativeQuery = true)
	List<Menu> findMenusByKeyword(@Param("keyword") String keyword);

}
