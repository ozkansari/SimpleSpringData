package com.ozkansari;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ozkansari.db.entity.DBUser;
import com.ozkansari.db.repo.SimpleUserRepository;

@Component
public class App implements CommandLineRunner {
	
	@Autowired
	private SimpleUserRepository dbUserDAO;

	@Override
	public void run(String... args) throws Exception {
		DBUser createdUser = createUser();
		System.out.println("User created: " + createdUser );
		
		List<DBUser> foundUser1 = dbUserDAO.findByFirstname(createdUser.getFirstname());
		System.out.println("User found: " + foundUser1.get(foundUser1.size()-1) );
		
		List<DBUser> foundUser2 = dbUserDAO.findByFirstnameOrLastname(createdUser.getLastname());
		System.out.println("User found: " + foundUser2.get(foundUser2.size()-1) );
		
		
	}

	private DBUser createUser() {
		Date now = new Date();

		DBUser user = new DBUser();
		user.setUsername("User_" + now.getTime());
		user.setCreatedBy("system");
		user.setCreatedDate(now);
		user.setFirstname("firstname");
		user.setLastname("lastname");

		dbUserDAO.save(user);
		
		return user;
	}
}