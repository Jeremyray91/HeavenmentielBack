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
	
	public void createUser(User user) {
		userRepo.create(user);
	}

}
