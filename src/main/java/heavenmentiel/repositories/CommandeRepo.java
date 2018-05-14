package heavenmentiel.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import heavenmentiel.models.AchatsEvents;
import heavenmentiel.models.Commande;
import heavenmentiel.models.Event;
import heavenmentiel.models.User;
import heavenmentiel.services.CommandeService;
import heavenmentiel.services.EventService;

@Repository
@Transactional
@CrossOrigin
public class CommandeRepo {
	@PersistenceContext	EntityManager em;
	@Autowired protected Environment env;
	@Autowired	CommandeService cmds;
	
	public Commande create(Commande commande) {
		em.persist(commande);
		for(AchatsEvents ae : commande.getEvents()) {
			 ae.setCommande(commande);
			 em.persist(ae);
		}
		return commande;
	}
	
	public JsonNode getAll(){
		List<Commande> commandesQ = em.createQuery("from Commande", Commande.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode commandes = mapper.createArrayNode();
		for (Commande commande : commandesQ) {
			commandes.add(cmds.toJson(commande));
		}
		return commandes;
	}
	
	public List<Commande> getMulticriteria(String nom, String prenom, Long idClient, Date datemin, Date datemax, Integer page){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Commande> q = cb.createQuery(Commande.class);
		Root<Commande> commande = q.from(Commande.class);
		Join<Commande,User> join = commande.join( "user" );
		q.select(commande);
		List<Predicate> criteres = new ArrayList<Predicate>();
		List<Object[]> params = new ArrayList<Object[]>();
		
		if(nom!=null) {
			criteres.add(cb.equal(join.get("firstName"),cb.parameter(String.class,"firstname")));
			params.add(new Object [] {"firstname",nom});
		}
		if(prenom!=null) {
			criteres.add(cb.like(join.get("lastName"), cb.parameter(String.class,"lastname")));
			params.add(new Object [] {"lastname",prenom});
		}
		if(idClient!=null) {
			criteres.add(cb.equal(join.get("id"), cb.parameter(Long.class,"idClient")));
			params.add(new Object [] {"idClient",idClient});
		}
		if(datemin!=null) {
			criteres.add(cb.greaterThanOrEqualTo(commande.get("date"), cb.parameter(Date.class,"datemin")));
			params.add(new Object [] {"datemin",datemin});
		}
		if(datemax!=null) {
			criteres.add(cb.lessThanOrEqualTo(commande.get("date"), cb.parameter(Date.class,"datemax")));
			params.add(new Object [] {"datemax",datemax});
		}
		
		q.where(cb.and(criteres.toArray(new Predicate[criteres.size()])));
		TypedQuery<Commande> query = em.createQuery(q);
		for(Object[] param : params) {
			query.setParameter((String) param[0],param[1]);
		}
		
		List<Commande> commands = query.getResultList();
		return commands;
	}
	
	public Object[] getMulticriteriaPredicateParameters(String firstname, String lastname, Integer idClient, Date datemin, Date datemax) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Commande> q = cb.createQuery(Commande.class);
//		Root<Commande> commande = q.from(Commande.class);
//		Join<Commande,User> join = commande.join( "user" );
//		
//		List<Predicate> criteres = new ArrayList<Predicate>();
//		Predicate criteresPredicate;
//		List<Object[]> parametres = new ArrayList<Object[]>();
//		
//		if(firstname!=null) {
//			criteres.add(cb.like(join.get("firstName"), cb.parameter(String.class,"firstname")));
//			parametres.add(new Object[] {"firstname","%"+firstname+"%"});
//		}
//		if(lastname!=null) {
//			criteres.add(cb.like(join.get("lastName"), cb.parameter(String.class,"lastname")));
//			parametres.add(new Object[] {"lastname","%"+lastname+"%"});
//		}
//		if(idClient!=null) {
//			criteres.add(cb.equal(join.get("id"), cb.parameter(Integer.class,"idClient")));
//			parametres.add(new Object[] {"idClient","%"+idClient+"%"});
//		}
//
//		return new Object [] {criteresPredicate,parametres};
//	}
		return null;
	}





}
