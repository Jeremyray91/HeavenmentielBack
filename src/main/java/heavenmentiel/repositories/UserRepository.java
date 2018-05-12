package heavenmentiel.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import heavenmentiel.enums.RoleEnum;
import heavenmentiel.models.User;

@Repository
@Transactional
public class UserRepository {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * @author Barbara
	 * @param mail
	 * 
	 * Cherche en base l'utilisateur avec le mail demandé
	 * 
	 * @return l'utilisateur correspondant au mail demandé
	 */
	public User getByMail(String mail) {
		TypedQuery<User> userQ = em.createQuery("SELECT u FROM User u WHERE mail = :mail", User.class);
		userQ.setParameter("mail", mail);
		try
		{
			User user = userQ.getSingleResult();
			return user;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public boolean checkMail(String mail)
	{
		if(getByMail(mail) != null)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * @author Barbara
	 * @param user
	 * 
	 * Créée en base un utilisateur
	 */
	
	public void create(User user) {
		String pwd = user.getPwd();
		user.setPwd(passwordEncoder.encode(pwd));
		em.persist(user);
	}
	
	public void update(User user) {
		String pwd = user.getPwd();
		if(pwd != null) {
			user.setPwd(passwordEncoder.encode(pwd));
		}
		em.merge(user);
	}

	public void add(String mdp) {
		User user = new User();
		user.setRole(RoleEnum.ADMIN);
		user.setMail("test");
		user.setPwd(mdp);
		em.persist(user);
	}
}
