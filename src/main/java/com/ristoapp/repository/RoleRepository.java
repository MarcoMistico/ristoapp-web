package com.ristoapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ristoapp.enums.ERole;
import com.ristoapp.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
	List<Role> findAll();

	Role findById(int id);
}
