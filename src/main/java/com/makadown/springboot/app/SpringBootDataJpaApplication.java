package com.makadown.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.makadown.springboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {
	
	@Autowired
	IUploadFileService uploadFileService;
	/*
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
*/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Borra todo y vuelve a crear folder ... es nomas para cascarear y aprender
		//uploadFileService.deleteAll();
		//uploadFileService.init();	
		
		//String password = "12345";
		
		//for(int i =0; i<2 ; i++)
		//{
			//String bcryptPassword = passwordEncoder.encode(password);
			//System.out.println(bcryptPassword);
		//}
		// para este pass dummy deberia regresar por ej.  
		// $2a$10$Mz1vulRz/DafMXHiOJc6GefjlBz5JG3SULR.RIDnsKvCBFDV1DYdS
	}
}
