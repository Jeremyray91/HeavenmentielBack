package heavenmentiel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;

@RestController
@RequestMapping(value="/api")
public class UserController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public void test()
	{
		userRepo.add(passwordEncoder.encode("test"));
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String success()
	{
		return "success";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody User user)
	{
		userRepo.create(user);
	}
}
