package heavenmentiel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.models.AchatsEvents;
import heavenmentiel.models.Commande;
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
	
	public void updateUser(User user, boolean pwdChanged)
	{
		userRepo.update(user, pwdChanged);
	}
	
	public User getConnectedUser()
	{
		return securityService.getConnectedUser();
	}
	
	public JsonNode getById(long id) {
		return userRepo.getById(id);
	}

	public boolean checkUserMail(String mail)
	{
		return userRepo.checkMail(mail);
	}
}
