package com.christiansanchez.babynames.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.christiansanchez.babynames.models.Name;



public interface NameRepository extends CrudRepository<Name, Long>{
	List<Name> findAll();
	List<Name> findByUserIdIs(Long userId);
}
