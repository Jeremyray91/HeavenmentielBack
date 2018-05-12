package heavenmentiel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired UserRepository userRepo;
	
	@Autowired
	SecurityService securityService;
	
	public void createUser(User user)
	{
		userRepo.create(user);
	}
	
	public void updateUser(User user)
	{
		userRepo.update(user);
	}
	
	public User getConnectedUser()
	{
		return securityService.getConnectedUser();
	}

	public boolean checkUserMail(String mail)
	{
		return userRepo.checkMail(mail);
	}
}
