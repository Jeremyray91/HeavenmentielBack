package heavenmentiel.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import heavenmentiel.models.User;

@Repository
@Transactional
public class UserRepository {

	@PersistenceContext
	EntityManager em;
	
	
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
		User user = userQ.getSingleResult();
		return user;
	}
	
	/**
	 * @author Barbara
	 * @param user
	 * 
	 * Créée en base un utilisateur
	 */
	
	public void create(User user) {
		em.persist(user);
	}
}
