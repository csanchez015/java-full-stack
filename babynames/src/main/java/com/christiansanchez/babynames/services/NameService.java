package com.christiansanchez.babynames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.christiansanchez.babynames.models.Name;
import com.christiansanchez.babynames.repositories.NameRepository;


@Service
public class NameService {
private final NameRepository nameRepo;
	
	
	public NameService(NameRepository nameRepo) {
		this.nameRepo = nameRepo;
	}
	
	
	public List<Name> allNames(){
		return nameRepo.findAll();
	}
	
	
	public Name addName(Name name) {
		return nameRepo.save(name);
	}
	
	
	public Name updateName(Name name) {
		return nameRepo.save(name);
	}
	
	
	public void deleteName(Name name) {
		nameRepo.delete(name);
	}
	
	
	public Name findName(Long id) {
		Optional<Name> optionalName = nameRepo.findById(id);
		if(optionalName.isPresent()) {
			return optionalName.get();
		}else {
			return null;
		}
	}

}
