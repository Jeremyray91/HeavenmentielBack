package heavenmentiel.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;

@Service(value="securityService")
public class SecurityService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserRepository userRepo;
	
	public User getConnectedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null) {
			return null;
		}
		
		org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) authentication
                .getPrincipal();
        return userRepo.getByMail(u.getUsername());

	}

}
