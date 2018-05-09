package heavenmentiel.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import heavenmentiel.enums.RoleEnum;
import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;
import heavenmentiel.services.UserService;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping(value="/user")
public class UserController {

	/*@Autowired
	PasswordEncoder passwordEncoder;*/
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	/*@RequestMapping(value="", method=RequestMethod.POST)
	public void test()
	{
		System.out.println("add");
		userRepo.add(passwordEncoder.encode("test"));
	}*/
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public User success(@RequestParam(value="username") String mail)
	{
		System.out.println("success");
		return userRepo.getByMail(mail);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody User user)
	{
		userService.createUser(user);
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody User user)
	{
		userService.updateUser(user);
	}
}
