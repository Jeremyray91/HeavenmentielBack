package heavenmentiel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import heavenmentiel.enums.RoleEnum;
import heavenmentiel.models.User;
import heavenmentiel.repositories.UserRepository;

@Component
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(final String mail) {
		User user = userRepo.getByMail(mail);
		if (user != null) {
			List<GrantedAuthority> rules = this.getUserCredentials(user);
			return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPwd(), rules);
		}
		throw new UsernameNotFoundException("username.not.found");
	}

	public List<GrantedAuthority> getUserCredentials(User user) {

		List<GrantedAuthority> rules = new ArrayList<>();
		if (user.getRole() == RoleEnum.ADMIN) {
			rules.add(new SimpleGrantedAuthority("ADMIN"));
		} else if (user.getRole() == RoleEnum.USER) {
			rules.add(new SimpleGrantedAuthority("USER"));
		}
		return rules;
	}

}
