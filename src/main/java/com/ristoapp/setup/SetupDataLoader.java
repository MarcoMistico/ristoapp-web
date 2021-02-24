package com.ristoapp.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ristoapp.enums.ERole;
import com.ristoapp.models.Role;
import com.ristoapp.repository.RoleRepository;

@Component
public class SetupDataLoader implements
ApplicationListener<ApplicationReadyEvent>{
	
	boolean alreadySetup = false;
	
	@Autowired
    private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for (int i = 0; i < ERole.values().length; i++) { 
			createOrUpdateRole(i+1, ERole.values()[i]);
		}
	}
	
	private void createOrUpdateRole(int id, ERole name) {
		Role role = roleRepository.findById(id);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }else {
        	role.setName(name);
        	roleRepository.save(role);
        }
	}

}
