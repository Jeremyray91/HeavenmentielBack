package heavenmentiel.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import heavenmentiel.enums.RoleEnum;
import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;
import heavenmentiel.services.UserService;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public User getUser(@RequestParam(value="username") String mail)
	{
		return userRepo.getByMail(mail);
	}
	
	@RequestMapping(value="/check", method=RequestMethod.GET)
	public boolean checkUser(@RequestParam(value="username") String mail)
	{
		return userService.checkUserMail(mail);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public JsonNode getById(@PathVariable long id)
	{
		return userService.getById(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody User user)
	{
		userService.createUser(user);
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody User user, @RequestParam(value="newPwd") boolean pwdChanged)
	{
		userService.updateUser(user, pwdChanged);
	}
}
