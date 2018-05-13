package heavenmentiel.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.enums.RoleEnum;
import heavenmentiel.models.Commande;
import heavenmentiel.models.Event;
import heavenmentiel.models.User;
import heavenmentiel.services.CommandeService;

@Repository
@Transactional
public class UserRepository {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	CommandeService cmdService;
	
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
	
	public JsonNode getById(long id) {
		User user = em.find(User.class, id);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode users = mapper.createArrayNode();
			users.add(toJsonCmd(user));
		return users;
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
	
	public void update(User user,boolean pwdChanged) {
		String pwd = user.getPwd();
		if(pwdChanged) {
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
	
	public JsonNode toJsonCmd(User user) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("id", user.getId());
		rootNode.put("firstname", user.getFirstName());
		rootNode.put("lastname", user.getLastName());
		rootNode.put("birthday", user.getBirthDay().toString());
		rootNode.put("adresse", user.getAdress());
		rootNode.put("CodePostal", user.getZipCode());
		rootNode.put("ville", user.getCity());
		rootNode.put("mail", user.getMail());
		rootNode.put("tel", user.getTel());
		rootNode.put("motDePasse", user.getPwd());
		rootNode.put("role", user.getRole().toString());
//		ArrayNode commandes = rootNode.putArray("commandes");
//		for(Commande commande : user.getCommands()) {
//			commandes.add(cmdService.toJson(commande));
//		}
		
		return rootNode;
	}
}
