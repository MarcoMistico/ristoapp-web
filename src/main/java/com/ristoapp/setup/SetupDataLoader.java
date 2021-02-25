package com.ristoapp.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ristoapp.enums.ProductCategoryEnum;
import com.ristoapp.enums.RoleEnum;
import com.ristoapp.models.ProductCategory;
import com.ristoapp.models.Role;
import com.ristoapp.repository.ProductCategoryRepository;
import com.ristoapp.repository.RoleRepository;

@Component
public class SetupDataLoader implements
ApplicationListener<ApplicationReadyEvent>{
	
	boolean alreadySetup = false;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for (int i = 0; i < RoleEnum.values().length; i++) { 
			createOrUpdateRole(i+1, RoleEnum.values()[i]);
		}
		
		for (int i = 0; i < ProductCategoryEnum.values().length; i++) { 
			createOrUpdateproductCategory(i+1, ProductCategoryEnum.values()[i]);
		}
	}
	
	private void createOrUpdateproductCategory(int id, ProductCategoryEnum productCategoryEnum) {
		ProductCategory category = productCategoryRepository.findById(id);
        if (category == null) {
        	category = new ProductCategory(productCategoryEnum);
        	productCategoryRepository.save(category);
        }else {
        	category.setName(productCategoryEnum);
        	productCategoryRepository.save(category);
        }
		
	}

	private void createOrUpdateRole(int id, RoleEnum name) {
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
