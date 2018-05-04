package heavenmentiel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import heavenmentiel.enums.RoleEnum;
import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;

@RestController
@RequestMapping(value="/test")
public class UserController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="", method= RequestMethod.POST)
	public void test()
    {
        userRepo.add(passwordEncoder.encode("test"));
    }
	
}
