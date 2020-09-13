package com.department.api;


import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.model.departments.DepartmentUserTagsModel;
import com.department.api.repository.DepartmentUserRepository;
import com.department.api.repository.DepartmentUserTagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}

	@Autowired
	private DepartmentUserTagsRepository departmentUserTagsRepository;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

//		Set<DepartmentUserTagsModel> departmentUserTagsModel = new HashSet<>();

//		departmentUserTagsModel.add(new DepartmentUserTagsModel("svov", true));
//		departmentUserTagsModel.add(new DepartmentUserTagsModel("svo23v", true));
//
//		departmentUserTagsRepository.save(departmentUserTagsModel)

		return args->{
			System.out.println("DEPARTMENT PROFILE HAS BEEN STARTED...");
			String[] beanNames = ctx.getBeanDefinitionNames();
		};
	}

}
